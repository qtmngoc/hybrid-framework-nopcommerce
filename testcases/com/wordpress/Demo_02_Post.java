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
		WpExtentTestManagerV5.startTest(method.getName() + " - " + browserName, "Create a new post on Admin site");
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step 01: Click on 'Posts' menu and 'Add New' button to go to Admin ADD NEW POST page.");
		adminPostNewPage = adminPostAllPage.clickOnAddNewButton();
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step 02: Enter \"" + postTitle + "\" into post Title.");
		adminPostNewPage.inputIntoPostTitle(postTitle);
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step 03: Enter \"" + postBody + "\" into post Body.");
		adminPostNewPage.inputIntoPostBody(postBody);
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step 04: Open 'Post' settings sidebar.");
		adminPostNewPage.clickOnPostSidebar();
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step 05: Select 'Stick to the top of the blog' checkbox.");
		adminPostNewPage.checkCheckboxByLabel("Stick to the top of the blog");
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step 06: Expand 'Categories' panel.");
		adminPostNewPage.clickOnPanelByText("Categories");
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step 07: Enter \"" + postCategory + "\" into 'Search Categories' field and select it.");
		adminPostNewPage.inputIntoSearchCategoryTextbox(postCategory);
		adminPostNewPage.checkCheckboxByLabel(postCategory);
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step 08: Expand 'Tags' panel.");
		adminPostNewPage.clickOnPanelByText("Tags");
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step 09: Enter \"" + postTag + "\" into 'Add New Tag' field.");
		adminPostNewPage.inputIntoAddNewTagTextbox(postTag);
		verifyTrue(adminPostNewPage.isRemoveTagButtonDisplayed(postTag));

		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step 10: Expand 'Featured image' panel.");
		adminPostNewPage.clickOnPanelByText("Featured image");
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step 11: Open 'Set featured image' menu and click on 'Media Library' item.");
		adminPostNewPage.clickOnImageMenu("Set featured image");
		adminPostNewPage.clickOnMediaLibraryItem();
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step 12: Switch to 'Upload files' tab.");
		adminPostNewPage.clickOnUploadFilesTab();
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step 13: Upload \"" + postImage + "\" and get image name after uploading.");
		adminPostNewPage.uploadMultipleFiles(driver, postImage);
		uploadedImageName = adminPostNewPage.getUploadedImageName();
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step 14: Click on 'Set featured image' button.");
		adminPostNewPage.clickOnSetImageButton();

		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step 15: Verify \"" + uploadedImageName + "\" post image is updated.");
		verifyTrue(adminPostNewPage.isImageUploaded(uploadedImageName));
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step 16: Click on 'Publish' and 'Pre-publish' buttons.");
		adminPostNewPage.clickOnPublishOrUpdateButton();
		adminPostNewPage.clickOnPrePublishButton();
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step 17: Verify 'Post published.' and '" + postTitle + " is now live.' messages are displayed.");
		verifyTrue(adminPostNewPage.isPostPublishedOrUpdatedMessageDisplayed("Post published."));
		verifyEquals(adminPostNewPage.getPostNowLiveMessage(), postTitle + " is now live.");
		publishedDate = adminPostNewPage.getCurrentDate();
	}
	
	@Test
	public void Post_02_Search_And_View_Post(Method method) {
		WpExtentTestManagerV5.startTest(method.getName() + " - " + browserName, "Search and view newly created post on Admin and User sites");

		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step 01: Click on WordPress logo and 'All Posts' link to go to Admin ALL POSTS page.");
		adminPostNewPage.clickOnWordpressLogo();
		adminPostAllPage = adminPostNewPage.clickOnAllPostsLink();
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step 02: Enter \"" + postTitle + "\" into Search field and click on 'Search Posts' button.");
		adminPostAllPage.inputIntoSearchTextbox(postTitle);
		adminPostAllPage.clickOnSearchPostsButton();
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step 03: Verify post Title, Author, Category, and Tag are displayed.");
		verifyTrue(adminPostAllPage.isPostInfoResultTableDisplayed("Title", postTitle));
		verifyTrue(adminPostAllPage.isPostInfoResultTableDisplayed("Author", authorName));
		verifyTrue(adminPostAllPage.isPostInfoResultTableDisplayed("Categories", postCategory));
		verifyTrue(adminPostAllPage.isPostInfoResultTableDisplayed("Tags", postTag));
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step 04: Open User HOME page.");
		userHomePage = adminPostAllPage.openUserSite(driver, userUrl);
		userHomePage.clickOnAcceptCookiesButton();
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step 05: Verify post Title, Image, Category, and Published Date are displayed.");
		verifyTrue(userHomePage.isPostTitleDisplayed(postTitle));
		verifyTrue(userHomePage.isPostImageDisplayed(postTitle, uploadedImageName));
		verifyTrue(userHomePage.isPostCategoryDisplayed(postTitle, postCategory));
		verifyTrue(userHomePage.isPostPublishedDateDisplayed(postTitle, publishedDate));
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step 06: Click to expand 'Search' field and enter \"" + postTitle + "\".");
		userHomePage.clickOnSearchToggle();
		userHomePage.inputIntoSearchTextbox(postTitle);
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step 07: Click on Search button to go to User SEARCH page.");
		userSearchPage = userHomePage.clickOnSearchButton();

		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step 08: Verify '1 Post' title is displayed.");
		verifyTrue(userSearchPage.isOnePostMessageDisplayed("1 Post"));

		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step 09: Verify post Title, Image, Category, and Published Date are displayed.");
		verifyTrue(userSearchPage.isPostOrPageTitleDisplayed(postTitle));
		verifyTrue(userSearchPage.isPostOrPageImageDisplayed(postTitle, uploadedImageName));
		verifyTrue(userSearchPage.isPostCategoryDisplayed(postTitle, postCategory));
		verifyTrue(userSearchPage.isPostOrPagePublishedDateDisplayed(postTitle, publishedDate));
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step 10: Click on post Title link to go to User POST DETAILS page.");
		userPostDetailsPage = userSearchPage.clickOnPostTitleLink(postTitle);
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step 11: Verify post Title, Image, Category, and Published Date are displayed.");
		verifyTrue(userPostDetailsPage.isPostTitleDisplayed(postTitle));
		verifyTrue(userPostDetailsPage.isPostImageDisplayed(uploadedImageName));
		verifyTrue(userPostDetailsPage.isPostCategoryDisplayed(postCategory));
		verifyTrue(userPostDetailsPage.isPostPublishedDateDisplayed(publishedDate));
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step 12: Verify post Body, Author, Tag, and Comment box are displayed.");
		verifyTrue(userPostDetailsPage.isPostBodyDisplayed(postBody));
		verifyTrue(userPostDetailsPage.isPostAuthorDisplayed(authorName));
		verifyTrue(userPostDetailsPage.isPostTagDisplayed(postTag));
		verifyTrue(userPostDetailsPage.isPostCommentTextareaDisplayed());
	}
	
	@Test
	public void Post_03_Edit_Post(Method method) {
		WpExtentTestManagerV5.startTest(method.getName() + " - " + browserName, "Edit post on Admin site");

		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step 01: Open Admin DASHBOARD page and click on 'Posts' menu to go to Admin ALL POSTS page.");
		adminDashboardPage = userPostDetailsPage.openAdminSite(driver, adminUrl);		
		adminPostAllPage = adminDashboardPage.clickOnPostMenu();
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step 02: Enter \"" + postTitle + "\" into Search field and click on 'Search Posts' button.");
		adminPostAllPage.inputIntoSearchTextbox(postTitle);		
		adminPostAllPage.clickOnSearchPostsButton();
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step 03: Click on \"" + postTitle + "\" link to go to Admin EDIT POST page.");
		adminPostNewPage = adminPostAllPage.clickOnPostTitleLink("Title", postTitle);

		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step 04: Enter \"" + editTitle + "\" into post Title.");
		adminPostNewPage.inputIntoPostTitle(editTitle);
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step 05: Enter \"" + editBody + "\" into post Body.");
		adminPostNewPage.inputIntoEditPostBody(editBody);
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step 06: Open 'Post' settings sidebar.");
		adminPostNewPage.clickOnPostSidebar();
				
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step 07: Expand 'Categories' panel and deselect \"" + postCategory + "\".");
		adminPostNewPage.clickOnPanelByText("Categories");
		adminPostNewPage.uncheckCheckboxByLabel(postCategory);
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step 08: Enter \"" + editCategory + "\" into 'Search Categories' field and select it.");
		adminPostNewPage.inputIntoSearchCategoryTextbox(editCategory);
		adminPostNewPage.checkCheckboxByLabel(editCategory);
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step 09: Expand 'Tags' panel.");
		adminPostNewPage.clickOnPanelByText("Tags");
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step 10: Clear \"" + postTag + "\" and enter \"" + editTag + "\" into 'Add New Tag' field.");
		adminPostNewPage.clickOnRemoveTagButton(postTag);
		adminPostNewPage.inputIntoAddNewTagTextbox(editTag);
		verifyTrue(adminPostNewPage.isRemoveTagButtonDisplayed(editTag));

		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step 11: Expand 'Featured image' panel.");
		adminPostNewPage.clickOnPanelByText("Featured image");
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step 12: Open 'Replace Image' menu and click on 'Media Library' item.");
		adminPostNewPage.clickOnImageMenu("Replace Image");
		adminPostNewPage.clickOnMediaLibraryItem();
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step 13: Select \"" + uploadedImageName + "\" and click on 'Delete permanently' button.");
		adminPostNewPage.clickOnDeleteImageButton();
		adminPostNewPage.acceptAlert(driver);
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step 14: Enter \"" + editImage + "\" into 'Search' field and select it.");
		adminPostNewPage.inputIntoSearchImageTextbox(editImage);
		adminPostNewPage.selectPostImage(editImage);
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step 15: Click on 'Set featured image' button.");
		adminPostNewPage.clickOnSetImageButton();
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step 16: Verify \"" + editImage + ".jpg\" post image is updated.");
		verifyTrue(adminPostNewPage.isImageUploaded(editImage));
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step 17: Expand 'Discussion' panel.");
		adminPostNewPage.clickOnPanelByText("Discussion");

		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step 18: Deselect 'Allow comments' checkbox.");
		adminPostNewPage.uncheckCheckboxByLabel("Allow comments");
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step 19: Click on 'Update' button.");
		adminPostNewPage.clickOnPublishOrUpdateButton();
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step 20: Verify 'Post updated.' message is displayed.");
		verifyTrue(adminPostNewPage.isPostPublishedOrUpdatedMessageDisplayed("Post updated."));
	}
	
	@Test
	public void Post_04_Search_And_View_Post_After_Editing(Method method) {
		WpExtentTestManagerV5.startTest(method.getName() + " - " + browserName, "Search and view after editing post on Admin and User sites");
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step 01: Click on WordPress logo and 'All Posts' link to go to Admin ALL POSTS page.");
		adminPostNewPage.clickOnWordpressLogo();
		adminPostAllPage = adminPostNewPage.clickOnAllPostsLink();
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step 02: Enter \"" + editTitle + "\" into Search field and click on 'Search Posts' button.");
		adminPostAllPage.inputIntoSearchTextbox(editTitle);
		adminPostAllPage.clickOnSearchPostsButton();
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step 03: Verify post Title, Author, Category, and Tag are displayed.");
		verifyTrue(adminPostAllPage.isPostInfoResultTableDisplayed("Title", editTitle));
		verifyTrue(adminPostAllPage.isPostInfoResultTableDisplayed("Author", authorName));
		verifyTrue(adminPostAllPage.isPostInfoResultTableDisplayed("Categories", editCategory));
		verifyTrue(adminPostAllPage.isPostInfoResultTableDisplayed("Tags", editTag));
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step 04: Open User HOME page.");
		userHomePage = adminPostAllPage.openUserSite(driver, userUrl);
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step 05: Verify post Title, Image, Category, and Published Date are displayed.");
		verifyTrue(userHomePage.isPostTitleDisplayed(editTitle));
		verifyTrue(userHomePage.isPostImageDisplayed(editTitle, editImage));
		verifyTrue(userHomePage.isPostCategoryDisplayed(editTitle, editCategory));
		verifyTrue(userHomePage.isPostPublishedDateDisplayed(editTitle, publishedDate));
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step 06: Click to expand 'Search' field and enter \"" + editTitle + "\".");
		userHomePage.clickOnSearchToggle();
		userHomePage.inputIntoSearchTextbox(editTitle);
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step 07: Click on Search button to go to User SEARCH page.");
		userSearchPage = userHomePage.clickOnSearchButton();

		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step 08: Verify '1 Post' title is displayed.");
		verifyTrue(userSearchPage.isOnePostMessageDisplayed("1 Post"));

		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step 09: Verify post Title, Image, Category, and Published Date are displayed.");
		verifyTrue(userSearchPage.isPostOrPageTitleDisplayed(editTitle));
		verifyTrue(userSearchPage.isPostOrPageImageDisplayed(editTitle, editImage));
		verifyTrue(userSearchPage.isPostCategoryDisplayed(editTitle, editCategory));
		verifyTrue(userSearchPage.isPostOrPagePublishedDateDisplayed(editTitle, publishedDate));
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step 10: Click on post Title link to go to User POST DETAILS page.");
		userPostDetailsPage = userSearchPage.clickOnPostTitleLink(editTitle);
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step 11: Verify post Title, Image, Category, and Published Date are displayed.");
		verifyTrue(userPostDetailsPage.isPostTitleDisplayed(editTitle));
		verifyTrue(userPostDetailsPage.isPostImageDisplayed(editImage));
		verifyTrue(userPostDetailsPage.isPostCategoryDisplayed(editCategory));
		verifyTrue(userPostDetailsPage.isPostPublishedDateDisplayed(publishedDate));
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step 12: Verify post Body, Author, and Tag are displayed.");
		verifyTrue(userPostDetailsPage.isPostBodyDisplayed(editBody));
		verifyTrue(userPostDetailsPage.isPostAuthorDisplayed(authorName));
		verifyTrue(userPostDetailsPage.isPostTagDisplayed(editTag));
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step 13: Verify post Comment box is undisplayed.");
		verifyTrue(userPostDetailsPage.isPostCommentTextareaUndisplayed());
	}
	
	@Test
	public void Post_05_Delete_Post(Method method) {
		WpExtentTestManagerV5.startTest(method.getName() + " - " + browserName, "Delete post on Admin site");

		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step 01: Open Admin DASHBOARD page and click on 'Posts' menu to go to Admin ALL POSTS page.");
		adminDashboardPage = userPostDetailsPage.openAdminSite(driver, adminUrl);		
		adminPostAllPage = adminDashboardPage.clickOnPostMenu();
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step 02: Enter \"" + editTitle + "\" into Search field and click on 'Search Posts' button.");
		adminPostAllPage.inputIntoSearchTextbox(editTitle);		
		adminPostAllPage.clickOnSearchPostsButton();
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step 03: Select \"" + editTitle + "\" checkbox.");
		adminPostAllPage.checkPostTitleCheckbox(editTitle);
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step 04: Select 'Move to Trash' item from 'Bulk actions' dropdown.");
		adminPostAllPage.selectItemFromBulkActionsDropdown("Move to Trash");
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step 05: Click on 'Apply' button.");
		adminPostAllPage.clickOnApplyButton();
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step 06: Verify '1 post moved to the Trash.' message and 'No posts found.' are displayed.");
		verifyTrue(adminPostAllPage.isDeletePostMessageDisplayed("1 post moved to the Trash."));
		verifyTrue(adminPostAllPage.isNoPostsFoundMessageDisplayed("No posts found."));

		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step 07: Click on 'Trash' tab.");
		adminPostAllPage.clickOnTrashTab();
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step 08: Enter \"" + editTitle + "\" into Search field and click on 'Search Posts' button.");
		adminPostAllPage.inputIntoSearchTextbox(editTitle);		
		adminPostAllPage.clickOnSearchPostsButton();
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step 09: Select \"" + editTitle + "\" checkbox.");
		adminPostAllPage.checkPostTitleCheckbox(editTitle);
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step 10: Select 'Delete permanently' item from 'Bulk actions' dropdown.");
		adminPostAllPage.selectItemFromBulkActionsDropdown("Delete permanently");
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step 11: Click on 'Apply' button.");
		adminPostAllPage.clickOnApplyButton();
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step 12: Verify '1 post permanently deleted.' message and 'No posts found in Trash.' are displayed.");
		verifyTrue(adminPostAllPage.isDeletePostMessageDisplayed("1 post permanently deleted."));
		verifyTrue(adminPostAllPage.isNoPostsFoundMessageDisplayed("No posts found in Trash."));

		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step 13: Open User HOME page.");
		userHomePage = adminPostAllPage.openUserSite(driver, userUrl);
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step 14: Click to expand 'Search' field and enter \"" + editTitle + "\".");
		userHomePage.clickOnSearchToggle();
		userHomePage.inputIntoSearchTextbox(editTitle);
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step 15: Click on Search button to go to User SEARCH page.");
		userSearchPage = userHomePage.clickOnSearchButton();

		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step 16: Verify 'It seems we can't find what you’re looking for.' message is displayed.");
		verifyTrue(userSearchPage.isSearchResultsMessageDisplayed());
	}

	@AfterClass(alwaysRun = true)
	public void afterClass() {
		closeBrowserAndDriver();
	}
	
	String adminUrl, userUrl, browserName, publishedDate, uploadedImageName;
	int randomNumber2 = Demo_01_Login.randomNumber1;
	String authorName = "Automation FC";
	String postTitle = "[Annie]_NewPostTitle_" + randomNumber2;
	String postBody = "Coding Demo: post body " + randomNumber2;
	String postCategory = "[Annie] New Category 2022";
	String postTag = "annie_tag_" + randomNumber2; 
	String postImage = "annie_sunflower.jpg";
	String editTitle = "[Annie]_EditPostTitle_" + randomNumber2;
	String editBody = "Edit post body " + randomNumber2;
	String editCategory = "[Annie] Edit Category 2022";
	String editTag = "annie_edit_" + randomNumber2; 
	String editImage = "annie_cake";
	
	WebDriver driver;
	WpAdminLoginPO adminLoginPage;
	WpAdminDashboardPO adminDashboardPage;
	WpAdminPostAllPO adminPostAllPage;
	WpAdminPostNewPO adminPostNewPage;
	WpUserHomePO userHomePage;
	WpUserSearchPO userSearchPage;
	WpUserPostDetailPO userPostDetailsPage;
}