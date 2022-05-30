package com.jquery.datatable;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.jQuery.BaseTest;
import commons.jQuery.PageGeneratorManager;
import pageObjects.jQuery.HomePageObject;

public class Level_10_Data_Grid extends BaseTest {
	HomePageObject homePage;

	@Parameters({ "browser", "url" })
	@BeforeClass
	public void beforeClass(String browserName, String appUrl) {
		driver = getBrowserDriver(browserName, appUrl);
		homePage = PageGeneratorManager.getHomePage(driver);
	}

	@Test
	public void Table_01_Paging() {
		homePage.openPaginationByPageNumber("8");
		Assert.assertTrue(homePage.isPageNumberActived("8"));
		
		homePage.openPaginationByPageNumber("15");
		Assert.assertTrue(homePage.isPageNumberActived("15"));

		homePage.openPaginationByPageNumber("3");
		Assert.assertTrue(homePage.isPageNumberActived("3"));

		homePage.openPaginationByPageNumber("21");
		Assert.assertTrue(homePage.isPageNumberActived("21"));

		homePage.openPaginationByPageNumber("12");
		Assert.assertTrue(homePage.isPageNumberActived("12"));
	}

	@Test
	public void Table_02() {

	}

	@Test
	public void Table_03() {

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	private WebDriver driver;

}