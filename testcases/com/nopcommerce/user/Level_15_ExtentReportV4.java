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

public class Level_15_ExtentReportV4 extends BaseTest {

	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = getBrowserDriver(browserName);
		userHomePage = PageGeneratorManager.getUserHomePage(driver);

		firstName = "ReportNG";
		lastName = "Screenshot";
		email = "reportngscreenshot.level15." + generateFakeNumber() + "@mail.com";
		password = "160622";
		
	}

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

	@Test
	public void User_02_Login(Method method) {
		userHomePage = userRegisterPage.clickOnLogoutLink();
		userLoginPage = userHomePage.clickOnLoginLink();

		userLoginPage.inputIntoEmailTextbox(email);

		userLoginPage.inputIntoPasswordTextbox(password);

		userHomePage = userLoginPage.clickOnLoginButton();

		Assert.assertFalse(userHomePage.isMyAccountLinkDisplayed());

		userCustomerInfoPage = userHomePage.clickOnMyAccountLink();

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