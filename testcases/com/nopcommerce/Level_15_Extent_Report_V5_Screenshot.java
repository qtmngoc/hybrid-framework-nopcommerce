package com.nopcommerce;

import java.lang.reflect.Method;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import commons.nopCommerce.NcBaseTest;
import commons.nopCommerce.NcPageGeneratorManager;
import pageObjects.nopCommerce.NcUserCustomerInfoPO;
import pageObjects.nopCommerce.NcUserHomePO;
import pageObjects.nopCommerce.NcUserLoginPO;
import pageObjects.nopCommerce.NcUserRegisterPO;
import reportConfig.nopCommerce.NcExtentTestManagerV5;

public class Level_15_Extent_Report_V5_Screenshot extends NcBaseTest {

	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = getBrowserDriver(browserName);
		userHomePage = NcPageGeneratorManager.getUserHomePage(driver);

		firstName = "Extent Report";
		lastName = "Screenshot";
		email = "extentreportv5.level15." + generateFakeNumber() + "@mail.com";
		password = "190622";
	}

	@Test
	public void User_01_Register(Method method) {
		NcExtentTestManagerV5.startTest(method.getName(), "User_01_Register");

		NcExtentTestManagerV5.getTest().log(Status.INFO, "Register - Step 01: Navigate to Register page");
		userRegisterPage = userHomePage.clickOnRegisterLink();

		NcExtentTestManagerV5.getTest().log(Status.INFO, "Register - Step 02: Enter '" + firstName + "' into First Name text box");
		userRegisterPage.inputIntoFirstNameTextbox(firstName);

		NcExtentTestManagerV5.getTest().log(Status.INFO, "Register - Step 03: Enter '" + lastName + "' into Last Name text box");
		userRegisterPage.inputIntoLastNameTextbox(lastName);

		NcExtentTestManagerV5.getTest().log(Status.INFO, "Register - Step 04: Enter '" + email + "' into Email text box");
		userRegisterPage.inputIntoEmailTextbox(email);

		NcExtentTestManagerV5.getTest().log(Status.INFO, "Register - Step 05: Enter '" + password + "' into Password text box");
		userRegisterPage.inputIntoPasswordTextbox(password);

		NcExtentTestManagerV5.getTest().log(Status.INFO, "Register - Step 06: Enter '" + password + "' into Confirm Password text box");
		userRegisterPage.inputIntoConfirmPasswordTextbox(password);

		NcExtentTestManagerV5.getTest().log(Status.INFO, "Register - Step 07: Click on Register button");
		userRegisterPage.clickOnRegisterButton();

		NcExtentTestManagerV5.getTest().log(Status.INFO, "Register - Step 08: Verify registration success message is displayed");
		Assert.assertEquals(userRegisterPage.getRegisterSuccessMessage(), "Your registration completed.");
	}

	@Test
	public void User_02_Login(Method method) {
		NcExtentTestManagerV5.startTest(method.getName(), "User_02_Login");

		NcExtentTestManagerV5.getTest().log(Status.INFO, "Login - Step 01: Navigate to Login page");
		userLoginPage = userRegisterPage.clickOnLoginLink();

		NcExtentTestManagerV5.getTest().log(Status.INFO, "Login - Step 02: Enter '" + email + "' into Email text box");
		userLoginPage.inputIntoEmailTextbox(email);

		NcExtentTestManagerV5.getTest().log(Status.INFO, "Login - Step 03: Enter '" + password + "' into Password text box");
		userLoginPage.inputIntoPasswordTextbox(password);

		NcExtentTestManagerV5.getTest().log(Status.INFO, "Login - Step 04: Click on Login button");
		userHomePage = userLoginPage.clickOnLoginButton();

		NcExtentTestManagerV5.getTest().log(Status.INFO, "Login - Step 05: Verify My Account link is displayed");
		Assert.assertFalse(userHomePage.isMyAccountLinkDisplayed());

		NcExtentTestManagerV5.getTest().log(Status.INFO, "Login - Step 06: Navigate to My Account page");
		userCustomerInfoPage = userHomePage.clickOnMyAccountLink();

		NcExtentTestManagerV5.getTest().log(Status.INFO, "Login - Step 07: Verify Customer Info page is displayed");
		Assert.assertFalse(userCustomerInfoPage.isCustomerInfoHeaderDisplayed());
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