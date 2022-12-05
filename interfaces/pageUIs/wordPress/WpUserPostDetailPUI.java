package pageUIs.wordPress;

public class WpUserPostDetailPUI {

	public static final String POST_IMAGE = "//figure[contains(@class, 'post-image')]/img[contains(@src, '%s')]";
	public static final String POST_AUTHOR_TEXT = "//span[@class='post-meta-author']/a[text()='%s']";
	public static final String POST_BODY_TEXT = "//div[@class='post-content']/p[contains(string(),'%s')]";
	public static final String POST_CATEGORY_TEXT = "//p[@class='post-categories']/a[text()='%s']";
	public static final String POST_EDIT_LINK = "//a[@class='post-edit-link']";
	public static final String POST_TAG_TEXT = "//div[@class='post-tags']/a[text()='%s']";
	public static final String POST_TITLE_TEXT = "//h1[@class='post-title' and text()='%s']";
	public static final String PUBLISHED_DATE_TEXT = "//span[@class='post-meta-date']/a[text()='%s']";
	public static final String COMMENT_TEXTAREA = "//textarea[@id='comment']";
	public static final String POST_COMMENT_BUTTON = "//input[@id='comment-submit']";
	public static final String COMMENT_CONTENT = "//div[@class='comment-content']/p";
	public static final String LIKE_IFRAME = "//iframe[@title='Like or Reblog']";
	public static final String LIKE_BUTTON = "//div[@class='wpl-button like']/a";
	public static final String YOU_LIKE_THIS_MESSAGE = "//div[@class='wpl-count sd-like-count']/*[text()='You like this.']";
	public static final String ONE_THOUGHT_MESSAGE = "//h3[@class='comments-title']";
	public static final String COMMENTS_CLOSED_MESSAGE = "//p[@class='no-comments']";
	
}
