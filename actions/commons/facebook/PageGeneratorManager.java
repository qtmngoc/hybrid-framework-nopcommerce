package commons.facebook;

import org.openqa.selenium.WebDriver;

import pageObjects.facebook.LoginPageObject;

public class PageGeneratorManager {

	public static LoginPageObject getLoginPage(WebDriver driver) {
		return new LoginPageObject(driver);
	}
	
}
