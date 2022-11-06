package com.nopcommerce;

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

public class Level_14_ReportNG_Screenshot extends NcBaseTest {
	
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
	public void User_01_Register() {
		log.info("Register - Step 01: Navigate to Register page");
		userRegisterPage = userHomePage.clickOnRegisterLink();

		log.info("Register - Step 02: Enter '" + firstName + "' into First Name text box");
		userRegisterPage.inputIntoFirstNameTextbox(firstName);
		
		log.info("Register - Step 03: Enter '" + lastName + "' into Last Name text box");
		userRegisterPage.inputIntoLastNameTextbox(lastName);
		
		log.info("Register - Step 04: Enter '" + email + "' into Email text box");
		userRegisterPage.inputIntoEmailTextbox(email);
		
		log.info("Register - Step 05: Enter '" + password + "' into Password text box");
		userRegisterPage.inputIntoPasswordTextbox(password);
		
		log.info("Register - Step 06: Enter '" + password + "' into Confirm Password text box");
		userRegisterPage.inputIntoConfirmPasswordTextbox(password);
		
		log.info("Register - Step 07: Click on Register button");
		userRegisterPage.clickOnRegisterButton();
		
		log.info("Register - Step 08: Verify registration success message is displayed");
		Assert.assertEquals(userRegisterPage.getRegisterSuccessMessage(), "Your registration completed...");
	}
	
	@Test
	public void User_02_Login() {
		userHomePage = userRegisterPage.clickOnLogoutLink();
		
		log.info("Login - Step 01: Navigate to Login page");
		userLoginPage = userHomePage.clickOnLoginLink();
		
		log.info("Login - Step 02: Enter '" + email + "' into Email text box");
		userLoginPage.inputIntoEmailTextbox(email);
		
		log.info("Login - Step 03: Enter '" + password + "' into Password text box");
		userLoginPage.inputIntoPasswordTextbox(password);

		log.info("Login - Step 04: Click on Login button");
		userHomePage = userLoginPage.clickOnLoginButton();
		
		log.info("Login - Step 05: Verify My Account link is displayed");
		Assert.assertFalse(userHomePage.isMyAccountLinkDisplayed());
		
		log.info("Login - Step 06: Navigate to My Account page");
		userCustomerInfoPage = userHomePage.clickOnMyAccountLink();
		
		log.info("Login - Step 07: Verify Customer Info page is displayed");
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