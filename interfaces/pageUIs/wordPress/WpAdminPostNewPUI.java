package pageUIs.wordPress;

public class WpAdminPostNewPUI {

	public static final String POST_PATTERN_IFRAME = "//iframe[@class='is-loaded']";	
	public static final String ADD_NEW_CATEGORY_BUTTON = "//button[@type='submit' and text()='Add New Category']";
	public static final String BODY_BUTTON = "//p[@aria-label='Add default block']";
	public static final String DELETE_IMAGE_BUTTON = "//button[@class='button-link delete-attachment']";
	public static final String PRE_PUBLISH_BUTTON = "//div[@class='editor-post-publish-panel__header-publish-button']//button";
	public static final String PUBLISH_OR_UPDATE_BUTTON = "//div[@class='edit-post-header']//button[contains(@class, 'post-publish')]";
	public static final String REMOVE_TAG_BUTTON = "//span[contains(text(), '%s')]/parent::span/following-sibling::button";
	public static final String SET_FEATURED_IMAGE_BUTTON = "//div[@class='media-toolbar']//button[text()='Set featured image']";
	public static final String CHECKBOX_BY_LABEL = "//label[text()='%s']/preceding-sibling::span/input";
	public static final String IMAGE_CHECKBOX = "//img[contains(@src, '%s')]/ancestor::li[@role='checkbox']";
	public static final String PARENT_CATEGORY_DROPDOWN = "//label[text()='Parent Category']/parent::div/following-sibling::div/select";
	public static final String ADD_NEW_CATEGORY_FORM = "//button[@type='button' and text()='Add New Category']";
	public static final String UPLOADED_IMAGE = "//div[@class='editor-post-featured-image']//img[contains(@src, '%s')]";
	public static final String CATEGORY_ITEM = "//label[text()='Parent Category']/parent::div/following-sibling::div/select/option[contains(text(), '%s')]";
	public static final String MEDIA_LIBRARY_ITEM = "//span[text()='Media Library']/parent::button";
	public static final String ALL_POSTS_LINK = "//div[@aria-label='Block editor sidebar']//a[text()='All Posts']";
	public static final String WORDPRESS_LOGO = "//button[@aria-label='Block editor sidebar']";
	public static final String IMAGE_MENU = "//div[@class='editor-post-featured-image']//button[text()='%s']";
	public static final String PUBLISHED_OR_UPDATED_MESSAGE = "//div[@class='components-snackbar__content' and text()='%s']";
	public static final String PANEL_BY_TEXT = "//button[text()='%s']";
	public static final String UPLOAD_FILE_TAB = "//button[text()='Upload files']";
	public static final String UPLOADED_IMAGE_NAME = "//div[@class='attachment-info']//div[@class='filename']";
	public static final String BODY_TEXTAREA = "//p[contains(@class, 'block-editor-rich-text')]";
	public static final String ADD_NEW_TAG_TEXTBOX = "//label[text()='Add New Tag']/parent::div//input";
	public static final String NEW_CATEGORY_TEXTBOX = "//label[text()='New Category Name']/following-sibling::input";
	public static final String SEARCH_CATEGORY_TEXTBOX = "//label[text()='Search Categories']/following-sibling::input";
	public static final String SEARCH_IMAGE_TEXTBOX = "//input[@id='media-search-input']";
	public static final String TITLE_TEXTBOX = "//h1[@aria-label='Add title']";
	public static final String POST_SIDEBAR = "//button[@data-label='Post']";
	public static final String POST_NOW_LIVE_MESSAGE = "//div[contains(@class, 'postpublish-header')]";
	
}

