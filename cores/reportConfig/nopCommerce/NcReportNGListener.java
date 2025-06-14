package reportConfig.nopCommerce;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import commons.nopCommerce.NcBaseTest;

import commons.nopCommerce.NcGlobalConstants;
import commons.wordPress.WpBaseTest;

public class NcReportNGListener extends WpBaseTest implements ITestListener {

	@Override
	public void onStart(ITestContext context) {
		//System.out.println("---------- " + context.getName() + " STARTED test ----------");
	}

	@Override
	public void onFinish(ITestContext context) {
		//System.out.println("---------- " + context.getName() + " FINISHED test ----------");
	}

	@Override
	public void onTestStart(ITestResult result) {
		//System.out.println("---------- " + result.getName() + " STARTED test ----------");
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		//System.out.println("---------- " + result.getName() + " SUCCESS test ----------");
	}

	@Override
	public void onTestFailure(ITestResult result) {		
		//System.out.println("---------- " + result.getName() + " FAILED test ----------");
		System.setProperty("org.uncommons.reportng.escape-output", "false");

		Object testClass = result.getInstance();
		WebDriver webDriver = ((NcBaseTest) testClass).getDriverInstance();

		/*
		// Image file
		String screenshotImagePath = captureScreenshotAsFile(webDriver, result.getName());
		Reporter.getCurrentTestResult();
		Reporter.log("<br><a target=\"_blank\" href=\"file:///" + screenshotImagePath + "\">" + "<img src=\"file:///" + screenshotImagePath + "\" " + "height='100' width='150'/> " + "</a></br>");
		Reporter.setCurrentTestResult(null);
		*/

		// Base 64
		String screenshotBase64Path = captureScreenshotAsBase64(webDriver, result.getName());
		Reporter.getCurrentTestResult();
		Reporter.log("<br><a href=\"data:image/png;base64," + screenshotBase64Path + "\">" + "<img src=\"data:image/png;base64," + screenshotBase64Path + "\" " + "height='100' width='150'/> " + "</a></br>");
		Reporter.setCurrentTestResult(null);
	}
	
	@Override
	public void onTestSkipped(ITestResult result) {
		//System.out.println("---------- " + result.getName() + " SKIPPED test ----------");
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		//System.out.println("---------- " + result.getName() + " FAILED WITH SUCCESS PERCENTAGE test ----------");
	}

	public String captureScreenshotAsFile(WebDriver driver, String screenshotName) {
		try {
			Calendar calendar = Calendar.getInstance();
			SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
			File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			String screenPath = NcGlobalConstants.REPORTNG_SCREENSHOT_PATH + screenshotName + "_" + formater.format(calendar.getTime()) + ".png";
			FileUtils.copyFile(source, new File(screenPath));
			return screenPath;
		} catch (IOException e) {
			System.out.println("Exception while taking screenshot: " + e.getMessage());
			return e.getMessage();
		}
	}

	public String captureScreenshotAsBase64(WebDriver driver, String screenshotName) {
		return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
	}

}
