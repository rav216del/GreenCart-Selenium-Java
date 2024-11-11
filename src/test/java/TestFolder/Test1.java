package TestFolder;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Test1 {

	private WebDriver driver;
	private WebDriverWait wait;
	private SoftAssert softAssert;

	@BeforeMethod
	public void setup() {
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--remote-allow-origins=*");
		options.addArguments("--no-sandbox");
		options.addArguments("--disable-dev-shm-usage");

		driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		softAssert = new SoftAssert();

	}

	@Test
	public void openGoogleTest() {
		driver.get("https://rahulshettyacademy.com/");
		System.out.println("Page title is: " + driver.getTitle());
		System.out.println("Current url is: " + driver.getCurrentUrl());
	}

	@Test
	public void checkTheLocators() {
		driver.get("https://rahulshettyacademy.com/locatorspractice/");
		driver.findElement(By.id("inputUsername")).sendKeys("rahul");
		driver.findElement(By.name("inputPassword")).sendKeys("hello123");
		driver.findElement(By.className("signInBtn")).click();
		WebElement errorElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("p.error")));
		String errorMessage = errorElement.getText();
		System.out.println(errorMessage);
		softAssert.assertEquals(errorMessage, "* Incorrect username or password", "Error message validation");
		driver.findElement(By.linkText("Forgot your password?")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Name']")))
				.sendKeys("John");
		driver.findElement(By.cssSelector("input[placeholder='Email']")).sendKeys("john@rsa.com");
		WebElement emailField = driver.findElement(By.xpath("//input[@type='text'][2]"));
		emailField.clear();
		emailField.sendKeys("john@gmail.com");
		// driver.findElement(By.cssSelector("input[type='text']:nth-child(3)")).sendKeys("john@gmail.com");
		driver.findElement(By.xpath("//form/input[3]")).sendKeys("9864353253");
		driver.findElement(By.cssSelector(".reset-pwd-btn")).click();
		WebElement resetMessageElement = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("form p")));
		String resetMessage = resetMessageElement.getText();
		System.out.println(resetMessage);
		softAssert.assertTrue(resetMessage.contains("Please use temporary password 'rahulshettyacademy' to Login."),
				"Reset message validation");
		driver.findElement(By.xpath("//div[@class='forgot-pwd-btn-conainer']/button[1]")).click();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#inputUsername"))).sendKeys("rahul");
		driver.findElement(By.cssSelector("input[type*='pass']")).sendKeys("rahulshettyacademy");

		driver.findElement(By.id("chkboxOne")).click();
		driver.findElement(By.xpath("//button[contains(@class,'submit')]")).click();

		// Verify successful login (assert expected page title or other unique elements)
		WebElement successMessageElement = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".login-container h2")));
		String successMessage = successMessageElement.getText();
		System.out.println(successMessage);
		softAssert.assertEquals(successMessage, "Hello rahul,", "Successful login validation");

		// Assert all at the end
		softAssert.assertAll();
	}

	@Test
	public void foundThePassword() {
		String name = "rahul";
		String password;
		try {
			password = getPassword(driver);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.get("https://rahulshettyacademy.com/locatorspractice/");
		driver.findElement(By.id("inputUsername")).sendKeys(name);
		// driver.findElement(By.name("inputPassword")).sendKeys(password);
		driver.findElement(By.className("signInBtn")).click();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		WebElement successMessagesElement = driver.findElement(By.tagName("p"));
		String successMessage = successMessagesElement.getText();
		System.out.println(successMessage);
		softAssert.assertEquals(successMessage, "You are successfully logged in.", "Successful login validation");

		Assert.assertEquals(driver.findElement(By.cssSelector("div[class='login-container'] h2")).getText(),
				"Hello " + name + ",");

		driver.findElement(By.xpath("//*[text()='Log Out']")).click();

		driver.close();

	}

	public static String getPassword(WebDriver driver) throws InterruptedException

	{

		driver.get("https://rahulshettyacademy.com/locatorspractice/");
		driver.findElement(By.linkText("Forgot your password?")).click();
		Thread.sleep(1000);
		driver.findElement(By.cssSelector(".reset-pwd-btn")).click();
		String passwordText = driver.findElement(By.cssSelector("form p")).getText();
		String[] passwordArray = passwordText.split("'");
		// String[] passwordArray2 = passwordArray[1].split("'");
		// passwordArray2[0]
		String password = passwordArray[1].split("'")[0];
		return password;

	}

	@AfterMethod
	public void tearDown() {
		if (driver != null) {
			driver.quit();
		}
	}
}
