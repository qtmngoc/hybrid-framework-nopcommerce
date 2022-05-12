package com.nopcommerce.user;

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

public class Level_06_Page_Generater_Manager extends BaseTest {
	
	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = getBrowserDriver(browserName);
		driver.get("https://demo.nopcommerce.com/");

		homePage = PageGeneratorManager.getUserHomePage(driver);

		firstName = "Page";
		lastName = "Object";
		invalidEmail = "invalidemail@123#321";
		nonExistentEmail = "nonexistingemail" + generateFakeNumber() + "@mail.com";
		existingEmail = "level3" + generateFakeNumber() + "@mail.vn";
		correctPassword = "123456";
		incorrectPassword = "19042022";
		companyName = "Automation FC";

		// Register
		registerPage = homePage.clickOnRegisterLink();

		registerPage.inputIntoFirstNameTextbox(firstName);
		registerPage.inputIntoLastNameTextbox(lastName);
		registerPage.inputIntoEmailTextbox(existingEmail);
		registerPage.inputIntoPasswordTextbox(correctPassword);
		registerPage.inputIntoConfirmPasswordTextbox(correctPassword);
		registerPage.clickOnRegisterButton();
		Assert.assertEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed");

		homePage = registerPage.clickOnLogoutLink();
	}

	@Test
	public void Login_01_Empty_Data() {
		loginPage = homePage.clickOnLoginLink();

		loginPage.clickOnLoginButton();
		Assert.assertEquals(loginPage.getErrorMessageBelowEmailTextbox(), "Please enter your email");
	}

	@Test
	public void Login_02_Invalid_Email() {
		loginPage = homePage.clickOnLoginLink();

		loginPage.inputIntoEmailTextbox(invalidEmail);
		loginPage.clickOnLoginButton();
		Assert.assertEquals(loginPage.getErrorMessageBelowEmailTextbox(), "Wrong email");
	}

	@Test
	public void Login_03_Email_Not_Found() {
		loginPage = homePage.clickOnLoginLink();

		loginPage.inputIntoEmailTextbox(nonExistentEmail);
		loginPage.inputIntoPasswordTextbox(correctPassword);
		loginPage.clickOnLoginButton();
		Assert.assertEquals(loginPage.getLoginUnsuccessMessage(), "Login was unsuccessful. Please correct the errors and try again.\nNo customer account found");
	}

	@Test
	public void Login_04_Existing_Email_And_Empty_Password() {
		loginPage = homePage.clickOnLoginLink();

		loginPage.inputIntoEmailTextbox(existingEmail);
		loginPage.clickOnLoginButton();
		Assert.assertEquals(loginPage.getLoginUnsuccessMessage(), "Login was unsuccessful. Please correct the errors and try again.\nThe credentials provided are incorrect");
	}

	@Test
	public void Login_05_Existing_Email_And_Incorrect_Password() {
		loginPage = homePage.clickOnLoginLink();

		loginPage.inputIntoEmailTextbox(existingEmail);
		loginPage.inputIntoPasswordTextbox(incorrectPassword);
		loginPage.clickOnLoginButton();
		Assert.assertEquals(loginPage.getLoginUnsuccessMessage(), "Login was unsuccessful. Please correct the errors and try again.\nThe credentials provided are incorrect");
	}

	@Test
	public void Login_06_Success() {
		loginPage = homePage.clickOnLoginLink();

		loginPage.inputIntoEmailTextbox(existingEmail);
		loginPage.inputIntoPasswordTextbox(correctPassword);

		homePage = loginPage.clickOnLoginButton();

		Assert.assertTrue(homePage.isMyAccountLinkDisplayed());
		Assert.assertTrue(homePage.isLogoutLinkDisplayed());
		
		myAccountPage = homePage.clickOnMyAccountLink();
		
		myAccountPage.inputIntoCompanyNameTextbox(companyName);
		myAccountPage.clickOnNewsletterCheckbox();
		myAccountPage.clickOnSaveButton();
		Assert.assertEquals(myAccountPage.getCompanyName(), companyName);
		Assert.assertTrue(myAccountPage.isNewsletterCheckboxSelected());
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
	private WebDriver driver;
	private UserHomePageObject homePage;
	private UserRegisterPageObject registerPage;
	private UserLoginPageObject loginPage;
	private UserCustomerInfoPageObject myAccountPage;
	private String firstName, lastName, invalidEmail, nonExistentEmail, existingEmail, correctPassword, incorrectPassword, companyName;
	
}