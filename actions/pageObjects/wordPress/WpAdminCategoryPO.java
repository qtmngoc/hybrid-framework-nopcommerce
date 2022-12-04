package pageObjects.wordPress;

import org.openqa.selenium.WebDriver;

import commons.wordPress.WpBasePage;
import commons.wordPress.WpGlobalConstants;
import pageUIs.wordPress.WpAdminCategoryPUI;

public class WpAdminCategoryPO extends WpBasePage {
	WebDriver driver;

	public WpAdminCategoryPO(WebDriver driver) {
		this.driver = driver;
	}

	public void inputIntoCategoryNameTextbox(String categoryName) {
		sendKeysToElement(driver, WpAdminCategoryPUI.DIALOG_NAME_TEXTBOX, categoryName);
	}
	
	public void inputIntoCategoryDescriptionTextarea(String categoryDesc) {
		sendKeysToElement(driver, WpAdminCategoryPUI.DIALOG_DESCRIPTION_TEXTAREA, categoryDesc);
	}
	
	public void inputIntoDialogSearchTextbox(String categoryName) {
		sendKeysToElement(driver, WpAdminCategoryPUI.DIALOG_SEARCH_TEXTBOX, categoryName);
		sleepInSecond(1);
	}

	public void inputIntoSearchTextbox(String categoryName) {
		sendKeysToElement(driver, WpAdminCategoryPUI.SEARCH_TEXTBOX, categoryName);
		sleepInSecond(1);
	}
	
	public void clickOnEllipsisMenu(String categoryName, String menuItem) {
		clickOnElement(driver, WpAdminCategoryPUI.ELLIPSIS_MENU, categoryName);
		sleepInSecond(1);
		clickOnElement(driver, WpAdminCategoryPUI.ELLIPSIS_MENU_ITEM, menuItem);
		sleepInSecond(1);
	}
	
	public void clickOnAddNewCategoryButton() {
		clickOnElement(driver, WpAdminCategoryPUI.ADD_NEW_CATEGORY_BUTTON);
	}
	
	public void clickOnAddOrUpdateOrOkButton() {
		clickOnElement(driver, WpAdminCategoryPUI.DIALOG_ADD_OR_UPDATE_OR_OK_BUTTON);
		waitForElementUndisplayed(driver, WpAdminCategoryPUI.DIALOG_ADD_OR_UPDATE_OR_OK_BUTTON);
	}

	public void clickOnCloseSearchIcon() {
		clickOnElement(driver, WpAdminCategoryPUI.CLOSE_ICON);
	}
	
	public void clickOnTopLevelToggle() {
		clickOnElementByJS(driver, WpAdminCategoryPUI.DIALOG_PARENT_TOGGLE);
	}
	
	public void checkParentCategoryRadio(String categoryName) {
		clickOnElementByJS(driver, WpAdminCategoryPUI.DIALOG_CATEGORY_RADIO, categoryName);
	}

	public boolean isParentToggleSelected() {
		return isElementSelected(driver, WpAdminCategoryPUI.DIALOG_PARENT_TOGGLE);
	}

	public boolean isCategoryDisplayedByScrollDown(String categoryName) {
		long timeout = 1;
		setImplicitWait(driver, timeout);
		while(true) {
			try {
				waitForElementVisible(driver, timeout, WpAdminCategoryPUI.CATEGORY_ITEM, categoryName);
				break;
			} catch (Exception e) {
				scrollDownByJS(driver, 400);
				continue;
			}
		}
		setImplicitWait(driver, WpGlobalConstants.LONG_TIMEOUT);
		//scrollToTopPageByJS(driver);
		return isElementDisplayed(driver, WpAdminCategoryPUI.CATEGORY_ITEM, categoryName);
	}
	
	public boolean isCategoryDisplayed(String categoryName) {
		return isElementDisplayed(driver, WpAdminCategoryPUI.CATEGORY_ITEM, categoryName);
	}
	
	public boolean isCategoryUndisplayed(String categoryName) {
		return isElementUndisplayed(driver, WpAdminCategoryPUI.CATEGORY_ITEM, categoryName);
	}

	public boolean isParentCategoryMessageDisplayed() {
		return isElementDisplayed(driver, WpAdminCategoryPUI.DIALOG_PARENT_MESSAGE);
	}

	public boolean isConfirmDeleteMessageDisplayed(String categoryName) {
		return isElementDisplayed(driver, WpAdminCategoryPUI.DIALOG_CONFIRM_DELETE_MESSAGE, categoryName);
	}

	public boolean isNoResultsFoundMessageDisplayed() {
		return isElementDisplayed(driver, WpAdminCategoryPUI.NO_RESULTS_MESSAGE);
	}
	
}
