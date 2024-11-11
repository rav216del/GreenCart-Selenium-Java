package GreenCart.PageObject;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import GreenCart.AbstractComponent.AbstractComponent;

public class CartPage extends AbstractComponent {
	WebDriver driver;

	// Constructor to initialize the driver
	public CartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		// Page factory initialization
		PageFactory.initElements(driver, this);
	}

	// create all the locator using page factory
	@FindBy(xpath = "//button[contains(text(),'Checkout')]")
	WebElement checkoutButton;

	@FindBy(css = ".cartSection h3")
	List<WebElement> cartProducts;

	public Boolean verifyProductDisplay(String Productname) {
		cartProducts.stream().map(WebElement::getText).forEach(System.out::println);
		boolean match = cartProducts.stream()
				.anyMatch(cartproduct -> cartproduct.getText().equalsIgnoreCase(Productname));
		return match;
	}

	public void goToCheckOut() {
		waitForElementToBeClickable(checkoutButton, 5);
		scrollToElement(checkoutButton);
		checkoutButton.click();
	}

}
