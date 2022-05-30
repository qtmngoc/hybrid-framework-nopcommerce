package pageObjects.jQuery.dataTable;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import commons.jQuery.BasePage;
import pageUIs.jQuery.dataTable.HomePageUI;

public class HomePageObject extends BasePage{
	WebDriver driver;
	
	public HomePageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void openPaginationByPageNumber(String pageNumber) {
		clickOnElement(driver, HomePageUI.PAGINATION_PAGE_BY_NUMBER, pageNumber);
	}

	public boolean isPageNumberActived(String pageNumber) {
		return isElementDisplayed(driver, HomePageUI.PAGINATION_PAGE_ACTIVATED_BY_NUMBER, pageNumber);
	}

	public void inputIntoHeaderTextboxByLabel(String headerLabel, String value) {
		sendKeysToElement(driver, HomePageUI.HEADER_TEXTBOX_BY_LABEL, value, headerLabel);
		sendKeypressToElement(driver, HomePageUI.HEADER_TEXTBOX_BY_LABEL, Keys.ENTER, headerLabel);
	}
	
	public List<String> getRowCountryOnAllPages() {
		int totalPages = getListElementsSize(driver, HomePageUI.TOTAL_PAGINATION);
		
		List<String> allRowCountriesAllPages = new ArrayList<String>();
		for (int page = 1; page <= totalPages; page++) {
			clickOnElement(driver, HomePageUI.PAGE_NUMBER, String.valueOf(page));
			
			List<WebElement> allRowElementsEachPage = getListElements(driver, HomePageUI.ALL_ROW_COUNTRY_EACH_PAGE);
			for (WebElement eachRow : allRowElementsEachPage) {
				allRowCountriesAllPages.add(eachRow.getText());
			}
		}
		
		return allRowCountriesAllPages;
	}
	
	public List<String> getRowValueOnAllPages() {
		int totalPages = getListElementsSize(driver, HomePageUI.TOTAL_PAGINATION);
		
		// Set<String> allRowUniqueValuesAllPages = new HashSet<String>();
		
		List<String> allRowValuesAllPages = new ArrayList<String>();
		for (int page = 1; page <= totalPages; page++) {
			clickOnElement(driver, HomePageUI.PAGE_NUMBER, String.valueOf(page));
			
			List<WebElement> allRowElementsEachPage = getListElements(driver, HomePageUI.ALL_ROW_EACH_PAGE);
			for (WebElement eachRow : allRowElementsEachPage) {
				allRowValuesAllPages.add(eachRow.getText());
			}
		}
		
		return allRowValuesAllPages;
	}

	public void inputIntoTextboxByRowNumberAndColumnName(String rowNumber, String columnName, String value) {
		String columnIndex = String.valueOf(getListElementsSize(driver, HomePageUI.COLUMN_INDEX_BY_NAME, columnName) + 1);	
		sendKeysToElement(driver, HomePageUI.TEXTBOX_BY_COLUMN_AND_ROW_INDEX, value, rowNumber, columnIndex);
	}

	public void selectOptionInDropdownByRowNumberAndColumnName(String rowNumber, String columnName, String value) {
		String columnIndex = String.valueOf(getListElementsSize(driver, HomePageUI.COLUMN_INDEX_BY_NAME, columnName) + 1);
		selectOptionInDefaultDropdown(driver, HomePageUI.DROPDOWN_BY_COLUMN_AND_ROW_INDEX, value, rowNumber, columnIndex);
	}

}
