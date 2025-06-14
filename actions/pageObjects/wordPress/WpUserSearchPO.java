package pageObjects.wordPress;

import org.openqa.selenium.WebDriver;

import commons.wordPress.WpBasePage;
import commons.wordPress.WpPageGeneratorManager;
import pageUIs.wordPress.WpUserSearchPUI;

public class WpUserSearchPO extends WpBasePage {
	WebDriver driver;
	
	public WpUserSearchPO(WebDriver driver) {
		this.driver = driver;
	}

	public boolean isOnePostMessageDisplayed(String message) {
		return isElementDisplayed(driver, WpUserSearchPUI.ONE_POST_MESSAGE, message);
	}
	
	public boolean isPostOrPageTitleDisplayed(String postTitle) {
		return isElementDisplayed(driver, WpUserSearchPUI.POST_OR_PAGE_TITLE_LINK, postTitle);
	}

	public boolean isPostOrPageImageDisplayed(String postTitle, String imageUploadedName) {
		return isElementDisplayed(driver, WpUserSearchPUI.IMAGE_BY_TITLE, postTitle, imageUploadedName);
	}

	public boolean isPostCategoryDisplayed(String postTitle, String categoryName) {
		return isElementDisplayed(driver, WpUserSearchPUI.POST_CATEGORY_TEXT_BY_POST_TITLE, postTitle, categoryName);
	}

	public boolean isPostOrPageMetaDisplayed(String postTitle, String publishedDate) {
		return isElementDisplayed(driver, WpUserSearchPUI.META_BY_TITLE, postTitle, publishedDate);
	}

	public WpUserHomePO clickOnHomePageLink() {
		clickOnElement(driver, WpUserSearchPUI.HOME_PAGE_LINK);
		return WpPageGeneratorManager.getUserHomePage(driver);
	}

	public WpUserPostDetailPO clickOnPostTitleLink(String postTitle) {
		clickOnElement(driver, WpUserSearchPUI.POST_OR_PAGE_TITLE_LINK, postTitle);
		return WpPageGeneratorManager.getUserPostDetailPage(driver);
	}

	public boolean isSearchResultsMessageDisplayed() {
		return isElementDisplayed(driver, WpUserSearchPUI.SEARCH_RESULTS_MESSAGE);
	}

	public WpUserPageDetailPO clickOnPageTitleLink(String pageTitle) {
		clickOnElement(driver, WpUserSearchPUI.POST_OR_PAGE_TITLE_LINK, pageTitle);
		return WpPageGeneratorManager.getUserPageDetailPage(driver);
	}

}
