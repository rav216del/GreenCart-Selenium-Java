package GreenCart.PageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import GreenCart.AbstractComponent.AbstractComponent;

public class ConfirmationPage extends AbstractComponent {
	WebDriver driver;

	// Constructor to initialize the driver
	public ConfirmationPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		// Page factory initialization
		PageFactory.initElements(driver, this);
	}

	// create all the locator using page factory
	@FindBy(css = ".hero-primary")
	WebElement confirmMessage;

	public String getConfirmationMessage() {
		return confirmMessage.getText();
//		 
//		System.out.println(confirmMessage);
//		return confirmMessages;

	}
}
