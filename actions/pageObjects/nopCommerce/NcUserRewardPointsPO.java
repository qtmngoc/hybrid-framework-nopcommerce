package pageObjects.nopCommerce;

import org.openqa.selenium.WebDriver;

import commons.nopCommerce.NcBasePage;
import pageUIs.nopCommerce.NcUserRewardPointsPUI;

public class NcUserRewardPointsPO extends NcBasePage {
	
	private WebDriver driver;

	public NcUserRewardPointsPO(WebDriver driver) {
		this.driver = driver;
	}

	public boolean isRewardPointsHeaderDisplayed() {
		return isElementDisplayed(driver, NcUserRewardPointsPUI.REWARD_POINTS_HEADER);
	}
	
}
