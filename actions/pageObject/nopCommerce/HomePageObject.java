package pageObject.nopCommerce;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUI.nopCommerce.HomePageUI;

public class HomePageObject extends BasePage {
	private WebDriver driver;

	public HomePageObject(WebDriver driver) {
		this.driver = driver;
	}

	public RegisterPageObject clickOnRegisterLink() {
		waitElementClickable(driver, HomePageUI.REGISTER_LINK);
		clickOnElement(driver, HomePageUI.REGISTER_LINK);
		// return new RegisterPageObject(driver);
		return PageGeneratorManager.getRegisterPage(driver);
	}

	public LoginPageObject clickOnLoginLink() {
		waitElementClickable(driver, HomePageUI.LOGIN_LINK);
		clickOnElement(driver, HomePageUI.LOGIN_LINK);
		return PageGeneratorManager.getLoginPage(driver);
	}

	public boolean isMyAccountLinkDisplayed() {
		waitElementVisible(driver, HomePageUI.MY_ACCOUNT_LINK);
		return isElementDisplayed(driver, HomePageUI.MY_ACCOUNT_LINK);
	}

	public boolean isLogoutLinkDisplayed() {
		waitElementVisible(driver, HomePageUI.LOGOUT_LINK);
		return isElementDisplayed(driver, HomePageUI.LOGOUT_LINK);
	}

	public MyAccountPageObject clickOnMyAccountLink() {
		waitElementClickable(driver, HomePageUI.MY_ACCOUNT_LINK);
		clickOnElement(driver, HomePageUI.MY_ACCOUNT_LINK);		
		return PageGeneratorManager.getMyAccountPage(driver);
	}
}
