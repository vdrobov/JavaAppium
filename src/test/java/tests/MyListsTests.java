package tests;

import lib.CoreTestCase;
import lib.Platform;
import lib.ui.*;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.MyListsPageObjectFactory;
import lib.ui.factories.NavigationUIFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;

public class MyListsTests extends CoreTestCase
{
    private static final String name_of_folder = "Learning programming";
    private static final String search_line = "Java";
    private static final String
            login = "mobile_automation_test",
            password = "Qwerty123";

    @Test
    public void testSaveFirstArticleToMyList()
    {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);

        ArticlePageObject.hideBanner();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(search_line);
        SearchPageObject.clickByArticleWithSubstring("bject-oriented programming language");

        ArticlePageObject.hideBanner();
        ArticlePageObject.waitForTitleElement();
        String article_title = ArticlePageObject.getArticleTitle();

        if (Platform.getInstance().isAndroid()){
            ArticlePageObject.clickAddArticleToMyList();
            ArticlePageObject.fillMyListName(name_of_folder);
        } else {
            ArticlePageObject.hideBanner();
            ArticlePageObject.addArticlesToMySaved();
        }

        if (Platform.getInstance().isIOS()) {
            ArticlePageObject.closeSyncPopup();
        }

        if (Platform.getInstance().isMW()){
            AuthorizationPageObject Auth = new AuthorizationPageObject(driver);
            Auth.clickAuthButton();
            Auth.enterLoginData(login,password);
            Auth.submitForm();

            ArticlePageObject.waitForTitleElement();

            assertEquals(
                    "We are not on the same page after login",
                    article_title,
                    ArticlePageObject.getArticleTitle());

            ArticlePageObject.addArticlesToMySaved();
        }

        ArticlePageObject.closeArticle();

        NavigationUI NavigationUI = NavigationUIFactory.get(driver);
        NavigationUI.openNavigation();
        NavigationUI.clickMyLists();

        MyListsPageObject MyListsPageObject = MyListsPageObjectFactory.get(driver);

        if (Platform.getInstance().isAndroid()){
            MyListsPageObject.openFolderByName(name_of_folder);
        }

        MyListsPageObject.swipeByArticleToDelete(article_title);
    }

    @Test
    public void testSaveTwoArticlesToMyList()
    {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(search_line);
        SearchPageObject.clickByArticleWithSubstring("bject-oriented programming language");

        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        ArticlePageObject.waitForTitleElement();
        String article_title1 = ArticlePageObject.getArticleTitle();

        if (Platform.getInstance().isAndroid()){
            String name_of_folder = "Folder";
            ArticlePageObject.clickAddArticleToMyList();
            ArticlePageObject.fillMyListName(name_of_folder);
        } else {
            ArticlePageObject.addArticlesToMySaved();
            ArticlePageObject.closeSyncPopup();
        }

        ArticlePageObject.moveToSearchPage();

        if (Platform.getInstance().isAndroid()){
            SearchPageObject.clickByRecentSearchResultWithSubstring(search_line);
        }

        SearchPageObject.waitForSearchResult("Island of Indonesia");
        SearchPageObject.clickByArticleWithSubstring("Island of Indonesia");

        ArticlePageObject.waitForTitleElement();
        String article_title2_before_saving = ArticlePageObject.getArticleTitle();

        if (Platform.getInstance().isAndroid()){
            ArticlePageObject.clickAddArticleToMyList();
            ArticlePageObject.clickByFolderWithSubstring(name_of_folder);
        } else {
            ArticlePageObject.addArticlesToMySaved();
        }

        ArticlePageObject.closeArticle();

        NavigationUI NavigationUI = NavigationUIFactory.get(driver);
        NavigationUI.clickMyLists();

        MyListsPageObject MyListsPageObject = MyListsPageObjectFactory.get(driver);

        if (Platform.getInstance().isAndroid()){
            MyListsPageObject.openFolderByName(name_of_folder);
        }

        MyListsPageObject.swipeByArticleToDelete(article_title1);
        MyListsPageObject.waitForArticleToDisappearByTitle(article_title1);

        MyListsPageObject.clickByArticleByTitle(article_title2_before_saving);

        ArticlePageObject.waitForTitleElement();
        String article_title2_after_saving = ArticlePageObject.getArticleTitle();

        assertEquals(
                "Article title have been changed after adding to reading list",
                article_title2_before_saving,
                article_title2_after_saving
        );
    }
}
