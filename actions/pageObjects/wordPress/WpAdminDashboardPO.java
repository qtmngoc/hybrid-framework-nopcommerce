package pageObjects.wordPress;

import org.openqa.selenium.WebDriver;

import commons.wordPress.WpBasePage;
import commons.wordPress.WpPageGeneratorManager;
import pageUIs.wordPress.WpAdminDashboardPUI;

public class WpAdminDashboardPO extends WpBasePage{
	WebDriver driver;

	public WpAdminDashboardPO(WebDriver driver) {
		this.driver = driver;
	}

	public boolean isDashboardHeaderDisplayed() {
		return isElementDisplayed(driver, WpAdminDashboardPUI.DASHBOARD_HEADER);
	}
	
	public WpAdminPostAllPO clickOnPostMenu() {
		clickOnElement(driver, WpAdminDashboardPUI.POST_MENU);
		return WpPageGeneratorManager.getAdminPostAllPage(driver);
	}

	public WpAdminCategoryPO clickOnPostsCategoriesMenuLink() {
		clickOnElement(driver, WpAdminDashboardPUI.POST_MENU);
		clickOnElement(driver, WpAdminDashboardPUI.CATEGORY_LINK);
		return WpPageGeneratorManager.getAdminCategoryPage(driver);
	}
	
	public WpAdminPageAllPO clickOnPageMenuLink() {
		clickOnElement(driver, WpAdminDashboardPUI.PAGE_LINK);
		return WpPageGeneratorManager.getAdminPageAllPage(driver);
	}

}
