package commons.jQuery;

import org.openqa.selenium.WebDriver;

import pageObjects.jQuery.dataTable.HomePageObject;

public class PageGeneratorManager {

	public static HomePageObject getHomePage(WebDriver driver) {
		return new HomePageObject(driver);
	}

}
