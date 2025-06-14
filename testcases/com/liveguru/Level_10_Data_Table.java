package com.liveguru;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.liveGuru.LgBaseTest;
import commons.liveGuru.LgGlobalConstants;
import commons.liveGuru.LgPageGeneratorManager;
import pageObjects.liveGuru.LgAdminCustomersPageObject;
import pageObjects.liveGuru.LgAdminLoginPageObject;
import pageObjects.liveGuru.LgUserHomePageObject;
import pageObjects.liveGuru.LgUserLoginPageObject;
import pageObjects.liveGuru.LgUserMyAccountDashboardPageObject;
import pageObjects.liveGuru.LgUserRegisterPageObject;

public class Level_10_Data_Table extends LgBaseTest {
	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = getBrowserDriver(browserName);
		
		fakeNumber = generateFakeNumber();
		firstName = "Data";
		lastName = "Table" + " " + fakeNumber;
		fullName = firstName + " " + lastName;
		userEmail = "datatable.level10." + fakeNumber + "@mail.com";
		userPassword = "060122";
		adminUserName = "user01";
		adminPassword = "guru99com";
	}
	
	@Test
	public void Role_01_User_Register() {
		userHomePage = LgPageGeneratorManager.getUserHomePage(driver);
		
		userLoginPage = userHomePage.clickOnMyAccountLink();
		
		userRegisterPage = userLoginPage.clickOnCreateButton();
		userRegisterPage.inputIntoFirstNameTextbox(firstName);
		userRegisterPage.inputIntoLastNameTextbox(lastName);
		userRegisterPage.inputIntoEmailTextbox(userEmail);
		userRegisterPage.inputIntoPasswordTextbox(userPassword);
		userRegisterPage.inputIntoConfirmPasswordTextbox(userPassword);
		
		userMyDashboardPage = userRegisterPage.clickOnRegisterButton();
		Assert.assertEquals(userMyDashboardPage.getRegisterSuccessMessage(), "Thank you for registering with Main Website Store.");
		userMyDashboardPage.clickOnAccountMenu();
		
		userHomePage = userMyDashboardPage.clickOnLogOutLink();
	}
	
	@Test
	public void Role_02_Admin_Search_For_Existing_User() {
		userHomePage.openPageUrl(driver, LgGlobalConstants.ADMIN_PAGE_URL);
		
		adminLoginPage = LgPageGeneratorManager.getAdminLoginPage(driver);
		adminLoginPage.inputIntoUserNameTextbox(adminUserName);
		adminLoginPage.inputIntoPasswordTextbox(adminPassword);
		
		adminCustomersPage = adminLoginPage.clickOnLoginButton();
		adminCustomersPage.closePopup();
		adminCustomersPage.inputIntoFilterTextboxByColumnName("Email", userEmail);
		adminCustomersPage.clickOnButtonByName("Search");
		Assert.assertEquals(adminCustomersPage.resultCellByRowNumberAndColumnName("1", "Name"), fullName);
		Assert.assertEquals(adminCustomersPage.resultCellByRowNumberAndColumnName("1", "Email"), userEmail);
	}
	
	@Test
	public void Role_03_Admin_Delete_User() {
		adminCustomersPage.checkCheckboxByRowNumber("1");
		adminCustomersPage.selectOptionInActionsDropdownByText("Delete");
		adminCustomersPage.clickOnButtonByName("Submit");
		adminCustomersPage.accepAlert();
		
		adminLoginPage = adminCustomersPage.clickOnLogoutLink();		
		adminLoginPage.openPageUrl(driver, LgGlobalConstants.USER_PAGE_URL);
		
		userHomePage = LgPageGeneratorManager.getUserHomePage(driver);
		
		userLoginPage = userHomePage.clickOnMyAccountLink();
		userLoginPage.inputIntoEmailTextbox(userEmail);
		userLoginPage.inputIntoPasswordTextbox(userPassword);
		userLoginPage.clickOnLoginButton();
		Assert.assertEquals(userLoginPage.getLoginUnsuccessMessage(), "Invalid login or password.");
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
	private WebDriver driver;
	private LgUserHomePageObject userHomePage;
	private LgUserLoginPageObject userLoginPage;
	private LgUserRegisterPageObject userRegisterPage;
	private LgUserMyAccountDashboardPageObject userMyDashboardPage;
	private LgAdminLoginPageObject adminLoginPage;
	private LgAdminCustomersPageObject adminCustomersPage;
	private String firstName, lastName, fullName, userEmail, userPassword, adminUserName, adminPassword;
	private int fakeNumber;
}
