package pageObjects.liveGuru.admin;

import org.openqa.selenium.WebDriver;

import commons.liveGuru.BasePage;
import commons.liveGuru.PageGeneratorManager;
import pageUIs.liveGuru.admin.AdminCustomersPageUI;

public class AdminCustomersPageObject extends BasePage {

	private WebDriver driver;

	public AdminCustomersPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void closePopup() {
		waitForElementClickable(driver, AdminCustomersPageUI.CLOSE_MESSAGE_POPUP);
		clickOnElement(driver, AdminCustomersPageUI.CLOSE_MESSAGE_POPUP);
	}

	public void inputIntoFilterTextboxByColumnName(String columnName, String userEmail) {
		String columnIndex = String.valueOf(getListElementsSize(driver, AdminCustomersPageUI.COLUMN_INDEX_BY_NAME, columnName) + 1);
		waitForElementVisible(driver, AdminCustomersPageUI.FILTER_TEXTBOX_BY_COLUMN_NAME, columnIndex);
		sendKeysToElement(driver, AdminCustomersPageUI.FILTER_TEXTBOX_BY_COLUMN_NAME, userEmail, columnIndex);
	}

	public void clickOnButtonByName(String buttonName) {
		waitForElementClickable(driver, AdminCustomersPageUI.BUTTON_BY_NAME, buttonName);
		clickOnElement(driver, AdminCustomersPageUI.BUTTON_BY_NAME, buttonName);
	}

	public String resultCellByRowNumberAndColumnName(String rowNumber, String columnName) {
		String columnIndex = String.valueOf(getListElementsSize(driver, AdminCustomersPageUI.COLUMN_INDEX_BY_NAME, columnName) + 1);
		waitForElementVisible(driver, AdminCustomersPageUI.TEXT_CELL_BY_ROW_AND_COLUMN_INDEX, rowNumber, columnIndex);
		return getElementText(driver, AdminCustomersPageUI.TEXT_CELL_BY_ROW_AND_COLUMN_INDEX, rowNumber, columnIndex);
	}

	public void checkCheckboxByRowNumber(String rowNumber) {
		sleepInSecond(1);
		waitForElementClickable(driver, AdminCustomersPageUI.CHECKBOX_CELL_BY_ROW_INDEX, rowNumber);
		checkCheckboxOrRadio(driver, AdminCustomersPageUI.CHECKBOX_CELL_BY_ROW_INDEX, rowNumber);
	}

	public void selectOptionInActionsDropdownByText(String visibleText) {
		waitForElementClickable(driver, AdminCustomersPageUI.ACTIONS_DROPDOWN, visibleText);
		selectOptionInDefaultDropdown(driver, AdminCustomersPageUI.ACTIONS_DROPDOWN, visibleText);
	}

	public void accepAlert() {
		accepAlert(driver);
	}

	public AdminLoginPageObject clickOnLogoutLink() {
		waitForElementClickable(driver, AdminCustomersPageUI.LOGOUT_LINK);
		clickOnElement(driver, AdminCustomersPageUI.LOGOUT_LINK);
		return PageGeneratorManager.getAdminLoginPage(driver);
	}

}
