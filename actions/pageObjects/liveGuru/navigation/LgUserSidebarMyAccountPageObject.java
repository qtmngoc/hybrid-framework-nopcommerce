package pageObjects.liveGuru.navigation;

import org.openqa.selenium.WebDriver;

import commons.liveGuru.LgPageGeneratorManager;
import pageObjects.liveGuru.LgUserAccountInformationPageObject;
import pageObjects.liveGuru.LgUserMyAccountDashboardPageObject;
import pageObjects.liveGuru.LgUserNewsletterSubscriptionPageObject;
import pageObjects.liveGuru.LgUserRecurringProfilesPageObject;
import pageUIs.liveGuru.navigation.LgUserSidebarMyAccountPageUI;

public class LgUserSidebarMyAccountPageObject extends LgUserFooterContainerPageObject {

	WebDriver driver;

	public LgUserSidebarMyAccountPageObject(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}
	
	public void openMyAccountSidebarPageByTitle(String pageTitle) {
		waitForElementClickable(driver, LgUserSidebarMyAccountPageUI.DYNAMIC_SIDEBAR_LINK, pageTitle);
		clickOnElement(driver, LgUserSidebarMyAccountPageUI.DYNAMIC_SIDEBAR_LINK, pageTitle);
	}
	
	public LgUserMyAccountDashboardPageObject openAccountDashboardPage() {
		waitForElementClickable(driver, LgUserSidebarMyAccountPageUI.ACCOUNT_DASHBOARD_LINK);
		clickOnElement(driver, LgUserSidebarMyAccountPageUI.ACCOUNT_DASHBOARD_LINK);
		return LgPageGeneratorManager.getUserMyAccountDashboardPage(driver);
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
