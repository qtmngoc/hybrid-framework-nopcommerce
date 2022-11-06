package com.nopcommerce;

import java.lang.reflect.Method;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.nopCommerce.NcBaseTest;
import commons.nopCommerce.NcPageGeneratorManager;
import pageObjects.nopCommerce.NcUserCustomerInfoPO;
import pageObjects.nopCommerce.NcUserHomePO;
import pageObjects.nopCommerce.NcUserLoginPO;
import pageObjects.nopCommerce.NcUserRegisterPO;

public class Level_15_Extent_Report_V4 extends NcBaseTest {

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
	private NcUserHomePO userHomePage;
	private NcUserRegisterPO userRegisterPage;
	private NcUserLoginPO userLoginPage;
	private NcUserCustomerInfoPO userCustomerInfoPage;
	private String firstName, lastName, email, password;
	
}