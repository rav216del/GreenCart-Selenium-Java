package GreenCart.AbstractComponent;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AbstractComponent {
	public WebDriver driver;

	public AbstractComponent(WebDriver driver) {
		this.driver = driver;
	}

	public WebElement waitForElementToBeVisible(By locator, int seconds) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	public WebElement waitForElementToBeClickable(WebElement locator, int seconds) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
		return wait.until(ExpectedConditions.elementToBeClickable(locator));
	}

	public Boolean waitForElementToBeDisappear(By locator, int seconds) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
		return wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
	}

	public void scrollToElement(WebElement element) {
		Actions actions = new Actions(driver);
		actions.moveToElement(element).perform(); // Move to the element with Actions
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", element); // Ensure visibility with JavaScript
	}

	// Send keys to an input field using Actions
	public void sendKeysWithActions(WebElement element, String text) {
		Actions actions = new Actions(driver);
		actions.sendKeys(element, text).build().perform();
	}

	// Scroll to an element using JavaScript
	public void scrollToElementJS(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", element);
	}

	// Click on an element using JavaScript
	public void clickElementWithJS(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", element);
	}

	public static String captureScreenshot(WebDriver driver, String testName) {
		// Create a timestamp for unique screenshot names
		String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
		// Define the file path and name
		String fileName = "screenshots/" + testName + "_" + timestamp + ".png";
		// Take the screenshot and store it in a temporary file
		File sourceFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		// Create the target file (where the screenshot will be saved)
		File destinationFile = new File(fileName);

		try {
			// Copy the screenshot to the specified location
			FileUtils.copyFile(sourceFile, destinationFile);
			System.out.println("Screenshot saved to: " + destinationFile.getAbsolutePath());
			return destinationFile.getAbsolutePath();
		} catch (IOException e) {
			System.err.println("Failed to capture screenshot: " + e.getMessage());
			return null;
		}

	}

}
