package GreenCart.PageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import GreenCart.AbstractComponent.AbstractComponent;

public class CommanLocators extends AbstractComponent {

	WebDriver driver;

	public CommanLocators(WebDriver driver) {
		super(driver);
		this.driver = driver;
		// Page factory initialization
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "[routerlink*='cart']")
	WebElement cartHeader;

	@FindBy(css = "button[routerlink='/dashboard/myorders']")
	WebElement orderHeader;

	public void goToCartPage() {
		cartHeader.click();
	}

	public void goToOrderPage() {
		clickElementWithJS(orderHeader);
	}

}
