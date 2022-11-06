package pageUIs.wordPress;

public class WpAdminPageNewPUI {
	
	public static final String PAGE_PATTERN_IFRAME = "//iframe[@class='is-loaded']";	
	public static final String BLANK_PAGE_BUTTON = "//button[text()='Blank page']";	
	public static final String CLOSE_DIALOG_BUTTON = "//button[@aria-label='Close dialog']";	
	public static final String TITLE_TEXTBOX = "//h1[@aria-label='Add title']";
	public static final String BODY_BUTTON = "//p[@aria-label='Add default block']";
	public static final String BODY_TEXTAREA = "//p[contains(@class, 'block-editor-rich-text')]";
	public static final String PAGE_SIDEBAR = "//button[@data-label='Page']";
	public static final String PANEL_BY_TEXT = "//button[text()='%s']";
	public static final String IMAGE_MENU = "//div[@class='editor-post-featured-image']//button[text()='%s']";
	public static final String MEDIA_LIBRARY_ITEM = "//span[text()='Media Library']/parent::button";
	public static final String OPEN_SEARCH_IMAGE_ICON = "//div[@aria-label='Open Search']/*";
	public static final String CLOSE_SEARCH_IMAGE_ICON = "//div[@aria-label='Close Search']/*";
	public static final String SEARCH_IMAGE_TEXTBOX = "//input[@aria-label='Search']";
	public static final String ADD_NEW_IMAGE = "//form[contains(@class, 'upload-button')]//input[@type='file']";
	public static final String IMAGE_BUTTON = "//img[contains(@src, '%s')]/ancestor::button";
	public static final String DELETE_IMAGE_ICON = "//button[@data-e2e-button='delete']";
	public static final String CONFIRM_DELETE_IMAGE_BUTTON = "//div[@class='dialog__action-buttons']//button[@data-e2e-button='accept']";
	public static final String INSERT_IMAGE_BUTTON = "//span[text()='Insert']/parent::button";
	public static final String UPLOADED_IMAGE = "//div[@class='editor-post-featured-image']//img[contains(@src, '%s')]";
	public static final String UPLOADED_IMAGE_NAME = "//button[contains(@class, 'media-library__list-item is-selected')]/figure";
	public static final String ALLOW_COMMENTS_CHECKBOX = "//label[text()='Allow comments']/preceding-sibling::span/input";
	public static final String PUBLISH_OR_UPDATE_BUTTON = "//div[@class='edit-post-header']//button[contains(@class, 'post-publish')]";
	public static final String PRE_PUBLISH_BUTTON = "//div[@class='editor-post-publish-panel__header-publish-button']//button";
	public static final String PUBLISHED_OR_UPDATED_MESSAGE = "//div[@class='components-snackbar__content' and text()='%s']";
	public static final String PAGE_NOW_LIVE_MESSAGE = "//div[contains(@class, 'postpublish-header')]";
	public static final String WORDPRESS_LOGO = "//button[@aria-label='Block editor sidebar']";
	public static final String ALL_PAGES_LINK = "//div[@aria-label='Block editor sidebar']//a[text()='View Pages']";

}

