package com.wordpress;

import java.lang.reflect.Method;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import commons.wordPress.WpBaseTest;
import commons.wordPress.WpPageGeneratorManager;
import pageObjects.wordPress.WpAdminDashboardPO;
import pageObjects.wordPress.WpAdminLoginPO;
import pageObjects.wordPress.WpAdminPageAllPO;
import pageObjects.wordPress.WpAdminPageNewPO;
import pageObjects.wordPress.WpUserHomePO;
import pageObjects.wordPress.WpUserPageDetailPO;
import pageObjects.wordPress.WpUserSearchPO;
import reportConfig.wordPress.WpExtentTestManagerV5;

public class Demo_04_Page extends WpBaseTest {
	
	@Parameters({ "browser", "adminUrl", "userUrl" })
	@BeforeClass
	public void beforeClass(String browserName, String adminUrl, String userUrl) {
		this.adminUrl = adminUrl;	
		this.userUrl = userUrl;
		this.browserName = browserName;
		
		driver = getBrowserDriver(browserName, adminUrl);
		adminLoginPage = WpPageGeneratorManager.getAdminLoginPage(driver);
		
		adminDashboardPage = adminLoginPage.loginToSystem(driver, Demo_01_Login.username, Demo_01_Login.password);
		
		adminPageAllPage = adminDashboardPage.clickOnPageMenuLink();
	}
	
