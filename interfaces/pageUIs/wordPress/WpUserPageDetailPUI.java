package pageUIs.wordPress;

public class WpUserPageDetailPUI {
	
	public static final String PAGE_TITLE = "//h1[@class='post-title' and text()='%s']";
	public static final String PAGE_IMAGE = "//a[@title='%s']/img[contains(@src, '%s')]";
	public static final String PAGE_BODY = "//div[@class='post-content']/p[contains(string(),'%s')]";
	public static final String COMMENT_TEXTAREA = "//textarea[@id='comment']";
	public static final String HOME_PAGE_LINK = "//h2[@class='site-title']/a";
	
}
