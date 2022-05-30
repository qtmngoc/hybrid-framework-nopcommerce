package com.jquery.datatable;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.jQuery.BaseTest;
import commons.jQuery.PageGeneratorManager;
import pageObjects.jQuery.dataTable.HomePageObject;

public class Level_10_Append_Grid extends BaseTest {

	@Parameters({ "browser", "appendGrid_url" })
	@BeforeClass
	public void beforeClass(String browserName, String appUrl) {
		driver = getBrowserDriver(browserName, appUrl);
		homePage = PageGeneratorManager.getHomePage(driver);
	}

	@Test
	public void Table_01_Action_In_Any_Row() {
		homePage.inputIntoTextboxByRowNumberAndColumnName("1", "Year", "2021");
		homePage.inputIntoTextboxByRowNumberAndColumnName("1", "Album", "Bloom");
		homePage.inputIntoTextboxByRowNumberAndColumnName("1", "Price", "263");
		homePage.inputIntoTextboxByRowNumberAndColumnName("1", "Artist", "Red Velvet");
		
		homePage.selectOptionInDropdownByRowNumberAndColumnName("1", "Origin", "Korea");
		homePage.selectOptionInDropdownByRowNumberAndColumnName("1", "Origin", "Hong Kong");
		homePage.selectOptionInDropdownByRowNumberAndColumnName("1", "Origin", "US");
	}

	@Test
	public void Table_02_Input_Into_Header() {

	}

	@Test
	public void Table_03_Get_Value() {

	}

	@AfterClass
	public void afterClass() {
		//driver.quit();
	}

	private WebDriver driver;
	HomePageObject homePage;
}