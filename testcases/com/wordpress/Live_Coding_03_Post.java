package com.wordpress;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.wordPress.BaseTest;
import commons.wordPress.PageGeneratorManager;
import pageObjects.wordPress.AdminDashboardPO;
import pageObjects.wordPress.AdminLoginPO;
import pageObjects.wordPress.AdminPostAllPO;
import pageObjects.wordPress.AdminPostNewPO;
import pageObjects.wordPress.UserHomePO;
import pageObjects.wordPress.UserPostDetailPO;

public class Live_Coding_03_Post extends BaseTest {
	int randomNumber = generateFakeNumber();
	String username = "automationeditor";
	String password = "automationfc";
	String authorName = "Automation FC";
	String postTitle = "[Annie] Live Coding title " + randomNumber;
	String postBody = "Live Coding body " + randomNumber;
	
	@Parameters({ "browser", "adminUrl" })
	@BeforeClass
	public void beforeClass(String browserName, String adminUrl) {		
		log.info("Pre-condition - Step 01: Open browser and navigate to admin Login page");
		driver = getBrowserDriver(browserName, adminUrl);
		adminLoginPage = PageGeneratorManager.getAdminLoginPage(driver);
		
		log.info("Pre-condition - Step 02: Enter '" + username + "' into Username text box");
		adminLoginPage.inputIntoUsernameTextbox(username);
		
		log.info("Pre-condition - Step 03: Click on Continue button");
		adminLoginPage.clickOnContinueButton();
				
		log.info("Pre-condition - Step 04: Enter '" + password + "' into Password text box");
		adminLoginPage.inputIntoPasswordTextbox(password);
		
		log.info("Pre-condition - Step 05: Navigate to admin Dashboard page");
		adminDashboardPage = adminLoginPage.clickOnLoginButton();	
	}

	@Test
	public void Post_01_Create_New_Post() {
		log.info("Create Post - Step 01: Click on Posts menu");
		adminPostAllPage = adminDashboardPage.clickOnPostMenu();
		
		log.info("Create Post - Step 02: Navigate to admin Add New Post page");
		adminPostNewPage = adminPostAllPage.clickOnAddNewButton();
		
		log.info("Create Post - Step 03: Enter '" + postTitle + "' into Post title");
		adminPostNewPage.inputIntoPostTitle(postTitle);
		
		log.info("Create Post - Step 04: Enter '" + postBody + "' into Post body");
		adminPostNewPage.inputIntoPostBody(postBody);
		
		log.info("Create Post - Step 05: Click on Publish button");
		adminPostNewPage.clickOnPublishButton(postBody);
		
		log.info("Create Post - Step 06: Click on Pre-publish button");
		adminPostNewPage.clickOnPrePublishButton(postBody);
		
		log.info("Create Post - Step 07: Verify 'Post published.' message is displayed");
		verifyTrue(adminPostNewPage.isPostPublishedMessageDisplayed("Post published."));
	}
	
	@Test
	public void Post_02_Search_Post() {
		log.info("Search Post - Step 01: Click on Wordpress button to open editor sidebar");
		adminPostNewPage.clickOnWordpressButton();
		
		log.info("Search Post - Step 02: Navigate to admin All Posts page");
		adminPostAllPage = adminPostNewPage.clickOnAllPostsLink();
		
		log.info("Search Post - Step 03: Enter '" + postTitle + "' into Search Posts");
		adminPostAllPage.inputIntoSearchTextbox(postTitle);
		
		log.info("Search Post - Step 04: Click on Search Posts button");
		adminPostAllPage.clickOnSearchPostsButton();
		
		log.info("Search Post - Step 05: Verify Search Results table contains post: '" + postTitle + "'");
		verifyTrue(adminPostAllPage.isSearchResultTableDisplayed("Title", postTitle));
		
		log.info("Search Post - Step 06: Verify Search Results table contains author: '" + authorName + "'");
		verifyTrue(adminPostAllPage.isSearchResultTableDisplayed("Author", authorName));
	}
	
	@Test
	public void Post_03_View_Post() {
		log.info("View Post - Step 01: Open user Home page");
		
		log.info("View Post - Step 02: Verify post: '" + postTitle + "' is displayed");
		
		log.info("View Post - Step 03: Navigate to Post Details page");
		
		log.info("View Post - Step 04: Verify Post Body is true");
		
		log.info("View Post - Step 05: Verify Post Date is true");
		
		log.info("View Post - Step 05: Verify Author is true");
	}
	
	@Test
	public void Post_04_Edit_Post() {
		
	}
	
	@Test
	public void Post_05_Delete_Post() {

	}

	@AfterClass(alwaysRun = true)
	public void afterClass() {
		closeBrowserAndDriver();
	}
	
	WebDriver driver;
	AdminLoginPO adminLoginPage;
	AdminDashboardPO adminDashboardPage;
	AdminPostAllPO adminPostAllPage;
	AdminPostNewPO adminPostNewPage;
	UserHomePO userHomePage;
	UserPostDetailPO userPostDetailPage;
}