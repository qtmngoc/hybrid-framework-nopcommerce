package pageUIs.wordPress;

public class AdminPostNewPUI {

	public static final String TITLE_TEXTBOX = "//h1[contains(@class, 'editor-post-title')]";
	public static final String BODY_BUTTON = "//p[contains(@class, 'block-editor-default')]";
	public static final String BODY_TEXTBOX = "//p[contains(@class, 'block-editor-rich-text')]";
	public static final String PUBLISH_BUTTON = "//div[@aria-label='Editor top bar']//button[contains(@class, 'post-publish')]";
	public static final String PRE_PUBLISH_BUTTON = "//div[@aria-label='Editor publish']//button[contains(@class, 'post-publish')]";
	public static final String PUBLISHED_MESSAGE = "//div[@class='components-snackbar__content' and text()='%s']";
	public static final String WORDPRESS_BUTTON = "//button[@aria-label='Block editor sidebar']";
	public static final String ALL_POSTS_LINK = "//div[@aria-label='Block editor sidebar']//a[text()='All Posts']";
	
}

