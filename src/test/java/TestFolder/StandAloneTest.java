package TestFolder;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import GreenCart.PageObject.CartPage;
import GreenCart.PageObject.CheckOutPage;
import GreenCart.PageObject.CommanLocators;
import GreenCart.PageObject.ConfirmationPage;
import GreenCart.PageObject.LandingPage;
import GreenCart.PageObject.OrderPage;
import GreenCart.PageObject.ProductCataloguePage;
import GreenCart.TestComponent.BaseTest;

public class StandAloneTest extends BaseTest {
	String Productname = "ZARA COAT 3";
	String country = "India";

	@Test

	public void endToEndTest() {

		// TODO Auto-generated method stub

		LandingPage landingPage = new LandingPage(getDriver());
		landingPage.loginApplication("rahul20@gmail.com", "Rahul@2024");
		ProductCataloguePage productCataloguePage = new ProductCataloguePage(getDriver());
		List<WebElement> products = productCataloguePage.getProductList();
		productCataloguePage.addProductTocart(Productname);
		CommanLocators commanLocators = new CommanLocators(getDriver());
		commanLocators.goToCartPage();
		CartPage cartPage = new CartPage(getDriver());
		Boolean match = cartPage.verifyProductDisplay(Productname);
		Assert.assertTrue(match);
		cartPage.goToCheckOut();
		CheckOutPage checkOutPage = new CheckOutPage(getDriver());
		checkOutPage.selectCountry(country);
		checkOutPage.clickOnPlaceOrder();
		ConfirmationPage confirmationPage = new ConfirmationPage(getDriver());
		String confirmMessages = confirmationPage.getConfirmationMessage();
		System.out.println(confirmMessages);
		Assert.assertTrue(confirmMessages.equalsIgnoreCase("Thankyou for the order."));
	}

	@Test()
	public void orderHistoryTest() {
		LandingPage landingPage = new LandingPage(getDriver());
		landingPage.loginApplication("rahul20@gmail.com", "Rahul@2024");
		ProductCataloguePage productCataloguePage = new ProductCataloguePage(getDriver());
		List<WebElement> products = productCataloguePage.getProductList();
		productCataloguePage.addProductTocart(Productname);
		CommanLocators commanLocators = new CommanLocators(getDriver());
		commanLocators.goToCartPage();
		CartPage cartPage = new CartPage(getDriver());
		Boolean match = cartPage.verifyProductDisplay(Productname);
		Assert.assertTrue(match);
		cartPage.goToCheckOut();
		CheckOutPage checkOutPage = new CheckOutPage(getDriver());
		checkOutPage.selectCountry(country);
		checkOutPage.clickOnPlaceOrder();
		commanLocators.goToOrderPage();
		OrderPage orderPage = new OrderPage(getDriver());
		Boolean matches = orderPage.verifyProductDisplays(Productname);
		Assert.assertFalse(matches);
	}

}
