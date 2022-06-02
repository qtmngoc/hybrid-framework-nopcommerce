package pageObjects.liveGuru.user;

import org.openqa.selenium.WebDriver;

import commons.liveGuru.BasePage;
import commons.liveGuru.PageGeneratorManager;
import pageUIs.liveGuru.user.UserMyDashboardPageUI;

public class UserMyDashboardPageObject extends BasePage {
	
	private WebDriver driver;

	public UserMyDashboardPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public boolean isRegisterSuccessMessageDisplayed() {
		waitElementVisible(driver, UserMyDashboardPageUI.REGISTER_SUCCESS_MESSAGE);
		return isElementDisplayed(driver, UserMyDashboardPageUI.REGISTER_SUCCESS_MESSAGE);
	}

	public String getRegisterSuccessMessage() {
		waitElementVisible(driver, UserMyDashboardPageUI.REGISTER_SUCCESS_MESSAGE);
		return getElementText(driver, UserMyDashboardPageUI.REGISTER_SUCCESS_MESSAGE);
	}

	public void clickOnAccountMenu() {
		waitElementClickable(driver, UserMyDashboardPageUI.ACCOUNT_MENU);
		clickOnElement(driver, UserMyDashboardPageUI.ACCOUNT_MENU);
	}

	public String getWelcomeMessage() {
		waitElementVisible(driver, UserMyDashboardPageUI.WELCOME_MESSAGE);
		return getElementText(driver, UserMyDashboardPageUI.WELCOME_MESSAGE);
	}

	public String getContactInformation() {
		waitElementVisible(driver, UserMyDashboardPageUI.CONTACT_INFORMATION);
		return getElementText(driver, UserMyDashboardPageUI.CONTACT_INFORMATION);
	}
	

	public UserHomePageObject clickOnLogOutLink() {
		waitElementClickable(driver, UserMyDashboardPageUI.LOGOUT_LINK);
		clickOnElement(driver, UserMyDashboardPageUI.LOGOUT_LINK);
		return PageGeneratorManager.getUserHomePage(driver);
	}
	
}
