package com.nopcommerce.common;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import commons.nopCommerce.NcBaseTest;
import commons.nopCommerce.NcPageGeneratorManager;
import pageObjects.nopCommerce.NcUserHomePO;
import pageObjects.nopCommerce.NcUserRegisterPO;

public class Common_01_Register_End_User extends NcBaseTest {

	@Parameters("browser")
	@BeforeTest(description = "Create new user for all classes test")
	public void Register(String browserName) {
		driver = getBrowserDriver(browserName);
		userHomePage = NcPageGeneratorManager.getUserHomePage(driver);

		firstName = "Automation";
		lastName = "Tester";
		email = "automation.tester." + generateFakeNumber() + "@mail.com";
		password = "26032017";
		
		log.info("Pre-condition - Step 01: Navigate to Register page");
		userRegisterPage = userHomePage.clickOnRegisterLink();

		log.info("Pre-condition - Step 02: Enter '" + firstName + "' into First Name text box");
		userRegisterPage.inputIntoFirstNameTextbox(firstName);

		log.info("Pre-condition - Step 03: Enter '" + lastName + "' into Last Name text box");
		userRegisterPage.inputIntoLastNameTextbox(lastName);

		log.info("Pre-condition - Step 04: Enter '" + email + "' into Email text box");
		userRegisterPage.inputIntoEmailTextbox(email);

		log.info("Pre-condition - Step 05: Enter '" + password + "' into Password text box");
		userRegisterPage.inputIntoPasswordTextbox(password);

		log.info("Pre-condition - Step 06: Enter '" + password + "' into Confirm Password text box");
		userRegisterPage.inputIntoConfirmPasswordTextbox(password);

		log.info("Pre-condition - Step 07: Click on Register button");
		userRegisterPage.clickOnRegisterButton();

		log.info("Pre-condition - Step 08: Verify registration success message is displayed");
		verifyEquals(userRegisterPage.getRegisterSuccessMessage(), "Your registration completed");
		
		log.info("Pre-condition - Step 09: Click on Logout link");
		userHomePage = userRegisterPage.clickOnLogoutLink();
		
		driver.quit();
	}

	private WebDriver driver;
	private NcUserHomePO userHomePage;
	private NcUserRegisterPO userRegisterPage;
	private String firstName, lastName;
	public static String email, password;

}