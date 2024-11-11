package GreenCart.PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import GreenCart.AbstractComponent.AbstractComponent;

public class CheckOutPage extends AbstractComponent {
	WebDriver driver;

	// Constructor to initialize the driver
	public CheckOutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		// Page factory initialization
		PageFactory.initElements(driver, this);
	}

	// create all the locator using page factory
	@FindBy(css = "[placeholder='Select Country']")
	WebElement countryInput;

	@FindBy(xpath = "//section//button[span[normalize-space(text())='India']]")
	WebElement indiaOption;

	@FindBy(xpath = "//a[@class='btnn action__submit ng-star-inserted']")
	WebElement placeOrderButton;

	By dropDownBox = By.cssSelector("section .ta-results");

	public void selectCountry(String country) {
		sendKeysWithActions(countryInput, country);
		waitForElementToBeVisible(dropDownBox, 3);
		scrollToElementJS(indiaOption);
		clickElementWithJS(indiaOption);
	}

	public void clickOnPlaceOrder() {
		scrollToElementJS(placeOrderButton);
		clickElementWithJS(placeOrderButton);

	}

}
