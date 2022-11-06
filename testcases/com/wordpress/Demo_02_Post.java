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

public class Demo_02_Post extends WpBaseTest {
	
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
		WpExtentTestManagerV5.startTest(method.getName() + " - " + browserName, "Create new post at Admin site");
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step 01: Navigate to Admin ADD NEW POST page.");
		adminPostNewPage = adminPostAllPage.clickOnAddNewButton();
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step 02: Enter \"" + postTitle + "\" into 'Post Title'.");
		adminPostNewPage.inputIntoPostTitle(postTitle);
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step 03: Enter \"" + postBody + "\" into 'Post Body'.");
		adminPostNewPage.inputIntoPostBody(postBody);
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step 04: Open 'Post' setting sidebar.");
		adminPostNewPage.clickOnPostSidebar();
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step 05: Select 'Stick to the top of the blog' checkbox.");
		adminPostNewPage.checkCheckboxByLabel("Stick to the top of the blog");
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step 06: Expand 'Categories' panel.");
		adminPostNewPage.clickOnPanelByText("Categories");
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step 07: Enter \"" + postCategory + "\" into 'Search Categories' textbox and select.");
		adminPostNewPage.inputIntoSearchCategoryTextbox(postCategory);
		adminPostNewPage.checkCheckboxByLabel(postCategory);
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step 08: Expand 'Tags' panel and enter \"" + postTag + "\" into 'Add New Tag' textbox.");
		adminPostNewPage.clickOnPanelByText("Tags");
		adminPostNewPage.inputIntoAddNewTagTextbox(postTag);
		verifyTrue(adminPostNewPage.isRemoveTagButtonDisplayed(postTag));

		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step 09: Expand 'Featured image' panel.");
		adminPostNewPage.clickOnPanelByText("Featured image");
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step 10: Open 'Set featured image' menu and click on 'Media Library' item.");
		adminPostNewPage.clickOnImageMenu("Set featured image");
		adminPostNewPage.clickOnMediaLibraryItem();
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step 11: Switch to 'Upload files' tab and upload \"" + postImage + "\".");
		adminPostNewPage.clickOnUploadFilesTab();
		adminPostNewPage.uploadMultipleFiles(driver, postImage);
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step 12: Get image name after uploaded.");
		uploadedImageName = adminPostNewPage.getUploadedImageName();
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step 13: Click on 'Set featured image' button and verify \"" + uploadedImageName + "\" is uploaded.");
		adminPostNewPage.clickOnSetImageButton();
		verifyTrue(adminPostNewPage.isImageUploaded(uploadedImageName));
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step 14: Click on 'Publish' and 'Pre-publish' button.");
		adminPostNewPage.clickOnPublishOrUpdateButton();
		adminPostNewPage.clickOnPrePublishButton();
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step 15: Verify 'Post published.' message is displayed.");
		verifyTrue(adminPostNewPage.isPostPublishedOrUpdatedMessageDisplayed("Post published."));
		publishedDate = adminPostNewPage.getCurrentDate();
	}
	
	@Test
	public void Post_02_Search_And_View_Post(Method method) {
		WpExtentTestManagerV5.startTest(method.getName() + " - " + browserName, "Search and view newly created post at Admin and User sites");

		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step 01: Click on Wordpress logo and navigate to Admin ALL POSTS page.");
		adminPostNewPage.clickOnWordpressLogo();
		adminPostAllPage = adminPostNewPage.clickOnAllPostsLink();
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step 02: Enter \"" + postTitle + "\" into 'Search' textbox and click on 'Search Posts' button.");
		adminPostAllPage.inputIntoSearchTextbox(postTitle);
		adminPostAllPage.clickOnSearchPostsButton();
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step 03: Verify 'Search results' title contains \"" + postTitle + "\".");
		verifyEquals(adminPostAllPage.getSearchResultsTitle(), "Search results for: " + postTitle);
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step 04: Verify post Title, Author, Category, Tag are displayed.");
		verifyTrue(adminPostAllPage.isPostInfoResultTableDisplayed("Title", postTitle));
		verifyTrue(adminPostAllPage.isPostInfoResultTableDisplayed("Author", authorName));
		verifyTrue(adminPostAllPage.isPostInfoResultTableDisplayed("Categories", postCategory));
		verifyTrue(adminPostAllPage.isPostInfoResultTableDisplayed("Tags", postTag));
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step 05: Open User HOME page.");
		userHomePage = adminPostAllPage.openUserSite(driver, userUrl);
		userHomePage.clickOnAcceptCookiesButton();
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step 06: Click on 'Search' toggle and enter \"" + postTitle + "\" into 'Search' textbox.");
		userHomePage.clickOnSearchToggle();
		userHomePage.inputIntoSearchTextbox(postTitle);
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step 07: Navigate to User SEARCH page.");
		userSearchPage = userHomePage.clickOnSearchButton();

		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step 08: Verify 'Search Results' title contains \"" + postTitle + "\".");
		verifyEquals(userSearchPage.getSearchResultsTitle(), "Search Results for: " + postTitle);

		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step 09: Verify '1 Post' is displayed.");
		verifyTrue(userSearchPage.isOnePostMessageDisplayed("1 Post"));

		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step 10: Verify post Title, Image, Category, Published Date are displayed.");
		verifyTrue(userSearchPage.isPostOrPageTitleDisplayed(postTitle));
		verifyTrue(userSearchPage.isPostOrPageImageDisplayed(postTitle, uploadedImageName));
		verifyTrue(userSearchPage.isPostCategoryDisplayed(postTitle, postCategory));
		verifyTrue(userSearchPage.isPostOrPublishedDateDisplayed(postTitle, publishedDate));

		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step 11: Navigate to User HOME page");
		userHomePage = userSearchPage.clickOnHomePageLink();
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step 12: Verify post Title, Image, Category, Published Date are displayed.");
		verifyTrue(userHomePage.isPostTitleDisplayed(postTitle));
		verifyTrue(userHomePage.isPostImageDisplayed(postTitle, uploadedImageName));
		verifyTrue(userHomePage.isPostCategoryDisplayed(postTitle, postCategory));
		verifyTrue(userHomePage.isPostPublishedDateDisplayed(postTitle, publishedDate));
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step 13: Navigate to User POST DETAILS page");
		userPostDetailPage = userHomePage.clickOnPostTitleLink(postTitle);
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step 14: Verify post Title, Image, Category, Published Date are displayed.");
		verifyTrue(userPostDetailPage.isPostTitleDisplayed(postTitle));
		verifyTrue(userPostDetailPage.isPostImageDisplayed(uploadedImageName));
		verifyTrue(userPostDetailPage.isPostCategoryDisplayed(postCategory));
		verifyTrue(userPostDetailPage.isPostPublishedDateDisplayed(publishedDate));
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step 15: Verify post Body, Author, Tag, Comment textarea are displayed.");
		verifyTrue(userPostDetailPage.isPostBodyDisplayed(postBody));
		verifyTrue(userPostDetailPage.isPostAuthorDisplayed(authorName));
		verifyTrue(userPostDetailPage.isPostTagDisplayed(postTag));
		verifyTrue(userPostDetailPage.isCommentTextareaDisplayed());
	}
	
	@Test
	public void Post_03_Edit_Post(Method method) {
		WpExtentTestManagerV5.startTest(method.getName() + " - " + browserName, "Edit post at Admin site");

		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step 01: Open Admin DASHBOARD page and navigate to Admin ALL POSTS page.");
		adminDashboardPage = userPostDetailPage.openAdminSite(driver, adminUrl);		
		adminPostAllPage = adminDashboardPage.clickOnPostMenu();
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step 02: Enter \"" + postTitle + "\" into 'Search' textbox and click on 'Search Posts' button.");
		adminPostAllPage.inputIntoSearchTextbox(postTitle);		
		adminPostAllPage.clickOnSearchPostsButton();
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step 03: Click on \"" + postTitle + "\" link to navigate to Admin EDIT POST page.");
		adminPostNewPage = adminPostAllPage.clickOnPostTitleLink("Title", postTitle);

		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step 04: Enter \"" + editTitle + "\" into 'Post Title'.");
		adminPostNewPage.inputIntoPostTitle(editTitle);
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step 05: Enter \"" + editBody + "\" into 'Post Body'.");
		adminPostNewPage.inputIntoEditPostBody(editBody);
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step 06: Open 'Post' setting sidebar.");
		adminPostNewPage.clickOnPostSidebar();
				
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step 07: Expand 'Categories' panel and deselect \"" + postCategory + "\".");
		adminPostNewPage.clickOnPanelByText("Categories");
		adminPostNewPage.uncheckCheckboxByLabel(postCategory);
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step 08: Enter \"" + editCategory + "\" into 'Search Categories' textbox and select.");
		adminPostNewPage.inputIntoSearchCategoryTextbox(editCategory);
		adminPostNewPage.checkCheckboxByLabel(editCategory);
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step 09: Expand 'Tags' panel, clear \"" + postTag + "\" and enter \"" + editTag + "\" into 'Add New Tag' textbox.");
		adminPostNewPage.clickOnPanelByText("Tags");
		adminPostNewPage.clickOnRemoveTagButton(postTag);
		adminPostNewPage.inputIntoAddNewTagTextbox(editTag);
		verifyTrue(adminPostNewPage.isRemoveTagButtonDisplayed(editTag));

		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step 10: Expand 'Featured image' panel.");
		adminPostNewPage.clickOnPanelByText("Featured image");
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step 11: Open 'Replace Image' menu and click on 'Media Library' item.");
		adminPostNewPage.clickOnImageMenu("Replace Image");
		adminPostNewPage.clickOnMediaLibraryItem();
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step 12: Select \"" + uploadedImageName + "\" and click on 'Delete permanently' button.");
		adminPostNewPage.clickOnDeleteImageButton();
		adminPostNewPage.acceptAlert(driver);
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step 13: Enter \"" + editImage + "\" into 'Search' textbox and select.");
		adminPostNewPage.inputIntoSearchImageTextbox(editImage);
		adminPostNewPage.selectPostImage(editImage);
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step 14: Click on 'Set featured image' button and verify \"" + editImage + ".jpg\" is uploaded.");
		adminPostNewPage.clickOnSetImageButton();
		verifyTrue(adminPostNewPage.isImageUploaded(editImage));
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step 15: Expand 'Discussion' panel and deselect 'Allow comments' checkbox.");
		adminPostNewPage.clickOnPanelByText("Discussion");
		adminPostNewPage.uncheckCheckboxByLabel("Allow comments");
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step 16: Click on 'Update' button.");
		adminPostNewPage.clickOnPublishOrUpdateButton();
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step 17: Verify 'Post updated.' message is displayed.");
		verifyTrue(adminPostNewPage.isPostPublishedOrUpdatedMessageDisplayed("Post updated."));
	}
	
	@Test
	public void Post_04_Search_And_View_Post_After_Edit(Method method) {
		WpExtentTestManagerV5.startTest(method.getName() + " - " + browserName, "Search and view after editing post at Admin and User sites");
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step 01: Click on Wordpress logo and navigate to Admin ALL POSTS page.");
		adminPostNewPage.clickOnWordpressLogo();
		adminPostAllPage = adminPostNewPage.clickOnAllPostsLink();
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step 02: Enter \"" + editTitle + "\" into 'Search' textbox and click on 'Search Posts' button.");
		adminPostAllPage.inputIntoSearchTextbox(editTitle);
		adminPostAllPage.clickOnSearchPostsButton();
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step 03: Verify 'Search results' title contains \"" + editTitle + "\".");
		verifyEquals(adminPostAllPage.getSearchResultsTitle(), "Search results for: " + editTitle);
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step 04: Verify post Title, Author, Category, Tag are displayed.");
		verifyTrue(adminPostAllPage.isPostInfoResultTableDisplayed("Title", editTitle));
		verifyTrue(adminPostAllPage.isPostInfoResultTableDisplayed("Author", authorName));
		verifyTrue(adminPostAllPage.isPostInfoResultTableDisplayed("Categories", editCategory));
		verifyTrue(adminPostAllPage.isPostInfoResultTableDisplayed("Tags", editTag));
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step 05: Open User HOME page.");
		userHomePage = adminPostAllPage.openUserSite(driver, userUrl);
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step 06: Click on 'Search' toggle and enter \"" + editTitle + "\" into 'Search' textbox.");
		userHomePage.clickOnSearchToggle();
		userHomePage.inputIntoSearchTextbox(editTitle);
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step 07: Navigate to User SEARCH page.");
		userSearchPage = userHomePage.clickOnSearchButton();

		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step 08: Verify 'Search Results' title contains \"" + editTitle + "\".");
		verifyEquals(userSearchPage.getSearchResultsTitle(), "Search Results for: " + editTitle);
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step 09: Verify '1 Post' is displayed.");
		verifyTrue(userSearchPage.isOnePostMessageDisplayed("1 Post"));

		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step 10: Verify post Title, Image, Category, Published Date are displayed.");
		verifyTrue(userSearchPage.isPostOrPageTitleDisplayed(editTitle));
		verifyTrue(userSearchPage.isPostOrPageImageDisplayed(editTitle, editImage));
		verifyTrue(userSearchPage.isPostCategoryDisplayed(editTitle, editCategory));
		verifyTrue(userSearchPage.isPostOrPublishedDateDisplayed(editTitle, publishedDate));
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step 11: Navigate to User POST DETAILS page");
		userPostDetailPage = userSearchPage.clickOnPostTitleLink(editTitle);
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step 12: Verify post Title, Image, Category, Published Date are displayed.");
		verifyTrue(userPostDetailPage.isPostTitleDisplayed(editTitle));
		verifyTrue(userPostDetailPage.isPostImageDisplayed(editImage));
		verifyTrue(userPostDetailPage.isPostCategoryDisplayed(editCategory));
		verifyTrue(userPostDetailPage.isPostPublishedDateDisplayed(publishedDate));
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step 13: Verify post Body, Author, Tag textarea are displayed.");
		verifyTrue(userPostDetailPage.isPostBodyDisplayed(editBody));
		verifyTrue(userPostDetailPage.isPostAuthorDisplayed(authorName));
		verifyTrue(userPostDetailPage.isPostTagDisplayed(editTag));
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step 14: Verify Comment textarea is undisplayed.");
		verifyTrue(userPostDetailPage.isCommentTextareaUndisplayed());
	}
	
	@Test
	public void Post_05_Delete_Post(Method method) {
		WpExtentTestManagerV5.startTest(method.getName() + " - " + browserName, "Delete post at Admin site");

		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step 01: Open Admin DASHBOARD page and navigate to Admin ALL POSTS page.");
		adminDashboardPage = userPostDetailPage.openAdminSite(driver, adminUrl);		
		adminPostAllPage = adminDashboardPage.clickOnPostMenu();
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step 02: Enter \"" + editTitle + "\" into 'Search' textbox and click on 'Search Posts' button.");
		adminPostAllPage.inputIntoSearchTextbox(editTitle);		
		adminPostAllPage.clickOnSearchPostsButton();
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step 03: Select \"" + editTitle + "\" checkbox.");
		adminPostAllPage.checkPostTitleCheckbox(editTitle);
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step 04: Select 'Move to Trash' item from 'Bulk actions' dropdown.");
		adminPostAllPage.selectItemFromBulkActionsDropdown("Move to Trash");
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step 05: Click on 'Apply' button.");
		adminPostAllPage.clickOnApplyButton();
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step 06: Verify '1 post moved to the Trash.' message is displayed.");
		verifyTrue(adminPostAllPage.isDeletePostMessageDisplayed("1 post moved to the Trash."));
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step 07: Verify 'No posts found.' message is displayed.");
		verifyTrue(adminPostAllPage.isNoPostsFoundMessageDisplayed());

		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step 08: Open User HOME page.");
		userHomePage = adminPostAllPage.openUserSite(driver, userUrl);
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step 09: Click on 'Search' toggle and enter \"" + editTitle + "\" into 'Search' textbox.");
		userHomePage.clickOnSearchToggle();
		userHomePage.inputIntoSearchTextbox(editTitle);
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step 10: Navigate to User SEARCH page.");
		userSearchPage = userHomePage.clickOnSearchButton();

		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step 11: Verify 'It seems we can't find what you’re looking for.' message is displayed.");
		verifyTrue(userSearchPage.isSearchResultsMessageDisplayed());
	}

	@AfterClass(alwaysRun = true)
	public void afterClass() {
		closeBrowserAndDriver();
	}
	
	String adminUrl, userUrl, browserName, publishedDate, uploadedImageName;
	int randomNumber2 = Demo_01_Login.randomNumber1;
	String authorName = "Automation FC";
	String postTitle = "[Annie]_New-post-title_" + randomNumber2;
	String postBody = "Coding Demo: post body " + randomNumber2;
	String postCategory = "Annie_new-category-2022";
	String postTag = "annie_tag_" + randomNumber2; 
	String postImage = "annie_sunflower.jpg";
	String editTitle = "[Annie]_Edit-post-title_" + randomNumber2;
	String editBody = "Edit post body " + randomNumber2;
	String editCategory = "Annie_edit-category-2022";
	String editTag = "annie_edit_" + randomNumber2; 
	String editImage = "annie_cake";
	
	WebDriver driver;
	WpAdminLoginPO adminLoginPage;
	WpAdminDashboardPO adminDashboardPage;
	WpAdminPostAllPO adminPostAllPage;
	WpAdminPostNewPO adminPostNewPage;
	WpUserHomePO userHomePage;
	WpUserSearchPO userSearchPage;
	WpUserPostDetailPO userPostDetailPage;
}