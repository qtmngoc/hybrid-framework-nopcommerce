package reportConfig.wordPress;

import static reportConfig.wordPress.ExtentTestManagerV5.getTest;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.Status;

import commons.wordPress.BaseTest;

public class ExtentTestListenerV5 extends BaseTest implements ITestListener {
	private static String getTestName(ITestResult iTestResult) {
		 return iTestResult.getTestName() != null ? iTestResult.getTestName() : iTestResult.getMethod().getConstructorOrMethod().getName();
	}
	
	public String getTestDescription(ITestResult iTestResult) {
        return iTestResult.getMethod().getDescription() != null ? iTestResult.getMethod().getDescription() : getTestName(iTestResult);
    }

	@Override
	public void onStart(ITestContext iTestContext) {
		iTestContext.setAttribute("WebDriver", this.getDriverInstance());
	}

	@Override
	public void onFinish(ITestContext iTestContext) {
		ExtentManagerV5.extentReports.flush();
	}

	@Override
	public void onTestStart(ITestResult iTestResult) {
		
	}

	@Override
	public void onTestSuccess(ITestResult iTestResult) {
		//getTest().log(Status.PASS, "Test passed");
	}

	@Override
	public void onTestFailure(ITestResult iTestResult) {
		Object testClass = iTestResult.getInstance();
		WebDriver driver = ((BaseTest) testClass).getDriverInstance();
		String base64Screenshot = "data:image/png;base64," + ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
		getTest().log(Status.FAIL, "Test Failed", getTest().addScreenCaptureFromBase64String(base64Screenshot).getModel().getMedia().get(0));
		//getTest().log(Status.FAIL, getTestDescription(iTestResult), getTest().addScreenCaptureFromBase64String(base64Screenshot).getModel().getMedia().get(0));
	}

	@Override
	public void onTestSkipped(ITestResult iTestResult) {
		getTest().log(Status.SKIP, "Test Skipped");
		//getTest().log(Status.SKIP, getTestName(iTestResult) + " test is skipped.");
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
		getTest().log(Status.FAIL, "Test Failed with percentage" + getTestName(iTestResult));
	}
}
