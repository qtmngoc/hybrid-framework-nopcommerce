package pageUIs.wordPress;

public class WpUserHomePUI {

	public static final String ACCEPT_COOKIE_BUTTON = "//div[@id='eu-cookie-law']//input";
	public static final String SEARCH_BUTTON = "//button[@class='search-button']";
	public static final String POST_IMAGE_BY_POST_TITLE = "//a[text()='%s']//ancestor::header/preceding-sibling::figure//img[contains(@src,'%s')]";
	public static final String POST_CATEGORY_TEXT_BY_POST_TITLE = "//a[text()='%s']/parent::h2//preceding-sibling::p[@class='post-categories']/a[text()='%s']";
	public static final String POST_META_TEXT_BY_POST_TITLE = "//a[text()='%s']/parent::h2//following-sibling::p[@class='post-meta']/a[text()='%s']";
	public static final String POST_TITLE_TEXT = "//a[text()='%s']";
	public static final String POST_STICKY_TAG_BY_TITLE = "//a[@title='Sticky post: %s']";
	public static final String SEARCH_TEXTBOX = "//input[@class='search-field']";
	public static final String SEARCH_TOGGLE = "//a[@class='search-toggle']";
	
}
