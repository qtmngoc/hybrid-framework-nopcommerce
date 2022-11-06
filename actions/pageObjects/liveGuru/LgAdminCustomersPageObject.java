package pageObjects.liveGuru;

import org.openqa.selenium.WebDriver;

import commons.liveGuru.LgBasePage;
import commons.liveGuru.LgPageGeneratorManager;
import pageUIs.liveGuru.LgAdminCustomersPageUI;

public class LgAdminCustomersPageObject extends LgBasePage {

	private WebDriver driver;

	public LgAdminCustomersPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void closePopup() {
		waitForElementClickable(driver, LgAdminCustomersPageUI.CLOSE_MESSAGE_POPUP);
		clickOnElement(driver, LgAdminCustomersPageUI.CLOSE_MESSAGE_POPUP);
	}

	public void inputIntoFilterTextboxByColumnName(String columnName, String userEmail) {
		String columnIndex = String.valueOf(getListElementsSize(driver, LgAdminCustomersPageUI.COLUMN_INDEX_BY_NAME, columnName) + 1);
		waitForElementVisible(driver, LgAdminCustomersPageUI.FILTER_TEXTBOX_BY_COLUMN_NAME, columnIndex);
		sendKeysToElement(driver, LgAdminCustomersPageUI.FILTER_TEXTBOX_BY_COLUMN_NAME, userEmail, columnIndex);
	}

	public void clickOnButtonByName(String buttonName) {
		waitForElementClickable(driver, LgAdminCustomersPageUI.BUTTON_BY_NAME, buttonName);
		clickOnElement(driver, LgAdminCustomersPageUI.BUTTON_BY_NAME, buttonName);
	}

	public String resultCellByRowNumberAndColumnName(String rowNumber, String columnName) {
		String columnIndex = String.valueOf(getListElementsSize(driver, LgAdminCustomersPageUI.COLUMN_INDEX_BY_NAME, columnName) + 1);
		waitForElementVisible(driver, LgAdminCustomersPageUI.TEXT_CELL_BY_ROW_AND_COLUMN_INDEX, rowNumber, columnIndex);
		return getElementText(driver, LgAdminCustomersPageUI.TEXT_CELL_BY_ROW_AND_COLUMN_INDEX, rowNumber, columnIndex);
	}

	public void checkCheckboxByRowNumber(String rowNumber) {
		sleepInSecond(1);
		waitForElementClickable(driver, LgAdminCustomersPageUI.CHECKBOX_CELL_BY_ROW_INDEX, rowNumber);
		checkCheckboxOrRadio(driver, LgAdminCustomersPageUI.CHECKBOX_CELL_BY_ROW_INDEX, rowNumber);
	}

	public void selectOptionInActionsDropdownByText(String visibleText) {
		waitForElementClickable(driver, LgAdminCustomersPageUI.ACTIONS_DROPDOWN, visibleText);
		selectOptionFromDefaultDropdown(driver, LgAdminCustomersPageUI.ACTIONS_DROPDOWN, visibleText);
	}

	public void accepAlert() {
		acceptAlert(driver);
	}

	public LgAdminLoginPageObject clickOnLogoutLink() {
		waitForElementClickable(driver, LgAdminCustomersPageUI.LOGOUT_LINK);
		clickOnElement(driver, LgAdminCustomersPageUI.LOGOUT_LINK);
		return LgPageGeneratorManager.getAdminLoginPage(driver);
	}

}
