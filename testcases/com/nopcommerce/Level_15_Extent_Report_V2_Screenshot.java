package com.nopcommerce;

import java.lang.reflect.Method;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

// import com.relevantcodes.extentreports.LogStatus;

import commons.nopCommerce.NcBaseTest;
import commons.nopCommerce.NcPageGeneratorManager;
import pageObjects.nopCommerce.NcUserCustomerInfoPO;
import pageObjects.nopCommerce.NcUserHomePO;
import pageObjects.nopCommerce.NcUserLoginPO;
import pageObjects.nopCommerce.NcUserRegisterPO;

public class Level_15_Extent_Report_V2_Screenshot extends NcBaseTest {
	
	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = getBrowserDriver(browserName);
		userHomePage = NcPageGeneratorManager.getUserHomePage(driver);

		firstName = "ReportNG";
		lastName = "Screenshot";
		email = "reportngscreenshot.level15." + generateFakeNumber() + "@mail.com";
		password = "160622";
	}

	@Test
	public void User_01_Register(Method method) {
		// ExtentTestManagerV2.startTest(method.getName(), "User_01_Register");
		
		// ExtentTestManagerV2.getTest().log(LogStatus.INFO, "Register - Step 01: Navigate to Register page");
		userRegisterPage = userHomePage.clickOnRegisterLink();

		// ExtentTestManagerV2.getTest().log(LogStatus.INFO, "Register - Step 02: Enter '" + firstName + "' into First Name text box");
		userRegisterPage.inputIntoFirstNameTextbox(firstName);
		
		// ExtentTestManagerV2.getTest().log(LogStatus.INFO, "Register - Step 03: Enter '" + lastName + "' into Last Name text box");
		userRegisterPage.inputIntoLastNameTextbox(lastName);
		
		// ExtentTestManagerV2.getTest().log(LogStatus.INFO, "Register - Step 04: Enter '" + email + "' into Email text box");
		userRegisterPage.inputIntoEmailTextbox(email);
		
		// ExtentTestManagerV2.getTest().log(LogStatus.INFO, "Register - Step 05: Enter '" + password + "' into Password text box");
		userRegisterPage.inputIntoPasswordTextbox(password);
		
		// ExtentTestManagerV2.getTest().log(LogStatus.INFO, "Register - Step 06: Enter '" + password + "' into Confirm Password text box");
		userRegisterPage.inputIntoConfirmPasswordTextbox(password);
		
		// ExtentTestManagerV2.getTest().log(LogStatus.INFO, "Register - Step 07: Click on Register button");
		userRegisterPage.clickOnRegisterButton();
		
		// ExtentTestManagerV2.getTest().log(LogStatus.INFO, "Register - Step 08: Verify registration success message is displayed");
		Assert.assertEquals(userRegisterPage.getRegisterSuccessMessage(), "Your registration completed...");
		
		// ExtentTestManagerV2.endTest();
	}
	
	@Test
	public void User_02_Login(Method method) {
		// ExtentTestManagerV2.startTest(method.getName(), "User_02_Login");
		
		// ExtentTestManagerV2.getTest().log(LogStatus.INFO, "Login - Step 01: Navigate to Login page");
		userHomePage = userRegisterPage.clickOnLogoutLink();
		userLoginPage = userHomePage.clickOnLoginLink();
		
		// ExtentTestManagerV2.getTest().log(LogStatus.INFO, "Login - Step 02: Enter '" + email + "' into Email text box");
		userLoginPage.inputIntoEmailTextbox(email);
		
		// ExtentTestManagerV2.getTest().log(LogStatus.INFO, "Login - Step 03: Enter '" + password + "' into Password text box");
		userLoginPage.inputIntoPasswordTextbox(password);

		// ExtentTestManagerV2.getTest().log(LogStatus.INFO, "Login - Step 04: Click on Login button");
		userHomePage = userLoginPage.clickOnLoginButton();
		
		// ExtentTestManagerV2.getTest().log(LogStatus.INFO, "Login - Step 05: Verify My Account link is displayed");
		Assert.assertFalse(userHomePage.isMyAccountLinkDisplayed());
		
		// ExtentTestManagerV2.getTest().log(LogStatus.INFO, "Login - Step 06: Navigate to My Account page");
		userCustomerInfoPage = userHomePage.clickOnMyAccountLink();
		
		// ExtentTestManagerV2.getTest().log(LogStatus.INFO, "Login - Step 07: Verify Customer Info page is displayed");
		Assert.assertFalse(userCustomerInfoPage.isCustomerInfoHeaderDisplayed());
		
		// ExtentTestManagerV2.endTest();
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	private WebDriver driver;
	private NcUserHomePO userHomePage;
	private NcUserRegisterPO userRegisterPage;
	private NcUserLoginPO userLoginPage;
	private NcUserCustomerInfoPO userCustomerInfoPage;
	private String firstName, lastName, email, password;
	
}