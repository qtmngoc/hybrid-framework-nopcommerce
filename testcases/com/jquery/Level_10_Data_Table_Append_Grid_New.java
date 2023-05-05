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

public class Level_10_Data_Table_Append_Grid_New extends JqBaseTest {

	@Parameters({ "browser", "appendGrid_url" })
	@BeforeClass
	public void beforeClass(String browserName, String appUrl) {
		driver = getBrowserDriver(browserName, appUrl);
		homePage = JqPageGeneratorManager.getHomePage(driver);
	}

	@Test
	public void Table_01_One_Row() {
		day = "07";
		month = "05";
		year = "2012";
		
		homePage.inputIntoTextboxByRowNumberAndColumnName("2", "Company", "YG Entertainment");
		homePage.inputIntoTextboxByRowNumberAndColumnName("2", "Contact Person", "Rosé");
		homePage.inputIntoTextboxByRowNumberAndColumnName("2", "Order Placed", "112");
		homePage.inputIntoTextboxByRowNumberAndColumnName("2", "Member Since", homePage.dateMemberSinceInput(day, month, year));
		homePage.selectOptionInDropdownByRowNumberAndColumnName("2", "Country", "Taiwan");
		homePage.selectCheckboxByRowNumberAndColumnName("2", "NPO?");
		
		Assert.assertEquals("YG Entertainment", homePage.getTextboxValueByRowNumberAndColumnName("2", "Company"));
		Assert.assertEquals("Rosé", homePage.getTextboxValueByRowNumberAndColumnName("2", "Contact Person"));
		Assert.assertEquals("112", homePage.getTextboxValueByRowNumberAndColumnName("2", "Order Placed"));
		Assert.assertEquals(homePage.dateMemberSinceOutput(day, month, year), homePage.getTextboxValueByRowNumberAndColumnName("2", "Member Since"));
		Assert.assertEquals("Taiwan", homePage.getDropdownValueByRowNumberAndColumnName("2", "Country"));
		Assert.assertTrue(homePage.isCheckboxSelectedByRowNumberAndColumnName("2", "NPO?"));
	}

	@Test
	public void Table_02_Any_Row() {
		day = "11";
		month = "04";
		year = "2011";
		
		homePage.clickOnLoadDataButton();
		
		homePage.inputIntoTextboxByRowNumberAndColumnName("3", "Company", "BlackPink");
		homePage.inputIntoTextboxByRowNumberAndColumnName("4", "Contact Person", "Lalisa Manobal");
		homePage.inputIntoTextboxByRowNumberAndColumnName("1", "Order Placed", "273");
		homePage.inputIntoTextboxByRowNumberAndColumnName("8", "Member Since", homePage.dateMemberSinceInput(day, month, year));
		homePage.selectOptionInDropdownByRowNumberAndColumnName("6", "Country", "Hong Kong");
		homePage.deselectCheckboxByRowNumberAndColumnName("5", "NPO?");
		
		Assert.assertEquals("BlackPink", homePage.getTextboxValueByRowNumberAndColumnName("3", "Company"));
		Assert.assertEquals("Lalisa Manobal", homePage.getTextboxValueByRowNumberAndColumnName("4", "Contact Person"));
		Assert.assertEquals("273", homePage.getTextboxValueByRowNumberAndColumnName("1", "Order Placed"));
		Assert.assertEquals(homePage.dateMemberSinceOutput(day, month, year), homePage.getTextboxValueByRowNumberAndColumnName("8", "Member Since"));
		Assert.assertEquals("Hong Kong", homePage.getDropdownValueByRowNumberAndColumnName("6", "Country"));
		Assert.assertFalse(homePage.isCheckboxSelectedByRowNumberAndColumnName("5", "NPO?"));
	}
	
	@Test
	public void Table_03_Icon() {
		homePage.clickOnLoadDataButton();
		
		homePage.clickOnAppendOrRemoveRow("Append Row");
		homePage.clickOnAppendOrRemoveRow("Append Row");
		homePage.clickOnAppendOrRemoveRow("Append Row");
		homePage.clickOnAppendOrRemoveRow("Append Row");
		homePage.clickOnAppendOrRemoveRow("Remove Last Row");
		Assert.assertEquals(11, homePage.numberOfRowsInTable());
		
		homePage.inputIntoTextboxByRowNumberAndColumnName("10", "Contact Person", "Kim Jennie");
		Assert.assertEquals("Kim Jennie", homePage.getTextboxValueByRowNumberAndColumnName("10", "Contact Person"));
		
		homePage.clickOnIconByRowNumber("6", "Move Up");
		Assert.assertEquals("MacGyver, Rohan and West", homePage.getTextboxValueByRowNumberAndColumnName("5", "Company"));
		
		homePage.clickOnIconByRowNumber("2", "Remove Current Row");
		Assert.assertEquals("282", homePage.getTextboxValueByRowNumberAndColumnName("2", "Order Placed"));

		homePage.clickOnIconByRowNumber("4", "Move Down");
		Assert.assertEquals("United Kingdom", homePage.getDropdownValueByRowNumberAndColumnName("5", "Country"));
		
		homePage.clickOnIconByRowNumber("7", "Insert Row Above");
		Assert.assertEquals(11, homePage.numberOfRowsInTable());
		
		day = "30";
		month = "01";
		year = "2023";
		
		homePage.inputIntoTextboxByRowNumberAndColumnName("7", "Company", "The Black Label");
		homePage.inputIntoTextboxByRowNumberAndColumnName("7", "Contact Person", "Park Bo-gum");
		homePage.inputIntoTextboxByRowNumberAndColumnName("7", "Order Placed", "166");
		homePage.inputIntoTextboxByRowNumberAndColumnName("7", "Member Since", homePage.dateMemberSinceInput(day, month, year));
		homePage.selectOptionInDropdownByRowNumberAndColumnName("7", "Country", "Malaysia");
		homePage.selectCheckboxByRowNumberAndColumnName("7", "NPO?");
		
		Assert.assertEquals("The Black Label", homePage.getTextboxValueByRowNumberAndColumnName("7", "Company"));
		Assert.assertEquals("Park Bo-gum", homePage.getTextboxValueByRowNumberAndColumnName("7", "Contact Person"));
		Assert.assertEquals("166", homePage.getTextboxValueByRowNumberAndColumnName("7", "Order Placed"));
		Assert.assertEquals(homePage.dateMemberSinceOutput(day, month, year), homePage.getTextboxValueByRowNumberAndColumnName("7", "Member Since"));
		Assert.assertEquals("Malaysia", homePage.getDropdownValueByRowNumberAndColumnName("7", "Country"));
		Assert.assertTrue(homePage.isCheckboxSelectedByRowNumberAndColumnName("7", "NPO?"));
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	private WebDriver driver;
	private JqHomePageObject homePage;
	private String day, month, year;
	
}