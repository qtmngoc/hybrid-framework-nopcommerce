package pageObjects.wordPress;

import org.openqa.selenium.WebDriver;

import commons.wordPress.BasePage;
import commons.wordPress.PageGeneratorManager;
import pageUIs.wordPress.UserSearchPUI;

public class UserSearchPO extends BasePage{
	WebDriver driver;
	
	public UserSearchPO(WebDriver driver) {
		this.driver = driver;
	}

	public boolean isSearchResultsTitleDisplayed(String postTitle) {
		return isElementDisplayed(driver, UserSearchPUI.SEARCH_RESULTS_TEXT_BY_POST_TITLE, postTitle);
	}

	public boolean isOnePostMessageDisplayed(String message) {
		return isElementDisplayed(driver, UserSearchPUI.ONE_POST_MESSAGE, message);
	}
	
	public boolean isPostTitleDisplayed(String postTitle) {
		return isElementDisplayed(driver, UserSearchPUI.POST_TITLE_TEXT, postTitle);
	}

	public boolean isPostImageDisplayed(String postTitle, String imageUploadedName) {
		return isElementDisplayed(driver, UserSearchPUI.POST_IMAGE_BY_POST_TITLE, postTitle, imageUploadedName);
	}

	public boolean isPostCategoryDisplayed(String postTitle, String categoryName) {
		return isElementDisplayed(driver, UserSearchPUI.POST_CATEGORY_TEXT_BY_POST_TITLE, postTitle, categoryName);
	}

	public boolean isPostPublishedDateDisplayed(String postTitle, String publishedDate) {
		return isElementDisplayed(driver, UserSearchPUI.POST_PUBLISHED_DATE_TEXT_BY_POST_TITLE, postTitle, publishedDate);
	}

	public UserHomePO clickOnHomePageLink() {
		clickOnElement(driver, UserSearchPUI.HOME_PAGE_LINK);
		return PageGeneratorManager.getUserHomePage(driver);
	}

	public UserPostDetailPO clickOnPostTitleLink(String postTitle) {
		clickOnElement(driver, UserSearchPUI.POST_TITLE_TEXT, postTitle);
		return PageGeneratorManager.getUserPostDetailPage(driver);
	}

	public boolean isSearchResultsMessageDisplayed() {
		return isElementDisplayed(driver, UserSearchPUI.SEARCH_RESULTS_MESSAGE);
	}

}
