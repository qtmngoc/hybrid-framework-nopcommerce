package com.jquery;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.jQuery.JqBaseTest;
import pageObjects.jQuery.uploadFiles.JqHomePageObject;
import pageObjects.jQuery.uploadFiles.JqPageGeneratorManager;

public class Level_11_Upload_Files extends JqBaseTest {
	
	@Parameters({ "browser", "upload_url" })
	@BeforeClass
	public void beforeClass(String browserName, String appUrl) {
		driver = getBrowserDriver(browserName, appUrl);
		homePage = JqPageGeneratorManager.getHomePage(driver);
	}

	@Test
	public void Upload_01_One_File_Per_Time() {
		homePage.uploadMultipleFiles(driver, fruitFileName);;
		
		Assert.assertTrue(homePage.isFileLoadedByName(fruitFileName));
		
		homePage.clickOnStartButton();
		
		Assert.assertTrue(homePage.isFileUploadedLinkByName(fruitFileName));
		Assert.assertTrue(homePage.isFileUploadedImageByName(fruitFileName));
	}

	@Test
	public void Upload_02_Multiple_Files_Per_Time() {
		homePage.refreshCurrentPage(driver);
		homePage.uploadMultipleFiles(driver, multipleFileNames);;
		
		Assert.assertTrue(homePage.isFileLoadedByName(cakeFileName));
		Assert.assertTrue(homePage.isFileLoadedByName(catDogFileName));
		Assert.assertTrue(homePage.isFileLoadedByName(fruitFileName));
		Assert.assertTrue(homePage.isFileLoadedByName(sunflowerFileName));
		
		homePage.clickOnStartButton();
		
		Assert.assertTrue(homePage.isFileUploadedLinkByName(cakeFileName));
		Assert.assertTrue(homePage.isFileUploadedLinkByName(catDogFileName));
		Assert.assertTrue(homePage.isFileUploadedLinkByName(fruitFileName));
		Assert.assertTrue(homePage.isFileUploadedLinkByName(sunflowerFileName));
		
		Assert.assertTrue(homePage.isFileUploadedImageByName(cakeFileName));
		Assert.assertTrue(homePage.isFileUploadedImageByName(catDogFileName));
		Assert.assertTrue(homePage.isFileUploadedImageByName(fruitFileName));
		Assert.assertTrue(homePage.isFileUploadedImageByName(sunflowerFileName));
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	private WebDriver driver;
	JqHomePageObject homePage;
	String cakeFileName = "annie_cake.jpg";
	String catDogFileName = "annie_catdog.jpg";
	String fruitFileName = "annie_fruit.jpg";
	String sunflowerFileName = "annie_sunflower.jpg";
	String[] multipleFileNames = { cakeFileName, catDogFileName, fruitFileName, sunflowerFileName };
}