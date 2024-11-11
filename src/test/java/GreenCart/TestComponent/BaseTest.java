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
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	// Use ThreadLocal for WebDriver to ensure each thread gets its own instance
	protected static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
	private Properties properties;

	// Constructor to load properties only once
	public BaseTest() {
		properties = new Properties();
		try {
			String projectPath = System.getProperty("user.dir"); // Get project root directory
			FileInputStream fileInputStream = new FileInputStream(
					projectPath + "/src/main/java/GreenCart/Resources/GlobalData.properties");
			properties.load(fileInputStream);
			fileInputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Method to initialize WebDriver based on the browser specified in properties
	// file
	public WebDriver initializeDriver() {
		// String browserName = properties.getProperty("browser",
		// "chrome").toLowerCase();
		String browserName = System.getProperty("browser", properties.getProperty("browser", "chrome")).toLowerCase();
		System.out.println("Using browser: " + browserName); // Debugging log

		WebDriver localDriver = null;

		switch (browserName) {
		case "chrome":
			WebDriverManager.chromedriver().setup();
			ChromeOptions chromeOptions = new ChromeOptions();
			chromeOptions.addArguments("--remote-allow-origins=*");
			chromeOptions.addArguments("--no-sandbox");
			chromeOptions.addArguments("--disable-dev-shm-usage");
			localDriver = new ChromeDriver(chromeOptions);
			break;

		case "firefox":
			WebDriverManager.firefoxdriver().setup();
			FirefoxOptions firefoxOptions = new FirefoxOptions();
			localDriver = new FirefoxDriver(firefoxOptions);
			break;

		case "edge":
			WebDriverManager.edgedriver().setup();
			EdgeOptions edgeOptions = new EdgeOptions();
			localDriver = new EdgeDriver(edgeOptions);
			break;

		case "safari":
			localDriver = new SafariDriver();
			break;

		default:
			System.out.println("Unsupported browser specified, defaulting to Chrome");
			WebDriverManager.chromedriver().setup();
			localDriver = new ChromeDriver();
			break;
		}

		localDriver.manage().window().maximize();
		localDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		return localDriver;
	}

	// Getter for properties to access them across tests
	public String getProperty(String key) {
		return properties.getProperty(key);
	}

	// Getter for WebDriver instance, which retrieves it from the ThreadLocal
	public WebDriver getDriver() {
		return driver.get();
	}

	// Method to launch the application and navigate to URL
	@BeforeMethod(alwaysRun = true)
	public void setUp() {
		if (driver.get() == null) {
			driver.set(initializeDriver()); // Initialize WebDriver if not already set
			driver.get().get(getProperty("url")); // Navigate to URL defined in properties
		}
	}

	@AfterMethod(alwaysRun = true)
	public void tearDown() {
		if (driver.get() != null) {
			driver.get().quit(); // Quit WebDriver instance
			driver.remove(); // Clean up the ThreadLocal WebDriver
		}
	}
}
