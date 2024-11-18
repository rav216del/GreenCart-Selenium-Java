package GreenCart.PageObject;

import org.openqa.selenium.WebDriver;

public class PageObjectManager {

	private WebDriver driver;
	private LandingPage landingPage;
	private ProductCataloguePage productCataloguePage;
	private CommanLocators commanLocators;
	private CartPage cartPage;
	private CheckOutPage checkOutPage;
	private ConfirmationPage confirmationPage;
	private OrderPage orderPage;

	public PageObjectManager(WebDriver driver) {
		this.driver = driver;
	}

	public LandingPage getLandingPage() {
		if (landingPage == null) {
			landingPage = new LandingPage(driver);
		}
		return landingPage;
	}

	public ProductCataloguePage getProductCataloguePage() {
		if (productCataloguePage == null) {
			productCataloguePage = new ProductCataloguePage(driver);
		}
		return productCataloguePage;
	}

	public CommanLocators getCommanLocators() {
		if (commanLocators == null) {
			commanLocators = new CommanLocators(driver);
		}
		return commanLocators;
	}

	public CartPage getCartPage() {
		if (cartPage == null) {
			cartPage = new CartPage(driver);
		}
		return cartPage;
	}

	public CheckOutPage getCheckOutPage() {
		if (checkOutPage == null) {
			checkOutPage = new CheckOutPage(driver);
		}
		return checkOutPage;
	}

	public ConfirmationPage getConfirmationPage() {
		if (confirmationPage == null) {
			confirmationPage = new ConfirmationPage(driver);
		}
		return confirmationPage;
	}

	public OrderPage getOrderPage() {
		if (orderPage == null) {
			orderPage = new OrderPage(driver);
		}
		return orderPage;
	}
}
