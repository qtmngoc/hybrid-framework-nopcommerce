package pageObjects.jQuery.dataTable;

import org.openqa.selenium.WebDriver;

public class JqPageGeneratorManager {

	public static JqHomePageObject getHomePage(WebDriver driver) {
		return new JqHomePageObject(driver);
	}

}
