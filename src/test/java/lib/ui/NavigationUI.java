package lib.ui;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import org.openqa.selenium.remote.RemoteWebDriver;

abstract public class NavigationUI extends MainPageObject{

    protected static String
            MY_LISTS_LINK,
            OPEN_NAVIGATION;

    public NavigationUI(RemoteWebDriver driver)
    {
        super(driver);
    }

    public void openNavigation()
    {
        if (Platform.getInstance().isMW()){
            this.waitForElementAndClick(OPEN_NAVIGATION,"Cannot find and click open navigation button",5);
        } else {
            System.out.println("Method openNavigation() does nothing for platform " + Platform.getInstance().getPlatformVar());
        }
    }

    public void clickMyLists()
    {
        if (Platform.getInstance().isMW()){
            this.tryClickElementWithFewAttempts(
                    MY_LISTS_LINK,
                    "Cannot click 'My lists' button in few attempts",
                    5);
        } else {
            this.waitForElementPresent(
                    MY_LISTS_LINK,
                    "Cant open 'my lists' screen ",
                    15
            );

            this.waitForElementAndClick(
                    MY_LISTS_LINK,
                    "Cannot find navigation button to My list",
                    5
            );
        }
    }
}
