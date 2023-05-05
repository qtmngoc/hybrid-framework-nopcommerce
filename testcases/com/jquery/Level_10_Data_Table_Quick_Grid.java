package com.jquery;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.jQuery.JqBaseTest;
import pageObjects.jQuery.dataTable.JqHomePageObject;
import pageObjects.jQuery.dataTable.JqPageGeneratorManager;

public class Level_10_Data_Table_Quick_Grid extends JqBaseTest {

	@Parameters({ "browser", "quickgrid_url" })
	@BeforeClass
	public void beforeClass(String browserName, String appUrl) {
		driver = getBrowserDriver(browserName, appUrl);
		homePage = JqPageGeneratorManager.getHomePage(driver);
	}

	@Test
	public void Table_01_Paging() {
		homePage.openPaginationByPageNumber("8");
		Assert.assertTrue(homePage.isPageNumberActived("8"));
		
		homePage.openPaginationByPageNumber("23");
		Assert.assertTrue(homePage.isPageNumberActived("23"));

		homePage.openPaginationByPageNumber("16");
		Assert.assertTrue(homePage.isPageNumberActived("16"));
	}

	@Test
	public void Table_02_Input_Into_Header() {
		homePage.inputIntoHeaderTextboxByLabel("Country", "Nigeria");
		homePage.clickOnCountry("Nigeria");
		System.out.println(homePage.getValueOfModal());
	}

	@Test
	public void Table_03_Get_Value() {
		homePage.refreshCurrentPage(driver);
		
		System.out.println(homePage.getRowCountryOnAllPages());
		System.out.println(homePage.getRowValueOnAllPages());
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	private WebDriver driver;
	private JqHomePageObject homePage;
}