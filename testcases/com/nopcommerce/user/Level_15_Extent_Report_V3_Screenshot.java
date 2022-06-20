package com.nopcommerce.user;

import java.lang.reflect.Method;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.nopCommerce.BaseTest;
import commons.nopCommerce.PageGeneratorManager;
import pageObjects.nopCommerce.user.UserCustomerInfoPageObject;
import pageObjects.nopCommerce.user.UserHomePageObject;
import pageObjects.nopCommerce.user.UserLoginPageObject;
import pageObjects.nopCommerce.user.UserRegisterPageObject;

// import com.aventstack.extentreports.ExtentReports;
// import com.aventstack.extentreports.ExtentTest;
// import com.aventstack.extentreports.Status;
// import reportConfig.nopCommerce.ExtentManagerV3;

public class Level_15_Extent_Report_V3_Screenshot extends BaseTest {

	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = getBrowserDriver(browserName);
		userHomePage = PageGeneratorManager.getUserHomePage(driver);

		firstName = "ReportNG";
		lastName = "Screenshot";
		email = "reportngscreenshot.level15." + generateFakeNumber() + "@mail.com";
		password = "160622";
		
		// extent = ExtentManagerV3.createInstance();
	}

	@Test
	public void User_01_Register(Method method) {
		// test = extent.createTest("User_01_Register");

		// test.log(Status.INFO, "Register - Step 01: Navigate to Register page");
		userRegisterPage = userHomePage.clickOnRegisterLink();

		// test.log(Status.INFO, "Register - Step 02: Enter '" + firstName + "' into First Name text box");
		userRegisterPage.inputIntoFirstNameTextbox(firstName);

		// test.log(Status.INFO, "Register - Step 03: Enter '" + lastName + "' into Last Name text box");
		userRegisterPage.inputIntoLastNameTextbox(lastName);

		// test.log(Status.INFO, "Register - Step 04: Enter '" + email + "' into Email text box");
		userRegisterPage.inputIntoEmailTextbox(email);

		// test.log(Status.INFO, "Register - Step 05: Enter '" + password + "' into Password text box");
		userRegisterPage.inputIntoPasswordTextbox(password);

		// test.log(Status.INFO, "Register - Step 06: Enter '" + password + "' into Confirm Password text box");
		userRegisterPage.inputIntoConfirmPasswordTextbox(password);

		// test.log(Status.INFO, "Register - Step 07: Click on Register button");
		userRegisterPage.clickOnRegisterButton();

		// test.log(Status.INFO, "Register - Step 08: Verify registration success message is displayed");
		Assert.assertEquals(userRegisterPage.getRegisterSuccessMessage(), "Your registration completed");
	}

	@Test
	public void User_02_Login(Method method) {
		// test = extent.createTest("User_02_Login");

		// test.log(Status.INFO, "Login - Step 01: Navigate to Login page");
		userHomePage = userRegisterPage.clickOnLogoutLink();
		userLoginPage = userHomePage.clickOnLoginLink();

		// test.log(Status.INFO, "Login - Step 02: Enter '" + email + "' into Email text box");
		userLoginPage.inputIntoEmailTextbox(email);

		// test.log(Status.INFO, "Login - Step 03: Enter '" + password + "' into Password text box");
		userLoginPage.inputIntoPasswordTextbox(password);

		// test.log(Status.INFO, "Login - Step 04: Click on Login button");
		userHomePage = userLoginPage.clickOnLoginButton();

		// test.log(Status.INFO, "Login - Step 05: Verify My Account link is displayed");
		Assert.assertFalse(userHomePage.isMyAccountLinkDisplayed());

		// test.log(Status.INFO, "Login - Step 06: Navigate to My Account page");
		userCustomerInfoPage = userHomePage.clickOnMyAccountLink();

		// test.log(Status.INFO, "Login - Step 07: Verify Customer Info page is displayed");
		Assert.assertFalse(userCustomerInfoPage.isCustomerInfoHeaderDisplayed());
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	private WebDriver driver;
	private UserHomePageObject userHomePage;
	private UserRegisterPageObject userRegisterPage;
	private UserLoginPageObject userLoginPage;
	private UserCustomerInfoPageObject userCustomerInfoPage;
	private String firstName, lastName, email, password;
	// private ExtentTest test;
	// private ExtentReports extent;

}