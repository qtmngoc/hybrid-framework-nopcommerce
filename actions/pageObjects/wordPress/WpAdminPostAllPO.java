package pageObjects.wordPress;

import org.openqa.selenium.WebDriver;

import commons.wordPress.WpBasePage;
import commons.wordPress.WpPageGeneratorManager;
import pageUIs.wordPress.WpAdminPostAllPUI;

public class WpAdminPostAllPO extends WpBasePage{
	WebDriver driver;

	public WpAdminPostAllPO(WebDriver driver) {
		this.driver = driver;
	}

	public WpAdminPostNewPO clickOnAddNewButton() {
		clickOnElement(driver, WpAdminPostAllPUI.ADD_NEW_BUTTON);
		return WpPageGeneratorManager.getAdminPostNewPage(driver);
	}

	public void inputIntoSearchTextbox(String postTitle) {
		sendKeysToElement(driver, WpAdminPostAllPUI.SEARCH_TEXTBOX, postTitle);
	}

	public void clickOnSearchPostsButton() {
		clickOnElement(driver, WpAdminPostAllPUI.SEARCH_POSTS_BUTTON);
	}

	public boolean isPostInfoResultTableDisplayed(String columnName, String infoValue) {
		return isElementDisplayed(driver, WpAdminPostAllPUI.POST_INFO_TEXT_BY_COLUMN_NAME, columnName, infoValue);
	}

	public WpAdminPostNewPO clickOnPostTitleLink(String title, String postTitle) {
		clickOnElement(driver, WpAdminPostAllPUI.POST_INFO_TEXT_BY_COLUMN_NAME, title, postTitle);
		return WpPageGeneratorManager.getAdminPostNewPage(driver);
	}

	public boolean isSearchResultsTitleDisplayed(String postTitle) {
		return isElementDisplayed(driver, WpAdminPostAllPUI.SEARCH_RESULTS_TEXT_BY_POST_TITLE, postTitle);
	}

	public void checkPostTitleCheckbox(String postTitle) {
		checkCheckboxOrRadio(driver, WpAdminPostAllPUI.ROW_CHECKBOX_BY_POST_TITLE, postTitle);
	}

	public void selectItemFromBulkActionsDropdown(String itemValue) {
		selectOptionFromDefaultDropdown(driver, WpAdminPostAllPUI.BULK_ACTIONS_DROPDOWN, itemValue);
	}

	public void clickOnApplyButton() {
		clickOnElement(driver, WpAdminPostAllPUI.APPLY_BUTTON);
	}

	public boolean isDeletePostMessageDisplayed(String message) {
		return isElementDisplayed(driver, WpAdminPostAllPUI.DELETE_POST_MESSAGE, message);
	}

	public boolean isNoPostsFoundMessageDisplayed(String message) {
		return isElementDisplayed(driver, WpAdminPostAllPUI.NO_POST_FOUND_MESSAGE, message);
	}

	public void clickOnTrashTab() {
		clickOnElement(driver, WpAdminPostAllPUI.TRASH_TAB);
	}

}
