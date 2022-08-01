package com.wordpress;

import java.lang.reflect.Method;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import commons.wordPress.BaseTest;
import commons.wordPress.PageGeneratorManager;
import pageObjects.wordPress.AdminDashboardPO;
import pageObjects.wordPress.AdminLoginPO;
import pageObjects.wordPress.AdminPageAllPO;
import pageObjects.wordPress.AdminPageNewPO;
import pageObjects.wordPress.UserHomePO;
import pageObjects.wordPress.UserPageDetailPO;
import pageObjects.wordPress.UserSearchPO;
import reportConfig.wordPress.ExtentTestManagerV5;

public class Live_Coding_04_Page extends BaseTest {
	
	@Parameters({ "browser", "adminUrl", "userUrl" })
	@BeforeClass
	public void beforeClass(String browserName, String adminUrl, String userUrl) {
		this.adminUrl = adminUrl;	
		this.userUrl = userUrl;
		
		driver = getBrowserDriver(browserName, this.adminUrl);
		adminLoginPage = PageGeneratorManager.getAdminLoginPage(driver);
		
		adminDashboardPage = adminLoginPage.loginToSystem(driver, Live_Coding_01_Login.username, Live_Coding_01_Login.password);
		
		adminPageAllPage = adminDashboardPage.clickOnPageMenuLink();
	}
	
	@Test
	public void Category_01_Create_New_Page(Method method) {
		ExtentTestManagerV5.startTest(method.getName(), "Create new page");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 01: Navigate to Admin ADD NEW PAGE page.");
		adminPageNewPage = adminPageAllPage.clickOnAddNewPageButton();
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 02: Click on 'Blank page' button.");
		adminPageNewPage.clickOnBlankPageButton();
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 03: Enter \"" + pageTitle + "\" into 'Page Title'.");
		adminPageNewPage.inputIntoPostTitle(pageTitle);
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 04: Enter \"" + pageBody + "\" into 'Page Body'.");
		adminPageNewPage.inputIntoPostBody(pageBody);
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 05: Open 'Page' setting sidebar.");
		adminPageNewPage.clickOnPageSidebar();
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 06: Expand 'Featured image' panel.");
		adminPageNewPage.clickOnPanelByText("Featured image");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 07: Open 'Set featured image' menu and click on 'Media Library' item.");
		adminPageNewPage.clickOnSetFeaturedImageMenu();
		adminPageNewPage.clickOnSetMediaLibraryItem();
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 08: Upload image and get image name after uploaded.");
		adminPageNewPage.uploadMultipleFiles(driver, pageImage);
		imageUploadedName = adminPageNewPage.getImageNameUploaded();
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 09: Click on 'Insert' button and verify image is uploaded.");
		adminPageNewPage.clickOnInsertButton();
		verifyTrue(adminPageNewPage.isImageUploaded(imageUploadedName));
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 10: Expand 'Discussion' panel and select 'Allow comments' checkbox.");
		adminPageNewPage.clickOnPanelByText("Discussion");
		adminPageNewPage.checkAllowCommentsCheckbox();
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 11: Click on 'Publish' and 'Pre-publish' button.");
		adminPageNewPage.clickOnPublishOrUpdateButton();
		adminPageNewPage.clickOnPrePublishButton();
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 12: Verify 'Page published.' message is displayed.");
		verifyTrue(adminPageNewPage.isPagePublishedOrUpdatedMessageDisplayed("Page published."));
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 12: Verify '" + pageTitle + " is now live.' message is displayed.");
		verifyTrue(adminPageNewPage.isPageNowLiveMessageDisplayed("Page published."));

	}
	
	@Test
	public void Category_02_Create_New_Child_Category(Method method) {
		ExtentTestManagerV5.startTest(method.getName(), "Create new child category");

		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 01: Click on 'Add New Category' button to open dialog.");
		
	}
	
	@Test
	public void Category_03_Search_Category(Method method) {
		ExtentTestManagerV5.startTest(method.getName(), "Search newly created categories");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 01: Enter \"" +  + "\" into 'Search' textbox.");
		
	}
	
	@Test
	public void Category_04_Edit_Child_Category(Method method) {
		ExtentTestManagerV5.startTest(method.getName(), "Edit child category");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 01: Enter \"" + childCatName + "\" into 'Search' textbox.");
		
	}
	
	@Test
	public void Category_05_Edit_Parent_Category(Method method) {
		ExtentTestManagerV5.startTest(method.getName(), "Edit parent category");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 01: Enter \"" + parentCatName + "\" into 'Search' textbox.");
		
	}
	
	@Test
	public void Category_06_Search_Category_After_Edit(Method method) {
		ExtentTestManagerV5.startTest(method.getName(), "Search edited categories");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 01: Enter \"" + editChildCatName + "\" into 'Search' textbox.");
		
	}
	
	@Test
	public void Category_07_Delete_Category(Method method) {
		ExtentTestManagerV5.startTest(method.getName(), "Delete categories");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 01: Enter \"" + editChildCatName + "\" into 'Search' textbox.");
		
	}

	@AfterClass(alwaysRun = true)
	public void afterClass() {
		closeBrowserAndDriver();
	}
	
	String adminUrl, userUrl, imageUploadedName;
	int randomNumber4 = Live_Coding_02_Post.randomNumber2;
	String pageTitle = "[Annie]_New-page-title_" + randomNumber4;
	String pageBody = "Live Coding: page body " + randomNumber4;
	String pageImage = "annie_catdog.jpg";
	String editTitle = "[Annie]_Edit-page-title_" + randomNumber4;
	String editBody = "Edit page body";
	String editImage = "annie_fruit";
	
	WebDriver driver;
	AdminLoginPO adminLoginPage;
	AdminDashboardPO adminDashboardPage;
	AdminPageAllPO adminPageAllPage;
	AdminPageNewPO adminPageNewPage;
	UserHomePO userHomePage;
	UserSearchPO userSearchPage;
	UserPageDetailPO userPageDetailPage;
}