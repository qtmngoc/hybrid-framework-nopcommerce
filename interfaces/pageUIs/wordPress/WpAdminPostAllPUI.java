package pageUIs.wordPress;

public class WpAdminPostAllPUI {
	
	public static final String ADD_NEW_POST_BUTTON = "//a[text()='Add new post']";
	public static final String OPEN_SEARCH_ICON = "//div[@aria-label='Open Search']/*";
	public static final String CLOSE_SEARCH_ICON = "//div[@aria-label='Close Search']/*";
	public static final String SEARCH_TEXTBOX = "//input[@aria-label='Search']";
	public static final String POST_TITLE_LINK = "//h1[@class='post-item__title']/a[text()='%s']";
	public static final String STICKY_TAG_BY_TITLE = "//a[text()='%s']/ancestor::div[@class='post-item__detail']//span[contains(@class, 'is-sticky')]";
	public static final String PUBLISHED_DATE_BY_TITLE = "//a[text()='%s']/ancestor::div[@class='post-item__detail']//time[contains(@datetime, '%s')]";
	public static final String LIKED_BY_TITLE = "//a[text()='%s']/ancestor::div[@class='post-item__detail']//a[text()='1 Like']";
	public static final String IMG_BY_TITLE = "//a[text()='%s']/ancestor::div[@class='post-item__panel']//img[contains(@src, '%s')]";
	public static final String ELLIPSIS_MENU_BY_TITLE = "//a[text()='%s']/ancestor::div[@class='post-item__panel']//button[@title='Toggle menu']";
	public static final String DELETE_ELLIPSIS_MENU_BY_TITLE = "//span[text()='%s']/ancestor::div[@class='post-item__panel']//button[@title='Toggle menu']";
	public static final String ELLIPSIS_MENU_ITEM = "//div[@class='popover__menu']//a[text()='%s']";
	public static final String DELETE_PERMANENTLY_ITEM = "//div[@class='popover__menu']//button[text()='Delete Permanently']";
	public static final String DELETE_MESSAGE = "//span[@class='notice__content']/span[text()='%s']";
	public static final String NO_RESULTS_MESSAGE = "//h2[text()='No posts found.']";
	public static final String WEB_PREVIEW_DROPDOWN = "//div[contains(@class, 'web-preview__device-switcher')]";
	public static final String PREVIEW_OPTION = "//a[@data-bold-text='%s']";
	public static final String CLOSE_PREVIEW_BUTTON = "//button[@aria-label='Close preview']";
	public static final String WEB_PREVIEW_IFRAME = "//iframe[@class='web-preview__frame']";
	public static final String POST_TITLE_PREVIEW = "//h1[@class='post-title' and text()='%s']";
	public static final String POST_IMAGE_PREVIEW = "//img[contains(@src, '%s')]";
	public static final String POST_BODY_PREVIEW = "//div[@class='post-content']/p[contains(string(),'%s')]";
	public static final String POST_AUTHOR_PREVIEW = "//span[@class='post-meta-author']/a[text()='%s']";
	public static final String POST_CATEGORY_PREVIEW = "//p[@class='post-categories']/a[text()='%s']";
	public static final String POST_TAG_PREVIEW = "//div[@class='post-tags']/a[text()='%s']";
	public static final String PUBLISHED_DATE_PREVIEW = "//span[@class='post-meta-date']/a[text()='%s']";
	public static final String COMMENT_TEXTAREA_PREVIEW = "//textarea[@id='comment']";
	public static final String LIKE_BUTTON_PREVIEW = "//div[@class='wpl-button like']/a";
	
// -------------------------------------------------------------------------------------------------------------------------
	public static final String ADD_NEW_BUTTON = "//span[@id='split-page-title-action']//a[text()='Add New']";
	public static final String TRASH_TAB = "//ul[@class='subsubsub']/li[@class='trash']";
	public static final String APPLY_BUTTON = "//input[@id='doaction']";
	public static final String SEARCH_POSTS_BUTTON = "//input[@id='search-submit']";
	public static final String ROW_CHECKBOX_BY_POST_TITLE = "//label[contains(text(),'%s')]//following-sibling::input";
	public static final String BULK_ACTIONS_DROPDOWN = "//select[@id='bulk-action-selector-top']";
	public static final String DELETE_POST_MESSAGE = "//div[@id='message']/p[contains(text(),'%s')]";
	public static final String NO_POST_FOUND_MESSAGE = "//table//tr[@class='no-items']/td[text()='%s']";
	public static final String POST_INFO_TEXT_BY_TITLE_AND_COLUMN_NAME = "//a[text()='%s']/ancestor::tr/td[@data-colname='%s']//*[text()='%s']";
	public static final String POST_IMAGE_BY_TITLE = "//a[text()='%s']/ancestor::tr/td[@data-colname='Thumbnail']/img[contains(@src, '%s')]";
	public static final String POST_PUBLISHED_DATE_BY_TITLE = "//a[text()='%s']/ancestor::tr/td[@data-colname='Date']";
	public static final String SEARCH_TEXTBOX_1 = "//input[@id='post-search-input']";
	public static final String NUMBER_SEARCH_RESULTS = "//div[@class='tablenav top']//span[@class='displaying-num']";
	public static final String POST_STICKY_BY_TITLE = "//a[text()='%s']/following-sibling::*[text()='Sticky']";
	
}
