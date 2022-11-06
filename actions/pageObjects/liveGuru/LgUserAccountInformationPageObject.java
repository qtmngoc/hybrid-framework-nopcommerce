package pageObjects.liveGuru;

import org.openqa.selenium.WebDriver;

import pageObjects.liveGuru.navigation.LgUserSidebarMyAccountPageObject;
import pageUIs.liveGuru.LgUserAccountInformationPageUI;

public class LgUserAccountInformationPageObject extends LgUserSidebarMyAccountPageObject {
	
	private WebDriver driver;

	public LgUserAccountInformationPageObject(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	public boolean isAccountInformationHeaderDisplayed() {
		waitForElementVisible(driver, LgUserAccountInformationPageUI.ACCOUNT_INFORMATION_HEADER);
		return isElementDisplayed(driver, LgUserAccountInformationPageUI.ACCOUNT_INFORMATION_HEADER);
	}
	
}
