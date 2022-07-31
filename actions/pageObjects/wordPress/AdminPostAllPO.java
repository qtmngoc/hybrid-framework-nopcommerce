package pageObjects.wordPress;

import org.openqa.selenium.WebDriver;

import commons.wordPress.BasePage;
import commons.wordPress.PageGeneratorManager;
import pageUIs.wordPress.AdminPostAllPUI;

public class AdminPostAllPO extends BasePage{
	WebDriver driver;

	public AdminPostAllPO(WebDriver driver) {
		this.driver = driver;
	}

	public AdminPostNewPO clickOnAddNewButton() {
		clickOnElement(driver, AdminPostAllPUI.ADD_NEW_BUTTON);
		return PageGeneratorManager.getAdminPostNewPage(driver);
	}

	public void inputIntoSearchTextbox(String postTitle) {
		sendKeysToElement(driver, AdminPostAllPUI.SEARCH_TEXTBOX, postTitle);
	}

	public void clickOnSearchPostsButton() {
		clickOnElement(driver, AdminPostAllPUI.SEARCH_POSTS_BUTTON);
	}

	public boolean isPostInfoResultTableDisplayed(String columnName, String infoValue) {
		return isElementDisplayed(driver, AdminPostAllPUI.POST_INFO_TEXT_BY_COLUMN_NAME, columnName, infoValue);
	}

	public AdminPostNewPO clickOnPostTitleLink(String title, String postTitle) {
		clickOnElement(driver, AdminPostAllPUI.POST_INFO_TEXT_BY_COLUMN_NAME, title, postTitle);
		return PageGeneratorManager.getAdminPostNewPage(driver);
	}

	public boolean isSearchResultTitleDisplayed(String postTitle) {
		return isElementDisplayed(driver, AdminPostAllPUI.SEARCH_RESULTS_TEXT_BY_POST_TITLE, postTitle);
		
	}

}
