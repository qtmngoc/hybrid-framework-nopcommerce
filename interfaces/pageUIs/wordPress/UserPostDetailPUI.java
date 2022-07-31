package pageUIs.wordPress;

public class UserPostDetailPUI {

	public static final String POST_IMAGE = "//figure[contains(@class, 'post-image')]/img[contains(@src, '%s')]";
	public static final String POST_AUTHOR_TEXT = "//span[@class='post-meta-author']/a[text()='%s']";
	public static final String POST_BODY_TEXT = "//div[@class='post-content']/p[text()='%s']";
	public static final String POST_CATEGORY_TEXT = "//p[@class='post-categories']/a[text()='%s']";
	public static final String POST_EDIT_LINK = "//a[@class='post-edit-link']";
	public static final String POST_TAG_TEXT = "//div[@class='post-tags']/a[text()='%s']";
	public static final String POST_TITLE_TEXT = "//h1[@class='post-title' and text()='%s']";
	public static final String PUBLISHED_DATE_TEXT = "//span[@class='post-meta-date']/a[text()='%s']";
	public static final String COMMENT_TEXTAREA = "//textarea[@id='comment']";
	
}
