package com.nopcommerce.user;

import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import pageObjects.HomePageObject;
import pageObjects.RegisterPageObject;

public class Level_04_Multiple_Browsers extends BaseTest{
	private WebDriver driver;
	private HomePageObject homePage;
	private RegisterPageObject registerPage;
	private String firstName, lastName, password, emailAddress;

	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = getBrowserDriver(browserName);
		driver.get("https://demo.nopcommerce.com/");

		homePage = new HomePageObject(driver);

		firstName = "Page";
		lastName = "Object";
		password = "123456";
		emailAddress = "level3" + generateFakeNumber() + "@mail.vn";
	}

	@Test
	public void Register_01_Empty_Data() {
		System.out.println("Register_01 - Step 01: Click on Register link");
		homePage.clickOnRegisterLink();

		registerPage = new RegisterPageObject(driver);

		System.out.println("Register_01 - Step 02: Click on Register button");
		registerPage.clickOnRegisterButton();

		System.out.println("Register_01 - Step 03: Verify error messages displayed");
		Assert.assertEquals(registerPage.getErrorMessageBelowFirstNameTextbox(), "First name is required.");
		Assert.assertEquals(registerPage.getErrorMessageBelowLastNameTextbox(), "Last name is required.");
		Assert.assertEquals(registerPage.getErrorMessageBelowEmailTextbox(), "Email is required.");
		Assert.assertEquals(registerPage.getErrorMessageBelowPasswordTextbox(), "Password is required.");
		Assert.assertEquals(registerPage.getErrorMessageBelowConfirmPasswordTextbox(), "Password is required.");
	}

	@Test
	public void Register_02_Invalid_Email() {
		System.out.println("Register_02 - Step 01: Click on Register link");
		homePage.clickOnRegisterLink();

		registerPage = new RegisterPageObject(driver);

		System.out.println("Register_02 - Step 02: Input into required fields");
		registerPage.inputIntoFirstNameTextbox(firstName);
		registerPage.inputIntoLastNameTextbox(lastName);
		registerPage.inputIntoEmailTextbox("123@456#%*");
		registerPage.inputIntoPasswordTextbox(password);
		registerPage.inputIntoConfirmPasswordTextbox(password);

		System.out.println("Register_02 - Step 03: Click on Register button");
		registerPage.clickOnRegisterButton();

		System.out.println("Register_02 - Step 04: Verify error message displayed");
		Assert.assertEquals(registerPage.getErrorMessageBelowEmailTextbox(), "Wrong email");
	}

	@Test
	public void Register_03_Success() {
		System.out.println("Register_03 - Step 01: Click on Register link");
		homePage.clickOnRegisterLink();

		registerPage = new RegisterPageObject(driver);

		System.out.println("Register_03 - Step 02: Input into required fields");
		registerPage.inputIntoFirstNameTextbox(firstName);
		registerPage.inputIntoLastNameTextbox(lastName);
		registerPage.inputIntoEmailTextbox(emailAddress);
		registerPage.inputIntoPasswordTextbox(password);
		registerPage.inputIntoConfirmPasswordTextbox(password);

		System.out.println("Register_03 - Step 03: Click on Register button");
		registerPage.clickOnRegisterButton();

		System.out.println("Register_03 - Step 04: Verify registration successful message displayed");
		Assert.assertEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed");

		System.out.println("Register_03 - Step 05: Click on Logout link");
		registerPage.clickOnLogoutLink();
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