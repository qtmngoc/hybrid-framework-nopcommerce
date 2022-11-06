package pageObjects.liveGuru;

import org.openqa.selenium.WebDriver;

import commons.liveGuru.LgPageGeneratorManager;
import pageObjects.liveGuru.navigation.LgUserSidebarMyAccountPageObject;
import pageUIs.liveGuru.LgUserMyDashboardPageUI;

public class LgUserMyDashboardPageObject extends LgUserSidebarMyAccountPageObject {
	
	private WebDriver driver;

	public LgUserMyDashboardPageObject(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}
	
	public boolean isMyDashboardHeaderDisplayed() {
		waitForElementVisible(driver, LgUserMyDashboardPageUI.MY_DASHBOARD_HEADER);
		return isElementDisplayed(driver, LgUserMyDashboardPageUI.MY_DASHBOARD_HEADER);
	}

	public boolean isRegisterSuccessMessageDisplayed() {
		waitForElementVisible(driver, LgUserMyDashboardPageUI.REGISTER_SUCCESS_MESSAGE);
		return isElementDisplayed(driver, LgUserMyDashboardPageUI.REGISTER_SUCCESS_MESSAGE);
	}

	public String getRegisterSuccessMessage() {
		waitForElementVisible(driver, LgUserMyDashboardPageUI.REGISTER_SUCCESS_MESSAGE);
		return getElementText(driver, LgUserMyDashboardPageUI.REGISTER_SUCCESS_MESSAGE);
	}

	public void clickOnAccountMenu() {
		waitForElementClickable(driver, LgUserMyDashboardPageUI.ACCOUNT_MENU);
		clickOnElement(driver, LgUserMyDashboardPageUI.ACCOUNT_MENU);
	}

	public String getWelcomeMessage() {
		waitForElementVisible(driver, LgUserMyDashboardPageUI.WELCOME_MESSAGE);
		return getElementText(driver, LgUserMyDashboardPageUI.WELCOME_MESSAGE);
	}

	public String getContactInformation() {
		waitForElementVisible(driver, LgUserMyDashboardPageUI.CONTACT_INFORMATION);
		return getElementText(driver, LgUserMyDashboardPageUI.CONTACT_INFORMATION);
	}
	
	public boolean isContactInformationDisplayed(String contactInfoValue) {
		waitForElementVisible(driver, LgUserMyDashboardPageUI.CONTACT_INFORMATION);
		String actualContactInfoText = getElementText(driver, LgUserMyDashboardPageUI.CONTACT_INFORMATION);
		return actualContactInfoText.contains(contactInfoValue);
	}
	
	public LgUserHomePageObject clickOnLogOutLink() {
		waitForElementClickable(driver, LgUserMyDashboardPageUI.LOGOUT_LINK);
		clickOnElement(driver, LgUserMyDashboardPageUI.LOGOUT_LINK);
		return LgPageGeneratorManager.getUserHomePage(driver);
	}
	
}
