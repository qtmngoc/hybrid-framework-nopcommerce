package com.liveguru.user;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.liveGuru.BaseTest;
import commons.liveGuru.PageGeneratorManager;
import pageObjects.liveGuru.HomePageObject;
import pageObjects.liveGuru.LoginPageObject;
import pageObjects.liveGuru.MyDashboardPageObject;
import pageObjects.liveGuru.RegisterPageObject;

public class Level_06_Page_Generater_Manager extends BaseTest {
	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = getBrowserDriver(browserName);
		homePage = PageGeneratorManager.getHomePage(driver);

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
	private HomePageObject homePage;
	private LoginPageObject loginPage;
	private RegisterPageObject registerPage;
	private MyDashboardPageObject myDashboardPage;
	private String firstName, lastName, email, password;
}