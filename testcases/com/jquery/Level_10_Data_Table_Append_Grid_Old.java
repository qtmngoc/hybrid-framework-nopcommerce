package com.jquery;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.jQuery.JqBaseTest;
import pageObjects.jQuery.dataTable.JqHomePageObject;
import pageObjects.jQuery.dataTable.JqPageGeneratorManager;

public class Level_10_Data_Table_Append_Grid_Old extends JqBaseTest {

	@Parameters({ "browser", "appendGrid_url" })
	@BeforeClass
	public void beforeClass(String browserName, String appUrl) {
		driver = getBrowserDriver(browserName, appUrl);
		homePage = JqPageGeneratorManager.getHomePage(driver);
	}

	@Test
	public void Table_01_Action_On_One_Row() {
		homePage.oldInputIntoTextboxByRowNumberAndColumnName("1", "Year", "2021");
		homePage.oldInputIntoTextboxByRowNumberAndColumnName("1", "Album", "Bloom");
		homePage.oldInputIntoTextboxByRowNumberAndColumnName("1", "Price", "263");
		homePage.oldInputIntoTextboxByRowNumberAndColumnName("1", "Artist", "Red Velvet");
		
		homePage.oldSelectOptionInDropdownByRowNumberAndColumnName("1", "Origin", "Korea");
		homePage.oldSelectOptionInDropdownByRowNumberAndColumnName("1", "Origin", "Hong Kong");
		homePage.oldSelectOptionInDropdownByRowNumberAndColumnName("1", "Origin", "US");
		homePage.sleepInSecond(1);
	}

	@Test
	public void Table_02_Action_On_Any_Row() {
		homePage.oldClickOnLoadButton();
		homePage.sleepInSecond(1);
		
		homePage.oldInputIntoTextboxByRowNumberAndColumnName("2", "Artist", "Black Pink");
		homePage.oldInputIntoTextboxByRowNumberAndColumnName("4", "Price", "400");
		homePage.oldInputIntoTextboxByRowNumberAndColumnName("5", "Album", "Lovesick Girls");
		homePage.oldInputIntoTextboxByRowNumberAndColumnName("3", "Year", "2020");
		homePage.oldSelectOptionInDropdownByRowNumberAndColumnName("1", "Origin", "Others");
		homePage.sleepInSecond(1);
		
		homePage.oldCheckCheckboxByRowNumberAndColumnName("3", "With Poster?");
		homePage.oldCheckCheckboxByRowNumberAndColumnName("5", "With Poster?");
		homePage.oldUncheckCheckboxByRowNumberAndColumnName("2", "With Poster?");
		homePage.oldUncheckCheckboxByRowNumberAndColumnName("4", "With Poster?");
		homePage.oldUncheckCheckboxByRowNumberAndColumnName("1", "With Poster?");
		homePage.sleepInSecond(1);
		
		homePage.clickOnIconByRowNumber("1", "Remove Current Row");
		homePage.clickOnIconByRowNumber("1", "Insert Row Above");
		homePage.clickOnIconByRowNumber("4", "Move Down");
		homePage.clickOnIconByRowNumber("3", "Move Up");
		homePage.clickOnIconByRowNumber("5", "Remove Current Row");
		homePage.clickOnIconByRowNumber("4", "Remove Current Row");
		homePage.clickOnIconByRowNumber("3", "Remove Current Row");
		homePage.clickOnIconByRowNumber("2", "Remove Current Row");
		homePage.clickOnIconByRowNumber("1", "Remove Current Row");
		homePage.sleepInSecond(1);
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	private WebDriver driver;
	private JqHomePageObject homePage;
}