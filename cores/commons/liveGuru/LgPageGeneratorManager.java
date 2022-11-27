package commons.liveGuru;

import org.openqa.selenium.WebDriver;

import pageObjects.liveGuru.LgAdminCustomersPageObject;
import pageObjects.liveGuru.LgAdminLoginPageObject;
import pageObjects.liveGuru.LgUserAccountInformationPageObject;
import pageObjects.liveGuru.LgUserContactUsPageObject;
import pageObjects.liveGuru.LgUserHomePageObject;
import pageObjects.liveGuru.LgUserLoginPageObject;
import pageObjects.liveGuru.LgUserMyAccountDashboardPageObject;
import pageObjects.liveGuru.LgUserNewsletterSubscriptionPageObject;
import pageObjects.liveGuru.LgUserPrivacyPolicyPageObject;
import pageObjects.liveGuru.LgUserRecurringProfilesPageObject;
import pageObjects.liveGuru.LgUserRegisterPageObject;
import pageObjects.liveGuru.LgUserSearchTermsPageObject;

public class LgPageGeneratorManager {
	
	public static LgUserHomePageObject getUserHomePage(WebDriver driver) {
		return new LgUserHomePageObject(driver);
	}
	
	public static LgUserLoginPageObject getUserLoginPage(WebDriver driver) {
		return new LgUserLoginPageObject(driver);
	}
	
	public static LgUserRegisterPageObject getUserRegisterPage(WebDriver driver) {
		return new LgUserRegisterPageObject(driver);
	}
	
	public static LgUserMyAccountDashboardPageObject getUserMyAccountDashboardPage(WebDriver driver) {
		return new LgUserMyAccountDashboardPageObject(driver);
	}

	public static LgUserAccountInformationPageObject getUserAccountInformationPage(WebDriver driver) {
		return new LgUserAccountInformationPageObject(driver);
	}

	public static LgUserRecurringProfilesPageObject getUserRecurringProfilesPage(WebDriver driver) {
		return new LgUserRecurringProfilesPageObject(driver);
	}

	public static LgUserNewsletterSubscriptionPageObject getUserNewsletterSubscriptionPage(WebDriver driver) {
		return new LgUserNewsletterSubscriptionPageObject(driver);
	}
	
	public static LgUserContactUsPageObject getUserContactUsPage(WebDriver driver) {
		return new LgUserContactUsPageObject(driver);
	}
	
	public static LgUserPrivacyPolicyPageObject getUserPrivacyPolicyPage(WebDriver driver) {
		return new LgUserPrivacyPolicyPageObject(driver);
	}
	
	public static LgUserSearchTermsPageObject getUserSearchTermsPage(WebDriver driver) {
		return new LgUserSearchTermsPageObject(driver);
	}
	
	public static LgAdminLoginPageObject getAdminLoginPage(WebDriver driver) {
		return new LgAdminLoginPageObject(driver);
	}
	
	public static LgAdminCustomersPageObject getAdminCustomersPage(WebDriver driver) {
		return new LgAdminCustomersPageObject(driver);
	}
	
}
