package TestFolder;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import GreenCart.PageObject.PageObjectManager;
import GreenCart.TestComponent.BaseTest;

public class StandAloneTest extends BaseTest {
	String Productname = "ZARA COAT 3";
	String country = "India";
	PageObjectManager pageObjectManager;

	@Test

	public void endToEndTest() {

		// TODO Auto-generated method stub
		pageObjectManager = new PageObjectManager(getDriver());
		pageObjectManager.getLandingPage().loginApplication("rahul20@gmail.com", "Rahul@2024");
		List<WebElement> products = pageObjectManager.getProductCataloguePage().getProductList();
		pageObjectManager.getProductCataloguePage().addProductTocart(Productname);
		pageObjectManager.getCommanLocators().goToCartPage();
		Boolean match = pageObjectManager.getCartPage().verifyProductDisplay(Productname);
		Assert.assertTrue(match);
		pageObjectManager.getCartPage().goToCheckOut();
		pageObjectManager.getCheckOutPage().selectCountry(country);
		pageObjectManager.getCheckOutPage().clickOnPlaceOrder();
		String confirmMessages = pageObjectManager.getConfirmationPage().getConfirmationMessage();
		System.out.println(confirmMessages);
		Assert.assertTrue(confirmMessages.equalsIgnoreCase("Thankyou for the order."));
	}

	@Test()
	public void orderHistoryTest() {
		pageObjectManager = new PageObjectManager(getDriver());
		pageObjectManager.getLandingPage().loginApplication("rahul20@gmail.com", "Rahul@2024");
		List<WebElement> products = pageObjectManager.getProductCataloguePage().getProductList();
		pageObjectManager.getProductCataloguePage().addProductTocart(Productname);
		pageObjectManager.getCommanLocators().goToCartPage();
		Boolean match = pageObjectManager.getCartPage().verifyProductDisplay(Productname);
		Assert.assertTrue(match);
		pageObjectManager.getCartPage().goToCheckOut();
		pageObjectManager.getCheckOutPage().selectCountry(country);
		pageObjectManager.getCheckOutPage().clickOnPlaceOrder();
		pageObjectManager.getCommanLocators().goToOrderPage();
		Boolean matches = pageObjectManager.getOrderPage().verifyProductDisplays(Productname);
		Assert.assertFalse(matches);
	}

}
