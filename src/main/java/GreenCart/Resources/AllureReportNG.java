package GreenCart.Resources;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import io.qameta.allure.Attachment;

public class AllureReportNG {

	// Capture a screenshot for Allure report on test failure
	@Attachment(value = "Screenshot", type = "image/png")
	public static byte[] captureScreenshot(WebDriver driver) {
		return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
	}

	// Attach text log information to Allure report
	@Attachment(value = "{0}", type = "text/plain")
	public static String attachTextLog(String message) {
		return message;
	}
}
