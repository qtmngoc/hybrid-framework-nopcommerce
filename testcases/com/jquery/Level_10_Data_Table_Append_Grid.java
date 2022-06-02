package com.jquery;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.jQuery.BaseTest;
import pageObjects.jQuery.dataTable.HomePageObject;
import pageObjects.jQuery.dataTable.PageGeneratorManager;

public class Level_10_Data_Table_Append_Grid extends BaseTest {

	@Parameters({ "browser", "appendGrid_url" })
	@BeforeClass
	public void beforeClass(String browserName, String appUrl) {
		driver = getBrowserDriver(browserName, appUrl);
		homePage = PageGeneratorManager.getHomePage(driver);
	}

	@Test
	public void Table_01_Action_On_One_Row() {
		homePage.inputIntoTextboxByRowNumberAndColumnName("1", "Year", "2021");
		homePage.inputIntoTextboxByRowNumberAndColumnName("1", "Album", "Bloom");
		homePage.inputIntoTextboxByRowNumberAndColumnName("1", "Price", "263");
		homePage.inputIntoTextboxByRowNumberAndColumnName("1", "Artist", "Red Velvet");
		
		homePage.selectOptionInDropdownByRowNumberAndColumnName("1", "Origin", "Korea");
		homePage.selectOptionInDropdownByRowNumberAndColumnName("1", "Origin", "Hong Kong");
		homePage.selectOptionInDropdownByRowNumberAndColumnName("1", "Origin", "US");
		homePage.sleepInSecond(1);
	}

	@Test
	public void Table_02_Action_On_Any_Row() {
		homePage.clickOnLoadButton();
		homePage.sleepInSecond(1);
		
		homePage.inputIntoTextboxByRowNumberAndColumnName("2", "Artist", "Black Pink");
		homePage.inputIntoTextboxByRowNumberAndColumnName("4", "Price", "400");
		homePage.inputIntoTextboxByRowNumberAndColumnName("5", "Album", "Lovesick Girls");
		homePage.inputIntoTextboxByRowNumberAndColumnName("3", "Year", "2020");
		homePage.selectOptionInDropdownByRowNumberAndColumnName("1", "Origin", "Others");
		homePage.sleepInSecond(1);
		
		homePage.checkCheckboxByRowNumberAndColumnName("3", "With Poster?");
		homePage.checkCheckboxByRowNumberAndColumnName("5", "With Poster?");
		homePage.uncheckCheckboxByRowNumberAndColumnName("2", "With Poster?");
		homePage.uncheckCheckboxByRowNumberAndColumnName("4", "With Poster?");
		homePage.uncheckCheckboxByRowNumberAndColumnName("1", "With Poster?");
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
	HomePageObject homePage;
}