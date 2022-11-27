package com.liveguru;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.liveGuru.LgBaseTest;
import commons.liveGuru.LgPageGeneratorManager;
import pageObjects.liveGuru.LgUserAccountInformationPageObject;
import pageObjects.liveGuru.LgUserContactUsPageObject;
import pageObjects.liveGuru.LgUserHomePageObject;
import pageObjects.liveGuru.LgUserMyAccountDashboardPageObject;
import pageObjects.liveGuru.LgUserNewsletterSubscriptionPageObject;
import pageObjects.liveGuru.LgUserPrivacyPolicyPageObject;
import pageObjects.liveGuru.LgUserRecurringProfilesPageObject;
import pageObjects.liveGuru.LgUserRegisterPageObject;
import pageObjects.liveGuru.LgUserSearchTermsPageObject;

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

		myAccountDashboardPage = registerPage.clickOnRegisterButton();
		verifyTrue(myAccountDashboardPage.isRegisterSuccessMessageDisplayed());
		verifyTrue(myAccountDashboardPage.isContactInformationDisplayed(firstName + " " + lastName));
		verifyTrue(myAccountDashboardPage.isContactInformationDisplayed(email));
	}
	
	@Test
	public void Switch_02_My_Account_Sidebar() {
		// Account Dashboard -> Newsletter Subscription
		newsletterSubscriptionPage = myAccountDashboardPage.openNewsletterSubscriptionPage();
		verifyTrue(newsletterSubscriptionPage.isNewsletterSubscriptionHeaderDisplayed());
		
		// Newsletter Subscription -> Account Information
		accountInformationPage = newsletterSubscriptionPage.openAccountInformationPage();
		verifyTrue(accountInformationPage.isAccountInformationHeaderDisplayed());
		
		// Account Information -> Recurring Profiles
		recurringProfilesPage = accountInformationPage.openRecurringProfilesPage();
		verifyTrue(recurringProfilesPage.isRecurringProfilesHeaderDisplayed());

		// Recurring Profiles -> Account Dashboard
		recurringProfilesPage.openMyAccountSidebarPageByTitle("Account Dashboard");
		myAccountDashboardPage = LgPageGeneratorManager.getUserMyAccountDashboardPage(driver);
		verifyTrue(myAccountDashboardPage.isMyDashboardHeaderDisplayed());
		
		// Account Dashboard -> Account Information
		myAccountDashboardPage.openMyAccountSidebarPageByTitle("Account Information");
		accountInformationPage = LgPageGeneratorManager.getUserAccountInformationPage(driver);
		verifyTrue(accountInformationPage.isAccountInformationHeaderDisplayed());
		
		// Account Information -> Newsletter Subscription
		accountInformationPage.openMyAccountSidebarPageByTitle("Newsletter Subscriptions");
		newsletterSubscriptionPage = LgPageGeneratorManager.getUserNewsletterSubscriptionPage(driver);
		verifyTrue(newsletterSubscriptionPage.isNewsletterSubscriptionHeaderDisplayed());
	}
	
	@Test
	public void Switch_03_Footer() {
		// Newsletter Subscription (sidebar) -> Privacy Policy (footer)
		newsletterSubscriptionPage.getUserFooterContainerPage(driver).openFooterPageByTitle("Privacy Policy");
		privacyPolicyPage = LgPageGeneratorManager.getUserPrivacyPolicyPage(driver);
		verifyTrue(privacyPolicyPage.isPrivacyPolicyHeaderDisplayed());
		
		// Privacy Policy -> My Account
		privacyPolicyPage.openFooterPageByTitle("My Account");
		myAccountDashboardPage = LgPageGeneratorManager.getUserMyAccountDashboardPage(driver);
		verifyTrue(myAccountDashboardPage.isMyDashboardHeaderDisplayed());
		
		// My Account (footer / sidebar) -> Recurring Profiles (sidebar)
		myAccountDashboardPage.openMyAccountSidebarPageByTitle("Recurring Profiles");
		recurringProfilesPage = LgPageGeneratorManager.getUserRecurringProfilesPage(driver);
		verifyTrue(recurringProfilesPage.isRecurringProfilesHeaderDisplayed());
		
		// Recurring Profiles (sidebar) -> Contact Us (footer) 
		recurringProfilesPage.openFooterPageByTitle("Contact Us");
		contactUsPage = LgPageGeneratorManager.getUserContactUsPage(driver);
		verifyTrue(contactUsPage.isContactUsHeaderDisplayed());
		
		// Contact Us -> My Account
		contactUsPage.openFooterPageByTitle("My Account");
		myAccountDashboardPage = LgPageGeneratorManager.getUserMyAccountDashboardPage(driver);
		verifyTrue(myAccountDashboardPage.isMyDashboardHeaderDisplayed());
		
		// My Account -> Search Terms
		myAccountDashboardPage.openFooterPageByTitle("Search Terms");
		searchTermsPage = LgPageGeneratorManager.getUserSearchTermsPage(driver);
		verifyTrue(searchTermsPage.isSearchTermsHeaderDisplayed());
		
		// Search Terms (footer) -> Account Information (sidebar): FAIL
		// searchTermsPage.openAccountInformationPage();
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
	private LgUserContactUsPageObject contactUsPage;
	private LgUserSearchTermsPageObject searchTermsPage;
	private LgUserPrivacyPolicyPageObject privacyPolicyPage;
	private LgUserRecurringProfilesPageObject recurringProfilesPage;
	private LgUserMyAccountDashboardPageObject myAccountDashboardPage;
	private LgUserAccountInformationPageObject accountInformationPage;
	private LgUserNewsletterSubscriptionPageObject newsletterSubscriptionPage;
	
}