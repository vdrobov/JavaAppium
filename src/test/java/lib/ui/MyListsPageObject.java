package lib.ui;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.LinkedList;
import java.util.List;

abstract public class MyListsPageObject extends MainPageObject{

    protected static String
            FOLDER_BY_NAME_TPL,
            ARTICLE_BY_TITLE_TPL,
            HIDDEN_ELEMENT,
            REMOVE_FROM_SAVED_BUTTON,
            ARTICLES_LIST;

    /* TEMPLATES METHODS */
    private static String getFolderXpathByName(String name_of_folder)
    {
        return FOLDER_BY_NAME_TPL.replace("{FOLDER_NAME}", name_of_folder);
    }

    private static String getSavedArticleXpathByTitle(String article_title)
    {
        return ARTICLE_BY_TITLE_TPL.replace("{TITLE}", article_title);
    }

    private static String getRemoveButtonByTitle(String article_title)
    {
        return REMOVE_FROM_SAVED_BUTTON.replace("{TITLE}", article_title);
    }
    /* TEMPLATES METHODS */

    public MyListsPageObject(RemoteWebDriver driver)
    {
        super(driver);
    }

    public void openFolderByName(String name_of_folder)
    {
        String folder_name_xpath = getFolderXpathByName(name_of_folder);
        this.waitForElementPresent(
                folder_name_xpath,
                "Cannot find folder by name " + name_of_folder,
                5
        );
        this.waitForElementAndClick(
                folder_name_xpath,
                "Cannot click folder by name " + name_of_folder,
                5
        );
    }

    public void waitForArticleToAppearByTitle(String article_title)
    {
        String article_xpath = getSavedArticleXpathByTitle(article_title);
        this.waitForElementPresent(article_xpath, "Cannot find saved article by title " + article_title, 15);
    }

    public void waitForArticleToDisappearByTitle(String article_title)
    {
        String article_xpath = getSavedArticleXpathByTitle(article_title);
        this.waitForElementNotPresent(article_xpath, "Saved article still present with title " + article_title, 15);
    }

    public void clickByArticleByTitle(String article_title)
    {
        String article_xpath = getSavedArticleXpathByTitle(article_title);
        this.waitForElementAndClick(article_xpath, "Cannot click saved article by title " + article_title, 15);
    }

    public void swipeByArticleToDelete(String article_title)
    {
        this.waitForArticleToAppearByTitle(article_title);
        String article_xpath = getSavedArticleXpathByTitle(article_title);
        if (Platform.getInstance().isIOS() || Platform.getInstance().isAndroid()) {
            this.swipeElementToLeft(
                    article_xpath,
                    "Cannot find saved article"
            );
        } else {
            String remove_locator = getRemoveButtonByTitle(article_title);
            this.waitForElementPresent(
                    HIDDEN_ELEMENT,
                    "Cannot click button to remove article from saved",
                    10
            );
            this.waitForElementNotPresent(
                    HIDDEN_ELEMENT,
                    "Cannot click button to remove article from saved",
                    10
            );
            this.waitForElementPresent(
                    remove_locator,
                    "Cannot click button to remove article from saved",
                    10
            );
            this.waitForElementAndClick(
                    remove_locator,
                    "Cannot click button to remove article from saved",
                    10
            );
        }
        if (Platform.getInstance().isIOS()){
        this.clickElementToTheRightUpperCorner(article_xpath, "Cannot find saved article");
        }

        if (Platform.getInstance().isMW()){
            driver.navigate().refresh();
        }

        this.waitForArticleToDisappearByTitle(article_title);
    }

    public List<String> getArticlesList()
    {
        List<String> titles = new LinkedList<>();
        By by = getLocatorByString(ARTICLES_LIST);
        List<WebElement> elements = driver.findElements(by);
        for(WebElement element : elements) {
            titles.add(element.getAttribute("name"));
        }
        return titles;
    }
}
