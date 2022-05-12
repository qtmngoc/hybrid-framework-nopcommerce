package pageObjects.nopCommerce.user;

import org.openqa.selenium.WebDriver;

import commons.nopCommerce.BasePage;
import pageUIs.nopCommerce.user.UserRewardPointsPageUI;

public class UserRewardPointsPageObject extends BasePage {
	
	private WebDriver driver;

	public UserRewardPointsPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public boolean isRewardPointsHeaderDisplayed() {
		return isElementDisplayed(driver, UserRewardPointsPageUI.REWARD_POINTS_HEADER);
	}
	
}
