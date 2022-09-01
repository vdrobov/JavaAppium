package lib.ui.android;

import lib.ui.ArticlePageObject;
import lib.ui.MTrackerPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class AndroidMTrackerPageObject extends MTrackerPageObject
{
    static {
        DRIVE_RECORDING_TITLE = "xpath://*[@text='Drive Recording']";
        DEDUCTION_AMOUNT = "id:com.stride.driverecordingkotlin:id/deduction_amount";
        TIME_VALUE = "id:com.stride.driverecordingkotlin:id/time_value";
        DISTANCE_VALUE = "id:com.stride.driverecordingkotlin:id/distance_value";
        CURRENT_POSITION_VALUE = "id:com.stride.driverecordingkotlin:id/current_position_value";
        START_STOP_TRIP_BUTTON = "id:com.stride.driverecordingkotlin:id/trip_button";
        NOTES_VIEW = "id:com.stride.driverecordingkotlin:id/notes_view";
        SAVE_NOTES_BUTTON = "id:com.stride.driverecordingkotlin:id/save_button";
    }

    public AndroidMTrackerPageObject(RemoteWebDriver driver)
    {
        super(driver);
    }
}
