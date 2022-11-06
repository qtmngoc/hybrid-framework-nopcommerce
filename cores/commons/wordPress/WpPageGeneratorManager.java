package commons.wordPress;

import org.openqa.selenium.WebDriver;

import pageObjects.wordPress.WpAdminCategoryPO;
import pageObjects.wordPress.WpAdminDashboardPO;
import pageObjects.wordPress.WpAdminLoginPO;
import pageObjects.wordPress.WpAdminPageAllPO;
import pageObjects.wordPress.WpAdminPageNewPO;
import pageObjects.wordPress.WpAdminPostAllPO;
import pageObjects.wordPress.WpAdminPostNewPO;
import pageObjects.wordPress.WpUserHomePO;
import pageObjects.wordPress.WpUserPageDetailPO;
import pageObjects.wordPress.WpUserPostDetailPO;
import pageObjects.wordPress.WpUserSearchPO;

public class WpPageGeneratorManager {
	
	public static WpAdminLoginPO getAdminLoginPage(WebDriver driver) {
		return new WpAdminLoginPO(driver);
	}
	
	public static WpAdminDashboardPO getAdminDashboardPage(WebDriver driver) {
		return new WpAdminDashboardPO(driver);
	}
	
	public static WpAdminPostAllPO getAdminPostAllPage(WebDriver driver) {
		return new WpAdminPostAllPO(driver);
	}
	
	public static WpAdminPostNewPO getAdminPostNewPage(WebDriver driver) {
		return new WpAdminPostNewPO(driver);
	}
	
	public static WpAdminPageAllPO getAdminPageAllPage(WebDriver driver) {
		return new WpAdminPageAllPO(driver);
	}
	
	public static WpAdminPageNewPO getAdminPageNewPage(WebDriver driver) {
		return new WpAdminPageNewPO(driver);
	}
	
	public static WpAdminCategoryPO getAdminCategoryPage(WebDriver driver) {
		return new WpAdminCategoryPO(driver);
	}
	
	public static WpUserHomePO getUserHomePage(WebDriver driver) {
		return new WpUserHomePO(driver);
	}
	
	public static WpUserPostDetailPO getUserPostDetailPage(WebDriver driver) {
		return new WpUserPostDetailPO(driver);
	}
	
	public static WpUserPageDetailPO getUserPageDetailPage(WebDriver driver) {
		return new WpUserPageDetailPO(driver);
	}
	
	public static WpUserSearchPO getUserSearchPage(WebDriver driver) {
		return new WpUserSearchPO(driver);
	}
	
}
