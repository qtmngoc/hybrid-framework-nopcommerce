package pageObjects.wordPress;

import org.openqa.selenium.WebDriver;

import commons.wordPress.WpBasePage;
import commons.wordPress.WpPageGeneratorManager;
import pageUIs.wordPress.WpUserHomePUI;

public class WpUserHomePO extends WpBasePage{
	WebDriver driver;
	
	public WpUserHomePO(WebDriver driver) {
		this.driver = driver;
	}
	
	public void clickOnAcceptCookiesButton() {
		if(getListElementsSize(driver, WpUserHomePUI.ACCEPT_COOKIE_BUTTON) > 0) {
			System.out.println(getListElementsSize(driver, WpUserHomePUI.ACCEPT_COOKIE_BUTTON));
			clickOnElement(driver, WpUserHomePUI.ACCEPT_COOKIE_BUTTON);
		}
	}

	public void clickOnSearchToggle() {
		clickOnElement(driver, WpUserHomePUI.SEARCH_TOGGLE);
	}

	public void inputIntoSearchTextbox(String postTitle) {
		sendKeysToElement(driver, WpUserHomePUI.SEARCH_TEXTBOX, postTitle);
	}

	public WpUserSearchPO clickOnSearchButton() {
		clickOnElement(driver, WpUserHomePUI.SEARCH_BUTTON);
		return WpPageGeneratorManager.getUserSearchPage(driver);
	}
	
	public boolean isPostTitleDisplayed(String postTitle) {
		return isElementDisplayed(driver, WpUserHomePUI.POST_TITLE_TEXT, postTitle);
	}

	public boolean isPostImageDisplayed(String postTitle, String imageUploadedName) {
		return isElementDisplayed(driver, WpUserHomePUI.POST_IMAGE_BY_POST_TITLE, postTitle, imageUploadedName);
	}

	public boolean isPostCategoryDisplayed(String postTitle, String categoryName) {
		return isElementDisplayed(driver, WpUserHomePUI.POST_CATEGORY_TEXT_BY_POST_TITLE, postTitle, categoryName);
	}

	public boolean isPostPublishedDateDisplayed(String postTitle, String publishedDate) {
		return isElementDisplayed(driver, WpUserHomePUI.POST_PUBLISHED_DATE_TEXT_BY_POST_TITLE, postTitle, publishedDate);
	}

	public WpUserPostDetailPO clickOnPostTitleLink(String postTitle) {
		clickOnElement(driver, WpUserHomePUI.POST_TITLE_TEXT, postTitle);
		return WpPageGeneratorManager.getUserPostDetailPage(driver);
	}

}
