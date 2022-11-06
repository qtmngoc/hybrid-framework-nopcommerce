package com.nopcommerce;

import java.io.File;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pageObjects.nopCommerce.NcUserHomePO;
import pageObjects.nopCommerce.NcUserLoginPO;
import pageObjects.nopCommerce.NcUserRegisterPO;

public class Level_03_Page_Object_02_Login {
	private WebDriver driver;
	private NcUserHomePO homePage;
	private NcUserRegisterPO registerPage;
	private NcUserLoginPO loginPage;
	private String firstName, lastName, invalidEmail, nonExistentEmail, existingEmail, correctPassword, incorrectPassword;

	private String projectPath = System.getProperty("user.dir");
	private String separatorChar = File.separator;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + separatorChar + "browserDrivers" + separatorChar + "geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.get("https://demo.nopcommerce.com/");

		homePage = new NcUserHomePO(driver);

		firstName = "Page";
		lastName = "Object";
		invalidEmail = "invalidemail@123#321";
		nonExistentEmail = "nonexistingemail" + generateFakeNumber() + "@mail.com";
		existingEmail = "level3" + generateFakeNumber() + "@mail.vn";
		correctPassword = "123456";
		incorrectPassword = "19042022";

		// Register account
		System.out.println("Pre-condition - Step 01: Click on Register link");
		homePage.clickOnRegisterLink();

		registerPage = new NcUserRegisterPO(driver);

		System.out.println("Pre-condition - Step 02: Input into required fields");
		registerPage.inputIntoFirstNameTextbox(firstName);
		registerPage.inputIntoLastNameTextbox(lastName);
		registerPage.inputIntoEmailTextbox(existingEmail);
		registerPage.inputIntoPasswordTextbox(correctPassword);
		registerPage.inputIntoConfirmPasswordTextbox(correctPassword);

		System.out.println("Pre-condition - Step 03: Click on Register button");
		registerPage.clickOnRegisterButton();

		System.out.println("Pre-condition - Step 04: Verify success message displayed");
		Assert.assertEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed");

		System.out.println("Pre-condition - Step 05: Click on Logout link");
		registerPage.clickOnLogoutLink();

		homePage = new NcUserHomePO(driver);
	}

	@Test
	public void Login_01_Empty_Data() {
		System.out.println("Login_01 - Step 01: Click on Login link");
		homePage.clickOnLoginLink();

		loginPage = new NcUserLoginPO(driver);

		System.out.println("Login_01 - Step 02: Click on Login button");
		loginPage.clickOnLoginButton();

		System.out.println("Login_01 - Step 03: Verify error message displayed");
		Assert.assertEquals(loginPage.getErrorMessageBelowEmailTextbox(), "Please enter your email");
	}

	@Test
	public void Login_02_Invalid_Email() {
		System.out.println("Login_02 - Step 01: Click on Login link");
		homePage.clickOnLoginLink();

		loginPage = new NcUserLoginPO(driver);

		System.out.println("Login_02 - Step 02: Input invalid email");
		loginPage.inputIntoEmailTextbox(invalidEmail);

		System.out.println("Login_02 - Step 03: Click on Login button");
		loginPage.clickOnLoginButton();

		System.out.println("Login_02 - Step 04: Verify error message displayed");
		Assert.assertEquals(loginPage.getErrorMessageBelowEmailTextbox(), "Wrong email");
	}

	@Test
	public void Login_03_Email_Not_Found() {
		System.out.println("Login_03 - Step 01: Click on Login link");
		homePage.clickOnLoginLink();

		loginPage = new NcUserLoginPO(driver);

		System.out.println("Login_03 - Step 02: Input non-existent e-mail and password");
		loginPage.inputIntoEmailTextbox(nonExistentEmail);
		loginPage.inputIntoPasswordTextbox(correctPassword);

		System.out.println("Login_03 - Step 03: Click on Login button");
		loginPage.clickOnLoginButton();

		System.out.println("Login_03 - Step 04: Verify login unsuccessful message displayed");
		Assert.assertEquals(loginPage.getLoginUnsuccessMessage(), "Login was unsuccessful. Please correct the errors and try again.\nNo customer account found");
	}

	@Test
	public void Login_04_Existing_Email_And_Empty_Password() {
		System.out.println("Login_04 - Step 01: Click on Login link");
		homePage.clickOnLoginLink();

		loginPage = new NcUserLoginPO(driver);

		System.out.println("Login_04 - Step 02: Input existing e-mail and empty password");
		loginPage.inputIntoEmailTextbox(existingEmail);

		System.out.println("Login_04 - Step 03: Click on Login button");
		loginPage.clickOnLoginButton();

		System.out.println("Login_04 - Step 04: Verify login unsuccessful message displayed");
		Assert.assertEquals(loginPage.getLoginUnsuccessMessage(), "Login was unsuccessful. Please correct the errors and try again.\nThe credentials provided are incorrect");
	}

	@Test
	public void Login_05_Existing_Email_And_Incorrect_Password() {
		System.out.println("Login_05 - Step 01: Click on Login link");
		homePage.clickOnLoginLink();

		loginPage = new NcUserLoginPO(driver);

		System.out.println("Login_05 - Step 02: Input existing e-mail and incorrect password");
		loginPage.inputIntoEmailTextbox(existingEmail);
		loginPage.inputIntoPasswordTextbox(incorrectPassword);

		System.out.println("Login_05 - Step 03: Click on Login button");
		loginPage.clickOnLoginButton();

		System.out.println("Login_05 - Step 04: Verify login unsuccessful message displayed");
		Assert.assertEquals(loginPage.getLoginUnsuccessMessage(), "Login was unsuccessful. Please correct the errors and try again.\nThe credentials provided are incorrect");
	}

	@Test
	public void Login_06_Success() {
		System.out.println("Login_06 - Step 01: Click on Login link");
		homePage.clickOnLoginLink();

		loginPage = new NcUserLoginPO(driver);

		System.out.println("Login_06 - Step 02: Input existing e-mail and correct password");
		loginPage.inputIntoEmailTextbox(existingEmail);
		loginPage.inputIntoPasswordTextbox(correctPassword);

		System.out.println("Login_06 - Step 03: Click on Login button");
		loginPage.clickOnLoginButton();

		homePage = new NcUserHomePO(driver);
		System.out.println("Login_06 - Step 04: Verify My account and Log out links are displayed");
		Assert.assertTrue(homePage.isMyAccountLinkDisplayed());
		Assert.assertTrue(homePage.isLogoutLinkDisplayed());
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	public int generateFakeNumber() {
		Random rand = new Random();
		return rand.nextInt(9999);
	}
}