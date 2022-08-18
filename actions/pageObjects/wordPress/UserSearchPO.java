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
	
	public boolean isPostOrPageTitleDisplayed(String postTitle) {
		return isElementDisplayed(driver, UserSearchPUI.POST_OR_PAGE_TITLE_TEXT, postTitle);
	}

	public boolean isPostOrPageImageDisplayed(String postTitle, String imageUploadedName) {
		return isElementDisplayed(driver, UserSearchPUI.POST_IMAGE_BY_POST_TITLE, postTitle, imageUploadedName);
	}

	public boolean isPostCategoryDisplayed(String postTitle, String categoryName) {
		return isElementDisplayed(driver, UserSearchPUI.POST_CATEGORY_TEXT_BY_POST_TITLE, postTitle, categoryName);
	}

	public boolean isPostOrPublishedDateDisplayed(String postTitle, String publishedDate) {
		return isElementDisplayed(driver, UserSearchPUI.POST_PUBLISHED_DATE_TEXT_BY_POST_TITLE, postTitle, publishedDate);
	}

	public UserHomePO clickOnHomePageLink() {
		clickOnElement(driver, UserSearchPUI.HOME_PAGE_LINK);
		return PageGeneratorManager.getUserHomePage(driver);
	}

	public UserPostDetailPO clickOnPostTitleLink(String postTitle) {
		clickOnElement(driver, UserSearchPUI.POST_OR_PAGE_TITLE_TEXT, postTitle);
		return PageGeneratorManager.getUserPostDetailPage(driver);
	}

	public boolean isSearchResultsMessageDisplayed() {
		return isElementDisplayed(driver, UserSearchPUI.SEARCH_RESULTS_MESSAGE);
	}

	public String getSearchResultsTitle() {
		return getElementText(driver, UserSearchPUI.SEARCH_RESULTS_TITLE);
	}

	public UserPageDetailPO clickOnPageTitleLink(String pageTitle) {
		clickOnElement(driver, UserSearchPUI.POST_OR_PAGE_TITLE_TEXT, pageTitle);
		return PageGeneratorManager.getUserPageDetailPage(driver);
	}

	public void clickOnSearchToggle() {
		clickOnElement(driver, UserSearchPUI.SEARCH_TOGGLE);
	}

	public void inputIntoSearchTextbox(String postTitle) {
		sendKeysToElement(driver, UserSearchPUI.SEARCH_TEXTBOX, postTitle);
	}

	public void clickOnSearchButton() {
		clickOnElement(driver, UserSearchPUI.SEARCH_BUTTON);
	}


}
