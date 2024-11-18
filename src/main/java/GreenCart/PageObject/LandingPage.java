package GreenCart.PageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import GreenCart.AbstractComponent.AbstractComponent;
import GreenCart.Resources.Log;

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
		Log.info("user enter the email");
		userPassword.sendKeys(password);
		Log.info("user enter the password");
		login.click();
		Log.info("user click on the enter button");
	}

	public boolean isErrorMessageDisplayed(String expectedMessage) {
		try {
			return errorMessage.isDisplayed() && errorMessage.getText().contains(expectedMessage);
		} catch (Exception e) {
			return false; // Return false if error message is not displayed
		}
	}

}
