package pageObjects.wordPress;

import org.openqa.selenium.WebDriver;

import commons.wordPress.BasePage;
import commons.wordPress.PageGeneratorManager;
import pageUIs.wordPress.UserHomePUI;

public class UserHomePO extends BasePage{
	WebDriver driver;
	
	public UserHomePO(WebDriver driver) {
		this.driver = driver;
	}
	
	public void clickOnAcceptCookiesButton() {
		if(getListElementsSize(driver, UserHomePUI.ACCEPT_COOKIE_BUTTON) > 0) {
			System.out.println(getListElementsSize(driver, UserHomePUI.ACCEPT_COOKIE_BUTTON));
			clickOnElement(driver, UserHomePUI.ACCEPT_COOKIE_BUTTON);
		}
	}

	public void clickOnSearchToggle() {
		clickOnElement(driver, UserHomePUI.SEARCH_TOGGLE);
	}

	public void inputIntoSearchTextbox(String postTitle) {
		sendKeysToElement(driver, UserHomePUI.SEARCH_TEXTBOX, postTitle);
	}

	public UserSearchPO clickOnSearchButton() {
		clickOnElement(driver, UserHomePUI.SEARCH_BUTTON);
		return PageGeneratorManager.getUserSearchPage(driver);
	}
	
	public boolean isPostTitleDisplayed(String postTitle) {
		return isElementDisplayed(driver, UserHomePUI.POST_TITLE_TEXT, postTitle);
	}

	public boolean isPostImageDisplayed(String postTitle, String imageUploadedName) {
		return isElementDisplayed(driver, UserHomePUI.POST_IMAGE_BY_POST_TITLE, postTitle, imageUploadedName);
	}

	public boolean isPostCategoryDisplayed(String postTitle, String categoryName) {
		return isElementDisplayed(driver, UserHomePUI.POST_CATEGORY_TEXT_BY_POST_TITLE, postTitle, categoryName);
	}

	public boolean isPostPublishedDateDisplayed(String postTitle, String publishedDate) {
		return isElementDisplayed(driver, UserHomePUI.POST_PUBLISHED_DATE_TEXT_BY_POST_TITLE, postTitle, publishedDate);
	}

	public UserPostDetailPO clickOnPostTitleLink(String postTitle) {
		clickOnElement(driver, UserHomePUI.POST_TITLE_TEXT, postTitle);
		return PageGeneratorManager.getUserPostDetailPage(driver);
	}

}
