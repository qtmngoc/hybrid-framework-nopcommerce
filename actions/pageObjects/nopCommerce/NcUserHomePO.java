package pageObjects.nopCommerce;

import org.openqa.selenium.WebDriver;

import commons.nopCommerce.NcBasePage;
import commons.nopCommerce.NcPageGeneratorManager;
import io.qameta.allure.Step;
import pageUIs.nopCommerce.NcUserHomePUI;

public class NcUserHomePO extends NcBasePage {
	
	private WebDriver driver;

	public NcUserHomePO(WebDriver driver) {
		this.driver = driver;
	}

	@Step("Navigate to Register page")
	public NcUserRegisterPO clickOnRegisterLink() {
		clickOnElement(driver, NcUserHomePUI.REGISTER_LINK);
		// return new RegisterPageObject(driver);
		return NcPageGeneratorManager.getUserRegisterPage(driver);
	}

	@Step("Navigate to Login page")
	public NcUserLoginPO clickOnLoginLink() {
		clickOnElement(driver, NcUserHomePUI.LOGIN_LINK);
		return NcPageGeneratorManager.getUserLoginPage(driver);
	}

	@Step("Verify My Account link is displayed")
	public boolean isMyAccountLinkDisplayed() {
		return isElementDisplayed(driver, NcUserHomePUI.MY_ACCOUNT_LINK);
	}

	@Step("Verify Logout link is displayed")
	public boolean isLogoutLinkDisplayed() {
		return isElementDisplayed(driver, NcUserHomePUI.LOGOUT_LINK);
	}

	@Step("Navigate to Customer Info page")
	public NcUserCustomerInfoPO clickOnMyAccountLink() {
		clickOnElement(driver, NcUserHomePUI.MY_ACCOUNT_LINK);		
		return NcPageGeneratorManager.getUserCustomerInfoPage(driver);
	}

	public NcUserSearchPO clickOnSearchLink() {
		clickOnElement(driver, NcUserHomePUI.SEARCH_LINK);		
		return NcPageGeneratorManager.getUserSearchPage(driver);
	}
	
}
