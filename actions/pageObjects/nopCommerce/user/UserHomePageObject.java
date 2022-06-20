package pageObjects.nopCommerce.user;

import org.openqa.selenium.WebDriver;

import commons.nopCommerce.BasePage;
import commons.nopCommerce.PageGeneratorManager;
import io.qameta.allure.Step;
import pageUIs.nopCommerce.user.UserHomePageUI;

public class UserHomePageObject extends BasePage {
	
	private WebDriver driver;

	public UserHomePageObject(WebDriver driver) {
		this.driver = driver;
	}

	@Step("Navigate to Register page")
	public UserRegisterPageObject clickOnRegisterLink() {
		clickOnElement(driver, UserHomePageUI.REGISTER_LINK);
		// return new RegisterPageObject(driver);
		return PageGeneratorManager.getUserRegisterPage(driver);
	}

	@Step("Navigate to Login page")
	public UserLoginPageObject clickOnLoginLink() {
		clickOnElement(driver, UserHomePageUI.LOGIN_LINK);
		return PageGeneratorManager.getUserLoginPage(driver);
	}

	@Step("Verify My Account link is displayed")
	public boolean isMyAccountLinkDisplayed() {
		return isElementDisplayed(driver, UserHomePageUI.MY_ACCOUNT_LINK);
	}

	@Step("Verify Logout link is displayed")
	public boolean isLogoutLinkDisplayed() {
		return isElementDisplayed(driver, UserHomePageUI.LOGOUT_LINK);
	}

	@Step("Navigate to Customer Info page")
	public UserCustomerInfoPageObject clickOnMyAccountLink() {
		clickOnElement(driver, UserHomePageUI.MY_ACCOUNT_LINK);		
		return PageGeneratorManager.getUserCustomerInfoPage(driver);
	}
	
}
