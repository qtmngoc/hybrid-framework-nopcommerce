package commons.liveGuru;

import org.openqa.selenium.WebDriver;

import pageObjects.liveGuru.admin.AdminCustomersPageObject;
import pageObjects.liveGuru.admin.AdminLoginPageObject;
import pageObjects.liveGuru.user.UserHomePageObject;
import pageObjects.liveGuru.user.UserLoginPageObject;
import pageObjects.liveGuru.user.UserMyDashboardPageObject;
import pageObjects.liveGuru.user.UserRegisterPageObject;

public class PageGeneratorManager {
	
	public static UserHomePageObject getUserHomePage(WebDriver driver) {
		return new UserHomePageObject(driver);
	}

	public static UserLoginPageObject getUserLoginPage(WebDriver driver) {
		return new UserLoginPageObject(driver);
	}

	public static UserRegisterPageObject getUserRegisterPage(WebDriver driver) {
		return new UserRegisterPageObject(driver);
	}

	public static UserMyDashboardPageObject getUserMyDashboardPage(WebDriver driver) {
		return new UserMyDashboardPageObject(driver);
	}
	
	public static AdminLoginPageObject getAdminLoginPage(WebDriver driver) {
		return new AdminLoginPageObject(driver);
	}
	
	public static AdminCustomersPageObject getAdminCustomersPage(WebDriver driver) {
		return new AdminCustomersPageObject(driver);
	}
	
}
