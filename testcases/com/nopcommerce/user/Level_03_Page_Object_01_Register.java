package com.nopcommerce.user;

import java.io.File;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pageObject.nopCommerce.HomePageObject;
import pageObject.nopCommerce.RegisterPageObject;

public class Level_03_Page_Object_01_Register {
	private WebDriver driver;
	private HomePageObject homePage;
	private RegisterPageObject registerPage;
	private String firstName, lastName, password, emailAddress;

	private String projectPath = System.getProperty("user.dir");
	private String separatorChar = File.separator;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", projectPath + separatorChar + "browserDrivers" + separatorChar + "chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
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

	@Test
	public void Register_04_Existing_Email() {
		System.out.println("Register_04 - Step 01: Click on Register link");
		homePage.clickOnRegisterLink();

		registerPage = new RegisterPageObject(driver);

		System.out.println("Register_04 - Step 02: Input into required fields");
		registerPage.inputIntoFirstNameTextbox(firstName);
		registerPage.inputIntoLastNameTextbox(lastName);
		registerPage.inputIntoEmailTextbox(emailAddress);
		registerPage.inputIntoPasswordTextbox(password);
		registerPage.inputIntoConfirmPasswordTextbox(password);

		System.out.println("Register_04 - Step 03: Click on Register button");
		registerPage.clickOnRegisterButton();

		System.out.println("Register_04 - Step 04: Verify error message of existing email displayed");
		Assert.assertEquals(registerPage.getErrorMessageExistingEmail(), "The specified email already exists");
	}

	@Test
	public void Register_05_Password_Less_Than_6_Characters() {
		System.out.println("Register_05 - Step 01: Click on Register link");
		homePage.clickOnRegisterLink();

		registerPage = new RegisterPageObject(driver);

		System.out.println("Register_05 - Step 02: Input into required fields");
		registerPage.inputIntoFirstNameTextbox(firstName);
		registerPage.inputIntoLastNameTextbox(lastName);
		registerPage.inputIntoEmailTextbox(emailAddress);
		registerPage.inputIntoPasswordTextbox("123");
		registerPage.inputIntoConfirmPasswordTextbox("123");

		System.out.println("Register_05 - Step 03: Click on Register button");
		registerPage.clickOnRegisterButton();

		System.out.println("Register_05 - Step 04: Verify error message displayed");
		Assert.assertEquals(registerPage.getErrorMessageBelowPasswordTextbox(), "Password must meet the following rules:\nmust have at least 6 characters");
	}

	@Test
	public void Register_06_Invalid_Confirm_Password() {
		System.out.println("Register_06 - Step 01: Click on Register link");
		homePage.clickOnRegisterLink();

		registerPage = new RegisterPageObject(driver);

		System.out.println("Register_06 - Step 02: Input into required fields");
		registerPage.inputIntoFirstNameTextbox(firstName);
		registerPage.inputIntoLastNameTextbox(lastName);
		registerPage.inputIntoEmailTextbox(emailAddress);
		registerPage.inputIntoPasswordTextbox(password);
		registerPage.inputIntoConfirmPasswordTextbox(emailAddress);

		System.out.println("Register_06 - Step 03: Click on Register button");
		registerPage.clickOnRegisterButton();

		System.out.println("Register_06 - Step 04: Verify error message displayed");
		Assert.assertEquals(registerPage.getErrorMessageBelowConfirmPasswordTextbox(), "The password and confirmation password do not match.");
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