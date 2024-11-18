package GreenCart.TestComponent;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {
	// Use ThreadLocal
	protected static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
	private Properties properties;

	// Constructor
	public BaseTest() {
		properties = new Properties();
		try {
			String projectPath = System.getProperty("user.dir"); // Get project root directory
			FileInputStream fileInputStream = new FileInputStream(
					projectPath + "/src/main/resources/GlobalData.properties");
			properties.load(fileInputStream);
			fileInputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Method to initialize WebDriver
	public WebDriver initializeDriver() {
		String browserName = properties.getProperty("browser", "chrome").toLowerCase();
		System.out.println("Using browser: " + browserName);

		WebDriver localDriver;

		switch (browserName) {
		case "chrome":
			localDriver = new ChromeDriver();
			ChromeOptions chromeOptions = new ChromeOptions();
			chromeOptions.addArguments("--disable-blink-features=AutomationControlled");
			chromeOptions.addArguments("--ignore-certificate-errors"); // Ignore certificate errors
			chromeOptions.addArguments("--allow-insecure-localhost");
			chromeOptions.addArguments("--disable-popup-blocking");
			chromeOptions.addArguments("--disable-notifications");
			chromeOptions.setAcceptInsecureCerts(true);
			chromeOptions.addArguments("--disable-web-security");
			chromeOptions.addArguments("--no-sandbox");

			chromeOptions.addArguments("--disable-gpu");
			chromeOptions.addArguments("--disable-extensions");
			chromeOptions.addArguments("--ignore-ssl-errors=yes");
			chromeOptions.addArguments("--ignore-certificate-errors");
			chromeOptions.addArguments("--disable-dev-shm-usage");
			chromeOptions.addArguments("--remote-allow-origins=*");
			chromeOptions.addArguments("--enable-automation");
			chromeOptions.addArguments("--allow-running-insecure-content");
			localDriver = new ChromeDriver(chromeOptions);

		case "firefox":

			localDriver = new FirefoxDriver();
			break;

		case "edge":
			EdgeOptions edgeOptions = new EdgeOptions();
			edgeOptions.setAcceptInsecureCerts(true);
			edgeOptions.addArguments("--ignore-certificate-errors");
			edgeOptions.addArguments("--disable-web-security");
			edgeOptions.addArguments("--allow-running-insecure-content");
			localDriver = new EdgeDriver(edgeOptions);
			break;

		case "safari":
			localDriver = new SafariDriver();
			break;

		default:
			throw new IllegalArgumentException("Unsupported browser: " + browserName);
		}
		localDriver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
		localDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		localDriver.manage().window().maximize();
		return localDriver;

	}

	public String getProperty(String key) {
		return properties.getProperty(key);
	}

	// Getter for WebDriver instance, which retrieves it from the ThreadLocal
	public WebDriver getDriver() {
		return driver.get();
	}

	@BeforeMethod(alwaysRun = true)
	public void setUp() {
		if (driver.get() == null) {
			driver.set(initializeDriver()); // Initialize WebDriver if not already set
			String url = getProperty("url");
			if (url == null || url.isEmpty()) {
				throw new IllegalArgumentException("URL property is not set in the properties file.");
			}
			driver.get().get(url);
		}
	}

	@AfterMethod(alwaysRun = true)
	public void tearDown() {
		if (driver.get() != null) {
			driver.get().quit();
			driver.remove();
		}
	}
}
