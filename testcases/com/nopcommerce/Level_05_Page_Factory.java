package com.nopcommerce;

import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.nopCommerce.NcBaseTest;
import pageFactories.nopCommerce.NcHomePageFactory;
import pageFactories.nopCommerce.NcLoginPageFactory;
import pageFactories.nopCommerce.NcRegisterPageFactory;

public class Level_05_Page_Factory extends NcBaseTest {
	
	private WebDriver driver;
	private NcHomePageFactory homePage;
	private NcRegisterPageFactory registerPage;
	private NcLoginPageFactory loginPage;
	private String firstName, lastName, invalidEmail, nonExistentEmail, existingEmail, correctPassword, incorrectPassword;

	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = getBrowserDriver(browserName);
		driver.get("https://demo.nopcommerce.com/");

		homePage = new NcHomePageFactory(driver);

		firstName = "Page";
		lastName = "Object";
		invalidEmail = "invalidemail@123#321";
		nonExistentEmail = "nonexistingemail" + generateFakeNumber() + "@mail.com";
		existingEmail = "level5" + generateFakeNumber() + "@mail.vn";
		correctPassword = "123456";
		incorrectPassword = "19042022";

		// Register 
		homePage.clickOnRegisterLink();

		registerPage = new NcRegisterPageFactory(driver);

		registerPage.inputIntoFirstNameTextbox(firstName);
		registerPage.inputIntoLastNameTextbox(lastName);
		registerPage.inputIntoEmailTextbox(existingEmail);
		registerPage.inputIntoPasswordTextbox(correctPassword);
		registerPage.inputIntoConfirmPasswordTextbox(correctPassword);

		registerPage.clickOnRegisterButton();

		Assert.assertEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed");

		registerPage.clickOnLogoutLink();

		homePage = new NcHomePageFactory(driver);
	}

	@Test
	public void Login_01_Empty_Data() {
		homePage.clickOnLoginLink();

		loginPage = new NcLoginPageFactory(driver);

		loginPage.clickOnLoginButton();

		Assert.assertEquals(loginPage.getErrorMessageBelowEmailTextbox(), "Please enter your email");
	}

	@Test
	public void Login_02_Invalid_Email() {
		homePage.clickOnLoginLink();

		loginPage = new NcLoginPageFactory(driver);

		loginPage.inputIntoEmailTextbox(invalidEmail);

		loginPage.clickOnLoginButton();

		Assert.assertEquals(loginPage.getErrorMessageBelowEmailTextbox(), "Wrong email");
	}

	@Test
	public void Login_03_Email_Not_Found() {
		homePage.clickOnLoginLink();

		loginPage = new NcLoginPageFactory(driver);

		loginPage.inputIntoEmailTextbox(nonExistentEmail);
		loginPage.inputIntoPasswordTextbox(correctPassword);

		loginPage.clickOnLoginButton();

		Assert.assertEquals(loginPage.getLoginUnsuccessMessage(), "Login was unsuccessful. Please correct the errors and try again.\nNo customer account found");
	}

	@Test
	public void Login_04_Existing_Email_And_Empty_Password() {
		homePage.clickOnLoginLink();

		loginPage = new NcLoginPageFactory(driver);

		loginPage.inputIntoEmailTextbox(existingEmail);

		loginPage.clickOnLoginButton();

		Assert.assertEquals(loginPage.getLoginUnsuccessMessage(), "Login was unsuccessful. Please correct the errors and try again.\nThe credentials provided are incorrect");
	}

	@Test
	public void Login_05_Existing_Email_And_Incorrect_Password() {
		homePage.clickOnLoginLink();

		loginPage = new NcLoginPageFactory(driver);

		loginPage.inputIntoEmailTextbox(existingEmail);
		loginPage.inputIntoPasswordTextbox(incorrectPassword);

		loginPage.clickOnLoginButton();

		Assert.assertEquals(loginPage.getLoginUnsuccessMessage(), "Login was unsuccessful. Please correct the errors and try again.\nThe credentials provided are incorrect");
	}

	@Test
	public void Login_06_Success() {
		homePage.clickOnLoginLink();

		loginPage = new NcLoginPageFactory(driver);

		loginPage.inputIntoEmailTextbox(existingEmail);
		loginPage.inputIntoPasswordTextbox(correctPassword);

		loginPage.clickOnLoginButton();

		homePage = new NcHomePageFactory(driver);
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