package pageUIs.wordPress;

public class WpUserSearchPUI {

	public static final String HOME_PAGE_LINK = "//h2[@class='site-title']/a";
	public static final String IMAGE_BY_TITLE = "//a[text()='%s']//ancestor::header/preceding-sibling::figure//img[contains(@src,'%s')]";
	public static final String ONE_POST_MESSAGE = "//p[text()='%s']";
	public static final String SEARCH_RESULTS_MESSAGE = "//div[@class='post-content']/p[contains(text(), 'It seems we can’t find what you’re looking for.')]";
	public static final String POST_CATEGORY_TEXT_BY_POST_TITLE = "//a[text()='%s']/parent::h2//preceding-sibling::p[@class='post-categories']/a[text()='%s']";
	public static final String META_BY_TITLE = "//a[text()='%s']/parent::h2//following-sibling::p[@class='post-meta']/a[text()='%s']";
	public static final String POST_OR_PAGE_TITLE_LINK = "//h2[@class='post-title']/a[text()='%s']";
	
}
