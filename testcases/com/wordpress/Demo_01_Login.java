package com.wordpress;

import java.lang.reflect.Method;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import commons.wordPress.BaseTest;
import commons.wordPress.PageGeneratorManager;
import pageObjects.wordPress.AdminDashboardPO;
import pageObjects.wordPress.AdminLoginPO;
import reportConfig.wordPress.ExtentTestManagerV5;

public class Demo_01_Login extends BaseTest {
	
	@Parameters({ "browser", "adminUrl" })
	@BeforeClass
	public void beforeClass(String browserName, String adminUrl) {		
		driver = getBrowserDriver(browserName, adminUrl);
		adminLoginPage = PageGeneratorManager.getAdminLoginPage(driver);
	}

	@Test
	public void Login_01_Empty_Username(Method method) {
		ExtentTestManagerV5.startTest(method.getName(), "Login with empty username");

		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 01: Enter empty value into 'Email Address or Username' textbox.");
		adminLoginPage.inputIntoUsernameTextbox("");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 02: Click on 'Continue' button.");
		adminLoginPage.clickOnContinueButton();
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 03: Verify 'Please enter a username or email address.' message is displayed.");
		verifyEquals(adminLoginPage.getLoginErrorMessage(), "Please enter a username or email address.");
	}
	
	@Test
	public void Login_02_Not_Existent_Username(Method method) {
		ExtentTestManagerV5.startTest(method.getName(), "Login with non-existent username");

		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 01: Refresh Admin LOGIN page.");
		adminLoginPage.refreshCurrentPage(driver);
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 02: Enter \"" + nonExistentUsername + "\" into 'Email Address or Username' textbox.");
		adminLoginPage.inputIntoUsernameTextbox(nonExistentUsername);
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 03: Click on 'Continue' button.");
		adminLoginPage.clickOnContinueButton();
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 04: Verify 'User does not exist. Would you like to create a new account?' message is displayed.");
		verifyEquals(adminLoginPage.getLoginErrorMessage(), "User does not exist. Would you like to create a new account?");
	}
	
	@Test
	public void Login_03_Incorrect_Password(Method method) {
		ExtentTestManagerV5.startTest(method.getName(), "Login with existing username and incorrect password");

		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 01: Refresh Admin LOGIN page.");
		adminLoginPage.refreshCurrentPage(driver);
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 02: Enter \"" + username + "\" into 'Email Address or Username' textbox.");
		adminLoginPage.inputIntoUsernameTextbox(username);
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 03: Click on 'Continue' button.");
		adminLoginPage.clickOnContinueButton();
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 04: Enter \"" + incorrectPassword + "\" into 'Password' textbox.");
		adminLoginPage.inputIntoPasswordTextbox(incorrectPassword);
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 05: Click on 'Log In' button.");
		adminLoginPage.clickOnLoginButton();
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 06: Verify 'Oops, that's not the right password. Please try again!' message is displayed.");
		verifyEquals(adminLoginPage.getLoginErrorMessage(), "Oops, that's not the right password. Please try again!");
	}
	
	@Test
	public void Login_04_Success(Method method) {
		ExtentTestManagerV5.startTest(method.getName(), "Login with existing username and correct password");

		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 01: Refresh Admin LOGIN page.");
		adminLoginPage.refreshCurrentPage(driver);
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 02: Enter \"" + username + "\" into 'Email Address or Username' text box.");
		adminLoginPage.inputIntoUsernameTextbox(username);
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 03: Click on 'Continue' button.");
		adminLoginPage.clickOnContinueButton();
				
		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 04: Enter \"" + password + "\" into 'Password' text box.");
		adminLoginPage.inputIntoPasswordTextbox(password);
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 05: Click on 'Log In' button.");
		adminDashboardPage = adminLoginPage.clickOnLoginButton();
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 06: Verify 'Dashboard' header is displayed.");
		verifyTrue(adminDashboardPage.isDashboardHeaderDisplayed());
	}

	@AfterClass(alwaysRun = true)
	public void afterClass() {
		closeBrowserAndDriver();
	}
	
	static int randomNumber1 = generateFakeNumber();
	static String username = "automationeditor";
	static String password = "automationfc";
	String nonExistentUsername = "annie.2022." + randomNumber1;
	String incorrectPassword = "2022." + randomNumber1;
	
	WebDriver driver;
	AdminLoginPO adminLoginPage;
	AdminDashboardPO adminDashboardPage;
}