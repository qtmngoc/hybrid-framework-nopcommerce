package pageObjects.liveGuru;

import org.openqa.selenium.WebDriver;

import commons.liveGuru.BasePage;
import pageUIs.liveGuru.HomePageUI;

public class HomePageObject extends BasePage {
	private WebDriver driver;

	public HomePageObject(WebDriver driver) {
		this.driver = driver;
	}

	public LoginPageObject clickOnMyAccountLink() {
		waitElementClickable(driver, HomePageUI.MY_ACCOUNT_LINK);
		clickOnElement(driver, HomePageUI.MY_ACCOUNT_LINK);
		return new LoginPageObject(driver);
	}

	public boolean isGuruLogoDisplayed() {
		waitElementVisible(driver, HomePageUI.GURU_LOGO);
		return isElementDisplayed(driver, HomePageUI.GURU_LOGO);
	}
}
