package pageObjects.wordPress;

import org.openqa.selenium.WebDriver;

import commons.wordPress.BasePage;
import commons.wordPress.GlobalConstants;
import pageUIs.wordPress.AdminCategoryPUI;

public class AdminCategoryPO extends BasePage{
	WebDriver driver;

	public AdminCategoryPO(WebDriver driver) {
		this.driver = driver;
	}

	public void inputIntoCategoryNameTextbox(String categoryName) {
		sendKeysToElement(driver, AdminCategoryPUI.DIALOG_NAME_TEXTBOX, categoryName);
	}
	
	public void inputIntoCategoryDescriptionTextarea(String categoryDesc) {
		sendKeysToElement(driver, AdminCategoryPUI.DIALOG_DESCRIPTION_TEXTAREA, categoryDesc);
	}
	
	public void inputIntoDialogSearchTextbox(String categoryName) {
		sendKeysToElement(driver, AdminCategoryPUI.DIALOG_SEARCH_TEXTBOX, categoryName);
		sleepInSecond(1);
	}

	public void inputIntoSearchTextbox(String categoryName) {
		sendKeysToElement(driver, AdminCategoryPUI.SEARCH_TEXTBOX, categoryName);
		sleepInSecond(1);
	}
	
	public void clickOnEllipsisMenu(String categoryName, String menuItem) {
		clickOnElement(driver, AdminCategoryPUI.ELLIPSIS_MENU, categoryName);
		sleepInSecond(1);
		clickOnElement(driver, AdminCategoryPUI.ELLIPSIS_MENU_ITEM, menuItem);
		sleepInSecond(1);
	}
	
	public void clickOnAddNewCategoryButton() {
		clickOnElement(driver, AdminCategoryPUI.ADD_NEW_CATEGORY_BUTTON);
	}
	
	public void clickOnAddOrUpdateOrOkButton() {
		clickOnElement(driver, AdminCategoryPUI.DIALOG_ADD_OR_UPDATE_OR_OK_BUTTON);
		waitForElementUndisplayed(driver, AdminCategoryPUI.DIALOG_ADD_OR_UPDATE_OR_OK_BUTTON);
	}

	public void clickOnCloseSearchIcon() {
		clickOnElement(driver, AdminCategoryPUI.CLOSE_ICON);
	}
	
	public void clickOnTopLevelToggle() {
		clickOnElementByJS(driver, AdminCategoryPUI.DIALOG_PARENT_TOGGLE);
	}
	
	public void checkParentCategoryRadio(String categoryName) {
		clickOnElementByJS(driver, AdminCategoryPUI.DIALOG_CATEGORY_RADIO, categoryName);
	}

	public boolean isParentToggleSelected() {
		return isElementSelected(driver, AdminCategoryPUI.DIALOG_PARENT_TOGGLE);
	}

	public boolean isCategoryDisplayed(String categoryName) {
		long timeout = 1;
		setImplicitWait(driver, timeout);
		while(true) {
			try {
				waitForElementVisible(driver, timeout, AdminCategoryPUI.CATEGORY_ITEM, categoryName);
				break;
			} catch (Exception e) {
				scrollDownByJS(driver, 400);
				continue;
			}
		}
		setImplicitWait(driver, GlobalConstants.LONG_TIMEOUT);
		return isElementDisplayed(driver, AdminCategoryPUI.CATEGORY_ITEM, categoryName);
	}

	public boolean isParentCategoryMessageDisplayed() {
		return isElementDisplayed(driver, AdminCategoryPUI.DIALOG_PARENT_MESSAGE);
	}

	public boolean isConfirmDeleteMessageDisplayed(String categoryName) {
		return isElementDisplayed(driver, AdminCategoryPUI.DIALOG_CONFIRM_DELETE_MESSAGE, categoryName);
	}

	public boolean isNoResultsFoundMessageDisplayed() {
		return isElementDisplayed(driver, AdminCategoryPUI.NO_RESULTS_MESSAGE);
	}
	
}
