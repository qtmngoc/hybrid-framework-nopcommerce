package com.nopcommerce.user;

import java.lang.reflect.Method;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import commons.nopCommerce.BaseTest;
import commons.nopCommerce.PageGeneratorManager;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import pageObjects.nopCommerce.user.UserCustomerInfoPageObject;
import pageObjects.nopCommerce.user.UserHomePageObject;
import pageObjects.nopCommerce.user.UserLoginPageObject;
import pageObjects.nopCommerce.user.UserRegisterPageObject;
import reportConfig.nopCommerce.ExtentTestManagerV5;

public class Level_15_Allure_Report_Screenshot extends BaseTest {

	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = getBrowserDriver(browserName);
		userHomePage = PageGeneratorManager.getUserHomePage(driver);

		firstName = "Extent Report";
		lastName = "Screenshot";
		email = "extentreportv5.level15." + generateFakeNumber() + "@mail.com";
		password = "190622";
	}

	@Description("User_01_Register")
	@Story("Register to system")
	@Severity(SeverityLevel.NORMAL)
	@Test
	public void User_01_Register(Method method) {

		userRegisterPage = userHomePage.clickOnRegisterLink();

		userRegisterPage.inputIntoFirstNameTextbox(firstName);

		userRegisterPage.inputIntoLastNameTextbox(lastName);

		userRegisterPage.inputIntoEmailTextbox(email);

		userRegisterPage.inputIntoPasswordTextbox(password);

		userRegisterPage.inputIntoConfirmPasswordTextbox(password);

		userRegisterPage.clickOnRegisterButton();

		Assert.assertEquals(userRegisterPage.getRegisterSuccessMessage(), "Your registration completed");
	}
	
	@Description("User_02_Login")
	@Story("Login to system")
	@Severity(SeverityLevel.NORMAL)
	@Test
	public void User_02_Login(Method method) {

		userHomePage = userRegisterPage.clickOnLogoutLink();
		userLoginPage = userHomePage.clickOnLoginLink();

		ExtentTestManagerV5.getTest().log(Status.INFO, "Login - Step 02: Enter '" + email + "' into Email text box");
		userLoginPage.inputIntoEmailTextbox(email);

		ExtentTestManagerV5.getTest().log(Status.INFO, "Login - Step 03: Enter '" + password + "' into Password text box");
		userLoginPage.inputIntoPasswordTextbox(password);

		ExtentTestManagerV5.getTest().log(Status.INFO, "Login - Step 04: Click on Login button");
		userHomePage = userLoginPage.clickOnLoginButton();

		ExtentTestManagerV5.getTest().log(Status.INFO, "Login - Step 05: Verify My Account link is displayed");
		Assert.assertFalse(userHomePage.isMyAccountLinkDisplayed());

		ExtentTestManagerV5.getTest().log(Status.INFO, "Login - Step 06: Navigate to My Account page");
		userCustomerInfoPage = userHomePage.clickOnMyAccountLink();

		ExtentTestManagerV5.getTest().log(Status.INFO, "Login - Step 07: Verify Customer Info page is displayed");
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

}