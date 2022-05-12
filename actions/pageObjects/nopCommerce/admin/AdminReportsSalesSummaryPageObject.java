package pageObjects.nopCommerce.admin;

import org.openqa.selenium.WebDriver;

import commons.nopCommerce.BasePage;
import commons.nopCommerce.PageGeneratorManager;
import pageUIs.nopCommerce.admin.AdminConfigurationStoresPageUI;
import pageUIs.nopCommerce.admin.AdminLoginPageUI;
import pageUIs.nopCommerce.admin.AdminReportsSalesSummaryPageUI;

public class AdminReportsSalesSummaryPageObject extends BasePage {
	
	private WebDriver driver;

	public AdminReportsSalesSummaryPageObject(WebDriver driver) {
		this.driver = driver;
	}
	
	public boolean isSalesSummaryHeaderDisplayed() {
		return isElementDisplayed(driver, AdminReportsSalesSummaryPageUI.SALES_SUMMARY_HEADER);
	}

}
