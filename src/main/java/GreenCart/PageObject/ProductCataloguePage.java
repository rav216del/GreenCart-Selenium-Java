package GreenCart.PageObject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import GreenCart.AbstractComponent.AbstractComponent;

public class ProductCataloguePage extends AbstractComponent {
	WebDriver driver;

	// Constructor to initialize the driver
	public ProductCataloguePage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		// Page factory initialization
		PageFactory.initElements(driver, this);
	}

	// create all the locator using page factory
	@FindBy(css = ".mb-3")
	List<WebElement> products;

	By productList = By.cssSelector(".mb-3");
	By addToCart = By.cssSelector(".card-body button:last-of-type");
	By toastMessage = By.cssSelector("#toast-container");
	By spinner = By.cssSelector(".ng-animating");

	public List<WebElement> getProductList() {
		waitForElementToBeVisible(productList, 3);
		return products;
	}

	public WebElement getProductByName(String productName) {
		WebElement prod = getProductList().stream()
				.filter(product -> product.findElement(By.cssSelector("b")).getText().equalsIgnoreCase(productName))
				.findFirst().orElse(null);
		return prod;
	}

	public void addProductTocart(String productName) {
		WebElement prod = getProductByName(productName);
		prod.findElement(addToCart).click();
		waitForElementToBeVisible(toastMessage, 3);
		waitForElementToBeDisappear(spinner, 3);

	}

}
