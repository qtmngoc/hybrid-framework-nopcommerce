package commons.facebook;

import org.openqa.selenium.WebDriver;

import pageObjects.facebook.FbLoginPageObject;

public class FbPageGeneratorManager {

	public static FbLoginPageObject getLoginPage(WebDriver driver) {
		return new FbLoginPageObject(driver);
	}
	
}
