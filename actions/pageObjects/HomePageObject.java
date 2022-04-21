package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.HomePageUI;

public class HomePageObject extends BasePage {
	private WebDriver driver;

	public HomePageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void clickOnRegisterLink() {
		waitElementClickable(driver, HomePageUI.REGISTER_LINK);
		clickOnElement(driver, HomePageUI.REGISTER_LINK);
	}

	public void clickOnLoginLink() {
		waitElementClickable(driver, HomePageUI.LOGIN_LINK);
		clickOnElement(driver, HomePageUI.LOGIN_LINK);
	}

	public boolean isMyAccountLinkDisplayed() {
		waitElementVisible(driver, HomePageUI.MY_ACCOUNT_LINK);
		return isElementDisplayed(driver, HomePageUI.MY_ACCOUNT_LINK);
	}

	public boolean isLogoutLinkDisplayed() {
		waitElementVisible(driver, HomePageUI.LOGOUT_LINK);
		return isElementDisplayed(driver, HomePageUI.LOGOUT_LINK);
	}
}
