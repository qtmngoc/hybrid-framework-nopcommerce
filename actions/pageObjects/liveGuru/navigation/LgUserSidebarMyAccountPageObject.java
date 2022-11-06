package pageObjects.liveGuru.navigation;

import org.openqa.selenium.WebDriver;

import commons.liveGuru.LgBasePage;
import commons.liveGuru.LgPageGeneratorManager;
import pageObjects.liveGuru.LgUserAccountInformationPageObject;
import pageObjects.liveGuru.LgUserMyDashboardPageObject;
import pageObjects.liveGuru.LgUserNewsletterSubscriptionPageObject;
import pageObjects.liveGuru.LgUserRecurringProfilesPageObject;
import pageUIs.liveGuru.navigation.LgUserSidebarMyAccountPageUI;

public class LgUserSidebarMyAccountPageObject extends LgBasePage {

	WebDriver driver;

	public LgUserSidebarMyAccountPageObject(WebDriver driver) {
		this.driver = driver;
	}
	
	public void openMyAccountSidebarPageByTitle(String pageTitle) {
		waitForElementClickable(driver, LgUserSidebarMyAccountPageUI.DYNAMIC_SIDEBAR_LINK, pageTitle);
		clickOnElement(driver, LgUserSidebarMyAccountPageUI.DYNAMIC_SIDEBAR_LINK, pageTitle);
	}
	
	public LgUserMyDashboardPageObject openAccountDashboardPage() {
		waitForElementClickable(driver, LgUserSidebarMyAccountPageUI.ACCOUNT_DASHBOARD_LINK);
		clickOnElement(driver, LgUserSidebarMyAccountPageUI.ACCOUNT_DASHBOARD_LINK);
		return LgPageGeneratorManager.getUserMyDashboardPage(driver);
	}
	
	public LgUserAccountInformationPageObject openAccountInformationPage() {
		waitForElementClickable(driver, LgUserSidebarMyAccountPageUI.ACCOUNT_INFORMATION_LINK);
		clickOnElement(driver, LgUserSidebarMyAccountPageUI.ACCOUNT_INFORMATION_LINK);
		return LgPageGeneratorManager.getUserAccountInformationPage(driver);
	}
	
	public LgUserRecurringProfilesPageObject openRecurringProfilesPage() {
		waitForElementClickable(driver, LgUserSidebarMyAccountPageUI.RECURRING_PROFILES_LINK);
		clickOnElement(driver, LgUserSidebarMyAccountPageUI.RECURRING_PROFILES_LINK);
		return LgPageGeneratorManager.getUserRecurringProfilesPage(driver);
	}

	public LgUserNewsletterSubscriptionPageObject openNewsletterSubscriptionPage() {
		waitForElementClickable(driver, LgUserSidebarMyAccountPageUI.NEWSLETTER_SUBSCRIPTION_LINK);
		clickOnElement(driver, LgUserSidebarMyAccountPageUI.NEWSLETTER_SUBSCRIPTION_LINK);
		return LgPageGeneratorManager.getUserNewsletterSubscriptionPage(driver);
	}

}
