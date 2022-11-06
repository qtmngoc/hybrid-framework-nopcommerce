package com.liveguru;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.liveGuru.LgBaseTest;
import commons.liveGuru.LgPageGeneratorManager;
import pageObjects.liveGuru.LgUserAccountInformationPageObject;
import pageObjects.liveGuru.LgUserHomePageObject;
import pageObjects.liveGuru.LgUserMyDashboardPageObject;
import pageObjects.liveGuru.LgUserNewsletterSubscriptionPageObject;
import pageObjects.liveGuru.LgUserRecurringProfilesPageObject;
import pageObjects.liveGuru.LgUserRegisterPageObject;

public class Level_07_Page_Navigation extends LgBaseTest {
	
	@Parameters({ "browser", "userUrl" })
	@BeforeClass
	public void beforeClass(String browserName, String userUrl) {
		driver = getBrowserDriver(browserName, userUrl);
		homePage = LgPageGeneratorManager.getUserHomePage(driver);
	}

	@Test
	public void Switch_01_Register_And_Login() {
		homePage.clickOnAccountMenu();
		
		registerPage = homePage.clickOnRegisterLink();
		registerPage.inputIntoFirstNameTextbox(firstName);
		registerPage.inputIntoLastNameTextbox(lastName);
		registerPage.inputIntoEmailTextbox(email);
		registerPage.inputIntoPasswordTextbox(password);
		registerPage.inputIntoConfirmPasswordTextbox(password);

		myDashboardPage = registerPage.clickOnRegisterButton();
		verifyTrue(myDashboardPage.isRegisterSuccessMessageDisplayed());
		verifyTrue(myDashboardPage.isContactInformationDisplayed(firstName + " " + lastName));
		verifyTrue(myDashboardPage.isContactInformationDisplayed(email));
	}
	
	@Test
	public void Switch_02_My_Account_Sidebar() {
		// Account Dashboard -> Newsletter Subscription
		newsletterSubscriptionPage = myDashboardPage.openNewsletterSubscriptionPage();
		verifyTrue(newsletterSubscriptionPage.isNewsletterSubscriptionHeaderDisplayed());
		
		// Newsletter Subscription -> Account Information
		accountInformationPage = newsletterSubscriptionPage.openAccountInformationPage();
		verifyTrue(accountInformationPage.isAccountInformationHeaderDisplayed());
		
		// Account Information -> Recurring Profiles
		recurringProfilesPage = accountInformationPage.openRecurringProfilesPage();
		verifyTrue(recurringProfilesPage.isRecurringProfilesHeaderDisplayed());
	}
	
	@Test
	public void Switch_03_My_Account_Sidebar() {
		// Recurring Profiles -> Account Dashboard
		recurringProfilesPage.openMyAccountSidebarPageByTitle("Account Dashboard");
		myDashboardPage = LgPageGeneratorManager.getUserMyDashboardPage(driver);
		verifyTrue(myDashboardPage.isMyDashboardHeaderDisplayed());
		
		// Account Dashboard -> Account Information
		myDashboardPage.openMyAccountSidebarPageByTitle("Account Information");
		accountInformationPage = LgPageGeneratorManager.getUserAccountInformationPage(driver);
		verifyTrue(accountInformationPage.isAccountInformationHeaderDisplayed());
		
		// Account Information -> Newsletter Subscription
		accountInformationPage.openMyAccountSidebarPageByTitle("Newsletter Subscriptions");
		newsletterSubscriptionPage = LgPageGeneratorManager.getUserNewsletterSubscriptionPage(driver);
		verifyTrue(newsletterSubscriptionPage.isNewsletterSubscriptionHeaderDisplayed());
	}
	
	@Test
	public void Switch_04_Footer() {
		
	}

	@AfterClass(alwaysRun = true)
	public void afterClass() {
		closeBrowserAndDriver();
	}
	
	private String firstName = "Page";
	private String lastName = "Navigation";
	private String email = "liveguru.level7" + generateFakeNumber() + "@mail.com";
	private String password = "051122";
	
	private WebDriver driver;
	private LgUserHomePageObject homePage;
	private LgUserRegisterPageObject registerPage;
	private LgUserMyDashboardPageObject myDashboardPage;
	private LgUserRecurringProfilesPageObject recurringProfilesPage;
	private LgUserAccountInformationPageObject accountInformationPage;
	private LgUserNewsletterSubscriptionPageObject newsletterSubscriptionPage;
	
}