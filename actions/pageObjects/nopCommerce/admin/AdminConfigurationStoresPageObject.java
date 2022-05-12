package pageObjects.nopCommerce.admin;

import org.openqa.selenium.WebDriver;

import commons.nopCommerce.BasePage;
import commons.nopCommerce.PageGeneratorManager;
import pageUIs.nopCommerce.admin.AdminConfigurationStoresPageUI;
import pageUIs.nopCommerce.admin.AdminLoginPageUI;

public class AdminConfigurationStoresPageObject extends BasePage {
	
	private WebDriver driver;

	public AdminConfigurationStoresPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public boolean isStoresHeaderDisplayed() {
		return isElementDisplayed(driver, AdminConfigurationStoresPageUI.STORES_HEADER);
	}

}
