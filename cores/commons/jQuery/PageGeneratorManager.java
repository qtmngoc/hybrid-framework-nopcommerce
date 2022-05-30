package commons.jQuery;

import org.openqa.selenium.WebDriver;

import pageObjects.jQuery.HomePageObject;

public class PageGeneratorManager {

	public static HomePageObject getHomePage(WebDriver driver) {
		return new HomePageObject(driver);
	}

}
