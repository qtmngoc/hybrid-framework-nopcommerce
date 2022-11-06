package pageObjects.liveGuru;

import org.openqa.selenium.WebDriver;

import commons.liveGuru.LgBasePage;
import commons.liveGuru.LgPageGeneratorManager;
import pageUIs.liveGuru.LgUserHomePageUI;

public class LgUserHomePageObject extends LgBasePage {
	
	private WebDriver driver;

	public LgUserHomePageObject(WebDriver driver) {
		this.driver = driver;
	}

	public boolean isGuruLogoDisplayed() {
		waitForElementVisible(driver, LgUserHomePageUI.GURU_LOGO);
		return isElementDisplayed(driver, LgUserHomePageUI.GURU_LOGO);
	}
	
	public LgUserLoginPageObject clickOnMyAccountLink() {
		waitForElementClickable(driver, LgUserHomePageUI.MY_ACCOUNT_LINK);
		clickOnElement(driver, LgUserHomePageUI.MY_ACCOUNT_LINK);
		return LgPageGeneratorManager.getUserLoginPage(driver);
	}

	public void clickOnAccountMenu() {
		waitForElementClickable(driver, LgUserHomePageUI.ACCOUNT_MENU);
		clickOnElement(driver, LgUserHomePageUI.ACCOUNT_MENU);
	}

	public LgUserRegisterPageObject clickOnRegisterLink() {
		waitForElementClickable(driver, LgUserHomePageUI.REGISTER_LINK);
		clickOnElement(driver, LgUserHomePageUI.REGISTER_LINK);
		return LgPageGeneratorManager.getUserRegisterPage(driver);
	}
	
}
