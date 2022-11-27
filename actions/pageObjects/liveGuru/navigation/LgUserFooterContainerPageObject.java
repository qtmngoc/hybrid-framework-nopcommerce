package pageObjects.liveGuru.navigation;

import org.openqa.selenium.WebDriver;

import commons.liveGuru.LgBasePage;
import pageUIs.liveGuru.navigation.LgUserFooterContainerPageUI;

public class LgUserFooterContainerPageObject extends LgBasePage {

	WebDriver driver;

	public LgUserFooterContainerPageObject(WebDriver driver) {
		this.driver = driver;
	}
	
	public void openFooterPageByTitle(String pageTitle) {
		waitForElementClickable(driver, LgUserFooterContainerPageUI.DYNAMIC_FOOTER_LINK, pageTitle);
		clickOnElement(driver, LgUserFooterContainerPageUI.DYNAMIC_FOOTER_LINK, pageTitle);
	}

}
