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
import pageObjects.wordPress.AdminPostAllPO;
import pageObjects.wordPress.AdminPostNewPO;
import pageObjects.wordPress.UserHomePO;
import pageObjects.wordPress.UserPostDetailPO;
import pageObjects.wordPress.UserSearchPO;
import reportConfig.wordPress.ExtentTestManagerV5;

public class Live_Coding_02_Post extends BaseTest {
	
	@Parameters({ "browser", "adminUrl", "userUrl" })
	@BeforeClass
	public void beforeClass(String browserName, String adminUrl, String userUrl) {
		this.adminUrl = adminUrl;	
		this.userUrl = userUrl;
		
		driver = getBrowserDriver(browserName, this.adminUrl);
		adminLoginPage = PageGeneratorManager.getAdminLoginPage(driver);
		
		adminDashboardPage = adminLoginPage.loginToSystem(driver, Live_Coding_01_Login.username, Live_Coding_01_Login.password);
		
		adminPostAllPage = adminDashboardPage.clickOnPostMenu();
	}

	@Test
	public void Post_01_Create_New_Post(Method method) {
		ExtentTestManagerV5.startTest(method.getName(), "Create new post at Admin site");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 01: Navigate to Admin ADD NEW POST page.");
		adminPostNewPage = adminPostAllPage.clickOnAddNewButton();
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 02: Enter \"" + postTitle + "\" into 'Post Title'.");
		adminPostNewPage.inputIntoPostTitle(postTitle);
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 03: Enter \"" + postBody + "\" into 'Post Body'.");
		adminPostNewPage.inputIntoPostBody(postBody);
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 04: Open 'Post' setting sidebar.");
		adminPostNewPage.clickOnPostSidebar();
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 05: Select 'Stick to the top of the blog' checkbox.");
		adminPostNewPage.checkCheckboxByLabel("Stick to the top of the blog");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 06: Expand 'Categories' panel.");
		adminPostNewPage.clickOnPanelByText("Categories");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 07: Enter \"" + postCategory + "\" into 'Search Categories' textbox and select.");
		adminPostNewPage.inputIntoSearchCategoryTextbox(postCategory);
		adminPostNewPage.checkCheckboxByLabel(postCategory);
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 08: Expand 'Tags' panel and enter \"" + postTag + "\" into 'Add New Tag' textbox.");
		adminPostNewPage.clickOnPanelByText("Tags");
		adminPostNewPage.inputIntoAddNewTagTextbox(postTag);

		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 09: Expand 'Featured image' panel.");
		adminPostNewPage.clickOnPanelByText("Featured image");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 10: Open 'Set featured image' menu and click on 'Media Library' item.");
		adminPostNewPage.clickOnSetFeaturedImageMenu();
		adminPostNewPage.clickOnSetMediaLibraryItem();
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 11: Switch to 'Upload files' tab and upload image.");
		adminPostNewPage.clickOnUploadFilesTab();
		adminPostNewPage.uploadMultipleFiles(driver, postImage);
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 12: Get image name after uploaded.");
		imageUploadedName = adminPostNewPage.getImageNameUploaded();
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 13: Click on 'Set featured image' button and verify image is uploaded.");
		adminPostNewPage.clickOnSetImageButton();
		verifyTrue(adminPostNewPage.isImageUploaded(imageUploadedName));
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 14: Click on 'Publish' and 'Pre-publish' button.");
		adminPostNewPage.clickOnPublishOrUpdateButton();
		adminPostNewPage.clickOnPrePublishButton();
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 15: Verify 'Post published.' message is displayed.");
		verifyTrue(adminPostNewPage.isPostPublishedOrUpdatedMessageDisplayed("Post published."));
	}
	
	@Test
	public void Post_02_Search_Post(Method method) {
		ExtentTestManagerV5.startTest(method.getName(), "Search for newly created post at Admin site and User site");

		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 01: Click on Wordpress logo and navigate to Admin ALL POSTS page.");
		adminPostNewPage.clickOnWordpressLogo();
		adminPostAllPage = adminPostNewPage.clickOnAllPostsLink();
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 02: Enter \"" + postTitle + "\" into 'Search' textbox and click on 'Search Posts' button.");
		adminPostAllPage.inputIntoSearchTextbox(postTitle);
		adminPostAllPage.clickOnSearchPostsButton();
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 03: Verify 'Search results' title contains \"" + postTitle + "\".");
		//verifyTrue(adminPostAllPage.isSearchResultsTitleDisplayed(postTitle));
		verifyEquals(adminPostAllPage.getSearchResultsTitle(), "Search results for: " + postTitle);
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 04: Verify post Title, Author, Category, Tag are displayed.");
		//verifyTrue(adminPostAllPage.isPostInfoResultTableDisplayed("Title", postTitle));
		//verifyTrue(adminPostAllPage.isPostInfoResultTableDisplayed("Author", authorName));
		//verifyTrue(adminPostAllPage.isPostInfoResultTableDisplayed("Categories", postCategory));
		//verifyTrue(adminPostAllPage.isPostInfoResultTableDisplayed("Tags", postTag));
		verifyEquals(adminPostAllPage.getPostInfoRowByColumnTitle("Title"), postTitle);
		verifyEquals(adminPostAllPage.isPostInfoResultTableDisplayed("Author"), authorName);
		verifyEquals(adminPostAllPage.isPostInfoResultTableDisplayed("Categories"), postCategory);
		verifyEquals(adminPostAllPage.isPostInfoResultTableDisplayed("Tags"), postTag);
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 05: Open User HOME page.");
		userHomePage = adminPostAllPage.openUserSite(driver, this.userUrl);
		userHomePage.clickOnAcceptCookiesButton();
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 06: Click on 'Search' toggle and enter \"" + postTitle + "\" into 'Search' textbox.");
		userHomePage.clickOnSearchToggle();
		userHomePage.inputIntoSearchTextbox(postTitle);
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 07: Navigate to User SEARCH page.");
		userSearchPage = userHomePage.clickOnSearchButton();

		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 08: Verify 'Search Results' title contains \"" + postTitle + "\".");
		verifyTrue(userSearchPage.isSearchResultsTitleDisplayed(postTitle));

		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 09: Verify '1 Post' is displayed.");
		verifyTrue(userSearchPage.isOnePostMessageDisplayed("1 Post"));

		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 10: Verify post Title, Image, Category, Published Date are displayed.");
		verifyTrue(userSearchPage.isPostTitleDisplayed(postTitle));
		verifyTrue(userSearchPage.isPostImageDisplayed(postTitle, imageUploadedName));
		verifyTrue(userSearchPage.isPostCategoryDisplayed(postTitle, postCategory));
		verifyTrue(userSearchPage.isPostPublishedDateDisplayed(postTitle, publishedDate));
	}
	
	@Test
	public void Post_03_View_Post(Method method) {
		ExtentTestManagerV5.startTest(method.getName(), "View newly created post at User site");

		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 01: Navigate to User HOME page");
		userHomePage = userSearchPage.clickOnHomePageLink();
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 02: Verify post Title, Image, Category, Published Date are displayed.");
		verifyTrue(userHomePage.isPostTitleDisplayed(postTitle));
		verifyTrue(userHomePage.isPostImageDisplayed(postTitle, imageUploadedName));
		verifyTrue(userHomePage.isPostCategoryDisplayed(postTitle, postCategory));
		verifyTrue(userHomePage.isPostPublishedDateDisplayed(postTitle, publishedDate));
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 03: Navigate to User POST DETAILS page");
		userPostDetailPage = userHomePage.clickOnPostTitleLink(postTitle);
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 04: Verify post Title, Image, Category, Published Date are displayed.");
		verifyTrue(userPostDetailPage.isPostTitleDisplayed(postTitle));
		verifyTrue(userPostDetailPage.isPostImageDisplayed(imageUploadedName));
		verifyTrue(userPostDetailPage.isPostCategoryDisplayed(postCategory));
		verifyTrue(userPostDetailPage.isPostPublishedDateDisplayed(publishedDate));
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 05: Verify post Body, Author, Tag, Comment textarea are displayed.");
		verifyTrue(userPostDetailPage.isPostBodyDisplayed(postBody));
		verifyTrue(userPostDetailPage.isPostAuthorDisplayed(authorName));
		verifyTrue(userPostDetailPage.isPostTagDisplayed(postTag));
		verifyTrue(userPostDetailPage.isCommentTextareaDisplayed());
	}
	
	@Test
	public void Post_04_Edit_Post(Method method) {
		ExtentTestManagerV5.startTest(method.getName(), "Edit post at Admin site");

		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 01: Open Admin DASHBOARD page and navigate to Admin ALL POSTS page.");
		adminDashboardPage = userPostDetailPage.openAdminSite(driver, this.adminUrl);		
		adminPostAllPage = adminDashboardPage.clickOnPostMenu();
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 02: Enter \"" + postTitle + "\" into 'Search' textbox and click on 'Search Posts' button.");
		adminPostAllPage.inputIntoSearchTextbox(postTitle);		
		adminPostAllPage.clickOnSearchPostsButton();
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 03: Click on \"" + postTitle + "\" link and navigate to Admin EDIT POST page.");
		adminPostNewPage = adminPostAllPage.clickOnPostTitleLink("Title", postTitle);

		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 04: Enter \"" + editTitle + "\" into 'Post Title'.");
		adminPostNewPage.inputIntoPostTitle(editTitle);
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 05: Enter \"" + editBody + "\" into 'Post Body'.");
		adminPostNewPage.inputIntoEditPostBody(editBody);
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 06: Open 'Post' setting sidebar.");
		adminPostNewPage.clickOnPostSidebar();
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 07: Expand 'Categories' panel and deselect \"" + postCategory + "\".");
		adminPostNewPage.clickOnPanelByText("Categories");
		adminPostNewPage.uncheckCheckboxByLabel(postCategory);
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 08: Enter \"" + editCategory + "\" into 'Search Categories' textbox and select.");
		adminPostNewPage.inputIntoSearchCategoryTextbox(editCategory);
		adminPostNewPage.checkCheckboxByLabel(editCategory);
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 09: Expand 'Tags' panel, clear \"" + postTag + "\" and enter \"" + editTag + "\" into 'Add New Tag' textbox.");
		adminPostNewPage.clickOnPanelByText("Tags");
		adminPostNewPage.clickOnRemoveTagButton(postTag);
		adminPostNewPage.inputIntoAddNewTagTextbox(editTag);

		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 10: Expand 'Featured image' panel.");
		adminPostNewPage.clickOnPanelByText("Featured image");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 11: Open 'Replace Image' menu and click on 'Media Library' item.");
		adminPostNewPage.clickOnReplaceImageMenu();
		adminPostNewPage.clickOnSetMediaLibraryItem();
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 12: Select \"" + postImage + "\" and click on 'Delete permanently'.");
		adminPostNewPage.clickOnDeleteImageButton();
		adminPostNewPage.accepAlert(driver);
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 13: Enter \"" + editImage + "\" into 'Search' textbox and select.");
		adminPostNewPage.inputIntoSearchImageTextbox(editImage);
		adminPostNewPage.selectPostImage(editImage);
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 14: Click on 'Set featured image' button and verify image is uploaded.");
		adminPostNewPage.clickOnSetImageButton();
		verifyTrue(adminPostNewPage.isImageUploaded(editImage));
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 15: Expand 'Discussion' panel and deselect 'Allow comments' checkbox.");
		adminPostNewPage.clickOnPanelByText("Discussion");
		adminPostNewPage.uncheckCheckboxByLabel("Allow comments");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 16: Click on 'Update' button.");
		adminPostNewPage.clickOnPublishOrUpdateButton();
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 17: Verify 'Post updated.' message is displayed.");
		verifyTrue(adminPostNewPage.isPostPublishedOrUpdatedMessageDisplayed("Post updated."));
	}
	
	@Test
	public void Post_05_Search_And_View_Post_After_Edit(Method method) {
		ExtentTestManagerV5.startTest(method.getName(), "Search and view after editing post at Admin site and User site");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 01: Click on Wordpress logo and navigate to Admin ALL POSTS page.");
		adminPostNewPage.clickOnWordpressLogo();
		adminPostAllPage = adminPostNewPage.clickOnAllPostsLink();
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 02: Enter \"" + editTitle + "\" into 'Search' textbox and click on 'Search Posts' button.");
		adminPostAllPage.inputIntoSearchTextbox(editTitle);
		adminPostAllPage.clickOnSearchPostsButton();
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 03: Verify 'Search results' title contains \"" + editTitle + "\".");
		verifyTrue(adminPostAllPage.isSearchResultsTitleDisplayed(editTitle));
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 04: Verify post Title, Author, Category, Tag are displayed.");
		verifyTrue(adminPostAllPage.isPostInfoResultTableDisplayed("Title", editTitle));
		verifyTrue(adminPostAllPage.isPostInfoResultTableDisplayed("Author", authorName));
		verifyTrue(adminPostAllPage.isPostInfoResultTableDisplayed("Categories", editCategory));
		verifyTrue(adminPostAllPage.isPostInfoResultTableDisplayed("Tags", editTag));
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 05: Open User HOME page.");
		userHomePage = adminPostAllPage.openUserSite(driver, this.userUrl);
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 06: Click on 'Search' toggle and enter \"" + editTitle + "\" into 'Search' textbox.");
		userHomePage.clickOnSearchToggle();
		userHomePage.inputIntoSearchTextbox(editTitle);
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 07: Navigate to User SEARCH page.");
		userSearchPage = userHomePage.clickOnSearchButton();

		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 08: Verify 'Search Results' title contains \"" + editTitle + "\".");
		verifyTrue(userSearchPage.isSearchResultsTitleDisplayed(editTitle));

		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 09: Verify '1 Post' is displayed.");
		verifyTrue(userSearchPage.isOnePostMessageDisplayed("1 Post"));

		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 10: Verify post Title, Image, Category, Published Date are displayed.");
		verifyTrue(userSearchPage.isPostTitleDisplayed(editTitle));
		verifyTrue(userSearchPage.isPostImageDisplayed(editTitle, editImage));
		verifyTrue(userSearchPage.isPostCategoryDisplayed(editTitle, editCategory));
		verifyTrue(userSearchPage.isPostPublishedDateDisplayed(editTitle, publishedDate));
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 11: Navigate to User POST DETAILS page");
		userPostDetailPage = userSearchPage.clickOnPostTitleLink(editTitle);
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 12: Verify post Title, Image, Category, Published Date are displayed.");
		verifyTrue(userPostDetailPage.isPostTitleDisplayed(editTitle));
		verifyTrue(userPostDetailPage.isPostImageDisplayed(editImage));
		verifyTrue(userPostDetailPage.isPostCategoryDisplayed(editCategory));
		verifyTrue(userPostDetailPage.isPostPublishedDateDisplayed(publishedDate));
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 13: Verify post Body, Author, Tag textarea are displayed.");
		verifyTrue(userPostDetailPage.isPostBodyDisplayed(editBody));
		verifyTrue(userPostDetailPage.isPostAuthorDisplayed(authorName));
		verifyTrue(userPostDetailPage.isPostTagDisplayed(editTag));
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 14: Verify Comment textarea is undisplayed.");
		verifyTrue(userPostDetailPage.isCommentTextareaUndisplayed());
	}
	
	@Test
	public void Post_06_Delete_Post(Method method) {
		ExtentTestManagerV5.startTest(method.getName(), "Delete post at Admin site");

		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 01: Open Admin DASHBOARD page and navigate to Admin ALL POSTS page.");
		adminDashboardPage = userPostDetailPage.openAdminSite(driver, this.adminUrl);		
		adminPostAllPage = adminDashboardPage.clickOnPostMenu();
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 02: Enter \"" + editTitle + "\" into 'Search' textbox and click on 'Search Posts' button.");
		adminPostAllPage.inputIntoSearchTextbox(editTitle);		
		adminPostAllPage.clickOnSearchPostsButton();
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 03: Select \"" + editTitle + "\" checkbox.");
		adminPostAllPage.checkPostTitleCheckbox(editTitle);
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 04: Select 'Move to Trash' item from 'Bulk actions' dropdown.");
		adminPostAllPage.selectItemFromBulkActionsDropdown("Move to Trash");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 05: Click on 'Apply' button.");
		adminPostAllPage.clickOnApplyButton();
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 06: Verify '1 post moved to the Trash.' message is displayed.");
		verifyTrue(adminPostAllPage.isDeletePostMessageDisplayed("1 post moved to the Trash."));
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 07: Verify 'No posts found.' message is displayed.");
		verifyTrue(adminPostAllPage.isNoPostsFoundMessageDisplayed());

		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 08: Open User HOME page.");
		userHomePage = adminPostAllPage.openUserSite(driver, this.userUrl);
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 09: Click on 'Search' toggle and enter \"" + editTitle + "\" into 'Search' textbox.");
		userHomePage.clickOnSearchToggle();
		userHomePage.inputIntoSearchTextbox(editTitle);
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 10: Navigate to User SEARCH page.");
		userSearchPage = userHomePage.clickOnSearchButton();

		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 11: Verify 'It seems we can't find what you’re looking for.' message is displayed.");
		verifyTrue(userSearchPage.isSearchResultsMessageDisplayed());
	}

	@AfterClass(alwaysRun = true)
	public void afterClass() {
		closeBrowserAndDriver();
	}
	
	String adminUrl, userUrl, imageUploadedName;
	static int randomNumber2 = generateFakeNumber();
	String authorName = "Automation FC";
	String postTitle = "[Annie]_New-post-title_" + randomNumber2;
	String postBody = "Live Coding: post body " + randomNumber2;
	String postCategory = "Annie_new-category-2022";
	String postTag = "annie_tag_" + randomNumber2; 
	String postImage = "annie_sunflower.jpg";
	String publishedDate = getCurrentDate();
	String editTitle = "[Annie]_Edit-post-title_" + randomNumber2;
	String editBody = "Edit post body " + randomNumber2;
	String editCategory = "Annie_edit-category-2022";
	String editTag = "annie_edit_" + randomNumber2; 
	String editImage = "annie_cake";
	
	WebDriver driver;
	AdminLoginPO adminLoginPage;
	AdminDashboardPO adminDashboardPage;
	AdminPostAllPO adminPostAllPage;
	AdminPostNewPO adminPostNewPage;
	UserHomePO userHomePage;
	UserSearchPO userSearchPage;
	UserPostDetailPO userPostDetailPage;
}