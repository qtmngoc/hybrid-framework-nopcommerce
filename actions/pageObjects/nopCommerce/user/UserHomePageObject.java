package pageObjects.nopCommerce.user;

import org.openqa.selenium.WebDriver;

import commons.nopCommerce.BasePage;
import commons.nopCommerce.PageGeneratorManager;
import pageUIs.nopCommerce.user.UserHomePageUI;

public class UserHomePageObject extends BasePage {
	
	private WebDriver driver;

	public UserHomePageObject(WebDriver driver) {
		this.driver = driver;
	}

	public UserRegisterPageObject clickOnRegisterLink() {
		clickOnElement(driver, UserHomePageUI.REGISTER_LINK);
		// return new RegisterPageObject(driver);
		return PageGeneratorManager.getUserRegisterPage(driver);
	}

	public UserLoginPageObject clickOnLoginLink() {
		clickOnElement(driver, UserHomePageUI.LOGIN_LINK);
		return PageGeneratorManager.getUserLoginPage(driver);
	}

	public boolean isMyAccountLinkDisplayed() {
		return isElementDisplayed(driver, UserHomePageUI.MY_ACCOUNT_LINK);
	}

	public boolean isLogoutLinkDisplayed() {
		return isElementDisplayed(driver, UserHomePageUI.LOGOUT_LINK);
	}

	public UserCustomerInfoPageObject clickOnMyAccountLink() {
		clickOnElement(driver, UserHomePageUI.MY_ACCOUNT_LINK);		
		return PageGeneratorManager.getUserCustomerInfoPage(driver);
	}
	
}
