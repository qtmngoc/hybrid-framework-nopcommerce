package pageObjects.jQuery.dataTable;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import commons.jQuery.JqBasePage;
import pageUIs.jQuery.dataTable.JqHomePageUI;

public class JqHomePageObject extends JqBasePage{
	WebDriver driver;
	
	public JqHomePageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void openPaginationByPageNumber(String pageNumber) {
		clickOnElement(driver, JqHomePageUI.PAGINATION_PAGE_BY_NUMBER, pageNumber);
	}

	public boolean isPageNumberActived(String pageNumber) {
		return isElementDisplayed(driver, JqHomePageUI.PAGINATION_PAGE_ACTIVATED_BY_NUMBER, pageNumber);
	}

	public void inputIntoHeaderTextboxByLabel(String headerLabel, String value) {
		sendKeysToElement(driver, JqHomePageUI.HEADER_TEXTBOX_BY_LABEL, value, headerLabel);
		pressKeyOnElement(driver, JqHomePageUI.HEADER_TEXTBOX_BY_LABEL, Keys.ENTER, headerLabel);
	}
	
	public List<String> getRowCountryOnAllPages() {
		int totalPages = getListElementsSize(driver, JqHomePageUI.TOTAL_PAGINATION);
		
		List<String> allRowCountriesAllPages = new ArrayList<String>();
		for (int page = 1; page <= totalPages; page++) {
			clickOnElement(driver, JqHomePageUI.PAGE_NUMBER, String.valueOf(page));
			
			List<WebElement> allRowElementsEachPage = getListElements(driver, JqHomePageUI.ALL_ROW_COUNTRY_EACH_PAGE);
			for (WebElement eachRow : allRowElementsEachPage) {
				allRowCountriesAllPages.add(eachRow.getText());
			}
		}
		
		return allRowCountriesAllPages;
	}
	
	public List<String> getRowValueOnAllPages() {
		int totalPages = getListElementsSize(driver, JqHomePageUI.TOTAL_PAGINATION);
		
		// Set<String> allRowUniqueValuesAllPages = new HashSet<String>();
		
		List<String> allRowValuesAllPages = new ArrayList<String>();
		for (int page = 1; page <= totalPages; page++) {
			clickOnElement(driver, JqHomePageUI.PAGE_NUMBER, String.valueOf(page));
			
			List<WebElement> allRowElementsEachPage = getListElements(driver, JqHomePageUI.ALL_ROW_EACH_PAGE);
			for (WebElement eachRow : allRowElementsEachPage) {
				allRowValuesAllPages.add(eachRow.getText());
			}
		}
		
		return allRowValuesAllPages;
	}

	public void inputIntoTextboxByRowNumberAndColumnName(String rowNumber, String columnName, String value) {
		String columnIndex = String.valueOf(getListElementsSize(driver, JqHomePageUI.COLUMN_INDEX_BY_NAME, columnName) + 1);	
		sendKeysToElement(driver, JqHomePageUI.TEXTBOX_BY_COLUMN_AND_ROW_INDEX, value, rowNumber, columnIndex);
	}

	public void selectOptionInDropdownByRowNumberAndColumnName(String rowNumber, String columnName, String value) {
		String columnIndex = String.valueOf(getListElementsSize(driver, JqHomePageUI.COLUMN_INDEX_BY_NAME, columnName) + 1);
		selectOptionFromDefaultDropdown(driver, JqHomePageUI.DROPDOWN_BY_COLUMN_AND_ROW_INDEX, value, rowNumber, columnIndex);
	}

	public void clickOnLoadButton() {
		clickOnElement(driver, JqHomePageUI.LOAD_BUTTON);
	}

	public void checkCheckboxByRowNumberAndColumnName(String rowNumber, String columnName) {
		String columnIndex = String.valueOf(getListElementsSize(driver, JqHomePageUI.COLUMN_INDEX_BY_NAME, columnName) + 1);
		checkCheckboxOrRadio(driver, JqHomePageUI.CHECKBOX_BY_COLUMN_AND_ROW_INDEX, rowNumber, columnIndex);
	}

	public void uncheckCheckboxByRowNumberAndColumnName(String rowNumber, String columnName) {
		String columnIndex = String.valueOf(getListElementsSize(driver, JqHomePageUI.COLUMN_INDEX_BY_NAME, columnName) + 1);
		uncheckCheckbox(driver, JqHomePageUI.CHECKBOX_BY_COLUMN_AND_ROW_INDEX, rowNumber, columnIndex);
	}

	public void clickOnIconByRowNumber(String rowNumber, String iconName) {
		clickOnElement(driver, JqHomePageUI.ICON_NAME_BY_ROW_INDEX, rowNumber, iconName);
	}

}
