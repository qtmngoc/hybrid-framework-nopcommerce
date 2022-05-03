package pageObjects.liveGuru;

import org.openqa.selenium.WebDriver;

import commons.liveGuru.BasePage;
import pageUIs.liveGuru.MyDashboardPageUI;

public class MyDashboardPageObject extends BasePage {
	private WebDriver driver;

	public MyDashboardPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public boolean isRegisterSuccessMessageDisplayed() {
		waitElementVisible(driver, MyDashboardPageUI.REGISTER_SUCCESS_MESSAGE);
		return isElementDisplayed(driver, MyDashboardPageUI.REGISTER_SUCCESS_MESSAGE);
	}

	public String getRegisterSuccessMessage() {
		waitElementVisible(driver, MyDashboardPageUI.REGISTER_SUCCESS_MESSAGE);
		return getElementText(driver, MyDashboardPageUI.REGISTER_SUCCESS_MESSAGE);
	}

	public void clickOnAccountMenu() {
		waitElementClickable(driver, MyDashboardPageUI.ACCOUNT_MENU);
		clickOnElement(driver, MyDashboardPageUI.ACCOUNT_MENU);
	}

	public HomePageObject clickOnLogOutLink() {
		waitElementClickable(driver, MyDashboardPageUI.LOGOUT_LINK);
		clickOnElement(driver, MyDashboardPageUI.LOGOUT_LINK);
		return new HomePageObject(driver);
	}

	public String getWelcomeMessage() {
		waitElementVisible(driver, MyDashboardPageUI.WELCOME_MESSAGE);
		return getElementText(driver, MyDashboardPageUI.WELCOME_MESSAGE);
	}

	public String getContactInformation() {
		waitElementVisible(driver, MyDashboardPageUI.CONTACT_INFORMATION);
		return getElementText(driver, MyDashboardPageUI.CONTACT_INFORMATION);
	}
}
