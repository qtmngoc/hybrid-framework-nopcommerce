package reportConfig.nopCommerce;

import static reportConfig.nopCommerce.NcExtentTestManagerV5.getTest;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.Status;

import commons.nopCommerce.NcBaseTest;

public class NcExtentTestListenerV5 extends NcBaseTest implements ITestListener {
	private static String getTestMethodName(ITestResult iTestResult) {
		return iTestResult.getMethod().getConstructorOrMethod().getName();
	}

	@Override
	public void onStart(ITestContext iTestContext) {
		iTestContext.setAttribute("WebDriver", this.getDriverInstance());
	}

	@Override
	public void onFinish(ITestContext iTestContext) {
		NcExtentManagerV5.extentReports.flush();
	}

	@Override
	public void onTestStart(ITestResult iTestResult) {
	}

	@Override
	public void onTestSuccess(ITestResult iTestResult) {
		getTest().log(Status.PASS, "Test passed");
	}

	@Override
	public void onTestFailure(ITestResult iTestResult) {
		Object testClass = iTestResult.getInstance();
		WebDriver driver = ((NcBaseTest) testClass).getDriverInstance();
		String base64Screenshot = "data:image/png;base64," + ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
		getTest().log(Status.FAIL, "Test Failed", getTest().addScreenCaptureFromBase64String(base64Screenshot).getModel().getMedia().get(0));
	}

	@Override
	public void onTestSkipped(ITestResult iTestResult) {
		getTest().log(Status.SKIP, "Test Skipped");
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
		getTest().log(Status.FAIL, "Test Failed with percentage" + getTestMethodName(iTestResult));
	}
}
