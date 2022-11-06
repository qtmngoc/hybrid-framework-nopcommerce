package commons.liveGuru;

import org.openqa.selenium.WebDriver;

import pageObjects.liveGuru.LgAdminCustomersPageObject;
import pageObjects.liveGuru.LgAdminLoginPageObject;
import pageObjects.liveGuru.LgUserAccountInformationPageObject;
import pageObjects.liveGuru.LgUserHomePageObject;
import pageObjects.liveGuru.LgUserLoginPageObject;
import pageObjects.liveGuru.LgUserMyDashboardPageObject;
import pageObjects.liveGuru.LgUserNewsletterSubscriptionPageObject;
import pageObjects.liveGuru.LgUserRecurringProfilesPageObject;
import pageObjects.liveGuru.LgUserRegisterPageObject;

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
	
	public static LgUserMyDashboardPageObject getUserMyDashboardPage(WebDriver driver) {
		return new LgUserMyDashboardPageObject(driver);
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
	
	public static LgAdminLoginPageObject getAdminLoginPage(WebDriver driver) {
		return new LgAdminLoginPageObject(driver);
	}
	
	public static LgAdminCustomersPageObject getAdminCustomersPage(WebDriver driver) {
		return new LgAdminCustomersPageObject(driver);
	}
	
}
