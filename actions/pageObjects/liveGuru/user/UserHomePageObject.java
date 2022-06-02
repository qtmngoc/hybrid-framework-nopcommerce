package pageObjects.liveGuru.user;

import org.openqa.selenium.WebDriver;

import commons.liveGuru.BasePage;
import commons.liveGuru.PageGeneratorManager;
import pageUIs.liveGuru.user.UserHomePageUI;

public class UserHomePageObject extends BasePage {
	
	private WebDriver driver;

	public UserHomePageObject(WebDriver driver) {
		this.driver = driver;
	}

	public boolean isGuruLogoDisplayed() {
		waitElementVisible(driver, UserHomePageUI.GURU_LOGO);
		return isElementDisplayed(driver, UserHomePageUI.GURU_LOGO);
	}
	
	public UserLoginPageObject clickOnMyAccountLink() {
		waitElementClickable(driver, UserHomePageUI.MY_ACCOUNT_LINK);
		clickOnElement(driver, UserHomePageUI.MY_ACCOUNT_LINK);
		return PageGeneratorManager.getUserLoginPage(driver);
	}
	
}
