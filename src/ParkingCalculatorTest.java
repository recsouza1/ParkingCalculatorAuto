import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

import static org.junit.Assert.assertEquals;


public class ParkingCalculatorTest extends ParkingCalculator{

    @Test
    public void validLotDateTimeAm() throws Exception {
        String expectedCost = "$ 2.00";
        String expectedDaysHoursMinutes = "(0 Days, 0 Hours, 0 Minutes)";

        driver.get(URL);
        calculateParking(
                "Short-Term Parking",
                "12:00",
                "AM",
                "12:00",
                "AM",
                currentDate(),
                currentDate()
        );

        String actualCost = getCost();
        String actualDaysHoursMinutes = getDaysHoursMinutes();

        assertEquals(actualCost,expectedCost);
        assertEquals(actualDaysHoursMinutes,expectedDaysHoursMinutes);
    }

    @Test
    public void validLotDateTimePm() throws Exception {
        String expectedCost = "$ 2.00";
        String expectedDaysHoursMinutes = "(0 Days, 0 Hours, 0 Minutes)";

        driver.get(URL);
        calculateParking(
                "Short-Term Parking",
                "12:00",
                "PM",
                "12:00",
                "PM",
                currentDate(),
                currentDate()
        );

        String actualCost = getCost();
        String actualDaysHoursMinutes = getDaysHoursMinutes();

        assertEquals(actualCost,expectedCost);
        assertEquals(actualDaysHoursMinutes,expectedDaysHoursMinutes);
    }

    @Test
    public void amTimeFieldsValueUnderTwelve() throws Exception {
        String expectedCost = "$ 2.00";
        String expectedDaysHoursMinutes = "(0 Days, 0 Hours, 0 Minutes)";

        driver.get(URL);
        calculateParking(
                "Short-Term Parking",
                "11:59",
                "AM",
                "11:59",
                "AM",
                currentDate(),
                currentDate()
        );

        String actualCost = getCost();
        String actualDaysHoursMinutes = getDaysHoursMinutes();
        String actualEntryTime = getEntryTime();
        String actualLeavingTime = getLeavingTime();

        assertEquals(actualCost,expectedCost);
        assertEquals(actualDaysHoursMinutes,expectedDaysHoursMinutes);

        //Verify times fields value persistence
        assertEquals(actualEntryTime,"11:59");
        assertEquals(actualLeavingTime,"11:59");
    }

    @Test
    public void pmTimeFieldsValueUnderTwelve() throws Exception {
        String expectedCost = "$ 2.00";
        String expectedDaysHoursMinutes = "(0 Days, 0 Hours, 0 Minutes)";

        driver.get(URL);
        calculateParking(
                "Short-Term Parking",
                "11:59",
                "PM",
                "11:59",
                "PM",
                currentDate(),
                currentDate()
        );

        String actualCost = getCost();
        String actualDaysHoursMinutes = getDaysHoursMinutes();
        String actualEntryTime = getEntryTime();
        String actualLeavingTime = getLeavingTime();

        assertEquals(actualCost,expectedCost);
        assertEquals(actualDaysHoursMinutes,expectedDaysHoursMinutes);

        //Verify times fields value persistence
        assertEquals(actualEntryTime,"11:59");
        assertEquals(actualLeavingTime,"11:59");
    }

    @Test
    public void amTimeFieldsValueAboveTwelve() throws Exception {
        driver.get(URL);
        calculateParking("Short-Term Parking", "13:00", "AM", "13:00", "AM", currentDate(), currentDate());
        verifyElementEquals(MESSAGE, "PLEASE ENTER A VALID TIME");
        verifyInputEqualsElementValue(ENTRY_TIME, "11:59");
        verifyInputEqualsElementValue(LEAVING_TIME, "11:59");
    }

    @Test
    public void pmTimeFieldsValueAboveTwelve() throws Exception {
        driver.get(URL);
        calculateParking("Short-Term Parking", "13:00", "PM", "13:00", "PM", currentDate(), currentDate());
        verifyElementEquals(MESSAGE, "PLEASE ENTER A VALID TIME");
        verifyInputEqualsElementValue(ENTRY_TIME, "11:59");
        verifyInputEqualsElementValue(LEAVING_TIME, "11:59");
    }

    @Test
    public void leavingBeforeEntryTime() throws Exception {
        driver.get(URL);
        calculateParking("Short-Term Parking", "05:00", "AM", "01:00", "AM", currentDate(), currentDate());
        verifyMessageText(MESSAGE, "LEAVING TIME MUST BE AFTER ENTRY TIME");
    }

    @Test
    public void leavingBeforeEntryDate() throws Exception {
        driver.get(URL);
        calculateParking("Short-Term Parking", "05:00", "AM", "09:00", "AM", futureDate(), currentDate());
        verifyMessageText(MESSAGE, "LEAVING DATE MUST BE AFTER ENTRY DATE");
    }

}
