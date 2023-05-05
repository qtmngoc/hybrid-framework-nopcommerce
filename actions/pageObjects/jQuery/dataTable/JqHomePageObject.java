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
	
	// Quick Grid
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
	
	public void clickOnCountry(String country) {
		clickOnElement(driver, JqHomePageUI.ROW_COUNTRY, country);
	}

	public List<String> getValueOfModal() {
		int totalRows = getListElementsSize(driver, JqHomePageUI.TOTAL_ROWS_OF_MODAL);
				
		List<String> allRowValues = new ArrayList<String>();
		for (int row = 1; row <= totalRows; row++) {
			allRowValues.add(getElementText(driver, JqHomePageUI.ROW_MODAL_LABEL, String.valueOf(row)) + ": " + getElementAttribute(driver, JqHomePageUI.ROW_MODAL_VALUE, "value" ,String.valueOf(row)));
		}
		
		return allRowValues;
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
	
//-------------------------------------------------------------------------------------------------------------- 
	// Append Grid (Old)
	public void oldInputIntoTextboxByRowNumberAndColumnName(String rowNumber, String columnName, String value) {
		String columnIndex = String.valueOf(getListElementsSize(driver, JqHomePageUI.old_COLUMN_INDEX_BY_NAME, columnName) + 1);	
		sendKeysToElement(driver, JqHomePageUI.TEXTBOX_BY_ROW_AND_COLUMN_INDEX, value, rowNumber, columnIndex);
	}

	public void oldSelectOptionInDropdownByRowNumberAndColumnName(String rowNumber, String columnName, String value) {
		String columnIndex = String.valueOf(getListElementsSize(driver, JqHomePageUI.old_COLUMN_INDEX_BY_NAME, columnName) + 1);
		selectOptionFromDefaultDropdown(driver, JqHomePageUI.DROPDOWN_BY_ROW_AND_COLUMN_INDEX, value, rowNumber, columnIndex);
	}

	public void oldClickOnLoadButton() {
		clickOnElement(driver, JqHomePageUI.old_LOAD_BUTTON);
	}

	public void oldCheckCheckboxByRowNumberAndColumnName(String rowNumber, String columnName) {
		String columnIndex = String.valueOf(getListElementsSize(driver, JqHomePageUI.old_COLUMN_INDEX_BY_NAME, columnName) + 1);
		checkCheckboxOrRadio(driver, JqHomePageUI.CHECKBOX_BY_ROW_AND_COLUMN_INDEX, rowNumber, columnIndex);
	}

	public void oldUncheckCheckboxByRowNumberAndColumnName(String rowNumber, String columnName) {
		String columnIndex = String.valueOf(getListElementsSize(driver, JqHomePageUI.old_COLUMN_INDEX_BY_NAME, columnName) + 1);
		uncheckCheckbox(driver, JqHomePageUI.CHECKBOX_BY_ROW_AND_COLUMN_INDEX, rowNumber, columnIndex);
	}

//-------------------------------------------------------------------------------------------------------------- 
	// Append Grid (New)
	public void inputIntoTextboxByRowNumberAndColumnName(String rowNumber, String columnName, String value) {
		String columnIndex = String.valueOf(getListElementsSize(driver, JqHomePageUI.COLUMN_INDEX_BY_NAME, columnName) + 1);
		sendKeysToElement(driver, JqHomePageUI.TEXTBOX_BY_ROW_AND_COLUMN_INDEX, value, rowNumber, columnIndex);
	}

	public void selectOptionInDropdownByRowNumberAndColumnName(String rowNumber, String columnName, String value) {
		String columnIndex = String.valueOf(getListElementsSize(driver, JqHomePageUI.COLUMN_INDEX_BY_NAME, columnName) + 1);
		selectOptionFromDefaultDropdown(driver, JqHomePageUI.DROPDOWN_BY_ROW_AND_COLUMN_INDEX, value, rowNumber, columnIndex);
	}
	
	public void selectCheckboxByRowNumberAndColumnName(String rowNumber, String columnName) {
		String columnIndex = String.valueOf(getListElementsSize(driver, JqHomePageUI.COLUMN_INDEX_BY_NAME, columnName) + 1);
		checkCheckboxOrRadio(driver, JqHomePageUI.CHECKBOX_BY_ROW_AND_COLUMN_INDEX, rowNumber, columnIndex);
	}

	public void deselectCheckboxByRowNumberAndColumnName(String rowNumber, String columnName) {
		String columnIndex = String.valueOf(getListElementsSize(driver, JqHomePageUI.COLUMN_INDEX_BY_NAME, columnName) + 1);
		uncheckCheckbox(driver, JqHomePageUI.CHECKBOX_BY_ROW_AND_COLUMN_INDEX, rowNumber, columnIndex);
	}
	
	public void clickOnLoadDataButton() {
		clickOnElement(driver, JqHomePageUI.LOAD_DATA_BUTTON);
	}
	
	public void clickOnIconByRowNumber(String rowNumber, String iconName) {
		clickOnElement(driver, JqHomePageUI.ICON_NAME_BY_ROW_INDEX, rowNumber, iconName);
	}
	
	public void clickOnAppendOrRemoveRow(String buttonName) {
		clickOnElement(driver, JqHomePageUI.APPEND_OR_REMOVE_ROW_BUTTON, buttonName);
	}
	
	public String getTextboxValueByRowNumberAndColumnName(String rowNumber, String columnName) {
		String columnIndex = String.valueOf(getListElementsSize(driver, JqHomePageUI.COLUMN_INDEX_BY_NAME, columnName) + 1);
		return getElementAttribute(driver, JqHomePageUI.TEXTBOX_BY_ROW_AND_COLUMN_INDEX, "value", rowNumber, columnIndex);
	}

	public String getDropdownValueByRowNumberAndColumnName(String rowNumber, String columnName) {
		String columnIndex = String.valueOf(getListElementsSize(driver, JqHomePageUI.COLUMN_INDEX_BY_NAME, columnName) + 1);
		return getElementAttribute(driver, JqHomePageUI.DROPDOWN_BY_ROW_AND_COLUMN_INDEX, "value", rowNumber, columnIndex);
	}
	
	public boolean isCheckboxSelectedByRowNumberAndColumnName(String rowNumber, String columnName) {
		String columnIndex = String.valueOf(getListElementsSize(driver, JqHomePageUI.COLUMN_INDEX_BY_NAME, columnName) + 1);
		return isElementSelected(driver, JqHomePageUI.CHECKBOX_BY_ROW_AND_COLUMN_INDEX, rowNumber, columnIndex);
	}
	
	public int numberOfRowsInTable( ) {
		return getListElementsSize(driver, JqHomePageUI.ALL_ROW_EACH_PAGE);
	}
	
	public String dateMemberSinceInput(String day, String month, String year) {
		return month + day + year;
	}
	
	public String dateMemberSinceOutput(String day, String month, String year) {
		return year + "-" + month + "-" + day;
	}

}
