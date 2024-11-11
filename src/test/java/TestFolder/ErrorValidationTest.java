package TestFolder;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import GreenCart.PageObject.CartPage;
import GreenCart.PageObject.CommanLocators;
import GreenCart.PageObject.LandingPage;
import GreenCart.PageObject.ProductCataloguePage;
import GreenCart.TestComponent.BaseTest;

public class ErrorValidationTest extends BaseTest {

	@Test
	public void loginErrorValiadtionTest() {

		// TODO Auto-generated method stub
		String Productname = "ZARA COAT 3";
		String country = "India";
		LandingPage landingPage = new LandingPage(getDriver());
		landingPage.loginApplication("acvf@gma.com", "45678");
		String expectedErrorMessage = "Incorrect email or password.";
		boolean isErrorDisplayed = landingPage.isErrorMessageDisplayed(expectedErrorMessage);
		Assert.assertTrue(isErrorDisplayed, "Error message not displayed as expected for empty login credentials.");

	}

	@Test
	public void productErrorvalidationTest() {
		String Productname = "ZARA COAT 3";
		String Productsname = "ZARA COAT 3213";
		String country = "India";
		LandingPage landingPage = new LandingPage(getDriver());
		landingPage.loginApplication("rahul20@gmail.com", "Rahul@2024");
		ProductCataloguePage productCataloguePage = new ProductCataloguePage(getDriver());
		List<WebElement> products = productCataloguePage.getProductList();
		productCataloguePage.addProductTocart(Productname);
		CommanLocators commanLocators = new CommanLocators(getDriver());
		commanLocators.goToCartPage();
		CartPage cartPage = new CartPage(getDriver());
		Boolean match = cartPage.verifyProductDisplay(Productsname);
		System.out.println(match);
		Assert.assertFalse(match);

	}

}
