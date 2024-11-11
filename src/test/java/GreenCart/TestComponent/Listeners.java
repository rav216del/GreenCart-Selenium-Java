package GreenCart.TestComponent;

import java.io.IOException;

import javax.mail.MessagingException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import GreenCart.AbstractComponent.AbstractComponent;
import GreenCart.Resources.AllureReportNG;
import GreenCart.Resources.EmailUtility;
import GreenCart.Resources.ExtendReportNG;

public class Listeners implements ITestListener {
	// , IRetryAnalyzer
	private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
	private int count = 0;
	private static final int MAX_RETRIES = 2; // Set retry limit
	ExtentTest test;
	ExtentReports extent = ExtendReportNG.getReportObject();
	ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();

	@Override
	public void onTestStart(ITestResult result) {
		test = extent.createTest(result.getMethod().getMethodName());
		extentTest.set(test);
		AllureReportNG.attachTextLog("Test Start" + "Starting test: " + result.getMethod().getMethodName());
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		extentTest.get().log(Status.PASS, "Test Passed");
		AllureReportNG.attachTextLog("Test Passed" + result.getMethod().getMethodName() + " passed.");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		extentTest.get().log(Status.FAIL, "Test Failed");
		extentTest.get().fail(result.getThrowable());
		WebDriver driver = BaseTest.driver.get(); // Use the ThreadLocal driver
		if (driver != null) {
			String screenshotPath = AbstractComponent.captureScreenshot(driver, result.getMethod().getMethodName());

			if (screenshotPath != null) {
				test.addScreenCaptureFromPath(screenshotPath, "Screenshot on Failure"); // Pass absolute path here
			}
			// Allure screenshot attachment
			AllureReportNG.captureScreenshot(driver);
		}
		AllureReportNG.attachTextLog("Test Failed" + result.getMethod().getMethodName() + " failed.");

		// Retry logic
		if (count < MAX_RETRIES) {
			count++;
			result.setStatus(ITestResult.SUCCESS); // Set the result to success to allow the test to re-run
		}
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		extentTest.get().log(Status.SKIP, "Test Skipped");
		AllureReportNG.attachTextLog("Test Skipped" + result.getMethod().getMethodName() + " skipped.");
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
	}

	@Override
	public void onStart(ITestContext context) {
	}

	@Override
	public void onFinish(ITestContext context) {
		System.out.println("Test execution finished, sending report...");
		extent.flush();
		EmailUtility emailUtility = new EmailUtility();
		try {
			String[] reportPaths = { "C:/Users/ravi.aggarwal_infobe/eclipse-workspace/GreenCart/reports/index.html", // Extent
																														// Report
					"C:/Users/ravi.aggarwal_infobe/eclipse-workspace/GreenCart/allure-report/index.html" // Allure
																											// Report
			};
			emailUtility.sendReport(reportPaths);
		} catch (MessagingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

//	@Override
//	public boolean retry(ITestResult result) {
//		if (count < MAX_RETRIES) {
//			count++;
//			return true; // Retry the test
//		}
//		return false;
//	}
}
