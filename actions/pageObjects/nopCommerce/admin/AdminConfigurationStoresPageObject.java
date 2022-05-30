package pageObjects.nopCommerce.admin;

import org.openqa.selenium.WebDriver;

import commons.nopCommerce.BasePage;
import pageUIs.nopCommerce.admin.AdminConfigurationStoresPageUI;

public class AdminConfigurationStoresPageObject extends BasePage {
	
	private WebDriver driver;

	public AdminConfigurationStoresPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public boolean isStoresHeaderDisplayed() {
		return isElementDisplayed(driver, AdminConfigurationStoresPageUI.STORES_HEADER);
	}

}
