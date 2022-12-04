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

public class Demo_04_Page_Default_View extends WpBaseTest {
	
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
		int s = 0;

		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step " + String.format("%02d", s=s+1) + ": Click on 'Pages' menu and 'Add new page' button to go to Admin ADD NEW PAGE page.");
		adminPageNewPage = adminPageAllPage.clickOnAddNewPageButton();
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step " + String.format("%02d", s=s+1) + ": Click on 'Blank page' button.");
		adminPageNewPage.switchToPagePatternIframe();
		adminPageNewPage.clickOnBlankPageButton();
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step " + String.format("%02d", s=s+1) + ": Enter \"" + pageTitle + "\" into page Title.");
		adminPageNewPage.inputIntoPageTitle(pageTitle);
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step " + String.format("%02d", s=s+1) + ": Enter \"" + pageBody + "\" into page Body.");
		adminPageNewPage.inputIntoPageBody(pageBody);
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step " + String.format("%02d", s=s+1) + ": Open 'Page' settings sidebar.");
		adminPageNewPage.clickOnPageSidebar();
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step " + String.format("%02d", s=s+1) + ": Expand 'Featured image' panel.");
		adminPageNewPage.clickOnPanelByText("Featured image");
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step " + String.format("%02d", s=s+1) + ": Open 'Set featured image' menu and click on 'Media Library' item.");
		adminPageNewPage.clickOnImageMenu("Set featured image");
		adminPageNewPage.clickOnMediaLibraryItem();
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step " + String.format("%02d", s=s+1) + ": Upload \"" + pageImage + "\" and get image name after uploading.");
		adminPageNewPage.switchToDefaultContent(driver);
		adminPageNewPage.uploadPageImage(pageImage);
		uploadedImageName = adminPageNewPage.getUploadedImageName("title");
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step " + String.format("%02d", s=s+1) + ": Click on 'Insert' button.");
		adminPageNewPage.clickOnInsertButton();

		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step " + String.format("%02d", s=s+1) + ": Verify \"" + uploadedImageName + "\" page image is updated.");
		adminPageNewPage.switchToPagePatternIframe();
		verifyTrue(adminPageNewPage.isImageUploaded(uploadedImageName));
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step " + String.format("%02d", s=s+1) + ": Click on 'Publish' and 'Pre-publish' buttons.");
		adminPageNewPage.clickOnPublishOrUpdateButton();
		adminPageNewPage.clickOnPrePublishButton();
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step " + String.format("%02d", s=s+1) + ": Verify 'Page published.' and '" + pageTitle + " is now live.' messages are displayed.");
		verifyTrue(adminPageNewPage.isPagePublishedOrUpdatedMessageDisplayed("Page published."));
		verifyEquals(adminPageNewPage.getPageNowLiveMessage(), pageTitle + " is now live.");
		publishedDate = adminPageNewPage.pagePublishedDate();
	}
	
	@Test
	public void Page_02_Search_And_View_Page(Method method) {
		WpExtentTestManagerV5.startTest(method.getName() + " - " + browserName, "Search and view newly created page on Admin and User sites");
		int s = 0;

		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step " + String.format("%02d", s=s+1) + ": Click on WordPress logo and 'View Pages' link to go to Admin ALL PAGES page.");
		adminPageNewPage.clickOnWordpressLogo();
		adminPageAllPage = adminPageNewPage.clickOnViewPagesLink();
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step " + String.format("%02d", s=s+1) + ": Click to expand 'Search pages...' field and enter \"" + pageTitle + "\".");
		adminPageAllPage.switchToDefaultContent(driver);
		adminPageAllPage.clickOnOpenSearchIcon();
		adminPageAllPage.inputIntoSearchTextbox(pageTitle);

		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step " + String.format("%02d", s=s+1) + ": Verify \"" + pageTitle + "\" is displayed.");
		verifyTrue(adminPageAllPage.isPageTitleDisplayed(pageTitle));
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step " + String.format("%02d", s=s+1) + ": Open Ellipsis menu and click on 'View page' item.");
		adminPageAllPage.clickOnEllipsisMenu(pageTitle, "View page");
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step " + String.format("%02d", s=s+1) + ": Select 'Tablet' item from Web Preview dropdown.");
		adminPageAllPage.selectPreviewOption("Tablet");
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step " + String.format("%02d", s=s+1) + ": Verify page Title, Image, and Body are displayed.");
		adminPageAllPage.switchToWebPreviewIframe();
		verifyTrue(adminPageAllPage.isPageTitlePreviewDisplayed(pageTitle));
		verifyTrue(adminPageAllPage.isPageImagePreviewDisplayed(uploadedImageName));
		verifyTrue(adminPageAllPage.isPageBodyPreviewDisplayed(pageBody));
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step " + String.format("%02d", s=s+1) + ": Click on 'Close' preview button.");
		adminPageAllPage.switchToDefaultContent(driver);
		adminPageAllPage.clickonClosePreviewButton();
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step " + String.format("%02d", s=s+1) + ": Open User HOME page.");
		userHomePage = adminPageAllPage.openUserSite(driver, userUrl);
		userHomePage.clickOnAcceptCookiesButton();
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step " + String.format("%02d", s=s+1) + ": Click to expand 'Search' field and enter \"" + pageTitle + "\".");
		userHomePage.clickOnSearchToggle();
		userHomePage.inputIntoSearchTextbox(pageTitle);
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step " + String.format("%02d", s=s+1) + ": Click on Search button to go to User SEARCH page.");
		userSearchPage = userHomePage.clickOnSearchButton();

		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step " + String.format("%02d", s=s+1) + ": Verify '1 Post' result is displayed.");
		verifyTrue(userSearchPage.isOnePostMessageDisplayed("1 Post"));

		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step " + String.format("%02d", s=s+1) + ": Verify page Title, Image, and Published Date are displayed.");
		verifyTrue(userSearchPage.isPostOrPageTitleDisplayed(pageTitle));
		verifyTrue(userSearchPage.isPostOrPageImageDisplayed(pageTitle, uploadedImageName));
		verifyTrue(userSearchPage.isPostOrPagePublishedDateDisplayed(pageTitle, publishedDate));

		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step " + String.format("%02d", s=s+1) + ": Click on \"" + pageTitle + "\" link to go to User PAGE DETAILS page.");
		userPageDetailPage = userSearchPage.clickOnPageTitleLink(pageTitle);
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step " + String.format("%02d", s=s+1) + ": Verify page Title, Image, and Body are displayed.");
		verifyTrue(userPageDetailPage.isPageTitleDisplayed(pageTitle));
		verifyTrue(userPageDetailPage.isPageImageDisplayed(pageTitle, uploadedImageName));
		verifyTrue(userPageDetailPage.isPageBodyDisplayed(pageBody));
	}
	
	@Test
	public void Page_03_Edit_Page(Method method) {
		WpExtentTestManagerV5.startTest(method.getName() + " - " + browserName, "Edit page on Admin site");
		int s = 0;

		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step " + String.format("%02d", s=s+1) + ": Open Admin DASHBOARD page and click on 'Pages' menu to go to Admin ALL PAGES page.");
		adminDashboardPage = userPageDetailPage.openAdminSite(driver, adminUrl);	
		adminPageAllPage = adminDashboardPage.clickOnPageMenuLink();

		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step " + String.format("%02d", s=s+1) + ": Click to expand 'Search pages...' field and enter \"" + pageTitle + "\".");
		adminPageAllPage.clickOnOpenSearchIcon();
		adminPageAllPage.inputIntoSearchTextbox(pageTitle);

		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step " + String.format("%02d", s=s+1) + ": Click on \"" + pageTitle + "\" link to go to Admin EDIT PAGE page.");
		adminPageNewPage = adminPageAllPage.clickOnPageTitleLink(pageTitle);
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step " + String.format("%02d", s=s+1) + ": Enter \"" + editTitle + "\" into page Title.");
		adminPageNewPage.switchToPagePatternIframe();
		adminPageNewPage.inputIntoPageTitle(editTitle);
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step " + String.format("%02d", s=s+1) + ": Enter \"" + editBody + "\" into page Body.");
		adminPageNewPage.inputIntoEditPageBody(editBody);
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step " + String.format("%02d", s=s+1) + ": Open 'Page' settings sidebar.");
		adminPageNewPage.clickOnPageSidebar();
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step " + String.format("%02d", s=s+1) + ": Expand 'Featured image' panel.");
		adminPageNewPage.clickOnPanelByText("Featured image");
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step " + String.format("%02d", s=s+1) + ": Open 'Replace Image' menu and click on 'Media Library' item.");
		adminPageNewPage.clickOnImageMenu("Replace Image");
		adminPageNewPage.clickOnMediaLibraryItem();
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step " + String.format("%02d", s=s+1) + ": Click on Trash icon and 'Delete' button to delete \"" + uploadedImageName + "\" image.");
		adminPageNewPage.switchToDefaultContent(driver);
		adminPageNewPage.selectPageImage(uploadedImageName);
		adminPageNewPage.clickOnDeleteImageIcon();
		adminPageNewPage.clickOnConfirmDeleteImageButton();
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step " + String.format("%02d", s=s+1) + ": Click to expand 'Search images...' field.");
		adminPageNewPage.clickOnSearchImageButton();
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step " + String.format("%02d", s=s+1) + ": Enter \"" + editImage + "\" into Search field and select it.");		
		adminPageNewPage.inputIntoSearchImageTextbox(editImage);
		adminPageNewPage.selectPageImage(editImage);

		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step " + String.format("%02d", s=s+1) + ": Click on 'Insert' button.");
		adminPageNewPage.clickOnInsertButton();
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step " + String.format("%02d", s=s+1) + ": Verify \"" + editImage + ".jpg\" page image is updated.");
		adminPageNewPage.switchToPagePatternIframe();
		verifyTrue(adminPageNewPage.isImageUploaded(editImage));

		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step " + String.format("%02d", s=s+1) + ": Expand 'Discussion' panel.");
		adminPageNewPage.clickOnPanelByText("Discussion");

		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step " + String.format("%02d", s=s+1) + ": Select 'Allow comments' checkbox.");
		adminPageNewPage.checkAllowCommentsCheckbox();
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step " + String.format("%02d", s=s+1) + ": Click on 'Update' button.");
		adminPageNewPage.clickOnPublishOrUpdateButton();
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step " + String.format("%02d", s=s+1) + ": Verify 'Page updated.' message is displayed.");
		verifyTrue(adminPageNewPage.isPagePublishedOrUpdatedMessageDisplayed("Page updated."));
	}
	
	@Test
	public void Page_04_Search_And_View_Page_After_Editing(Method method) {
		WpExtentTestManagerV5.startTest(method.getName() + " - " + browserName, "Search and view after editing page on Admin and User sites");
		int s = 0;

		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step " + String.format("%02d", s=s+1) + ": Click on WordPress logo and 'View Pages' link to go to Admin ALL PAGES page.");
		adminPageNewPage.clickOnWordpressLogo();
		adminPageAllPage = adminPageNewPage.clickOnViewPagesLink();
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step " + String.format("%02d", s=s+1) + ": Click to expand 'Search pages...' field and enter \"" + editTitle + "\".");
		adminPageAllPage.switchToDefaultContent(driver);
		adminPageAllPage.clickOnOpenSearchIcon();
		adminPageAllPage.inputIntoSearchTextbox(editTitle);

		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step " + String.format("%02d", s=s+1) + ": Verify \"" + editTitle + "\" is displayed.");
		verifyTrue(adminPageAllPage.isPageTitleDisplayed(editTitle));
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step " + String.format("%02d", s=s+1) + ": Open Ellipsis menu and click on 'View page' item.");
		adminPageAllPage.clickOnEllipsisMenu(editTitle, "View page");
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step " + String.format("%02d", s=s+1) + ": Select 'Phone' item from Web Preview dropdown.");
		adminPageAllPage.selectPreviewOption("Phone");
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step " + String.format("%02d", s=s+1) + ": Verify page Title, Image, Body, and Comment box are displayed.");
		adminPageAllPage.switchToWebPreviewIframe();
		verifyTrue(adminPageAllPage.isPageTitlePreviewDisplayed(editTitle));
		verifyTrue(adminPageAllPage.isPageImagePreviewDisplayed(editImage));
		verifyTrue(adminPageAllPage.isPageBodyPreviewDisplayed(editBody));
		verifyTrue(adminPageAllPage.isPageCommentPreviewDisplayed());
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step " + String.format("%02d", s=s+1) + ": Click on 'Close' preview button.");
		adminPageAllPage.switchToDefaultContent(driver);
		adminPageAllPage.clickonClosePreviewButton();
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step " + String.format("%02d", s=s+1) + ": Open User HOME page.");
		userHomePage = adminPageAllPage.openUserSite(driver, userUrl);
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step " + String.format("%02d", s=s+1) + ": Click to expand 'Search' field and enter \"" + editTitle + "\".");
		userHomePage.clickOnSearchToggle();
		userHomePage.inputIntoSearchTextbox(editTitle);
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step " + String.format("%02d", s=s+1) + ": Click on Search button to go to User SEARCH page.");
		userSearchPage = userHomePage.clickOnSearchButton();

		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step " + String.format("%02d", s=s+1) + ": Verify '1 Post' result is displayed.");
		verifyTrue(userSearchPage.isOnePostMessageDisplayed("1 Post"));

		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step " + String.format("%02d", s=s+1) + ": Verify page Title, Image, and Published Date are displayed.");
		verifyTrue(userSearchPage.isPostOrPageTitleDisplayed(editTitle));
		verifyTrue(userSearchPage.isPostOrPageImageDisplayed(editTitle, editImage));
		verifyTrue(userSearchPage.isPostOrPagePublishedDateDisplayed(editTitle, publishedDate));

		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step " + String.format("%02d", s=s+1) + ": Click on \"" + editTitle + "\" link to go to User PAGE DETAILS page.");
		userPageDetailPage = userSearchPage.clickOnPageTitleLink(editTitle);
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step " + String.format("%02d", s=s+1) + ": Verify page Title, Image, Body, and Comment box are displayed.");
		verifyTrue(userPageDetailPage.isPageTitleDisplayed(editTitle));
		verifyTrue(userPageDetailPage.isPageImageDisplayed(editTitle, editImage));
		verifyTrue(userPageDetailPage.isPageBodyDisplayed(editBody));
		verifyTrue(userPageDetailPage.isPageCommentTextareaDisplayed());
	}
	
	@Test
	public void Page_05_Delete_Page(Method method) {
		WpExtentTestManagerV5.startTest(method.getName() + " - " + browserName, "Delete page on Admin site");
		int s = 0;

		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step " + String.format("%02d", s=s+1) + ": Open Admin DASHBOARD page and click on 'Pages' menu to go to Admin ALL PAGES page.");
		adminDashboardPage = userPageDetailPage.openAdminSite(driver, adminUrl);	
		adminPageAllPage = adminDashboardPage.clickOnPageMenuLink();

		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step " + String.format("%02d", s=s+1) + ": Click to expand 'Search pages...' field and enter \"" + editTitle + "\".");
		adminPageAllPage.clickOnOpenSearchIcon();
		adminPageAllPage.inputIntoSearchTextbox(editTitle);		
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step " + String.format("%02d", s=s+1) + ": Open Ellipsis menu and click on 'Trash' item.");
		adminPageAllPage.clickOnEllipsisMenu(editTitle, "Trash");
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step " + String.format("%02d", s=s+1) + ": Verify 'Page trashed.' message is displayed.");
		verifyTrue(adminPageAllPage.isPageTrashedOrDeletedMessageDisplayed("Page trashed."));
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step " + String.format("%02d", s=s+1) + ": Click on Close Search button.");
		adminPageAllPage.clickOnCloseSearchIcon();
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step " + String.format("%02d", s=s+1) + ": Click to expand 'Search pages...' field and enter \"" + editTitle + "\".");
		adminPageAllPage.clickOnOpenSearchIcon();
		adminPageAllPage.inputIntoSearchTextbox(editTitle);	
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step " + String.format("%02d", s=s+1) + ": Open Ellipsis menu and click on 'Delete' item.");
		adminPageAllPage.clickOnDeleteEllipsisMenu(editTitle, "Delete");
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step " + String.format("%02d", s=s+1) + ": Click 'OK' button on Delete alert.");
		adminPageAllPage.acceptAlert(driver);

		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step " + String.format("%02d", s=s+1) + ": Verify 'Page deleted.' message is displayed.");
		verifyTrue(adminPageAllPage.isPageTrashedOrDeletedMessageDisplayed("Page deleted."));
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step " + String.format("%02d", s=s+1) + ": Verify 'No pages match your search for " + editTitle + ".' message is displayed.");
		verifyEquals(adminPageAllPage.getNoResultsMessage(), "No pages match your search for " + editTitle + ".");
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step " + String.format("%02d", s=s+1) + ": Open User HOME page.");
		userHomePage = adminPageAllPage.openUserSite(driver, userUrl);
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step " + String.format("%02d", s=s+1) + ": Click to expand 'Search' field and enter \"" + editTitle + "\".");
		userHomePage.clickOnSearchToggle();
		userHomePage.inputIntoSearchTextbox(editTitle);
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step " + String.format("%02d", s=s+1) + ": Click on Search button to go to User SEARCH page.");
		userSearchPage = userHomePage.clickOnSearchButton();
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step " + String.format("%02d", s=s+1) + ": Verify 'It seems we can’t find what you’re looking for.' message is displayed.");
		verifyTrue(userSearchPage.isSearchResultsMessageDisplayed());
	}

	@AfterClass(alwaysRun = true)
	public void afterClass() {
		closeBrowserAndDriver();
	}
	
	private String adminUrl, userUrl, browserName, publishedDate, uploadedImageName;
	private int randomNumber4 = Demo_01_Login.randomNumber1;
	private String pageTitle = "[Annie]_NewPageTitle_" + randomNumber4;
	private String pageBody = "Coding Demo: page body " + randomNumber4;
	private String pageImage = "annie_catdog.jpg";
	private String editTitle = "[Annie]_EditPageTitle_" + randomNumber4;
	private String editBody = "Edit page body " + randomNumber4;
	private String editImage = "annie_fruit";
	
	private WebDriver driver;
	private WpAdminLoginPO adminLoginPage;
	private WpAdminDashboardPO adminDashboardPage;
	private WpAdminPageAllPO adminPageAllPage;
	private WpAdminPageNewPO adminPageNewPage;
	private WpUserHomePO userHomePage;
	private WpUserSearchPO userSearchPage;
	private WpUserPageDetailPO userPageDetailPage;
}