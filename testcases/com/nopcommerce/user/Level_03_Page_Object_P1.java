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

import pageObjects.HomePageObject;
import pageObjects.RegisterPageObject;

public class Level_03_Page_Object_P1{
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
		registerPage = new RegisterPageObject(driver);
		
		firstName = "Page";
		lastName = "Object";
		password = "123456";
		emailAddress = "level3" + generateFakeNumber() + "@mail.vn";
	}

	@Test
	public void TC_01_Register_Empty_Data() {
		System.out.println("Home Page - Step 01: Click on Register link");
		homePage.clickOnRegisterLink();
		
		System.out.println("Register Page - Step 02: Click on Register button");
		registerPage.clickOnRegisterButton();
		
		System.out.println("Register Page - Step 03: Verify error messages displayed");
		Assert.assertEquals(registerPage.getErrorMessageBelowFirstNameTextbox(), "First name is required.");
		Assert.assertEquals(registerPage.getErrorMessageBelowLastNameTextbox(), "Last name is required.");
		Assert.assertEquals(registerPage.getErrorMessageBelowEmailTextbox(), "Email is required.");
		Assert.assertEquals(registerPage.getErrorMessageBelowPasswordTextbox(), "Password is required.");
		Assert.assertEquals(registerPage.getErrorMessageBelowConfirmPasswordTextbox(), "Password is required.");
	}

	@Test
	public void TC_02_Register_Invalid_Email() {
		System.out.println("Home Page - Step 01: Click on Register link");
		homePage.clickOnRegisterLink();
		
		System.out.println("Register Page - Step 02: Input into required fields");
		registerPage.inputIntoFirstNameTextbox(firstName);
		registerPage.inputIntoLastNameTextbox(lastName);
		registerPage.inputIntoEmailTextbox("123@456#%*");
		registerPage.inputIntoPasswordTextbox(password);
		registerPage.inputIntoConfirmPasswordTextbox(password);

		System.out.println("Register Page - Step 03: Click on Register button");
		registerPage.clickOnRegisterButton();
		
		System.out.println("Register Page - Step 04: Verify error message displayed");
		Assert.assertEquals(registerPage.getErrorMessageBelowEmailTextbox(), "Wrong email");
	}

	@Test
	public void TC_03_Register_Success() {
		System.out.println("Home Page - Step 01: Click on Register link");
		homePage.clickOnRegisterLink();
		
		System.out.println("Register Page - Step 02: Input into required fields");
		registerPage.inputIntoFirstNameTextbox(firstName);
		registerPage.inputIntoLastNameTextbox(lastName);
		registerPage.inputIntoEmailTextbox(emailAddress);
		registerPage.inputIntoPasswordTextbox(password);
		registerPage.inputIntoConfirmPasswordTextbox(password);

		System.out.println("Register Page - Step 03: Click on Register button");
		registerPage.clickOnRegisterButton();
		
		System.out.println("Register Page - Step 04: Verify success message displayed");
		Assert.assertEquals(registerPage.getRegistrationSuccessfulMessage(), "Your registration completed");

		System.out.println("Register Page - Step 05: Click on Logout link");
		registerPage.clickOnLogoutLink();
	}

	@Test
	public void TC_04_Register_Existing_Email() {
		System.out.println("Home Page - Step 01: Click on Register link");
		homePage.clickOnRegisterLink();
		
		System.out.println("Register Page - Step 02: Input into required fields");
		registerPage.inputIntoFirstNameTextbox(firstName);
		registerPage.inputIntoLastNameTextbox(lastName);
		registerPage.inputIntoEmailTextbox(emailAddress);
		registerPage.inputIntoPasswordTextbox(password);
		registerPage.inputIntoConfirmPasswordTextbox(password);

		System.out.println("Register Page - Step 03: Click on Register button");
		registerPage.clickOnRegisterButton();

		System.out.println("Register Page - Step 04: Verify error message of existing email displayed");
		Assert.assertEquals(registerPage.getErrorMessageExistingEmail(), "The specified email already exists");
	}

	@Test
	public void TC_05_Register_Password_Less_Than_6_Characters() {
		System.out.println("Home Page - Step 01: Click on Register link");
		homePage.clickOnRegisterLink();
		
		System.out.println("Register Page - Step 02: Input into required fields");
		registerPage.inputIntoFirstNameTextbox(firstName);
		registerPage.inputIntoLastNameTextbox(lastName);
		registerPage.inputIntoEmailTextbox(emailAddress);
		registerPage.inputIntoPasswordTextbox("123");
		registerPage.inputIntoConfirmPasswordTextbox("123");

		System.out.println("Register Page - Step 03: Click on Register button");
		registerPage.clickOnRegisterButton();

		System.out.println("Register Page - Step 04: Verify error message displayed");
		Assert.assertEquals(registerPage.getErrorMessageBelowPasswordTextbox(), "Password must meet the following rules:\nmust have at least 6 characters");
	}

	@Test
	public void TC_06_Register_Invalid_Confirm_Password() {
		System.out.println("Home Page - Step 01: Click on Register link");
		homePage.clickOnRegisterLink();
		
		System.out.println("Register Page - Step 02: Input into required fields");
		registerPage.inputIntoFirstNameTextbox(firstName);
		registerPage.inputIntoLastNameTextbox(lastName);
		registerPage.inputIntoEmailTextbox(emailAddress);
		registerPage.inputIntoPasswordTextbox(password);
		registerPage.inputIntoConfirmPasswordTextbox(emailAddress);

		System.out.println("Register Page - Step 03: Click on Register button");
		registerPage.clickOnRegisterButton();

		System.out.println("Register Page - Step 04: Verify error message displayed");
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