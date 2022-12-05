package pageUIs.wordPress;

public class WpAdminPageAllPUI {

	public static final String ADD_NEW_PAGE_BUTTON = "//a[text()='Add new page']";
	public static final String OPEN_SEARCH_ICON = "//div[@aria-label='Open Search']/*";
	public static final String CLOSE_SEARCH_ICON = "//div[@aria-label='Close Search']/*";
	public static final String SEARCH_TEXTBOX = "//input[@aria-label='Search']";
	public static final String PAGE_TITLE_TEXT = "//a[@class='page__title' and text()='%s']";
	public static final String PUBLISHED_DATE_BY_TITLE = "//a[text()='%s']/parent::div//div[contains(@title, '%s')]";
	public static final String ELLIPSIS_MENU_BY_TITLE = "//a[text()='%s']/parent::div/following-sibling::span/button[@title='Toggle menu']";
	public static final String DELETE_ELLIPSIS_MENU_BY_TITLE = "//span[text()='%s']/parent::div/following-sibling::span/button[@title='Toggle menu']";
	public static final String ELLIPSIS_MENU_ITEM = "//div[@class='popover__menu']//button[text()='%s']";
	public static final String DELETE_MESSAGE = "//span[@class='notice__content']/span[text()='%s']";
	public static final String NO_RESULTS_MESSAGE = "//div[@class='no-results']/span";
	public static final String WEB_PREVIEW_DROPDOWN = "//div[contains(@class, 'web-preview__device-switcher')]";
	public static final String PREVIEW_OPTION = "//a[@data-bold-text='%s']";
	public static final String CLOSE_PREVIEW_BUTTON = "//button[@aria-label='Close preview']";
	public static final String VISIT_SITE_BUTTON = "//a[text()='Visit site']";
	public static final String WEB_PREVIEW_IFRAME = "//iframe[@class='web-preview__frame']";
	public static final String PAGE_TITLE_PREVIEW = "//h1[@class='post-title' and text()='%s']";
	public static final String PAGE_IMAGE_PREVIEW = "//figure[@class='post-image']//img[contains(@src, '%s')]";
	public static final String PAGE_BODY_PREVIEW = "//div[@class='post-content']/p[contains(string(),'%s')]";
	public static final String COMMENT_TEXTAREA_PREVIEW = "//textarea[@id='comment']";
	public static final String COMMENT_CONTENT = "//div[@class='comment-content']/p";

}
