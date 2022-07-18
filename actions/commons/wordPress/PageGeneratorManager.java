package commons.wordPress;

import org.openqa.selenium.WebDriver;

import pageObjects.wordPress.admin.AdminLoginPO;

public class PageGeneratorManager {
	
	public static AdminLoginPO getAdminLoginPage(WebDriver driver) {
		return new AdminLoginPO(driver);
	}
}
