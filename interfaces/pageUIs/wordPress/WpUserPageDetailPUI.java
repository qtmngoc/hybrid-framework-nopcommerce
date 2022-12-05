package pageUIs.wordPress;

public class WpUserPageDetailPUI {
	
	public static final String PAGE_TITLE = "//h1[@class='post-title' and text()='%s']";
	public static final String PAGE_IMAGE = "//figure[@class='post-image']//img[contains(@src, '%s')]";
	public static final String PAGE_BODY = "//div[@class='post-content']/p[contains(string(),'%s')]";
	public static final String HOME_PAGE_LINK = "//h2[@class='site-title']/a";
	public static final String COMMENT_TEXTAREA = "//textarea[@id='comment']";
	public static final String POST_COMMENT_BUTTON = "//input[@id='comment-submit']";
	public static final String COMMENT_CONTENT = "//div[@class='comment-content']/p";
	public static final String LIKE_IFRAME = "//iframe[@title='Like or Reblog']";
	public static final String LIKE_BUTTON = "//div[@class='wpl-button like']/a";
	public static final String YOU_LIKE_THIS_MESSAGE = "//div[@class='wpl-count sd-like-count']/*[text()='You like this.']";
	public static final String ONE_THOUGHT_MESSAGE = "//h3[@class='comments-title']";
	public static final String COMMENTS_CLOSED_MESSAGE = "//p[@class='no-comments']";
	
}
