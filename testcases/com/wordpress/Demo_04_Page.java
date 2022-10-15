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

public class Demo_04_Page extends BaseTest {
	
	@Parameters({ "browser", "adminUrl", "userUrl" })
	@BeforeClass
	public void beforeClass(String browserName, String adminUrl, String userUrl) {
		this.adminUrl = adminUrl;	
		this.userUrl = userUrl;
		
		driver = getBrowserDriver(browserName, this.adminUrl);
		adminLoginPage = PageGeneratorManager.getAdminLoginPage(driver);
		
		adminDashboardPage = adminLoginPage.loginToSystem(driver, Demo_01_Login.username, Demo_01_Login.password);
		
		adminPageAllPage = adminDashboardPage.clickOnPageMenuLink();
	}
	
	@Test
	public void Page_01_Create_New_Page(Method method) {
		ExtentTestManagerV5.startTest(method.getName(), "Create new page at Admin site");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 01: Navigate to Admin ADD NEW PAGE page.");
		adminPageNewPage = adminPageAllPage.clickOnAddNewPageButton();
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 02: Click on 'Blank page' button.");
		adminPageNewPage.switchToPagePatternIframe();
		adminPageNewPage.clickOnBlankPageButton();
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 03: Enter \"" + pageTitle + "\" into 'Page Title'.");
		adminPageNewPage.inputIntoPageTitle(pageTitle);
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 04: Enter \"" + pageBody + "\" into 'Page Body'.");
		adminPageNewPage.inputIntoPageBody(pageBody);
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 05: Open 'Page' setting sidebar.");
		adminPageNewPage.clickOnPageSidebar();
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 06: Expand 'Featured image' panel.");
		adminPageNewPage.clickOnPanelByText("Featured image");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 07: Open 'Set featured image' menu and click on 'Media Library' item.");
		adminPageNewPage.clickOnImageMenu("Set featured image");
		adminPageNewPage.clickOnMediaLibraryItem();
		adminPageNewPage.switchToDefaultContent(driver);
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 08: Upload \"" + pageImage + "\" and get image name after uploaded.");
		adminPageNewPage.uploadPageImage(pageImage);
		uploadedImageName = adminPageNewPage.getUploadedImageName("title");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 09: Click on 'Insert' button and verify \"" + uploadedImageName + "\" is uploaded.");
		adminPageNewPage.clickOnInsertButton();
		adminPageNewPage.switchToPagePatternIframe();
		verifyTrue(adminPageNewPage.isImageUploaded(uploadedImageName));
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 10: Expand 'Discussion' panel and select 'Allow comments' checkbox.");
		adminPageNewPage.clickOnPanelByText("Discussion");
		adminPageNewPage.checkAllowCommentsCheckbox();
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 11: Click on 'Publish' and 'Pre-publish' button.");
		adminPageNewPage.clickOnPublishOrUpdateButton();
		adminPageNewPage.clickOnPrePublishButton();
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 12: Verify 'Page published.' message is displayed.");
		verifyTrue(adminPageNewPage.isPagePublishedOrUpdatedMessageDisplayed("Page published."));
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 13: Verify '" + pageTitle + " is now live.' message is displayed.");
		verifyEquals(adminPageNewPage.getPageNowLiveMessage(), pageTitle + " is now live.");
	}
	
	@Test
	public void Page_02_Search_And_View_Page(Method method) {
		ExtentTestManagerV5.startTest(method.getName(), "Search and view newly created page at Admin and User sites");

		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 01: Click on Wordpress logo and navigate to Admin ALL PAGES page.");
		adminPageNewPage.clickOnWordpressLogo();
		adminPageAllPage = adminPageNewPage.clickOnAllPagesLink();
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 02: Open 'Search' textbox and enter \"" + pageTitle + "\".");
		adminPageAllPage.switchToDefaultContent(driver);
		adminPageAllPage.clickOnOpenSearchIcon();
		adminPageAllPage.inputIntoSearchTextbox(pageTitle);

		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 03: Verify \"" + pageTitle + "\" is displayed.");
		verifyTrue(adminPageAllPage.isPageTitleDisplayed(pageTitle));
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 04: Open 'Ellipsis' menu and click on 'View page' item.");
		adminPageAllPage.clickOnEllipsisMenu(pageTitle, "View page");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 05: Select 'Phone' item from 'Web Preview' dropdown.");
		adminPageAllPage.selectPreviewOption("Phone");
		adminPageAllPage.switchToWebPreviewIframe();
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 06: Verify page Title, Image, Body, Comment textarea are displayed.");
		verifyTrue(adminPageAllPage.isPageTitlePreviewDisplayed(pageTitle));
		verifyTrue(adminPageAllPage.isPageImagePreviewDisplayed(pageTitle, uploadedImageName));
		verifyTrue(adminPageAllPage.isPageBodyPreviewDisplayed(pageBody));
		verifyTrue(adminPageAllPage.isCommentPreviewDisplayed());
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 07: Click on 'Close preview' button .");
		adminPageAllPage.switchToDefaultContent(driver);
		adminPageAllPage.clickonClosePreviewButton();
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 08: Open User HOME page.");
		userHomePage = adminPageAllPage.openUserSite(driver, this.userUrl);
		userHomePage.clickOnAcceptCookiesButton();
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 09: Click on 'Search' toggle and enter \"" + pageTitle + "\" into 'Search' textbox.");
		userHomePage.clickOnSearchToggle();
		userHomePage.inputIntoSearchTextbox(pageTitle);
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 10: Navigate to User SEARCH page.");
		userSearchPage = userHomePage.clickOnSearchButton();

		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 11: Verify 'Search Results' title contains \"" + pageTitle + "\".");
		verifyEquals(userSearchPage.getSearchResultsTitle(), "Search Results for: " + pageTitle);

		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 12: Verify '1 Post' is displayed.");
		verifyTrue(userSearchPage.isOnePostMessageDisplayed("1 Post"));

		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 13: Verify page Title, Image, Published Date are displayed.");
		verifyTrue(userSearchPage.isPostOrPageTitleDisplayed(pageTitle));
		verifyTrue(userSearchPage.isPostOrPageImageDisplayed(pageTitle, uploadedImageName));
		verifyTrue(userSearchPage.isPostOrPublishedDateDisplayed(pageTitle, publishedDate));

		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 14: Navigate to User PAGE DETAILS page.");
		userPageDetailPage = userSearchPage.clickOnPageTitleLink(pageTitle);
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 15: Verify page Title, Image, Body, Comment textarea are displayed.");
		verifyTrue(userPageDetailPage.isPageTitleDisplayed(pageTitle));
		verifyTrue(userPageDetailPage.isPageImageDisplayed(pageTitle, uploadedImageName));
		verifyTrue(userPageDetailPage.isPageBodyDisplayed(pageBody));
		verifyTrue(userPageDetailPage.isCommentTextareaDisplayed());
	}
	
	@Test
	public void Page_03_Edit_Page(Method method) {
		ExtentTestManagerV5.startTest(method.getName(), "Edit page at Admin site");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 01: Open Admin DASHBOARD page and navigate to Admin ALL PAGES page.");
		adminDashboardPage = userPageDetailPage.openAdminSite(driver, this.adminUrl);	
		adminPageAllPage = adminDashboardPage.clickOnPageMenuLink();

		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 02: Open 'Search' textbox and enter \"" + pageTitle + "\".");
		adminPageAllPage.clickOnOpenSearchIcon();
		adminPageAllPage.inputIntoSearchTextbox(pageTitle);

		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 03: Click on \"" + pageTitle + "\" link to navigate to Admin EDIT PAGE page.");
		adminPageAllPage.clickOnPageTitleLink(pageTitle);
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 04: Enter \"" + editTitle + "\" into 'Page Title'.");
		adminPageNewPage.switchToPagePatternIframe();
		adminPageNewPage.inputIntoPageTitle(editTitle);
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 05: Enter \"" + editBody + "\" into 'Page Body'.");
		adminPageNewPage.inputIntoEditPageBody(editBody);
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 06: Open 'Page' setting sidebar.");
		adminPageNewPage.clickOnPageSidebar();
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 07: Expand 'Featured image' panel.");
		adminPageNewPage.clickOnPanelByText("Featured image");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 08: Open 'Replace Image' menu and click on 'Media Library' item.");
		adminPageNewPage.clickOnImageMenu("Replace Image");
		adminPageNewPage.clickOnMediaLibraryItem();
		adminPageNewPage.switchToDefaultContent(driver);
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 09: Select \"" + uploadedImageName + "\" and click on 'Delete' button.");
		adminPageNewPage.selectPageImage(uploadedImageName);
		adminPageNewPage.clickOnDeleteImageIcon();
		adminPageNewPage.clickOnConfirmDeleteImageButton();
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 10: Open 'Search images' textbox and enter \"" + editImage + "\".");
		adminPageNewPage.clickOnSearchImageButton();
		adminPageNewPage.inputIntoSearchImageTextbox(editImage);
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 11: Select \"" + editImage + ".jpg\" and click on 'Insert' button.");
		adminPageNewPage.selectPageImage(editImage);
		adminPageNewPage.clickOnInsertButton();
		adminPageNewPage.switchToPagePatternIframe();
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 12: Verify \"" + editImage + ".jpg\" is uploaded.");
		verifyTrue(adminPageNewPage.isImageUploaded(editImage));

		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 10: Expand 'Discussion' panel and deselect 'Allow comments' checkbox.");
		adminPageNewPage.clickOnPanelByText("Discussion");
		adminPageNewPage.uncheckAllowCommentsCheckbox();
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 11: Click on 'Update' button.");
		adminPageNewPage.clickOnPublishOrUpdateButton();
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 12: Verify 'Page updated.' message is displayed.");
		verifyTrue(adminPageNewPage.isPagePublishedOrUpdatedMessageDisplayed("Page updated."));
	}
	
	@Test
	public void Page_04_Search_And_View_Page_After_Edit(Method method) {
		ExtentTestManagerV5.startTest(method.getName(), "Search and view after editing page at Admin and User sites");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 01: Click on Wordpress logo and navigate to Admin ALL PAGES page.");
		adminPageNewPage.clickOnWordpressLogo();
		adminPageAllPage = adminPageNewPage.clickOnAllPagesLink();
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 02: Open 'Search' textbox and enter \"" + editTitle + "\".");
		adminPageAllPage.switchToDefaultContent(driver);
		adminPageAllPage.clickOnOpenSearchIcon();
		adminPageAllPage.inputIntoSearchTextbox(editTitle);

		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 03: Verify \"" + editTitle + "\" is displayed.");
		verifyTrue(adminPageAllPage.isPageTitleDisplayed(editTitle));
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 04: Open 'Ellipsis' menu and click on 'View page' item.");
		adminPageAllPage.clickOnEllipsisMenu(editTitle, "View page");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 05: Select 'Tablet' item from 'Web Preview' dropdown.");
		adminPageAllPage.selectPreviewOption("Tablet");
		adminPageAllPage.switchToWebPreviewIframe();
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 06: Verify page Title, Image, Body are displayed.");
		verifyTrue(adminPageAllPage.isPageTitlePreviewDisplayed(editTitle));
		verifyTrue(adminPageAllPage.isPageImagePreviewDisplayed(editTitle, editImage));
		verifyTrue(adminPageAllPage.isPageBodyPreviewDisplayed(editBody));
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 07: Verify Comment textarea is undisplayed.");
		verifyTrue(adminPageAllPage.isCommentPreviewUndisplayed());
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 08: Click on 'Visit site' button.");
		adminPageAllPage.switchToDefaultContent(driver);
		userPageDetailPage = adminPageAllPage.clickOnVisitSiteButton();
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 09: Switch to User PAGE DETAILS tab.");
		userPageDetailPage.switchToWindowByID(driver, driver.getWindowHandle());
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 10: Verify page Title, Image, Body are displayed.");
		verifyTrue(userPageDetailPage.isPageTitleDisplayed(editTitle));
		verifyTrue(userPageDetailPage.isPageImageDisplayed(editTitle, editImage));
		verifyTrue(userPageDetailPage.isPageBodyDisplayed(editBody));
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 11: Verify Comment textarea is undisplayed.");
		verifyTrue(userPageDetailPage.isCommentTextareaUndisplayed());
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 12: Navigate to User HOME page.");
		userHomePage = userPageDetailPage.clickOnHomePageLink();

		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 13: Click on 'Search' toggle and enter \"" + editTitle + "\" into 'Search' textbox.");
		userHomePage.clickOnSearchToggle();
		userHomePage.inputIntoSearchTextbox(editTitle);
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 14: Navigate to User SEARCH page.");
		userSearchPage = userHomePage.clickOnSearchButton();

		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 15: Verify 'Search Results' title contains \"" + editTitle + "\".");
		verifyEquals(userSearchPage.getSearchResultsTitle(), "Search Results for: " + editTitle);

		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 16: Verify '1 Post' is displayed.");
		verifyTrue(userSearchPage.isOnePostMessageDisplayed("1 Post"));

		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 17: Verify page Title, Image, Published Date are displayed.");
		verifyTrue(userSearchPage.isPostOrPageTitleDisplayed(editTitle));
		verifyTrue(userSearchPage.isPostOrPageImageDisplayed(editTitle, editImage));
		verifyTrue(userSearchPage.isPostOrPublishedDateDisplayed(editTitle, publishedDate));
	}
	
	@Test
	public void Page_05_Delete_Post(Method method) {
		ExtentTestManagerV5.startTest(method.getName(), "Delete post at Admin site");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 01: Switch to Admin ALL PAGES tab.");
		userSearchPage.switchToWindowByID(driver, driver.getWindowHandle());
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 02: Click on 'Close preview' button .");
		adminPageAllPage.clickonClosePreviewButton();

		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 03: Open 'Search' textbox and enter \"" + editTitle + "\".");
		adminPageAllPage.clickOnOpenSearchIcon();
		adminPageAllPage.inputIntoSearchTextbox(editTitle);		
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 04: Open 'Ellipsis' menu and click on 'Trash' item.");
		adminPageAllPage.clickOnEllipsisMenu(editTitle, "Trash");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 05: Verify 'Page trashed.' message is displayed.");
		verifyTrue(adminPageAllPage.isPageTrashedOrDeletedMessageDisplayed("Page trashed."));
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 06: Close 'Search' textbox.");
		adminPageAllPage.clickOnCloseSearchIcon();
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 07: Open 'Search' textbox again and enter \"" + editTitle + "\".");
		adminPageAllPage.clickOnOpenSearchIcon();
		adminPageAllPage.inputIntoSearchTextbox(editTitle);	
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 08: Open 'Ellipsis' menu and click on 'Trash' item.");
		adminPageAllPage.clickOnDeleteEllipsisMenu(editTitle, "Delete");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 09: Click 'OK' button on Delete confirmation alert.");
		adminPageAllPage.accepAlert(driver);

		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 10: Verify 'Page deleted.' message is displayed.");
		verifyTrue(adminPageAllPage.isPageTrashedOrDeletedMessageDisplayed("Page deleted."));
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 11: Verify 'No pages match your search for " + editTitle + ".' message is displayed.");
		verifyEquals(adminPageAllPage.getNoResultsMessage(), "No pages match your search for " + editTitle + ".");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 12: Switch to User SEARCH tab.");
		adminPageAllPage.switchToWindowByID(driver, driver.getWindowHandle());
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 13: Click on 'Search' toggle and enter \"" + editTitle + "\" into 'Search' textbox.");
		userSearchPage.clickOnSearchToggle();
		userSearchPage.inputIntoSearchTextbox(editTitle);
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 14: Click on 'Search' button.");
		userSearchPage.clickOnSearchButton();
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 15: Verify 'It seems we can't find what you’re looking for.' message is displayed.");
		verifyTrue(userSearchPage.isSearchResultsMessageDisplayed());
	}


	@AfterClass(alwaysRun = true)
	public void afterClass() {
		closeBrowserAndDriver();
	}
	
	String adminUrl, userUrl, uploadedImageName;
	int randomNumber4 = Demo_01_Login.randomNumber1;
	String pageTitle = "[Annie]_New-page-title_" + randomNumber4;
	String pageBody = "Coding Demo: page body " + randomNumber4;
	String pageImage = "annie_catdog.jpg";
	String publishedDate = getCurrentDate();
	String editTitle = "[Annie]_Edit-page-title_" + randomNumber4;
	String editBody = "Edit page body " + randomNumber4;
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