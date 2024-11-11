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
import GreenCart.PageObject.ProductCataloguePage;
import GreenCart.TestComponent.BaseTest;
import TestDataProviders.ExcelDataProvider;

public class SubmitOrderExcelTest extends BaseTest {

	String country = "India";

	@Test(groups = { "purchase" }, dataProvider = "excelData", dataProviderClass = ExcelDataProvider.class)
	public void endToEndTestWithDataProvider(String email, String password, String productName) {

		LandingPage landingPage = new LandingPage(getDriver());
		landingPage.loginApplication(email, password);
		ProductCataloguePage productCataloguePage = new ProductCataloguePage(getDriver());
		List<WebElement> products = productCataloguePage.getProductList();
		productCataloguePage.addProductTocart(productName);
		CommanLocators commanLocators = new CommanLocators(getDriver());
		commanLocators.goToCartPage();
		CartPage cartPage = new CartPage(getDriver());
		Assert.assertTrue(cartPage.verifyProductDisplay(productName));
		cartPage.goToCheckOut();
		CheckOutPage checkOutPage = new CheckOutPage(getDriver());
		checkOutPage.selectCountry(country);
		checkOutPage.clickOnPlaceOrder();
		ConfirmationPage confirmationPage = new ConfirmationPage(getDriver());
		Assert.assertTrue(confirmationPage.getConfirmationMessage().equalsIgnoreCase("Thankyou for the order."));
	}
}
