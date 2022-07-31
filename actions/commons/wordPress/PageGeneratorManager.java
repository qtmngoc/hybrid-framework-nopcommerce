package commons.wordPress;

import org.openqa.selenium.WebDriver;

import pageObjects.wordPress.AdminCategoryPO;
import pageObjects.wordPress.AdminDashboardPO;
import pageObjects.wordPress.AdminLoginPO;
import pageObjects.wordPress.AdminPostAllPO;
import pageObjects.wordPress.AdminPostNewPO;
import pageObjects.wordPress.UserHomePO;
import pageObjects.wordPress.UserPostDetailPO;
import pageObjects.wordPress.UserSearchPO;

public class PageGeneratorManager {
	
	public static AdminLoginPO getAdminLoginPage(WebDriver driver) {
		return new AdminLoginPO(driver);
	}
	
	public static AdminDashboardPO getAdminDashboardPage(WebDriver driver) {
		return new AdminDashboardPO(driver);
	}
	
	public static AdminPostAllPO getAdminPostAllPage(WebDriver driver) {
		return new AdminPostAllPO(driver);
	}
	
	public static AdminPostNewPO getAdminPostNewPage(WebDriver driver) {
		return new AdminPostNewPO(driver);
	}
	
	public static AdminCategoryPO getAdminCategoryPage(WebDriver driver) {
		return new AdminCategoryPO(driver);
	}
	
	public static UserHomePO getUserHomePage(WebDriver driver) {
		return new UserHomePO(driver);
	}
	
	public static UserPostDetailPO getUserPostDetailPage(WebDriver driver) {
		return new UserPostDetailPO(driver);
	}
	
	public static UserSearchPO getUserSearchPage(WebDriver driver) {
		return new UserSearchPO(driver);
	}
	
	
	
}
