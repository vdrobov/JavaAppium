package lib.ui.mobile_web;

import lib.ui.MyListsPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWMyListsPageObject extends MyListsPageObject {

    static {
        ARTICLE_BY_TITLE_TPL = "xpath://h3[contains(text(),'{TITLE}')]";
        REMOVE_FROM_SAVED_BUTTON = "xpath://ul[contains(@class, 'page-summary-list')]//*[@title='Stop watching']";
//        NOT_DELETED_ARTICLE = "xpath://a[href='/wiki/Cyprus']";
        HIDDEN_ELEMENT = "xpath://span[@class='modified-enhancement']";
        ARTICLES_LIST = "xpath://ul[contains(@class, 'page-summary-list')]";
    }

    public MWMyListsPageObject(RemoteWebDriver driver) {
        super(driver);
    }
}
