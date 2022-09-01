package lib.ui;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

abstract public class MTrackerPageObject extends MainPageObject {

    protected static String
            DRIVE_RECORDING_TITLE,
            DEDUCTION_AMOUNT,
            TIME_VALUE,
            DISTANCE_VALUE,
            CURRENT_POSITION_VALUE,
            START_STOP_TRIP_BUTTON,
            NOTES_VIEW,
            SAVE_NOTES_BUTTON;

    public MTrackerPageObject(RemoteWebDriver driver) {
        super(driver);
    }

    public WebElement checkDeductionAmountPresent() {
        return this.waitForElementPresent(
                DEDUCTION_AMOUNT,
                "Cannot find Deduction Amount on the screen");
    }

    public void validateDriveRecordingScreenElementsPresent() {
        this.waitForElementPresent(DRIVE_RECORDING_TITLE,
                "Cannot find Drive Recording title on the screen");
        this.waitForElementPresent(DEDUCTION_AMOUNT,
                "Cannot find Deduction Amount on the screen");
        this.waitForElementPresent(TIME_VALUE,
                "Cannot find Time Value on the screen");
        this.waitForElementPresent(DISTANCE_VALUE,
                "Cannot find Distance Value on the screen");
        this.waitForElementPresent(CURRENT_POSITION_VALUE,
                "Cannot find Current Position value on the screen");
        this.waitForElementPresent(START_STOP_TRIP_BUTTON,
                "Cannot find and click Start/Stop button on the screen");
        this.waitForElementPresent(NOTES_VIEW,
                "Cannot find Notes View on the screen");
        this.waitForElementPresent(SAVE_NOTES_BUTTON,
                "Cannot find Save Notes button on the screen");
    }

    public WebElement checkDistanceValuePresent() {
        return this.waitForElementPresent(DISTANCE_VALUE,
                "Cannot find Distance Value on the screen",
                10);
    }

    public float getDeductionValue() {
        WebElement deduction_value_string = checkDeductionAmountPresent();
        float deduction_value_float = Float
                .parseFloat(deduction_value_string
                        .getAttribute("text")
                        .replaceAll("[^0-9\\.]", ""));
        System.out.println("Deduction Value = " + deduction_value_float);
        return deduction_value_float;
    }

    public void clickStartStopTripButton() {
        this.waitForElementAndClick(START_STOP_TRIP_BUTTON,
                "Cannot find and click Start/Stop button on the screen",
                10);
    }

    public float getDistanceValue() {
        WebElement distance_value_string = checkDistanceValuePresent();
        float distance_value_float = Float
                .parseFloat(distance_value_string
                        .getAttribute("text")
                        .replaceAll("[^0-9\\.]", ""));
        return distance_value_float;
    }

    public void stopTripAfter5MilesPassed() {
        float distanceValue;
        do {
            distanceValue = getDistanceValue();
        } while (distanceValue < 5);

        System.out.println("Distance Value = " + distanceValue);
        clickStartStopTripButton();
    }

    public float calculatedDeductionValue() {
        float k = (float) 0.62;
        float actualDistanceValue = getDistanceValue();
        float calculatedDeductionValue = (actualDistanceValue * k);
        System.out.println("Calculated Deduction Value = " + calculatedDeductionValue);
        return calculatedDeductionValue;
    }

    public WebElement enterTheNoteText() {
        return this.waitForElementAndSendKeys(
                NOTES_VIEW,
                "test",
                "Cannot enter the Note",
                5);
    }

    public void clickSaveNotesButton() {
        this.waitForElementAndClick(SAVE_NOTES_BUTTON,
                "Cannot find and click Save Notes button on the screen",
                10);
    }

    public WebElement checkNoteValuePresent() {
        return this.waitForElementPresent(NOTES_VIEW,
                "Cannot find Note Value on the screen",
                10);
    }

    public String getNoteFiledValue() {
        WebElement note_field = checkNoteValuePresent();
        String note_field_value = note_field.getAttribute("text");
        System.out.println(note_field_value);
        return note_field_value;
    }
}




//        float value1 = Float.parseFloat(start_deduction_amount.replaceAll("[^0-9\\.]", ""));
//        System.out.println(value1);
//        float value2 = Float.parseFloat(stop_deduction_amount.replaceAll("[^0-9\\.]", ""));
//        System.out.println(value2);

//    public String getDeductionAmount() {
//        WebElement deduction_amount = checkDeductionAmountPresent();
//        String deduction_amount_text = deduction_amount.getAttribute("text");
//        System.out.println(deduction_amount_text);
//        return deduction_amount_text;
//    }
//
//    public float parseDeductionAmountToFloat(String string_deduction_amount) {
//        float deduction_value = Float
//                .parseFloat(string_deduction_amount
//                        .replaceAll("[^0-9\\.]", ""));
//        System.out.println(deduction_value);
//        return deduction_value;
//    }