package GreenCart.PageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import GreenCart.AbstractComponent.AbstractComponent;

public class LandingPage extends AbstractComponent {
	WebDriver driver;

	// Constructor to initialize the driver
	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		// Page factory initialization
		PageFactory.initElements(driver, this);
	}

	// create all the locator using page factory
	@FindBy(id = "userEmail")
	WebElement userEmail;

	@FindBy(id = "userPassword")
	WebElement userPassword;

	@FindBy(id = "login")
	WebElement login;

	@FindBy(css = "#toast-container")
	WebElement errorMessage;

	public void loginApplication(String email, String password) {
		userEmail.sendKeys(email);
		userPassword.sendKeys(password);
		login.click();
	}

	public boolean isErrorMessageDisplayed(String expectedMessage) {
		try {
			return errorMessage.isDisplayed() && errorMessage.getText().contains(expectedMessage);
		} catch (Exception e) {
			return false; // Return false if error message is not displayed
		}
	}

}
