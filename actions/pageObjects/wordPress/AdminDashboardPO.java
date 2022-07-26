package pageObjects.wordPress;

import org.openqa.selenium.WebDriver;

import commons.wordPress.BasePage;
import commons.wordPress.PageGeneratorManager;
import pageUIs.wordPress.AdminDashboardPUI;

public class AdminDashboardPO extends BasePage{
	WebDriver driver;

	public AdminDashboardPO(WebDriver driver) {
		this.driver = driver;
	}

	public boolean isDashboardHeaderDisplayed() {
		return isElementDisplayed(driver, AdminDashboardPUI.DASHBOARD_HEADER);
	}
	
	public AdminPostAllPO clickOnPostMenu() {
		clickOnElement(driver, AdminDashboardPUI.POST_MENU);
		return PageGeneratorManager.getAdminPostAllPage(driver);
	}

	public AdminCategoryPO clickOnPostsCategoriesMenuLink() {
		clickOnElement(driver, AdminDashboardPUI.POST_MENU);
		clickOnElement(driver, AdminDashboardPUI.CATEGORY_LINK);
		return PageGeneratorManager.getAdminCategoryPage(driver);
	}

}
