package TestFolder;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import GreenCart.PageObject.CartPage;
import GreenCart.PageObject.CheckOutPage;
import GreenCart.PageObject.CommanLocators;
import GreenCart.PageObject.ConfirmationPage;
import GreenCart.PageObject.LandingPage;
import GreenCart.PageObject.ProductCataloguePage;
import GreenCart.TestComponent.BaseTest;

public class StandAloneTestWitrhHashMapData extends BaseTest {

	String Productname = "ZARA COAT 3";
	String country = "India";

	@Test(dataProvider = "getData", groups = "purchase")

	public void endToEndTestWithHashMapData(String email, String password, String ProductNames) {

		// TODO Auto-generated method stub

		LandingPage landingPage = new LandingPage(getDriver());
		landingPage.loginApplication(email, password);
		ProductCataloguePage productCataloguePage = new ProductCataloguePage(getDriver());
		List<WebElement> products = productCataloguePage.getProductList();
		productCataloguePage.addProductTocart(ProductNames);
		CommanLocators commanLocators = new CommanLocators(getDriver());
		commanLocators.goToCartPage();
		CartPage cartPage = new CartPage(getDriver());
		Boolean match = cartPage.verifyProductDisplay(ProductNames);
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

//		@DataProvider
//		public Object[][] getData() {
//			return new Object[][] { { "rahul20@gmail.com", "Rahul@2024", "ZARA COAT 3" },
//					{ "anshika@gmail.com", "Iamking@000", "ADIDAS ORIGINAL" } };
//		}
	@DataProvider(parallel = false)
	public Object[][] getData() {
		return new Object[][] { { "rahul20@gmail.com", "Rahul@2024", "ZARA COAT 3" },
				{ "anshika@gmail.com", "Iamking@000", "ADIDAS ORIGINAL" } };
	}

}
