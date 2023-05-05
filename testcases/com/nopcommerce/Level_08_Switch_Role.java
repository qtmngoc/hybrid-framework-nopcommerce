package com.nopcommerce;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.nopCommerce.NcBaseTest;
import commons.nopCommerce.NcGlobalConstants;
import commons.nopCommerce.NcPageGeneratorManager;
import pageObjects.nopCommerce.NcAdminConfigurationStoresPO;
import pageObjects.nopCommerce.NcAdminDashboardPO;
import pageObjects.nopCommerce.NcAdminLoginPO;
import pageObjects.nopCommerce.NcAdminReportsSalesSummaryPO;
import pageObjects.nopCommerce.NcUserCustomerInfoPO;
import pageObjects.nopCommerce.NcUserHomePO;
import pageObjects.nopCommerce.NcUserLoginPO;
import pageObjects.nopCommerce.NcUserRegisterPO;

public class Level_08_Switch_Role extends NcBaseTest {

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
		userHomePage = NcPageGeneratorManager.getUserHomePage(driver);
		userRegisterPage = userHomePage.clickOnRegisterLink();

		userRegisterPage.inputIntoFirstNameTextbox(firstName);
		userRegisterPage.inputIntoLastNameTextbox(lastName);
		userRegisterPage.inputIntoEmailTextbox(userEmail);
		userRegisterPage.inputIntoPasswordTextbox(userPassword);
		userRegisterPage.inputIntoConfirmPasswordTextbox(userPassword);
		userRegisterPage.clickOnRegisterButton();
		Assert.assertEquals(userRegisterPage.getRegisterSuccessMessage(), "Your registration completed");
	}

	@Test
	public void Role_02_User_To_Admin() {
		// User: Home - login -> Home
		userLoginPage = userRegisterPage.clickOnLoginLink();
		userHomePage = userLoginPage.loginAsUser(userEmail, userPassword);
		Assert.assertTrue(userHomePage.isMyAccountLinkDisplayed());

		// User: Home -> Customer info
		userCustomerInfoPage = userHomePage.clickOnMyAccountLink();

		// User: Customer info - logout -> Home
		userHomePage = userCustomerInfoPage.clickLogoutLinkOnUserPage(driver);

		// User: open Admin url
		userHomePage.openPageUrl(driver, NcGlobalConstants.ADMIN_PAGE_URL);
		adminLoginPage = NcPageGeneratorManager.getAdminLoginPage(driver);

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
		adminLoginPage.openPageUrl(driver, NcGlobalConstants.USER_PAGE_URL);
		userHomePage = NcPageGeneratorManager.getUserHomePage(driver);

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
	private NcUserHomePO userHomePage;
	private NcUserRegisterPO userRegisterPage;
	private NcUserLoginPO userLoginPage;
	private NcUserCustomerInfoPO userCustomerInfoPage;
	private NcAdminLoginPO adminLoginPage;
	private NcAdminDashboardPO adminDashboardPage;
	private NcAdminConfigurationStoresPO adminConfigurationStoresPage;
	private NcAdminReportsSalesSummaryPO adminReportsSalesSummaryPage;
	private String firstName, lastName, userEmail, userPassword, adminEmail, adminPassword;

}