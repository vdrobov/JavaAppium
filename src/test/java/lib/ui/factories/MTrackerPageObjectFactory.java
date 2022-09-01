package lib.ui.factories;

import lib.Platform;
import lib.ui.ArticlePageObject;
import lib.ui.MTrackerPageObject;
import lib.ui.android.AndroidArticlePageObject;
import lib.ui.android.AndroidMTrackerPageObject;
import lib.ui.ios.iOSArticlePageObject;
import lib.ui.mobile_web.MWArticlePageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MTrackerPageObjectFactory {
    public static MTrackerPageObject get(RemoteWebDriver driver)
    {
            return new AndroidMTrackerPageObject(driver);
    }
}
