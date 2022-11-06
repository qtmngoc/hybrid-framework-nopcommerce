package pageObjects.nopCommerce;

import org.openqa.selenium.WebDriver;

import commons.nopCommerce.NcBasePage;
import pageUIs.nopCommerce.NcAdminDashboardPUI;

public class NcAdminDashboardPO extends NcBasePage {
	
	private WebDriver driver;

	public NcAdminDashboardPO(WebDriver driver) {
		this.driver = driver;
	}
	
	public boolean isDashboardHeaderDisplayed() {
		return isElementDisplayed(driver, NcAdminDashboardPUI.DASHBOARD_HEADER);
	}

}
