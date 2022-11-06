package com.nopcommerce;

import java.lang.reflect.Method;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.nopCommerce.NcBaseTest;
import commons.nopCommerce.NcPageGeneratorManager;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import pageObjects.nopCommerce.NcUserCustomerInfoPO;
import pageObjects.nopCommerce.NcUserHomePO;
import pageObjects.nopCommerce.NcUserLoginPO;
import pageObjects.nopCommerce.NcUserRegisterPO;

public class Level_16_Allure_Report_Screenshot extends NcBaseTest {

	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = getBrowserDriver(browserName);
		userHomePage = NcPageGeneratorManager.getUserHomePage(driver);

		firstName = "Allure Report";
		lastName = "Screenshot";
		email = "allurereport.level15." + generateFakeNumber() + "@mail.com";
		password = "210622";
	}

	@Description("User_01_Register")
	@Story("Register to system")
	@Severity(SeverityLevel.NORMAL)
	@Test
	public void User_01_Register(Method method) {
		userRegisterPage = userHomePage.clickOnRegisterLink();

		userRegisterPage.inputIntoFirstNameTextbox(firstName);
		userRegisterPage.inputIntoLastNameTextbox(lastName);
		userRegisterPage.inputIntoEmailTextbox(email);
		userRegisterPage.inputIntoPasswordTextbox(password);
		userRegisterPage.inputIntoConfirmPasswordTextbox(password);

		userRegisterPage.clickOnRegisterButton();
		Assert.assertEquals(userRegisterPage.getRegisterSuccessMessage(), "Your registration completed");
	}
	
	@Description("User_02_Login")
	@Story("Login to system")
	@Severity(SeverityLevel.NORMAL)
	@Test
	public void User_02_Login(Method method) {
		userHomePage = userRegisterPage.clickOnLogoutLink();
		userLoginPage = userHomePage.clickOnLoginLink();

		userLoginPage.inputIntoEmailTextbox(email);
		userLoginPage.inputIntoPasswordTextbox(password);

		userHomePage = userLoginPage.clickOnLoginButton();
		Assert.assertFalse(userHomePage.isMyAccountLinkDisplayed());

		userCustomerInfoPage = userHomePage.clickOnMyAccountLink();
		Assert.assertFalse(userCustomerInfoPage.isCustomerInfoHeaderDisplayed());
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	private WebDriver driver;
	private NcUserHomePO userHomePage;
	private NcUserRegisterPO userRegisterPage;
	private NcUserLoginPO userLoginPage;
	private NcUserCustomerInfoPO userCustomerInfoPage;
	private String firstName, lastName, email, password;

}