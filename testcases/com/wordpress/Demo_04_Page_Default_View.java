package com.wordpress;

import java.lang.reflect.Method;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import commons.wordPress.WpBaseTest;
import commons.wordPress.WpGlobalConstants;
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
	
	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		this.browserName = browserName;
		
		driver = getBrowserDriver(browserName, adminUrl);
		
		adminLoginPage = WpPageGeneratorManager.getAdminLoginPage(driver);
		adminDashboardPage = adminLoginPage.loginToSystem(driver, WpGlobalConstants.ADMIN_USERNAME, WpGlobalConstants.ADMIN_PASSWORD);	
	}
	
	@Test
	public void Page_01_Create_New_Page(Method method) {
		WpExtentTestManagerV5.startTest(method.getName() + " - " + browserName, "Create a new page on Admin site");
		int s = 0;

		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step " + String.format("%02d", s=s+1) + ": Click on 'Pages' menu and 'Add new page' button to go to Admin ADD NEW PAGE page.");
		adminPageAllPage = adminDashboardPage.clickOnPageMenuLink();
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
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step " + String.format("%02d", s=s+1) + ": Expand 'Discussion' panel.");
		adminPageNewPage.clickOnPanelByText("Discussion");
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step " + String.format("%02d", s=s+1) + ": Select 'Allow comments' checkbox.");
		adminPageNewPage.checkAllowCommentsCheckbox();
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step " + String.format("%02d", s=s+1) + ": Click on 'Publish' and 'Pre-publish' buttons.");
		adminPageNewPage.clickOnPublishOrUpdateButton();
		adminPageNewPage.clickOnPrePublishButton();
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step " + String.format("%02d", s=s+1) + ": Verify 'Page published.' and '" + pageTitle + " is now live.' messages are displayed.");
		verifyTrue(adminPageNewPage.isPagePublishedOrUpdatedMessageDisplayed("Page published."));
		verifyEquals(adminPageNewPage.getPageNowLiveMessage(), pageTitle + " is now live.");
		
		adminPagePublishedDate = adminPageNewPage.pagePublishedDateOnAdmin();
		userPagePublishedDate = adminPageNewPage.pagePublishedDateOnUser();
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

		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step " + String.format("%02d", s=s+1) + ": Verify page Title and Published Date displayed are correct.");
		verifyTrue(adminPageAllPage.isPageTitleDisplayed(pageTitle));
		verifyTrue(adminPageAllPage.isPagePublishedDateDisplayed(pageTitle, adminPagePublishedDate));
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step " + String.format("%02d", s=s+1) + ": Open Ellipsis menu and click on 'View page' item.");
		adminPageAllPage.clickOnEllipsisMenu(pageTitle, "View page");
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step " + String.format("%02d", s=s+1) + ": Select 'Tablet' item from Web Preview dropdown.");
		adminPageAllPage.selectPreviewOption("Tablet");
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step " + String.format("%02d", s=s+1) + ": Verify page Title, Image, and Body displayed are correct.");
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

		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step " + String.format("%02d", s=s+1) + ": Verify page Title, Image, and Published Date displayed are correct.");
		verifyTrue(userSearchPage.isPostOrPageTitleDisplayed(pageTitle));
		verifyTrue(userSearchPage.isPostOrPageImageDisplayed(pageTitle, uploadedImageName));
		verifyTrue(userSearchPage.isPostOrPageMetaDisplayed(pageTitle, userPagePublishedDate));

		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step " + String.format("%02d", s=s+1) + ": Click on \"" + pageTitle + "\" link to go to User PAGE DETAILS page.");
		userPageDetailPage = userSearchPage.clickOnPageTitleLink(pageTitle);
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step " + String.format("%02d", s=s+1) + ": Verify page Title, Image, and Body displayed are correct.");
		verifyTrue(userPageDetailPage.isPageTitleDisplayed(pageTitle));
		verifyTrue(userPageDetailPage.isPageImageDisplayed(uploadedImageName));
		verifyTrue(userPageDetailPage.isPageBodyDisplayed(pageBody));
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step " + String.format("%02d", s=s+1) + ": Click on 'Like' button.");
		userPageDetailPage.clickOnLikeButton();
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step " + String.format("%02d", s=s+1) + ": Enter \"" + pageComment + "\" into Comment box and click on 'Post Comment' button.");
		userPageDetailPage.inputIntoCommentTextarea(pageComment);
		userPageDetailPage.clickOnPostCommentButton();
	}
	
	@Test
	public void Page_03_Edit_Page(Method method) {
		WpExtentTestManagerV5.startTest(method.getName() + " - " + browserName, "Edit page on Admin site");
		int s = 0;

		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step " + String.format("%02d", s=s+1) + ": Open Admin DASHBOARD page and click on 'Pages' menu to go to Admin ALL PAGES page.");
		adminDashboardPage = userPageDetailPage.openAdminSite(driver, adminUrl);	
		adminPageAllPage = adminDashboardPage.clickOnPageMenuLink();

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

		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step " + String.format("%02d", s=s+1) + ": Deselect 'Allow comments' checkbox.");
		adminPageNewPage.uncheckAllowCommentsCheckbox();
		
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

		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step " + String.format("%02d", s=s+1) + ": Verify \"" + editTitle + "\" Title and Published Date displayed are correct.");
		adminPageAllPage.switchToDefaultContent(driver);
		verifyTrue(adminPageAllPage.isPageTitleDisplayed(editTitle));
		verifyTrue(adminPageAllPage.isPagePublishedDateDisplayed(editTitle, adminPagePublishedDate));
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step " + String.format("%02d", s=s+1) + ": Open Ellipsis menu and click on 'View page' item.");
		adminPageAllPage.clickOnEllipsisMenu(editTitle, "View page");
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step " + String.format("%02d", s=s+1) + ": Select 'Phone' item from Web Preview dropdown.");
		adminPageAllPage.selectPreviewOption("Phone");
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step " + String.format("%02d", s=s+1) + ": Verify page Title, Image, Body, and Comment content displayed are correct.");
		adminPageAllPage.switchToWebPreviewIframe();
		verifyTrue(adminPageAllPage.isPageTitlePreviewDisplayed(editTitle));
		verifyTrue(adminPageAllPage.isPageImagePreviewDisplayed(editImage));
		verifyTrue(adminPageAllPage.isPageBodyPreviewDisplayed(editBody));
		verifyTrue(adminPageAllPage.getPageCommentContentPreview().contains(pageComment));
		
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

		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step " + String.format("%02d", s=s+1) + ": Verify page Title, Image, and Published Date displayed are correct.");
		verifyTrue(userSearchPage.isPostOrPageTitleDisplayed(editTitle));
		verifyTrue(userSearchPage.isPostOrPageImageDisplayed(editTitle, editImage));
		verifyTrue(userSearchPage.isPostOrPageMetaDisplayed(editTitle, userPagePublishedDate));

		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step " + String.format("%02d", s=s+1) + ": Click on \"" + editTitle + "\" link to go to User PAGE DETAILS page.");
		userPageDetailPage = userSearchPage.clickOnPageTitleLink(editTitle);
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step " + String.format("%02d", s=s+1) + ": Verify page Title, Image, Body, and Comment content displayed are correct.");
		verifyTrue(userPageDetailPage.isPageTitleDisplayed(editTitle));
		verifyTrue(userPageDetailPage.isPageImageDisplayed(editImage));
		verifyTrue(userPageDetailPage.isPageBodyDisplayed(editBody));
		verifyTrue(userPageDetailPage.getPageCommentContent().contains(pageComment));
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step " + String.format("%02d", s=s+1) + ": Verify 'You like this.', 'One thought', and 'Comments are closed.' messages are displayed.");
		verifyTrue(userPageDetailPage.isYouLikeThisMessageDisplayed());
		userPageDetailPage.switchToDefaultContent(driver);
		verifyTrue(userPageDetailPage.getOneThoughtMessage().contains("One thought"));
		verifyEquals(userPageDetailPage.getCommentsClosedMessage(), "Comments are closed.");
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
	
	private String browserName, adminPagePublishedDate, userPagePublishedDate, uploadedImageName;
	private String adminUrl = WpGlobalConstants.ADMIN_PAGE_URL;
	private String userUrl = WpGlobalConstants.USER_PAGE_URL;
	private int randomNumber4 = Demo_01_Login.randomNumber1;
	private String pageTitle = "[Annie]_NewPageTitle_" + randomNumber4;
	private String pageBody = "Coding Demo: page body " + randomNumber4;
	private String pageImage = "annie_catdog.jpg";
	private String pageComment = "Automation Testing \n*** Ngoc Quach ***";
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