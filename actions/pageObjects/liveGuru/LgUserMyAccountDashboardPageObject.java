package pageObjects.liveGuru;

import org.openqa.selenium.WebDriver;

import commons.liveGuru.LgPageGeneratorManager;
import pageObjects.liveGuru.navigation.LgUserSidebarMyAccountPageObject;
import pageUIs.liveGuru.LgUserMyAccountDashboardPageUI;

public class LgUserMyAccountDashboardPageObject extends LgUserSidebarMyAccountPageObject {
	
	private WebDriver driver;

	public LgUserMyAccountDashboardPageObject(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}
	
	public boolean isMyDashboardHeaderDisplayed() {
		waitForElementVisible(driver, LgUserMyAccountDashboardPageUI.MY_DASHBOARD_HEADER);
		return isElementDisplayed(driver, LgUserMyAccountDashboardPageUI.MY_DASHBOARD_HEADER);
	}

	public boolean isRegisterSuccessMessageDisplayed() {
		waitForElementVisible(driver, LgUserMyAccountDashboardPageUI.REGISTER_SUCCESS_MESSAGE);
		return isElementDisplayed(driver, LgUserMyAccountDashboardPageUI.REGISTER_SUCCESS_MESSAGE);
	}

	public String getRegisterSuccessMessage() {
		waitForElementVisible(driver, LgUserMyAccountDashboardPageUI.REGISTER_SUCCESS_MESSAGE);
		return getElementText(driver, LgUserMyAccountDashboardPageUI.REGISTER_SUCCESS_MESSAGE);
	}

	public void clickOnAccountMenu() {
		waitForElementClickable(driver, LgUserMyAccountDashboardPageUI.ACCOUNT_MENU);
		clickOnElement(driver, LgUserMyAccountDashboardPageUI.ACCOUNT_MENU);
	}

	public String getWelcomeMessage() {
		waitForElementVisible(driver, LgUserMyAccountDashboardPageUI.WELCOME_MESSAGE);
		return getElementText(driver, LgUserMyAccountDashboardPageUI.WELCOME_MESSAGE);
	}

	public String getContactInformation() {
		waitForElementVisible(driver, LgUserMyAccountDashboardPageUI.CONTACT_INFORMATION);
		return getElementText(driver, LgUserMyAccountDashboardPageUI.CONTACT_INFORMATION);
	}
	
	public boolean isContactInformationDisplayed(String contactInfoValue) {
		waitForElementVisible(driver, LgUserMyAccountDashboardPageUI.CONTACT_INFORMATION);
		String actualContactInfoText = getElementText(driver, LgUserMyAccountDashboardPageUI.CONTACT_INFORMATION);
		return actualContactInfoText.contains(contactInfoValue);
	}
	
	public LgUserHomePageObject clickOnLogOutLink() {
		waitForElementClickable(driver, LgUserMyAccountDashboardPageUI.LOGOUT_LINK);
		clickOnElement(driver, LgUserMyAccountDashboardPageUI.LOGOUT_LINK);
		return LgPageGeneratorManager.getUserHomePage(driver);
	}
	
}
