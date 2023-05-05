package com.nopcommerce;

import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.nopCommerce.NcBaseTest;
import pageObjects.nopCommerce.NcUserHomePO;
import pageObjects.nopCommerce.NcUserRegisterPO;

public class Level_04_Multiple_Browsers extends NcBaseTest{
	private WebDriver driver;
	private NcUserHomePO homePage;
	private NcUserRegisterPO registerPage;
	private String firstName, lastName, password, emailAddress;

	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = getBrowserDriver(browserName);
		driver.get("https://demo.nopcommerce.com/");

		homePage = new NcUserHomePO(driver);

		firstName = "Page";
		lastName = "Object";
		password = "123456";
		emailAddress = "level4" + generateFakeNumber() + "@mail.vn";
	}

	@Test
	public void Register_01_Empty_Data() {
		homePage.clickOnRegisterLink();

		registerPage = new NcUserRegisterPO(driver);

		registerPage.clickOnRegisterButton();

		Assert.assertEquals(registerPage.getErrorMessageBelowFirstNameTextbox(), "First name is required.");
		Assert.assertEquals(registerPage.getErrorMessageBelowLastNameTextbox(), "Last name is required.");
		Assert.assertEquals(registerPage.getErrorMessageBelowEmailTextbox(), "Email is required.");
		Assert.assertEquals(registerPage.getErrorMessageBelowPasswordTextbox(), "Password is required.");
		Assert.assertEquals(registerPage.getErrorMessageBelowConfirmPasswordTextbox(), "Password is required.");
	}

	@Test
	public void Register_02_Invalid_Email() {
		homePage.clickOnRegisterLink();

		registerPage = new NcUserRegisterPO(driver);

		registerPage.inputIntoFirstNameTextbox(firstName);
		registerPage.inputIntoLastNameTextbox(lastName);
		registerPage.inputIntoEmailTextbox("123@456#%*");
		registerPage.inputIntoPasswordTextbox(password);
		registerPage.inputIntoConfirmPasswordTextbox(password);

		registerPage.clickOnRegisterButton();

		Assert.assertEquals(registerPage.getErrorMessageBelowEmailTextbox(), "Wrong email");
	}

	@Test
	public void Register_03_Success() {

		homePage.clickOnRegisterLink();

		registerPage = new NcUserRegisterPO(driver);

		registerPage.inputIntoFirstNameTextbox(firstName);
		registerPage.inputIntoLastNameTextbox(lastName);
		registerPage.inputIntoEmailTextbox(emailAddress);
		registerPage.inputIntoPasswordTextbox(password);
		registerPage.inputIntoConfirmPasswordTextbox(password);

		registerPage.clickOnRegisterButton();

		Assert.assertEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed");
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