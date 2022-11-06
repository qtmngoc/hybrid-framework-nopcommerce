package com.liveguru;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.liveGuru.LgBaseTest;
import commons.liveGuru.LgPageGeneratorManager;
import pageObjects.liveGuru.LgUserHomePageObject;
import pageObjects.liveGuru.LgUserLoginPageObject;
import pageObjects.liveGuru.LgUserMyDashboardPageObject;
import pageObjects.liveGuru.LgUserRegisterPageObject;

public class Level_06_Page_Generater_Manager extends LgBaseTest {
	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = getBrowserDriver(browserName);
		homePage = LgPageGeneratorManager.getUserHomePage(driver);

		firstName = "Page";
		lastName = "Generator";
		email = "liveguru.level6" + generateFakeNumber() + "@mail.com";
		password = "152022";
	}

	@Test
	public void User_01_Register() {
		loginPage = homePage.clickOnMyAccountLink();

		registerPage = loginPage.clickOnCreateButton();
		registerPage.inputIntoFirstNameTextbox(firstName);
		registerPage.inputIntoLastNameTextbox(lastName);
		registerPage.inputIntoEmailTextbox(email);
		registerPage.inputIntoPasswordTextbox(password);
		registerPage.inputIntoConfirmPasswordTextbox(password);

		myDashboardPage = registerPage.clickOnRegisterButton();
		Assert.assertEquals(myDashboardPage.getRegisterSuccessMessage(), "Thank you for registering with Main Website Store.");
		myDashboardPage.clickOnAccountMenu();
		
		homePage = myDashboardPage.clickOnLogOutLink();
		Assert.assertTrue(homePage.isGuruLogoDisplayed());
	}

	@Test
	public void User_02_Login() {
		loginPage = homePage.clickOnMyAccountLink();
		loginPage.inputIntoEmailTextbox(email);
		loginPage.inputIntoPasswordTextbox(password);
		
		myDashboardPage = loginPage.clickOnLoginButton();
		Assert.assertEquals(myDashboardPage.getWelcomeMessage(), "Hello, " + firstName + " " + lastName + "!");
		Assert.assertTrue(myDashboardPage.getContactInformation().contains(email));
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	private WebDriver driver;
	private LgUserHomePageObject homePage;
	private LgUserLoginPageObject loginPage;
	private LgUserRegisterPageObject registerPage;
	private LgUserMyDashboardPageObject myDashboardPage;
	private String firstName, lastName, email, password;
}