/*package com.nopcommerce.user;

import java.io.File;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import commons.BasePage;

public class Level_02_Base_Page_P1 {
	WebDriver driver;

	// Declare
	BasePage basePage;
	// BasePage: class
	// basePage: object

	String projectPath = System.getProperty("user.dir");
	String emailAddress;
	String separatorChar = File.separator;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + separatorChar + "browserDrivers" + separatorChar + "geckodriver.exe");
		driver = new FirefoxDriver();

		// Initial
		basePage = new BasePage();

		emailAddress = "level2" + generateFakeNumber() + "@mail.vn";
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.get("https://demo.nopcommerce.com/");
	}

	@Test
	public void TC_01_Register_Empty_Data() {
		basePage.waitElementClickable(driver, "//a[@class='ico-register']");
		basePage.clickOnElement(driver, "//a[@class='ico-register']");

		basePage.waitElementClickable(driver, "//button[@id='register-button']");
		basePage.clickOnElement(driver, "//button[@id='register-button']");

		Assert.assertEquals(basePage.getElementText(driver, "//span[@id='FirstName-error']"), "First name is required.");
		Assert.assertEquals(basePage.getElementText(driver, "//span[@id='LastName-error']"), "Last name is required.");
		Assert.assertEquals(basePage.getElementText(driver, "//span[@id='Email-error']"), "Email is required.");
		Assert.assertEquals(basePage.getElementText(driver, "//span[@id='Password-error']"), "Password is required.");
		Assert.assertEquals(basePage.getElementText(driver, "//span[@id='ConfirmPassword-error']"), "Password is required.");
	}

	@Test
	public void TC_02_Register_Invalid_Email() {
		basePage.waitElementClickable(driver, "//a[@class='ico-register']");
		basePage.clickOnElement(driver, "//a[@class='ico-register']");

		basePage.sendKeysToElement(driver, "//input[@id='FirstName']", "Automation");
		basePage.sendKeysToElement(driver, "//input[@id='LastName']", "Tester");
		basePage.sendKeysToElement(driver, "//input[@id='Email']", "123@456#%*");
		basePage.sendKeysToElement(driver, "//input[@id='Password']", "123456");
		basePage.sendKeysToElement(driver, "//input[@id='ConfirmPassword']", "123456");

		basePage.waitElementClickable(driver, "//button[@id='register-button']");
		basePage.clickOnElement(driver, "//button[@id='register-button']");

		Assert.assertEquals(basePage.getElementText(driver, "//span[@id='Email-error']"), "Wrong email");
	}

	@Test
	public void TC_03_Register_Success() {
		basePage.waitElementClickable(driver, "//a[@class='ico-register']");
		basePage.clickOnElement(driver, "//a[@class='ico-register']");

		basePage.sendKeysToElement(driver, "//input[@id='FirstName']", "Automation");
		basePage.sendKeysToElement(driver, "//input[@id='LastName']", "Tester");
		basePage.sendKeysToElement(driver, "//input[@id='Email']", emailAddress);
		basePage.sendKeysToElement(driver, "//input[@id='Password']", "123456");
		basePage.sendKeysToElement(driver, "//input[@id='ConfirmPassword']", "123456");

		basePage.waitElementClickable(driver, "//button[@id='register-button']");
		basePage.clickOnElement(driver, "//button[@id='register-button']");

		Assert.assertEquals(basePage.getElementText(driver, "//div[@class='result']"), "Your registration completed");

		basePage.waitElementClickable(driver, "//a[@class='ico-logout']");
		basePage.clickOnElement(driver, "//a[@class='ico-logout']");
	}

	@Test
	public void TC_04_Register_Existing_Email() {
		basePage.waitElementClickable(driver, "//a[@class='ico-register']");
		basePage.clickOnElement(driver, "//a[@class='ico-register']");

		basePage.sendKeysToElement(driver, "//input[@id='FirstName']", "Automation");
		basePage.sendKeysToElement(driver, "//input[@id='LastName']", "Tester");
		basePage.sendKeysToElement(driver, "//input[@id='Email']", emailAddress);
		basePage.sendKeysToElement(driver, "//input[@id='Password']", "123456");
		basePage.sendKeysToElement(driver, "//input[@id='ConfirmPassword']", "123456");

		basePage.waitElementClickable(driver, "//button[@id='register-button']");
		basePage.clickOnElement(driver, "//button[@id='register-button']");
		Assert.assertEquals(basePage.getElementText(driver, "//div[contains(@class,'message-error')]//li"), "The specified email already exists");
	}

	@Test
	public void TC_05_Register_Password_Less_Than_6_Characters() {
		basePage.waitElementClickable(driver, "//a[@class='ico-register']");
		basePage.clickOnElement(driver, "//a[@class='ico-register']");

		basePage.sendKeysToElement(driver, "//input[@id='FirstName']", "Automation");
		basePage.sendKeysToElement(driver, "//input[@id='LastName']", "Tester");
		basePage.sendKeysToElement(driver, "//input[@id='Email']", emailAddress);
		basePage.sendKeysToElement(driver, "//input[@id='Password']", "123");
		basePage.sendKeysToElement(driver, "//input[@id='ConfirmPassword']", "123");

		basePage.waitElementClickable(driver, "//button[@id='register-button']");
		basePage.clickOnElement(driver, "//button[@id='register-button']");
		Assert.assertEquals(basePage.getElementText(driver, "//span[@id='Password-error']"), "Password must meet the following rules:\nmust have at least 6 characters");
	}

	@Test
	public void TC_06_Register_Invalid_Confirm_Password() {
		basePage.waitElementClickable(driver, "//a[@class='ico-register']");
		basePage.clickOnElement(driver, "//a[@class='ico-register']");

		basePage.sendKeysToElement(driver, "//input[@id='FirstName']", "Automation");
		basePage.sendKeysToElement(driver, "//input[@id='LastName']", "Tester");
		basePage.sendKeysToElement(driver, "//input[@id='Email']", emailAddress);
		basePage.sendKeysToElement(driver, "//input[@id='Password']", "123456");
		basePage.sendKeysToElement(driver, "//input[@id='ConfirmPassword']", "11121");

		basePage.waitElementClickable(driver, "//button[@id='register-button']");
		basePage.clickOnElement(driver, "//button[@id='register-button']");
		Assert.assertEquals(basePage.getElementText(driver, "//span[@id='ConfirmPassword-error']"), "The password and confirmation password do not match.");
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	public int generateFakeNumber() {
		Random rand = new Random();
		return rand.nextInt(9909);
	}
}*/