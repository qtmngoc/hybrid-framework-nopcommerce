package pageObjects.nopCommerce;

import org.openqa.selenium.WebDriver;

import commons.nopCommerce.NcBasePage;
import commons.nopCommerce.NcPageGeneratorManager;
import pageUIs.nopCommerce.NcAdminLoginPUI;

public class NcAdminLoginPO extends NcBasePage {
	
	private WebDriver driver;

	public NcAdminLoginPO(WebDriver driver) {
		this.driver = driver;
	}
	
	public NcAdminDashboardPO loginAsAdmin(String emailValue, String passwordValue) {
		sendKeysToElement(driver, NcAdminLoginPUI.EMAIL_TEXTBOX, emailValue);
		sendKeysToElement(driver, NcAdminLoginPUI.PASSWORD_TEXTBOX, passwordValue);
		clickOnElement(driver, NcAdminLoginPUI.LOGIN_BUTTON);
		return NcPageGeneratorManager.getAdminDashboardPage(driver);
	}

}
