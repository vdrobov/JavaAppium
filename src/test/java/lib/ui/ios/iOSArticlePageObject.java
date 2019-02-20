package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.ArticlePageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class iOSArticlePageObject extends ArticlePageObject
{
    static {
        TITLE = "id:Java (programming language)";
//        TITLE_BY_SUBSTRING_TPL = "xpath://XCUIElementTypeWebView[contains(text(),'{SUBSTRING}')]";
        FOOTER_ELEMENT = "id:View article in browser";
        OPTIONS_ADD_TO_MY_LIST_BUTTON = "id:Save for later";
//        CLOSE_ARTICLE_BUTTON = "id:Back";
        CLOSE_ARTICLE_BUTTON = "xpath://XCUIElementTypeButton[@name='Wikipedia, return to Explore']";
        SEARCH_MENU_BUTTON = "id:Search Wikipedia";
        SYNC_POPUP_CLOSE_BUTTON = "id:places auth close";
    }

    public iOSArticlePageObject(RemoteWebDriver driver)
    {
        super(driver);
    }
}
