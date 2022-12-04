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
import pageObjects.wordPress.WpAdminPostAllPO;
import pageObjects.wordPress.WpAdminPostNewPO;
import pageObjects.wordPress.WpUserHomePO;
import pageObjects.wordPress.WpUserPostDetailPO;
import pageObjects.wordPress.WpUserSearchPO;
import reportConfig.wordPress.WpExtentTestManagerV5;

public class Demo_02_Post_Classic_View extends WpBaseTest {
	
	@Parameters({ "browser", "adminUrl", "userUrl" })
	@BeforeClass
	public void beforeClass(String browserName, String adminUrl, String userUrl) {
		this.adminUrl = adminUrl;	
		this.userUrl = userUrl;
		this.browserName = browserName;
		
		driver = getBrowserDriver(browserName, adminUrl);
		adminLoginPage = WpPageGeneratorManager.getAdminLoginPage(driver);
		
		adminDashboardPage = adminLoginPage.loginToSystem(driver, Demo_01_Login.username, Demo_01_Login.password);
		
		adminPostAllPage = adminDashboardPage.clickOnPostMenu();
	}

	@Test
	public void Post_01_Create_New_Post(Method method) {
		WpExtentTestManagerV5.startTest(method.getName() + " - " + browserName, "Create a new post on Admin site");
		int s = 0;

		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step " + String.format("%02d", s=s+1) + ": Click on 'Posts' menu and 'Add new' button to go to Admin ADD NEW POST page.");
		adminPostNewPage = adminPostAllPage.clickOnAddNewButton();
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step " + String.format("%02d", s=s+1) + ": Enter \"" + postTitle + "\" into post Title.");
		adminPostNewPage.inputIntoPostTitle(postTitle);
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step " + String.format("%02d", s=s+1) + ": Enter \"" + postBody + "\" into post Body.");
		adminPostNewPage.inputIntoPostBody(postBody);
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step " + String.format("%02d", s=s+1) + ": Open 'Post' settings sidebar.");
		adminPostNewPage.clickOnPostSidebar();
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step " + String.format("%02d", s=s+1) + ": Expand 'Categories' panel.");
		adminPostNewPage.clickOnPanelByText("Categories");
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step " + String.format("%02d", s=s+1) + ": Enter \"" + postCategory + "\" into 'Search Categories' field and select it.");
		adminPostNewPage.inputIntoSearchCategoryTextbox(postCategory);
		adminPostNewPage.checkCheckboxByLabel(postCategory);
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step " + String.format("%02d", s=s+1) + ": Expand 'Tags' panel.");
		adminPostNewPage.clickOnPanelByText("Tags");
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step " + String.format("%02d", s=s+1) + ": Enter \"" + postTag + "\" into 'Add New Tag' field.");
		adminPostNewPage.inputIntoAddNewTagTextbox(postTag);
		verifyTrue(adminPostNewPage.isRemoveTagButtonDisplayed(postTag));

		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step " + String.format("%02d", s=s+1) + ": Expand 'Featured image' panel.");
		adminPostNewPage.clickOnPanelByText("Featured image");
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step " + String.format("%02d", s=s+1) + ": Open 'Set featured image' menu and click on 'Media Library' item.");
		adminPostNewPage.clickOnImageMenu("Set featured image");
		adminPostNewPage.clickOnMediaLibraryItem();
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step " + String.format("%02d", s=s+1) + ": Switch to 'Upload files' tab.");
		adminPostNewPage.clickOnUploadFilesTab();
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step " + String.format("%02d", s=s+1) + ": Upload \"" + postImage + "\" and get image name after uploading.");
		adminPostNewPage.uploadMultipleFiles(driver, postImage);
		uploadedImageName = adminPostNewPage.getUploadedImageName();
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step " + String.format("%02d", s=s+1) + ": Click on 'Set featured image' button.");
		adminPostNewPage.clickOnSetImageButton();

		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step " + String.format("%02d", s=s+1) + ": Verify \"" + uploadedImageName + "\" post image is updated.");
		verifyTrue(adminPostNewPage.isImageUploaded(uploadedImageName));
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step " + String.format("%02d", s=s+1) + ": Click on 'Publish' and 'Pre-publish' buttons.");
		adminPostNewPage.clickOnPublishOrUpdateButton();
		adminPostNewPage.clickOnPrePublishButton();
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step " + String.format("%02d", s=s+1) + ": Verify 'Post published.' and '" + postTitle + " is now live.' messages are displayed.");
		verifyTrue(adminPostNewPage.isPostPublishedOrUpdatedMessageDisplayed("Post published."));
		verifyEquals(adminPostNewPage.getPostNowLiveMessage(), postTitle + " is now live.");
		
		adminPostPublishedDate = adminPostNewPage.postPublishedDateOnAdmin();
		userPostPublishedDate = adminPostNewPage.postPublishedDateOnUser();
	}
	
	@Test
	public void Post_02_Search_And_View_Post(Method method) {
		WpExtentTestManagerV5.startTest(method.getName() + " - " + browserName, "Search and view newly created post on Admin and User sites");
		int s = 0;

		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step " + String.format("%02d", s=s+1) + ": Click on WordPress logo and 'All Posts' link to go to Admin ALL POSTS page.");
		adminPostNewPage.clickOnWordpressLogo();
		adminPostAllPage = adminPostNewPage.clickOnAllPostsLink();
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step " + String.format("%02d", s=s+1) + ": Enter \"" + postTitle + "\" into Search field and click on 'Search Posts' button.");
		adminPostAllPage.inputIntoSearchTextbox1(postTitle);		
		adminPostAllPage.clickOnSearchPostsButton();

		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step " + String.format("%02d", s=s+1) + ": Open User HOME page.");
		userHomePage = adminPostAllPage.openUserSite(driver, userUrl);
		userHomePage.clickOnAcceptCookiesButton();
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step " + String.format("%02d", s=s+1) + ": Verify post Title, Image, Category, and Published Date are displayed.");
		verifyTrue(userHomePage.isPostTitleDisplayed(postTitle));
		verifyTrue(userHomePage.isPostImageDisplayed(postTitle, uploadedImageName));
		verifyTrue(userHomePage.isPostCategoryDisplayed(postTitle, postCategory));
		verifyTrue(userHomePage.isPostMetaDisplayed(postTitle, userPostPublishedDate));
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step " + String.format("%02d", s=s+1) + ": Click to expand 'Search' field and enter \"" + postTitle + "\".");
		userHomePage.clickOnSearchToggle();
		userHomePage.inputIntoSearchTextbox(postTitle);
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step " + String.format("%02d", s=s+1) + ": Click on Search button to go to User SEARCH page.");
		userSearchPage = userHomePage.clickOnSearchButton();

		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step " + String.format("%02d", s=s+1) + ": Verify '1 Post' result is displayed.");
		verifyTrue(userSearchPage.isOnePostMessageDisplayed("1 Post"));

		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step " + String.format("%02d", s=s+1) + ": Verify post Title, Image, Category, and Published Date are displayed.");
		verifyTrue(userSearchPage.isPostOrPageTitleDisplayed(postTitle));
		verifyTrue(userSearchPage.isPostOrPageImageDisplayed(postTitle, uploadedImageName));
		verifyTrue(userSearchPage.isPostCategoryDisplayed(postTitle, postCategory));
		verifyTrue(userSearchPage.isPostOrPagePublishedDateDisplayed(postTitle, userPostPublishedDate));
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step " + String.format("%02d", s=s+1) + ": Click on \"" + postTitle + "\" link to go to User POST DETAILS page.");
		userPostDetailsPage = userSearchPage.clickOnPostTitleLink(postTitle);
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step " + String.format("%02d", s=s+1) + ": Verify post Title, Image, Category, and Published Date are displayed.");
		verifyTrue(userPostDetailsPage.isPostTitleDisplayed(postTitle));
		verifyTrue(userPostDetailsPage.isPostImageDisplayed(uploadedImageName));
		verifyTrue(userPostDetailsPage.isPostCategoryDisplayed(postCategory));
		verifyTrue(userPostDetailsPage.isPostPublishedDateDisplayed(userPostPublishedDate));
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step " + String.format("%02d", s=s+1) + ": Verify post Body, Author, Tag, and Comment box are displayed.");
		verifyTrue(userPostDetailsPage.isPostBodyDisplayed(postBody));
		verifyTrue(userPostDetailsPage.isPostAuthorDisplayed(authorName));
		verifyTrue(userPostDetailsPage.isPostTagDisplayed(postTag));
		verifyTrue(userPostDetailsPage.isPostCommentTextareaDisplayed());
	}
	
	@Test
	public void Post_03_Edit_Post(Method method) {
		WpExtentTestManagerV5.startTest(method.getName() + " - " + browserName, "Edit post on Admin site");
		int s = 0;

		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step " + String.format("%02d", s=s+1) + ": Open Admin DASHBOARD page and click on 'Posts' menu to go to Admin ALL POSTS page.");
		adminDashboardPage = userPostDetailsPage.openAdminSite(driver, adminUrl);		
		adminPostAllPage = adminDashboardPage.clickOnPostMenu();
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step " + String.format("%02d", s=s+1) + ": Click on \"" + postTitle + "\" link to go to Admin EDIT POST page.");
		adminPostNewPage = adminPostAllPage.clickOnPostTitleLink(postTitle, "Title");

		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step " + String.format("%02d", s=s+1) + ": Enter \"" + editTitle + "\" into post Title.");
		adminPostNewPage.inputIntoPostTitle(editTitle);
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step " + String.format("%02d", s=s+1) + ": Enter \"" + editBody + "\" into post Body.");
		adminPostNewPage.inputIntoEditPostBody(editBody);
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step " + String.format("%02d", s=s+1) + ": Open 'Post' settings sidebar.");
		adminPostNewPage.clickOnPostSidebar();
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step " + String.format("%02d", s=s+1) + ": Select 'Stick to the top of the blog' checkbox.");
		adminPostNewPage.checkCheckboxByLabel("Stick to the top of the blog");
				
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step " + String.format("%02d", s=s+1) + ": Expand 'Categories' panel and deselect \"" + postCategory + "\".");
		adminPostNewPage.clickOnPanelByText("Categories");
		adminPostNewPage.uncheckCheckboxByLabel(postCategory);
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step " + String.format("%02d", s=s+1) + ": Enter \"" + editCategory + "\" into 'Search Categories' field and select it.");
		adminPostNewPage.inputIntoSearchCategoryTextbox(editCategory);
		adminPostNewPage.checkCheckboxByLabel(editCategory);
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step " + String.format("%02d", s=s+1) + ": Expand 'Tags' panel.");
		adminPostNewPage.clickOnPanelByText("Tags");
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step " + String.format("%02d", s=s+1) + ": Clear \"" + postTag + "\" and enter \"" + editTag + "\" into 'Add New Tag' field.");
		adminPostNewPage.clickOnRemoveTagButton(postTag);
		adminPostNewPage.inputIntoAddNewTagTextbox(editTag);
		verifyTrue(adminPostNewPage.isRemoveTagButtonDisplayed(editTag));

		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step " + String.format("%02d", s=s+1) + ": Expand 'Featured image' panel.");
		adminPostNewPage.clickOnPanelByText("Featured image");
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step " + String.format("%02d", s=s+1) + ": Open 'Replace Image' menu and click on 'Media Library' item.");
		adminPostNewPage.clickOnImageMenu("Replace Image");
		adminPostNewPage.clickOnMediaLibraryItem();
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step " + String.format("%02d", s=s+1) + ": Select \"" + uploadedImageName + "\" and click on 'Delete permanently' button.");
		adminPostNewPage.clickOnDeleteImageButton();
		adminPostNewPage.acceptAlert(driver);
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step " + String.format("%02d", s=s+1) + ": Enter \"" + editImage + "\" into 'Search' field and select it.");
		adminPostNewPage.inputIntoSearchImageTextbox(editImage);
		adminPostNewPage.selectPostImage(editImage);
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step " + String.format("%02d", s=s+1) + ": Click on 'Set featured image' button.");
		adminPostNewPage.clickOnSetImageButton();
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step " + String.format("%02d", s=s+1) + ": Verify \"" + editImage + ".jpg\" post image is updated.");
		verifyTrue(adminPostNewPage.isImageUploaded(editImage));
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step " + String.format("%02d", s=s+1) + ": Expand 'Discussion' panel.");
		adminPostNewPage.clickOnPanelByText("Discussion");

		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step " + String.format("%02d", s=s+1) + ": Deselect 'Allow comments' checkbox.");
		adminPostNewPage.uncheckCheckboxByLabel("Allow comments");
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step " + String.format("%02d", s=s+1) + ": Click on 'Update' button.");
		adminPostNewPage.clickOnPublishOrUpdateButton();
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step " + String.format("%02d", s=s+1) + ": Verify 'Post updated.' message is displayed.");
		verifyTrue(adminPostNewPage.isPostPublishedOrUpdatedMessageDisplayed("Post updated."));
	}
	
	@Test
	public void Post_04_Search_And_View_Post_After_Editing(Method method) {
		WpExtentTestManagerV5.startTest(method.getName() + " - " + browserName, "Search and view after editing post on Admin and User sites");
		int s = 0;

		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step " + String.format("%02d", s=s+1) + ": Click on WordPress logo and 'All Posts' link to go to Admin ALL POSTS page.");
		adminPostNewPage.clickOnWordpressLogo();
		adminPostAllPage = adminPostNewPage.clickOnAllPostsLink();
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step " + String.format("%02d", s=s+1) + ": Verify post Title, Sticky, Author, Category, Tag, Published Date are displayed.");
		verifyTrue(adminPostAllPage.isPostInfoResultTableDisplayed(editTitle, "Title", editTitle));
		verifyTrue(adminPostAllPage.isPostStickyDisplayed(editTitle));
		verifyTrue(adminPostAllPage.isPostInfoResultTableDisplayed(editTitle, "Author", authorName));
		verifyTrue(adminPostAllPage.isPostInfoResultTableDisplayed(editTitle, "Categories", editCategory));
		verifyTrue(adminPostAllPage.isPostInfoResultTableDisplayed(editTitle, "Tags", editTag));
		verifyTrue(adminPostAllPage.getPostPublishedDate(editTitle).contains(adminPostPublishedDate));
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step " + String.format("%02d", s=s+1) + ": Open User HOME page.");
		userHomePage = adminPostAllPage.openUserSite(driver, userUrl);
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step " + String.format("%02d", s=s+1) + ": Verify post Title, Sticky star, Image, Category, and Published Date are displayed.");
		verifyTrue(userHomePage.isPostTitleDisplayed(editTitle));
		verifyTrue(userHomePage.isPostStickyDisplayed(editTitle));
		verifyTrue(userHomePage.isPostImageDisplayed(editTitle, editImage));
		verifyTrue(userHomePage.isPostCategoryDisplayed(editTitle, editCategory));
		verifyTrue(userHomePage.isPostMetaDisplayed(editTitle, userPostPublishedDate));
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step " + String.format("%02d", s=s+1) + ": Click to expand 'Search' field and enter \"" + editTitle + "\".");
		userHomePage.clickOnSearchToggle();
		userHomePage.inputIntoSearchTextbox(editTitle);
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step " + String.format("%02d", s=s+1) + ": Click on Search button to go to User SEARCH page.");
		userSearchPage = userHomePage.clickOnSearchButton();

		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step " + String.format("%02d", s=s+1) + ": Verify '1 Post' result is displayed.");
		verifyTrue(userSearchPage.isOnePostMessageDisplayed("1 Post"));

		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step " + String.format("%02d", s=s+1) + ": Verify post Title, Image, Category, and Published Date are displayed.");
		verifyTrue(userSearchPage.isPostOrPageTitleDisplayed(editTitle));
		verifyTrue(userSearchPage.isPostOrPageImageDisplayed(editTitle, editImage));
		verifyTrue(userSearchPage.isPostCategoryDisplayed(editTitle, editCategory));
		verifyTrue(userSearchPage.isPostOrPagePublishedDateDisplayed(editTitle, userPostPublishedDate));
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step " + String.format("%02d", s=s+1) + ": Click on \"" + editTitle + "\" link to go to User POST DETAILS page.");
		userPostDetailsPage = userSearchPage.clickOnPostTitleLink(editTitle);
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step " + String.format("%02d", s=s+1) + ": Verify post Title, Image, Category, and Published Date are displayed.");
		verifyTrue(userPostDetailsPage.isPostTitleDisplayed(editTitle));
		verifyTrue(userPostDetailsPage.isPostImageDisplayed(editImage));
		verifyTrue(userPostDetailsPage.isPostCategoryDisplayed(editCategory));
		verifyTrue(userPostDetailsPage.isPostPublishedDateDisplayed(userPostPublishedDate));
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step " + String.format("%02d", s=s+1) + ": Verify post Body, Author, and Tag are displayed.");
		verifyTrue(userPostDetailsPage.isPostBodyDisplayed(editBody));
		verifyTrue(userPostDetailsPage.isPostAuthorDisplayed(authorName));
		verifyTrue(userPostDetailsPage.isPostTagDisplayed(editTag));
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step " + String.format("%02d", s=s+1) + ": Verify post Comment box is undisplayed.");
		verifyTrue(userPostDetailsPage.isPostCommentTextareaUndisplayed());
	}
	
	@Test
	public void Post_05_Delete_Post(Method method) {
		WpExtentTestManagerV5.startTest(method.getName() + " - " + browserName, "Delete post on Admin site");
		int s = 0;

		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step " + String.format("%02d", s=s+1) + ": Open Admin DASHBOARD page and click on 'Posts' menu to go to Admin ALL POSTS page.");
		adminDashboardPage = userPostDetailsPage.openAdminSite(driver, adminUrl);		
		adminPostAllPage = adminDashboardPage.clickOnPostMenu();
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step " + String.format("%02d", s=s+1) + ": Enter \"" + editTitle + "\" into Search field and click on 'Search Posts' button.");
		adminPostAllPage.inputIntoSearchTextbox1(editTitle);		
		adminPostAllPage.clickOnSearchPostsButton();
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step " + String.format("%02d", s=s+1) + ": Select \"" + editTitle + "\" checkbox.");
		adminPostAllPage.checkPostTitleCheckbox(editTitle);
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step " + String.format("%02d", s=s+1) + ": Select 'Move to Trash' item from 'Bulk actions' dropdown.");
		adminPostAllPage.selectItemFromBulkActionsDropdown("Move to Trash");
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step " + String.format("%02d", s=s+1) + ": Click on 'Apply' button.");
		adminPostAllPage.clickOnApplyButton();
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step " + String.format("%02d", s=s+1) + ": Verify '1 post moved to the Trash.' and 'No posts found.' messages are displayed.");
		verifyTrue(adminPostAllPage.isDeletePostMessageDisplayed("1 post moved to the Trash."));
		verifyTrue(adminPostAllPage.isNoPostsFoundMessageDisplayed("No posts found."));

		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step " + String.format("%02d", s=s+1) + ": Switch to 'Trash' tab.");
		adminPostAllPage.clickOnTrashTab();
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step " + String.format("%02d", s=s+1) + ": Enter \"" + editTitle + "\" into Search field and click on 'Search Posts' button.");
		adminPostAllPage.inputIntoSearchTextbox1(editTitle);		
		adminPostAllPage.clickOnSearchPostsButton();
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step " + String.format("%02d", s=s+1) + ": Select \"" + editTitle + "\" checkbox.");
		adminPostAllPage.checkPostTitleCheckbox(editTitle);
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step " + String.format("%02d", s=s+1) + ": Select 'Delete permanently' item from 'Bulk actions' dropdown.");
		adminPostAllPage.selectItemFromBulkActionsDropdown("Delete permanently");
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step " + String.format("%02d", s=s+1) + ": Click on 'Apply' button.");
		adminPostAllPage.clickOnApplyButton();
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step " + String.format("%02d", s=s+1) + ": Verify '1 post permanently deleted.' and 'No posts found in Trash.' messages are displayed.");
		verifyTrue(adminPostAllPage.isDeletePostMessageDisplayed("1 post permanently deleted."));
		verifyTrue(adminPostAllPage.isNoPostsFoundMessageDisplayed("No posts found in Trash."));

		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step " + String.format("%02d", s=s+1) + ": Open User HOME page.");
		userHomePage = adminPostAllPage.openUserSite(driver, userUrl);
		
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
	
	private String adminUrl, userUrl, browserName, adminPostPublishedDate, userPostPublishedDate, uploadedImageName;
	private int randomNumber2 = Demo_01_Login.randomNumber1;
	private String authorName = "Automation FC";
	private String postTitle = "[Annie]_NewPostTitle_" + randomNumber2;
	private String postBody = "Coding Demo: post body " + randomNumber2;
	private String postCategory = "[Annie] New Category 2022";
	private String postTag = "annie_tag_" + randomNumber2; 
	private String postImage = "annie_sunflower.jpg";
	private String editTitle = "[Annie]_EditPostTitle_" + randomNumber2;
	private String editBody = "Edit post body " + randomNumber2;
	private String editCategory = "[Annie] Edit Category 2022";
	private String editTag = "annie_edit_" + randomNumber2; 
	private String editImage = "annie_cake";
	
	private WebDriver driver;
	private WpAdminLoginPO adminLoginPage;
	private WpAdminDashboardPO adminDashboardPage;
	private WpAdminPostAllPO adminPostAllPage;
	private WpAdminPostNewPO adminPostNewPage;
	private WpUserHomePO userHomePage;
	private WpUserSearchPO userSearchPage;
	private WpUserPostDetailPO userPostDetailsPage;
}