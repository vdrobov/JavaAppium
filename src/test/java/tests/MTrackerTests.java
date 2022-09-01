package tests;

import lib.CoreTestCase;
import lib.ui.MTrackerPageObject;
import lib.ui.factories.MTrackerPageObjectFactory;
import org.junit.Test;

public class MTrackerTests extends CoreTestCase {

    @Test
    public void testMTrackerStartValueLessThanStopValue() throws InterruptedException {

        MTrackerPageObject MTrackerPageObject = MTrackerPageObjectFactory.get(driver);

        MTrackerPageObject.validateDriveRecordingScreenElementsPresent();
        float start_deduction_value = MTrackerPageObject.getDeductionValue();
        MTrackerPageObject.clickStartStopTripButton();
        Thread.sleep(5000);
        MTrackerPageObject.clickStartStopTripButton();
        float stop_deduction_value = MTrackerPageObject.getDeductionValue();

        assertTrue(start_deduction_value < stop_deduction_value);
    }

    @Test
    public void testMTrackerStartValueLessThanStopValueAfter5MilesPassed() {

        MTrackerPageObject MTrackerPageObject = MTrackerPageObjectFactory.get(driver);

        MTrackerPageObject.validateDriveRecordingScreenElementsPresent();
        float start_deduction_value = MTrackerPageObject.getDeductionValue();
        MTrackerPageObject.clickStartStopTripButton();

        MTrackerPageObject.stopTripAfter5MilesPassed();

        float stop_deduction_value = MTrackerPageObject.getDeductionValue();

        assertTrue(start_deduction_value < stop_deduction_value);
    }

    @Test
    public void testMTrackerCalculationValidation() {

        MTrackerPageObject MTrackerPageObject = MTrackerPageObjectFactory.get(driver);

        MTrackerPageObject.validateDriveRecordingScreenElementsPresent();
        MTrackerPageObject.clickStartStopTripButton();
        MTrackerPageObject.stopTripAfter5MilesPassed();
        float displayed_deduction_value = MTrackerPageObject.getDeductionValue();
        float calculated_deduction_value = MTrackerPageObject.calculatedDeductionValue();

        assertEquals(
                "Actual and Calculated deduction amounts are not matching",
                displayed_deduction_value,
                calculated_deduction_value,
                0.000001
        );
    }

    @Test
    public void testMTrackerStopRecordingAfterClickStopButton() throws InterruptedException {

        MTrackerPageObject MTrackerPageObject = MTrackerPageObjectFactory.get(driver);

        MTrackerPageObject.validateDriveRecordingScreenElementsPresent();
        MTrackerPageObject.clickStartStopTripButton();

        MTrackerPageObject.stopTripAfter5MilesPassed();

        float stop_deduction_value1 = MTrackerPageObject.getDeductionValue();
        Thread.sleep(5000);
        float stop_deduction_value2 = MTrackerPageObject.getDeductionValue();

        assertEquals(
                stop_deduction_value1,
                stop_deduction_value2);
    }

    @Test
    public void testMTrackerChangeScreenOrientation() {

        MTrackerPageObject MTrackerPageObject = MTrackerPageObjectFactory.get(driver);

        MTrackerPageObject.validateDriveRecordingScreenElementsPresent();
        MTrackerPageObject.clickStartStopTripButton();

        MTrackerPageObject.stopTripAfter5MilesPassed();

        float deduction_amount_before_rotation = MTrackerPageObject.getDeductionValue();
        this.rotateScreenLandscape();
        float deduction_amount_after_rotation = MTrackerPageObject.getDeductionValue();

        assertEquals(
                "Deduction amount has been changed after screen rotation",
                deduction_amount_before_rotation,
                deduction_amount_after_rotation
        );

        this.rotateScreenPortrait();
        float deduction_amount_after_second_rotation = MTrackerPageObject.getDeductionValue();

        assertEquals(
                "Deduction amount has been changed after 2nd screen rotation",
                deduction_amount_before_rotation,
                deduction_amount_after_second_rotation
        );
    }

    @Test
    public void testMTrackerInBackground() {

        MTrackerPageObject MTrackerPageObject = MTrackerPageObjectFactory.get(driver);

        MTrackerPageObject.validateDriveRecordingScreenElementsPresent();
        MTrackerPageObject.clickStartStopTripButton();

        MTrackerPageObject.stopTripAfter5MilesPassed();

        float deduction_amount_before_background = MTrackerPageObject.getDeductionValue();
        this.backgroundApp(3);
        float deduction_amount_after_background = MTrackerPageObject.getDeductionValue();

        assertEquals(
                "Deduction amount has been changed after background app",
                deduction_amount_before_background,
                deduction_amount_after_background
        );
    }
}