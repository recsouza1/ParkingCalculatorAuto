import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;


public class ParkingCalculatorTest extends ParkingCalculator{

    @Test
    public void validLotDateTimeAm() throws Exception {
        driver.get(URL);
        setLot("Short-Term Parking");
        setEntryTime("12:00");
        setLeavingTime("12:00");
        selectEntryTimeRadioAm();
        selectLeavingTimeRadioAm();
        setEntryDate(currentDate());
        setLeavingDate(currentDate());
        calculate();
        verifyElementEquals(COST_VALUE, "$ 2.00");
        verifyElementEquals(DAYS_HOURS_MINUTES, "(0 Days, 0 Hours, 0 Minutes)");
    }

    @Test
    public void validLotDateTimePm() throws Exception {
        driver.get(URL);
        setLot("Short-Term Parking");
        setEntryTime("12:00");
        setLeavingTime("12:00");
        selectEntryTimeRadioPm();
        selectLeavingTimeRadioPm();
        setEntryDate(currentDate());
        setLeavingDate(currentDate());
        calculate();
        verifyElementEquals(COST_VALUE, "$ 2.00");
        verifyElementEquals(DAYS_HOURS_MINUTES, "(0 Days, 0 Hours, 0 Minutes)");
    }

    @Test
    public void amTimeFieldsValueUnderTwelve() throws Exception {
        driver.get(URL);
        setLot("Short-Term Parking");
        setEntryTime("11:59");
        setLeavingTime("11:59");
        selectEntryTimeRadioAm();
        selectLeavingTimeRadioAm();
        setEntryDate(currentDate());
        setLeavingDate(currentDate());
        calculate();
        verifyElementEquals(COST_VALUE, "$ 2.00");
        verifyElementEquals(DAYS_HOURS_MINUTES, "(0 Days, 0 Hours, 0 Minutes)");
        verifyInputEqualsElementValue(ENTRY_TIME, "11:59");
        verifyInputEqualsElementValue(LEAVING_TIME, "11:59");
    }

    @Test
    public void pmTimeFieldsValueUnderTwelve() throws Exception {
        driver.get(URL);
        setLot("Short-Term Parking");
        setEntryTime("11:59");
        setLeavingTime("11:59");
        selectEntryTimeRadioPm();
        selectLeavingTimeRadioPm();
        setEntryDate(currentDate());
        setLeavingDate(currentDate());
        calculate();
        verifyElementEquals(COST_VALUE, "$ 2.00");
        verifyElementEquals(DAYS_HOURS_MINUTES, "(0 Days, 0 Hours, 0 Minutes)");
        verifyInputEqualsElementValue(ENTRY_TIME, "11:59");
        verifyInputEqualsElementValue(LEAVING_TIME, "11:59");
    }

    @Test
    public void amTimeFieldsValueAboveTwelve() throws Exception {
        driver.get(URL);
        setLot("Short-Term Parking");
        setEntryTime("13:00");
        setLeavingTime("13:00");
        selectEntryTimeRadioAm();
        selectLeavingTimeRadioAm();
        setEntryDate(currentDate());
        setLeavingDate(currentDate());
        calculate();
        verifyElementEquals(MESSAGE, "PLEASE ENTER A VALID TIME");
        verifyInputEqualsElementValue(ENTRY_TIME, "11:59");
        verifyInputEqualsElementValue(LEAVING_TIME, "11:59");
    }

    @Test
    public void pmTimeFieldsValueAboveTwelve() throws Exception {
        driver.get(URL);
        setLot("Short-Term Parking");
        setEntryTime("13:00");
        setLeavingTime("13:00");
        selectEntryTimeRadioPm();
        selectLeavingTimeRadioPm();
        setEntryDate(currentDate());
        setLeavingDate(currentDate());
        calculate();
        verifyElementEquals(MESSAGE, "PLEASE ENTER A VALID TIME");
        verifyInputEqualsElementValue(ENTRY_TIME, "11:59");
        verifyInputEqualsElementValue(LEAVING_TIME, "11:59");
    }

    @Test
    public void leavingBeforeEntryTime() throws Exception {
        driver.get(URL);
        setLot("Short-Term Parking");
        setEntryTime("05:00");
        setLeavingTime("01:00");
        selectEntryTimeRadioAm();
        selectLeavingTimeRadioAm();
        setEntryDate(currentDate());
        setLeavingDate(currentDate());
        calculate();
        verifyMessageText(MESSAGE, "LEAVING TIME MUST BE AFTER ENTRY TIME");
    }

    @Test
    public void leavingBeforeEntryDate() throws Exception {
        driver.get(URL);
        setLot("Short-Term Parking");
        setEntryTime("05:00");
        setLeavingTime("09:00");
        selectEntryTimeRadioAm();
        selectLeavingTimeRadioAm();
        setEntryDate(futureDate());
        setLeavingDate(currentDate());
        calculate();
        verifyMessageText(MESSAGE, "LEAVING DATE MUST BE AFTER ENTRY DATE");
    }

}
