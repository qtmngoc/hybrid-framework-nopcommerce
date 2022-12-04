package com.wordpress;

import java.lang.reflect.Method;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import commons.wordPress.WpBaseTest;
import commons.wordPress.WpPageGeneratorManager;
import pageObjects.wordPress.WpAdminDashboardPO;
import pageObjects.wordPress.WpAdminLoginPO;
import reportConfig.wordPress.WpExtentTestManagerV5;

public class Demo_01_Login extends WpBaseTest {
	
	@Parameters({ "browser", "adminUrl" })
	@BeforeClass
	public void beforeClass(String browserName, String adminUrl) {	
		this.browserName = browserName;
		
		driver = getBrowserDriver(browserName, adminUrl);
		adminLoginPage = WpPageGeneratorManager.getAdminLoginPage(driver);
	}

	@Test
	public void Login_01_Empty_Username(Method method) {
		WpExtentTestManagerV5.startTest(method.getName() + " - " + browserName, "Login with empty username on Admin site");
		int s = 0;

		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step " + String.format("%02d", s=s+1) + ": Open Admin LOGIN page.");
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step " + String.format("%02d", s=s+1) + ": Enter an empty value into 'Email Address or Username' field.");
		adminLoginPage.inputIntoUsernameTextbox("");
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step " + String.format("%02d", s=s+1) + ": Click on 'Continue' button.");
		adminLoginPage.clickOnContinueButton();
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step " + String.format("%02d", s=s+1) + ": Verify 'Please enter a username or email address.' message is displayed.");
		verifyEquals(adminLoginPage.getLoginErrorMessage(), "Please enter a username or email address.");
	}
	
	@Test
	public void Login_02_Not_Existent_Username(Method method) {
		WpExtentTestManagerV5.startTest(method.getName() + " - " + browserName, "Login with non-existent username on Admin site");
		int s = 0;

		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step " + String.format("%02d", s=s+1) + ": Refresh Admin LOGIN page.");
		adminLoginPage.refreshCurrentPage(driver);
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step " + String.format("%02d", s=s+1) + ": Enter \"" + nonExistentUsername + "\" into 'Email Address or Username' field.");
		adminLoginPage.inputIntoUsernameTextbox(nonExistentUsername);
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step " + String.format("%02d", s=s+1) + ": Click on 'Continue' button.");
		adminLoginPage.clickOnContinueButton();
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step " + String.format("%02d", s=s+1) + ": Verify 'User does not exist. Would you like to create a new account?' message is displayed.");
		verifyEquals(adminLoginPage.getLoginErrorMessage(), "User does not exist. Would you like to create a new account?");
	}
	
	@Test
	public void Login_03_Incorrect_Password(Method method) {
		WpExtentTestManagerV5.startTest(method.getName() + " - " + browserName, "Login with existing username and incorrect password on Admin site");
		int s = 0;

		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step " + String.format("%02d", s=s+1) + ": Refresh Admin LOGIN page.");
		adminLoginPage.refreshCurrentPage(driver);
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step " + String.format("%02d", s=s+1) + ": Enter \"" + username + "\" into 'Email Address or Username' field.");
		adminLoginPage.inputIntoUsernameTextbox(username);
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step " + String.format("%02d", s=s+1) + ": Click on 'Continue' button.");
		adminLoginPage.clickOnContinueButton();
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step " + String.format("%02d", s=s+1) + ": Enter \"" + incorrectPassword + "\" into 'Password' field.");
		adminLoginPage.inputIntoPasswordTextbox(incorrectPassword);
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step " + String.format("%02d", s=s+1) + ": Click on 'Log In' button.");
		adminLoginPage.clickOnLoginButton();
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step " + String.format("%02d", s=s+1) + ": Verify 'Oops, that's not the right password. Please try again!' message is displayed.");
		verifyEquals(adminLoginPage.getLoginErrorMessage(), "Oops, that's not the right password. Please try again!");
	}
	
	@Test
	public void Login_04_Success(Method method) {
		WpExtentTestManagerV5.startTest(method.getName() + " - " + browserName, "Login with existing username and correct password on Admin site");
		int s = 0;

		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step " + String.format("%02d", s=s+1) + ": Refresh Admin LOGIN page.");
		adminLoginPage.refreshCurrentPage(driver);
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step " + String.format("%02d", s=s+1) + ": Enter \"" + username + "\" into 'Email Address or Username' field.");
		adminLoginPage.inputIntoUsernameTextbox(username);
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step " + String.format("%02d", s=s+1) + ": Click on 'Continue' button.");
		adminLoginPage.clickOnContinueButton();
				
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step " + String.format("%02d", s=s+1) + ": Enter \"" + password + "\" into 'Password' field.");
		adminLoginPage.inputIntoPasswordTextbox(password);
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step " + String.format("%02d", s=s+1) + ": Click on 'Log In' button.");
		adminDashboardPage = adminLoginPage.clickOnLoginButton();
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step " + String.format("%02d", s=s+1) + ": Verify 'Dashboard' header is displayed.");
		verifyTrue(adminDashboardPage.isDashboardHeaderDisplayed());
	}

	@AfterClass(alwaysRun = true)
	public void afterClass() {
		closeBrowserAndDriver();
	}
	
	private String browserName;
	static int randomNumber1 = generateFakeNumber();
	static String username = "automationeditor";
	static String password = "automationfc";
	private String nonExistentUsername = "annie.2022." + randomNumber1;
	private String incorrectPassword = "2022." + randomNumber1;
	
	private WebDriver driver;
	private WpAdminLoginPO adminLoginPage;
	private WpAdminDashboardPO adminDashboardPage;
}