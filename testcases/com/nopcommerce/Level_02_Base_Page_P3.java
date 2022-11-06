package com.nopcommerce;

import java.io.File;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import commons.nopCommerce.NcBasePage;

public class Level_02_Base_Page_P3 extends NcBasePage {
	WebDriver driver;
	String emailAddress;

	String projectPath = System.getProperty("user.dir");
	String separatorChar = File.separator;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", projectPath + separatorChar + "browserDrivers" + separatorChar + "chromedriver.exe");
		driver = new ChromeDriver();

		emailAddress = "level2" + generateFakeNumber() + "@mail.vn";
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.get("https://demo.nopcommerce.com/");
	}

	@Test
	public void TC_01_Register_Empty_Data() {
		waitForElementClickable(driver, "//a[@class='ico-register']");
		clickOnElement(driver, "//a[@class='ico-register']");

		waitForElementClickable(driver, "//button[@id='register-button']");
		clickOnElement(driver, "//button[@id='register-button']");

		Assert.assertEquals(getElementText(driver, "//span[@id='FirstName-error']"), "First name is required.");
		Assert.assertEquals(getElementText(driver, "//span[@id='LastName-error']"), "Last name is required.");
		Assert.assertEquals(getElementText(driver, "//span[@id='Email-error']"), "Email is required.");
		Assert.assertEquals(getElementText(driver, "//span[@id='Password-error']"), "Password is required.");
		Assert.assertEquals(getElementText(driver, "//span[@id='ConfirmPassword-error']"), "Password is required.");
	}

	@Test
	public void TC_02_Register_Invalid_Email() {
		waitForElementClickable(driver, "//a[@class='ico-register']");
		clickOnElement(driver, "//a[@class='ico-register']");

		sendKeysToElement(driver, "//input[@id='FirstName']", "Automation");
		sendKeysToElement(driver, "//input[@id='LastName']", "Tester");
		sendKeysToElement(driver, "//input[@id='Email']", "123@456#%*");
		sendKeysToElement(driver, "//input[@id='Password']", "123456");
		sendKeysToElement(driver, "//input[@id='ConfirmPassword']", "123456");

		waitForElementClickable(driver, "//button[@id='register-button']");
		clickOnElement(driver, "//button[@id='register-button']");

		Assert.assertEquals(getElementText(driver, "//span[@id='Email-error']"), "Wrong email");
	}

	@Test
	public void TC_03_Register_Success() {
		waitForElementClickable(driver, "//a[@class='ico-register']");
		clickOnElement(driver, "//a[@class='ico-register']");

		sendKeysToElement(driver, "//input[@id='FirstName']", "Automation");
		sendKeysToElement(driver, "//input[@id='LastName']", "Tester");
		sendKeysToElement(driver, "//input[@id='Email']", emailAddress);
		sendKeysToElement(driver, "//input[@id='Password']", "123456");
		sendKeysToElement(driver, "//input[@id='ConfirmPassword']", "123456");

		waitForElementClickable(driver, "//button[@id='register-button']");
		clickOnElement(driver, "//button[@id='register-button']");

		Assert.assertEquals(getElementText(driver, "//div[@class='result']"), "Your registration completed");

		waitForElementClickable(driver, "//a[@class='ico-logout']");
		clickOnElement(driver, "//a[@class='ico-logout']");
	}

	@Test
	public void TC_04_Register_Existing_Email() {
		waitForElementClickable(driver, "//a[@class='ico-register']");
		clickOnElement(driver, "//a[@class='ico-register']");

		sendKeysToElement(driver, "//input[@id='FirstName']", "Automation");
		sendKeysToElement(driver, "//input[@id='LastName']", "Tester");
		sendKeysToElement(driver, "//input[@id='Email']", emailAddress);
		sendKeysToElement(driver, "//input[@id='Password']", "123456");
		sendKeysToElement(driver, "//input[@id='ConfirmPassword']", "123456");

		waitForElementClickable(driver, "//button[@id='register-button']");
		clickOnElement(driver, "//button[@id='register-button']");
		Assert.assertEquals(getElementText(driver, "//div[contains(@class,'message-error')]//li"), "The specified email already exists");
	}

	@Test
	public void TC_05_Register_Password_Less_Than_6_Characters() {
		waitForElementClickable(driver, "//a[@class='ico-register']");
		clickOnElement(driver, "//a[@class='ico-register']");

		sendKeysToElement(driver, "//input[@id='FirstName']", "Automation");
		sendKeysToElement(driver, "//input[@id='LastName']", "Tester");
		sendKeysToElement(driver, "//input[@id='Email']", emailAddress);
		sendKeysToElement(driver, "//input[@id='Password']", "123");
		sendKeysToElement(driver, "//input[@id='ConfirmPassword']", "123");

		waitForElementClickable(driver, "//button[@id='register-button']");
		clickOnElement(driver, "//button[@id='register-button']");
		Assert.assertEquals(getElementText(driver, "//span[@id='Password-error']"), "Password must meet the following rules:\nmust have at least 6 characters");
	}

	@Test
	public void TC_06_Register_Invalid_Confirm_Password() {
		waitForElementClickable(driver, "//a[@class='ico-register']");
		clickOnElement(driver, "//a[@class='ico-register']");

		sendKeysToElement(driver, "//input[@id='FirstName']", "Automation");
		sendKeysToElement(driver, "//input[@id='LastName']", "Tester");
		sendKeysToElement(driver, "//input[@id='Email']", emailAddress);
		sendKeysToElement(driver, "//input[@id='Password']", "123456");
		sendKeysToElement(driver, "//input[@id='ConfirmPassword']", "11121");

		waitForElementClickable(driver, "//button[@id='register-button']");
		clickOnElement(driver, "//button[@id='register-button']");
		Assert.assertEquals(getElementText(driver, "//span[@id='ConfirmPassword-error']"), "The password and confirmation password do not match.");
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	public int generateFakeNumber() {
		Random rand = new Random();
		return rand.nextInt(9909);
	}
}