	@Test
	public void Page_01_Create_New_Page(Method method) {
		WpExtentTestManagerV5.startTest(method.getName() + " - " + browserName, "Create a new page on Admin site");
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step 01: Click on 'Pages' menu and 'Add new page' button to go to Admin ADD NEW PAGE page.");
		adminPageNewPage = adminPageAllPage.clickOnAddNewPageButton();
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step 02: Click on 'Blank page' button.");
		adminPageNewPage.switchToPagePatternIframe();
		adminPageNewPage.clickOnBlankPageButton();
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step 03: Enter \"" + pageTitle + "\" into page Title.");
		adminPageNewPage.inputIntoPageTitle(pageTitle);
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step 04: Enter \"" + pageBody + "\" into page Body.");
		adminPageNewPage.inputIntoPageBody(pageBody);
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step 05: Open 'Page' settings sidebar.");
		adminPageNewPage.clickOnPageSidebar();
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step 06: Expand 'Featured image' panel.");
		adminPageNewPage.clickOnPanelByText("Featured image");
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step 07: Open 'Set featured image' menu and click on 'Media Library' item.");
		adminPageNewPage.clickOnImageMenu("Set featured image");
		adminPageNewPage.clickOnMediaLibraryItem();
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step 08: Upload \"" + pageImage + "\" and get image name after uploading.");
		adminPageNewPage.switchToDefaultContent(driver);
		adminPageNewPage.uploadPageImage(pageImage);
		uploadedImageName = adminPageNewPage.getUploadedImageName("title");
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step 09: Click on 'Insert' button.");
		adminPageNewPage.clickOnInsertButton();

		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step 10: Verify \"" + uploadedImageName + "\" is uploaded.");
		adminPageNewPage.switchToPagePatternIframe();
		verifyTrue(adminPageNewPage.isImageUploaded(uploadedImageName));
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step 11: Click on 'Publish' and 'Pre-publish' buttons.");
		adminPageNewPage.clickOnPublishOrUpdateButton();
		adminPageNewPage.clickOnPrePublishButton();
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step 12: Verify 'Page published.' and '" + pageTitle + " is now live.' messages are displayed.");
		verifyTrue(adminPageNewPage.isPagePublishedOrUpdatedMessageDisplayed("Page published."));
		verifyEquals(adminPageNewPage.getPageNowLiveMessage(), pageTitle + " is now live.");
		publishedDate = adminPageNewPage.getCurrentDate();
	}
	
	@Test
	public void Page_02_Search_And_View_Page(Method method) {
		WpExtentTestManagerV5.startTest(method.getName() + " - " + browserName, "Search and view newly created page on Admin and User sites");

		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step 01: Click on Wordpress logo and navigate to Admin ALL PAGES page.");
		adminPageNewPage.clickOnWordpressLogo();
		adminPageAllPage = adminPageNewPage.clickOnAllPagesLink();
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step 02: Open 'Search' textbox and enter \"" + pageTitle + "\".");
		adminPageAllPage.switchToDefaultContent(driver);
		adminPageAllPage.clickOnOpenSearchIcon();
		adminPageAllPage.inputIntoSearchTextbox(pageTitle);

		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step 03: Verify \"" + pageTitle + "\" is displayed.");
		verifyTrue(adminPageAllPage.isPageTitleDisplayed(pageTitle));
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step 04: Open 'Ellipsis' menu and click on 'View page' item.");
		adminPageAllPage.clickOnEllipsisMenu(pageTitle, "View page");
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step 05: Select 'Phone' item from 'Web Preview' dropdown.");
		adminPageAllPage.selectPreviewOption("Phone");
		adminPageAllPage.switchToWebPreviewIframe();
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step 06: Verify page Title, Image, Body, Comment textarea are displayed.");
		verifyTrue(adminPageAllPage.isPageTitlePreviewDisplayed(pageTitle));
		verifyTrue(adminPageAllPage.isPageImagePreviewDisplayed(pageTitle, uploadedImageName));
		verifyTrue(adminPageAllPage.isPageBodyPreviewDisplayed(pageBody));
		verifyTrue(adminPageAllPage.isCommentPreviewDisplayed());
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step 07: Click on 'Close preview' button .");
		adminPageAllPage.switchToDefaultContent(driver);
		adminPageAllPage.clickonClosePreviewButton();
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step 08: Open User HOME page.");
		userHomePage = adminPageAllPage.openUserSite(driver, userUrl);
		userHomePage.clickOnAcceptCookiesButton();
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step 09: Click on 'Search' toggle and enter \"" + pageTitle + "\" into 'Search' textbox.");
		userHomePage.clickOnSearchToggle();
		userHomePage.inputIntoSearchTextbox(pageTitle);
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step 10: Navigate to User SEARCH page.");
		userSearchPage = userHomePage.clickOnSearchButton();

		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step 12: Verify '1 Post' is displayed.");
		verifyTrue(userSearchPage.isOnePostMessageDisplayed("1 Post"));

		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step 13: Verify page Title, Image, Published Date are displayed.");
		verifyTrue(userSearchPage.isPostOrPageTitleDisplayed(pageTitle));
		verifyTrue(userSearchPage.isPostOrPageImageDisplayed(pageTitle, uploadedImageName));
		verifyTrue(userSearchPage.isPostOrPagePublishedDateDisplayed(pageTitle, publishedDate));

		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step 14: Navigate to User PAGE DETAILS page.");
		userPageDetailPage = userSearchPage.clickOnPageTitleLink(pageTitle);
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step 15: Verify page Title, Image, Body, Comment textarea are displayed.");
		verifyTrue(userPageDetailPage.isPageTitleDisplayed(pageTitle));
		verifyTrue(userPageDetailPage.isPageImageDisplayed(pageTitle, uploadedImageName));
		verifyTrue(userPageDetailPage.isPageBodyDisplayed(pageBody));
		verifyTrue(userPageDetailPage.isPageCommentTextareaDisplayed());
	}
	
	@Test
	public void Page_03_Edit_Page(Method method) {
		WpExtentTestManagerV5.startTest(method.getName() + " - " + browserName, "Edit page on Admin site");
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step 01: Open Admin DASHBOARD page and navigate to Admin ALL PAGES page.");
		adminDashboardPage = userPageDetailPage.openAdminSite(driver, adminUrl);	
		adminPageAllPage = adminDashboardPage.clickOnPageMenuLink();

		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step 02: Open 'Search' textbox and enter \"" + pageTitle + "\".");
		adminPageAllPage.clickOnOpenSearchIcon();
		adminPageAllPage.inputIntoSearchTextbox(pageTitle);

		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step 03: Click on \"" + pageTitle + "\" link to navigate to Admin EDIT PAGE page.");
		adminPageAllPage.clickOnPageTitleLink(pageTitle);
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step 04: Enter \"" + editTitle + "\" into page Title.");
		adminPageNewPage.switchToPagePatternIframe();
		adminPageNewPage.inputIntoPageTitle(editTitle);
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step 05: Enter \"" + editBody + "\" into page Body.");
		adminPageNewPage.inputIntoEditPageBody(editBody);
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step 06: Open 'Page' settings sidebar.");
		adminPageNewPage.clickOnPageSidebar();
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step 07: Expand 'Featured image' panel.");
		adminPageNewPage.clickOnPanelByText("Featured image");
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step 08: Open 'Replace Image' menu and click on 'Media Library' item.");
		adminPageNewPage.clickOnImageMenu("Replace Image");
		adminPageNewPage.clickOnMediaLibraryItem();
		adminPageNewPage.switchToDefaultContent(driver);
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step 09: Select \"" + uploadedImageName + "\" and click on 'Delete' button.");
		adminPageNewPage.selectPageImage(uploadedImageName);
		adminPageNewPage.clickOnDeleteImageIcon();
		adminPageNewPage.clickOnConfirmDeleteImageButton();
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step 10: Open 'Search images' textbox and enter \"" + editImage + "\".");
		adminPageNewPage.clickOnSearchImageButton();
		adminPageNewPage.inputIntoSearchImageTextbox(editImage);
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step 11: Select \"" + editImage + ".jpg\" and click on 'Insert' button.");
		adminPageNewPage.selectPageImage(editImage);
		adminPageNewPage.clickOnInsertButton();
		adminPageNewPage.switchToPagePatternIframe();
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step 12: Verify \"" + editImage + ".jpg\" is uploaded.");
		verifyTrue(adminPageNewPage.isImageUploaded(editImage));

		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step 13: Expand 'Discussion' panel and deselect 'Allow comments' checkbox.");
		adminPageNewPage.clickOnPanelByText("Discussion");
		adminPageNewPage.uncheckAllowCommentsCheckbox();
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step 14: Click on 'Update' button.");
		adminPageNewPage.clickOnPublishOrUpdateButton();
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step 15: Verify 'Page updated.' message is displayed.");
		verifyTrue(adminPageNewPage.isPagePublishedOrUpdatedMessageDisplayed("Page updated."));
	}
	
	@Test
	public void Page_04_Search_And_View_Page_After_Edit(Method method) {
		WpExtentTestManagerV5.startTest(method.getName() + " - " + browserName, "Search and view after editing page on Admin and User sites");
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step 01: Click on Wordpress logo and navigate to Admin ALL PAGES page.");
		adminPageNewPage.clickOnWordpressLogo();
		adminPageAllPage = adminPageNewPage.clickOnAllPagesLink();
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step 02: Open 'Search' textbox and enter \"" + editTitle + "\".");
		adminPageAllPage.switchToDefaultContent(driver);
		adminPageAllPage.clickOnOpenSearchIcon();
		adminPageAllPage.inputIntoSearchTextbox(editTitle);

		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step 03: Verify \"" + editTitle + "\" is displayed.");
		verifyTrue(adminPageAllPage.isPageTitleDisplayed(editTitle));
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step 04: Open 'Ellipsis' menu and click on 'View page' item.");
		adminPageAllPage.clickOnEllipsisMenu(editTitle, "View page");
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step 05: Select 'Tablet' item from 'Web Preview' dropdown.");
		adminPageAllPage.selectPreviewOption("Tablet");
		adminPageAllPage.switchToWebPreviewIframe();
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step 06: Verify page Title, Image, Body are displayed.");
		verifyTrue(adminPageAllPage.isPageTitlePreviewDisplayed(editTitle));
		verifyTrue(adminPageAllPage.isPageImagePreviewDisplayed(editTitle, editImage));
		verifyTrue(adminPageAllPage.isPageBodyPreviewDisplayed(editBody));
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step 07: Verify Comment textarea is undisplayed.");
		verifyTrue(adminPageAllPage.isCommentPreviewUndisplayed());
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step 08: Click on 'Visit site' button.");
		adminPageAllPage.switchToDefaultContent(driver);
		userPageDetailPage = adminPageAllPage.clickOnVisitSiteButton();
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step 09: Switch to User PAGE DETAILS tab.");
		userPageDetailPage.switchToWindowByID(driver, driver.getWindowHandle());
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step 10: Verify page Title, Image, Body are displayed.");
		verifyTrue(userPageDetailPage.isPageTitleDisplayed(editTitle));
		verifyTrue(userPageDetailPage.isPageImageDisplayed(editTitle, editImage));
		verifyTrue(userPageDetailPage.isPageBodyDisplayed(editBody));
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step 11: Verify Comment textarea is undisplayed.");
		verifyTrue(userPageDetailPage.isPageCommentTextareaUndisplayed());
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step 12: Navigate to User HOME page.");
		userHomePage = userPageDetailPage.clickOnHomePageLink();

		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step 13: Click on 'Search' toggle and enter \"" + editTitle + "\" into 'Search' textbox.");
		userHomePage.clickOnSearchToggle();
		userHomePage.inputIntoSearchTextbox(editTitle);
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step 14: Navigate to User SEARCH page.");
		userSearchPage = userHomePage.clickOnSearchButton();

		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step 16: Verify '1 Post' is displayed.");
		verifyTrue(userSearchPage.isOnePostMessageDisplayed("1 Post"));

		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step 17: Verify page Title, Image, Published Date are displayed.");
		verifyTrue(userSearchPage.isPostOrPageTitleDisplayed(editTitle));
		verifyTrue(userSearchPage.isPostOrPageImageDisplayed(editTitle, editImage));
		verifyTrue(userSearchPage.isPostOrPagePublishedDateDisplayed(editTitle, publishedDate));
	}
	
	@Test
	public void Page_05_Delete_Post(Method method) {
		WpExtentTestManagerV5.startTest(method.getName() + " - " + browserName, "Delete post on Admin site");
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step 01: Switch to Admin ALL PAGES tab.");
		userSearchPage.switchToWindowByID(driver, driver.getWindowHandle());
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step 02: Click on 'Close preview' button .");
		adminPageAllPage.clickonClosePreviewButton();

		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step 03: Open 'Search' textbox and enter \"" + editTitle + "\".");
		adminPageAllPage.clickOnOpenSearchIcon();
		adminPageAllPage.inputIntoSearchTextbox(editTitle);		
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step 04: Open 'Ellipsis' menu and click on 'Trash' item.");
		adminPageAllPage.clickOnEllipsisMenu(editTitle, "Trash");
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step 05: Verify 'Page trashed.' message is displayed.");
		verifyTrue(adminPageAllPage.isPageTrashedOrDeletedMessageDisplayed("Page trashed."));
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step 06: Close 'Search' textbox.");
		adminPageAllPage.clickOnCloseSearchIcon();
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step 07: Open 'Search' textbox again and enter \"" + editTitle + "\".");
		adminPageAllPage.clickOnOpenSearchIcon();
		adminPageAllPage.inputIntoSearchTextbox(editTitle);	
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step 08: Open 'Ellipsis' menu and click on 'Trash' item.");
		adminPageAllPage.clickOnDeleteEllipsisMenu(editTitle, "Delete");
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step 09: Click 'OK' button on Delete confirmation alert.");
		adminPageAllPage.acceptAlert(driver);

		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step 10: Verify 'Page deleted.' message is displayed.");
		verifyTrue(adminPageAllPage.isPageTrashedOrDeletedMessageDisplayed("Page deleted."));
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step 11: Verify 'No pages match your search for " + editTitle + ".' message is displayed.");
		verifyEquals(adminPageAllPage.getNoResultsMessage(), "No pages match your search for " + editTitle + ".");
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step 12: Switch to User SEARCH tab.");
		adminPageAllPage.switchToWindowByID(driver, driver.getWindowHandle());
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step 13: Click on 'Search' toggle and enter \"" + editTitle + "\" into 'Search' textbox.");
		userSearchPage.clickOnSearchToggle();
		userSearchPage.inputIntoSearchTextbox(editTitle);
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step 14: Click on 'Search' button.");
		userSearchPage.clickOnSearchButton();
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step 15: Verify 'It seems we can't find what you’re looking for.' message is displayed.");
		verifyTrue(userSearchPage.isSearchResultsMessageDisplayed());
	}

	@AfterClass(alwaysRun = true)
	public void afterClass() {
		closeBrowserAndDriver();
	}
	
	String adminUrl, userUrl, browserName, publishedDate, uploadedImageName;
	int randomNumber4 = Demo_01_Login.randomNumber1;
	String pageTitle = "[Annie]_New-page-title_" + randomNumber4;
	String pageBody = "Coding Demo: page body " + randomNumber4;
	String pageImage = "annie_catdog.jpg";
	String editTitle = "[Annie]_Edit-page-title_" + randomNumber4;
	String editBody = "Edit page body " + randomNumber4;
	String editImage = "annie_fruit";
	
	WebDriver driver;
	WpAdminLoginPO adminLoginPage;
	WpAdminDashboardPO adminDashboardPage;
	WpAdminPageAllPO adminPageAllPage;
	WpAdminPageNewPO adminPageNewPage;
	WpUserHomePO userHomePage;
	WpUserSearchPO userSearchPage;
	WpUserPageDetailPO userPageDetailPage;
}