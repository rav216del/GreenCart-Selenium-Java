package GreenCart.PageObject;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import GreenCart.AbstractComponent.AbstractComponent;

public class OrderPage extends AbstractComponent {
	WebDriver driver;

	// Constructor to initialize the driver
	public OrderPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		// Page factory initialization
		PageFactory.initElements(driver, this);
	}

	// create all the locator using page factory
	@FindBy(css = "tr td:nth-child(3)")
	List<WebElement> productsNameInOrder;

	public Boolean verifyProductDisplays(String Productname) {
		productsNameInOrder.stream().map(WebElement::getText).forEach(System.out::println);
		boolean match = productsNameInOrder.stream()
				.anyMatch(cartproduct -> cartproduct.getText().equalsIgnoreCase(Productname));
		return match;
	}

}
