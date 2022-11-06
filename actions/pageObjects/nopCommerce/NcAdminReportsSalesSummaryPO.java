package pageObjects.nopCommerce;

import org.openqa.selenium.WebDriver;

import commons.nopCommerce.NcBasePage;
import pageUIs.nopCommerce.NcAdminReportsSalesSummaryPUI;

public class NcAdminReportsSalesSummaryPO extends NcBasePage {
	
	private WebDriver driver;

	public NcAdminReportsSalesSummaryPO(WebDriver driver) {
		this.driver = driver;
	}
	
	public boolean isSalesSummaryHeaderDisplayed() {
		return isElementDisplayed(driver, NcAdminReportsSalesSummaryPUI.SALES_SUMMARY_HEADER);
	}

}
