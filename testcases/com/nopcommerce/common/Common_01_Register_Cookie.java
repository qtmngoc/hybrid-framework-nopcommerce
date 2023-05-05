package com.nopcommerce.common;

import java.util.Set;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import commons.nopCommerce.NcBaseTest;
import commons.nopCommerce.NcPageGeneratorManager;
import pageObjects.nopCommerce.NcUserHomePO;
import pageObjects.nopCommerce.NcUserLoginPO;
import pageObjects.nopCommerce.NcUserRegisterPO;

public class Common_01_Register_Cookie extends NcBaseTest {

	@Parameters("browser")
	@BeforeTest(description = "Create new user for all classes test")
	public void Register(String browserName) {
		driver = getBrowserDriver(browserName);
		userHomePage = NcPageGeneratorManager.getUserHomePage(driver);

		firstName = "Automation";
		lastName = "Tester";
		email = "automation.cookie." + generateFakeNumber() + "@mail.com";
		password = "26032017";
		
		log.info("Create a user account - Step 01: Open Home page and navigate to Register page");
		userHomePage = NcPageGeneratorManager.getUserHomePage(driver);
		userRegisterPage = userHomePage.clickOnRegisterLink();

		log.info("Create a user account - Step 02: Enter '" + firstName + "' into First Name text box");
		userRegisterPage.inputIntoFirstNameTextbox(firstName);

		log.info("Create a user account - Step 03: Enter '" + lastName + "' into Last Name text box");
		userRegisterPage.inputIntoLastNameTextbox(lastName);

		log.info("Create a user account - Step 04: Enter '" + email + "' into Email text box");
		userRegisterPage.inputIntoEmailTextbox(email);

		log.info("Create a user account - Step 05: Enter '" + password + "' into Password text box");
		userRegisterPage.inputIntoPasswordTextbox(password);

		log.info("Create a user account - Step 06: Enter '" + password + "' into Confirm Password text box");
		userRegisterPage.inputIntoConfirmPasswordTextbox(password);

		log.info("Create a user account - Step 07: Click on Register button");
		userRegisterPage.clickOnRegisterButton();
		
		log.info("Create a user account - Step 08: Click on Log In link");
		userLoginPage = userRegisterPage.clickOnLoginLink();
		
		log.info("Create a user account - Step 09: Enter '" + email + "' into Email text box");
		userLoginPage.inputIntoEmailTextbox(email);
		
		log.info("Create a user account - Step 10: Enter '" + password + "' into Password text box");
		userLoginPage.inputIntoPasswordTextbox(password);
		
		log.info("Create a user account - Step 11: Click on Log In button");
		userHomePage = userLoginPage.clickOnLoginButton();
		
		log.info("Create a user account - Step 12: Get cookies");
		loggedCookies = userHomePage.getCookies(driver);
		
		driver.quit();
	}

	private WebDriver driver;
	private NcUserHomePO userHomePage;
	private NcUserRegisterPO userRegisterPage;
	private NcUserLoginPO userLoginPage;
	private String firstName, lastName, email, password;
	public static Set<Cookie> loggedCookies;

}