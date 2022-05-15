package com.nopcommerce.user;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.nopCommerce.BaseTest;
import commons.nopCommerce.GlobalConstants;
import commons.nopCommerce.PageGeneratorManager;
import pageObjects.nopCommerce.admin.AdminConfigurationStoresPageObject;
import pageObjects.nopCommerce.admin.AdminDashboardPageObject;
import pageObjects.nopCommerce.admin.AdminLoginPageObject;
import pageObjects.nopCommerce.admin.AdminReportsSalesSummaryPageObject;
import pageObjects.nopCommerce.user.UserCustomerInfoPageObject;
import pageObjects.nopCommerce.user.UserHomePageObject;
import pageObjects.nopCommerce.user.UserLoginPageObject;
import pageObjects.nopCommerce.user.UserRegisterPageObject;

public class Level_08_Switch_Role extends BaseTest {

	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = getBrowserDriver(browserName);

		firstName = "Switch";
		lastName = "Role";
		userEmail = "switchrole.level8" + generateFakeNumber() + "@mail.vn";
		userPassword = "532022";
		adminEmail = "admin@yourstore.com";
		adminPassword = "admin";
	}

	@Test
	public void Role_01_User_Register() {
		userHomePage = PageGeneratorManager.getUserHomePage(driver);
		userRegisterPage = userHomePage.clickOnRegisterLink();

		userRegisterPage.inputIntoFirstNameTextbox(firstName);
		userRegisterPage.inputIntoLastNameTextbox(lastName);
		userRegisterPage.inputIntoEmailTextbox(userEmail);
		userRegisterPage.inputIntoPasswordTextbox(userPassword);
		userRegisterPage.inputIntoConfirmPasswordTextbox(userPassword);
		userRegisterPage.clickOnRegisterButton();
		Assert.assertEquals(userRegisterPage.getRegisterSuccessMessage(), "Your registration completed");

		userHomePage = userRegisterPage.clickOnLogoutLink();
	}

	@Test
	public void Role_02_User_To_Admin() {
		// User: Home - login -> Home
		userLoginPage = userHomePage.clickOnLoginLink();
		userHomePage = userLoginPage.loginAsUser(userEmail, userPassword);
		Assert.assertTrue(userHomePage.isMyAccountLinkDisplayed());

		// User: Home -> Customer info
		userCustomerInfoPage = userHomePage.clickOnMyAccountLink();

		// User: Customer info - logout -> Home
		userHomePage = userCustomerInfoPage.clickLogoutLinkOnUserPage(driver);

		// User: open Admin url
		userHomePage.openPageUrl(driver, GlobalConstants.ADMIN_PAGE_URL);
		adminLoginPage = PageGeneratorManager.getAdminLoginPage(driver);

		// Admin: Login -> Dashboard
		adminDashboardPage = adminLoginPage.loginAsAdmin(adminEmail, adminPassword);
		Assert.assertTrue(adminDashboardPage.isDashboardHeaderDisplayed());	
	}

	@Test
	public void Role_03_Admin_To_User() {
		// Admin: Dashboard -> Sales Summary
		adminReportsSalesSummaryPage = adminDashboardPage.openAdminReportsSalesSummaryPage(driver);
		Assert.assertTrue(adminReportsSalesSummaryPage.isSalesSummaryHeaderDisplayed());
		
		// Admin: Sales Summary -> Stores
		adminConfigurationStoresPage = adminReportsSalesSummaryPage.openAdminConfigurationStoresPage(driver);
		Assert.assertTrue(adminConfigurationStoresPage.isStoresHeaderDisplayed());
		
		// Admin: Stores - logout -> Login
		adminLoginPage = adminConfigurationStoresPage.clickLogoutLinkOnAdminPage(driver);
		
		// Admin: open User url
		adminLoginPage.openPageUrl(driver, GlobalConstants.USER_PAGE_URL);
		userHomePage = PageGeneratorManager.getUserHomePage(driver);

		// User: Home - login -> Home
		userLoginPage = userHomePage.clickOnLoginLink();
		userHomePage = userLoginPage.loginAsUser(userEmail, userPassword);
		Assert.assertTrue(userHomePage.isMyAccountLinkDisplayed());
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	private WebDriver driver;
	private UserHomePageObject userHomePage;
	private UserRegisterPageObject userRegisterPage;
	private UserLoginPageObject userLoginPage;
	private UserCustomerInfoPageObject userCustomerInfoPage;
	private AdminLoginPageObject adminLoginPage;
	private AdminDashboardPageObject adminDashboardPage;
	private AdminConfigurationStoresPageObject adminConfigurationStoresPage;
	private AdminReportsSalesSummaryPageObject adminReportsSalesSummaryPage;
	private String firstName, lastName, userEmail, userPassword, adminEmail, adminPassword;

}