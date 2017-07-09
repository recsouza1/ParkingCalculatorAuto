import org.junit.Test;
import org.openqa.selenium.By;


public class ParkingCalculatorTest extends ParkingCalculator {

    @Test
    public void validLotDateTimeAm() throws Exception {
        driver.get(URL);
        selectComboLot("Short-Term Parking");
        enterTextToElement(By.xpath(ENTRY_TIME), "12:00");
        enterTextToElement(By.xpath(LEAVING_TIME), "12:00");
        clickElement(By.xpath(ENTRY_RADIO_BUTTON_AM));
        clickElement(By.xpath(LEAVING_RADIO_BUTTON_AM));
        enterTextToElement(By.xpath(ENTRY_DATE), currentDate());
        enterTextToElement(By.xpath(LEAVING_DATE), currentDate());
        clickElement(By.xpath(CALCULATE_BUTTON));
        verifyElementEquals(By.xpath(COST_VALUE), "$ 2.00");
        verifyElementEquals(By.xpath(DAYS_HOURS_MINUTES), "(0 Days, 0 Hours, 0 Minutes)");
    }

    @Test
    public void validLotDateTimePm() throws Exception {
        driver.get(URL);
        selectComboLot("Short-Term Parking");
        enterTextToElement(By.xpath(ENTRY_TIME), "12:00");
        enterTextToElement(By.xpath(LEAVING_TIME), "12:00");
        clickElement(By.xpath(ENTRY_RADIO_BUTTON_PM));
        clickElement(By.xpath(LEAVING_RADIO_BUTTON_PM));
        enterTextToElement(By.xpath(ENTRY_DATE), currentDate());
        enterTextToElement(By.xpath(LEAVING_DATE), currentDate());
        clickElement(By.xpath(CALCULATE_BUTTON));
        verifyElementEquals(By.xpath(COST_VALUE), "$ 2.00");
        verifyElementEquals(By.xpath(DAYS_HOURS_MINUTES), "(0 Days, 0 Hours, 0 Minutes)");
    }

    @Test
    public void amTimeFieldsValueUnderTwelve() throws Exception {
        driver.get(URL);
        selectComboLot("Short-Term Parking");
        enterTextToElement(By.xpath(ENTRY_TIME), "11:59");
        enterTextToElement(By.xpath(LEAVING_TIME), "11:59");
        clickElement(By.xpath(ENTRY_RADIO_BUTTON_AM));
        clickElement(By.xpath(LEAVING_RADIO_BUTTON_AM));
        enterTextToElement(By.xpath(ENTRY_DATE), currentDate());
        enterTextToElement(By.xpath(LEAVING_DATE), currentDate());
        clickElement(By.xpath(CALCULATE_BUTTON));
        verifyElementEquals(By.xpath(COST_VALUE), "$ 2.00");
        verifyElementEquals(By.xpath(DAYS_HOURS_MINUTES), "(0 Days, 0 Hours, 0 Minutes)");
        verifyInputEqualsElementValue(By.xpath(ENTRY_TIME), "11:59");
        verifyInputEqualsElementValue(By.xpath(LEAVING_TIME), "11:59");
    }

    @Test
    public void pmTimeFieldsValueUnderTwelve() throws Exception {
        driver.get(URL);
        selectComboLot("Short-Term Parking");
        enterTextToElement(By.xpath(ENTRY_TIME), "11:59");
        enterTextToElement(By.xpath(LEAVING_TIME), "11:59");
        clickElement(By.xpath(ENTRY_RADIO_BUTTON_PM));
        clickElement(By.xpath(LEAVING_RADIO_BUTTON_PM));
        enterTextToElement(By.xpath(ENTRY_DATE), currentDate());
        enterTextToElement(By.xpath(LEAVING_DATE), currentDate());
        clickElement(By.xpath(CALCULATE_BUTTON));
        //Thread.sleep(10000);
        verifyElementEquals(By.xpath(COST_VALUE), "$ 2.00");
        verifyElementEquals(By.xpath(DAYS_HOURS_MINUTES), "(0 Days, 0 Hours, 0 Minutes)");
        verifyInputEqualsElementValue(By.xpath(ENTRY_TIME), "11:59");
        verifyInputEqualsElementValue(By.xpath(LEAVING_TIME), "11:59");
    }

    @Test
    public void amTimeFieldsValueAboveTwelve() throws Exception {
        driver.get(URL);
        selectComboLot("Short-Term Parking");
        enterTextToElement(By.xpath(ENTRY_TIME), "13:00");
        enterTextToElement(By.xpath(LEAVING_TIME), "13:00");
        clickElement(By.xpath(ENTRY_RADIO_BUTTON_AM));
        clickElement(By.xpath(LEAVING_RADIO_BUTTON_AM));
        enterTextToElement(By.xpath(ENTRY_DATE), currentDate());
        enterTextToElement(By.xpath(LEAVING_DATE), currentDate());
        clickElement(By.xpath(CALCULATE_BUTTON));
        verifyElementEquals(By.xpath(MESSAGE), "PLEASE ENTER A VALID TIME");
        verifyInputEqualsElementValue(By.xpath(ENTRY_TIME), "11:59");
        verifyInputEqualsElementValue(By.xpath(LEAVING_TIME), "11:59");
    }

    @Test
    public void pmTimeFieldsValueAboveTwelve() throws Exception {
        driver.get(URL);
        selectComboLot("Short-Term Parking");
        enterTextToElement(By.xpath(ENTRY_TIME), "13:00");
        enterTextToElement(By.xpath(LEAVING_TIME), "13:00");
        clickElement(By.xpath(ENTRY_RADIO_BUTTON_PM));
        clickElement(By.xpath(LEAVING_RADIO_BUTTON_PM));
        enterTextToElement(By.xpath(ENTRY_DATE), currentDate());
        enterTextToElement(By.xpath(LEAVING_DATE), currentDate());
        clickElement(By.xpath(CALCULATE_BUTTON));
        verifyElementEquals(By.xpath(MESSAGE), "PLEASE ENTER A VALID TIME");
        verifyInputEqualsElementValue(By.xpath(ENTRY_TIME), "11:59");
        verifyInputEqualsElementValue(By.xpath(LEAVING_TIME), "11:59");
    }

    @Test
    public void leavingBeforeEntryTime() throws Exception {
        driver.get(URL);
        selectComboLot("Short-Term Parking");
        enterTextToElement(By.xpath(ENTRY_TIME), "05:00");
        enterTextToElement(By.xpath(LEAVING_TIME), "01:00");
        clickElement(By.xpath(ENTRY_RADIO_BUTTON_AM));
        clickElement(By.xpath(LEAVING_RADIO_BUTTON_AM));
        enterTextToElement(By.xpath(ENTRY_DATE), currentDate());
        enterTextToElement(By.xpath(LEAVING_DATE), currentDate());
        clickElement(By.xpath(CALCULATE_BUTTON));
        verifyMessageText(By.xpath(MESSAGE), "LEAVING TIME MUST BE AFTER ENTRY TIME");
    }

    @Test
    public void leavingBeforeEntryDate() throws Exception {
        driver.get(URL);
        selectComboLot("Short-Term Parking");
        enterTextToElement(By.xpath(ENTRY_TIME), "05:00");
        enterTextToElement(By.xpath(LEAVING_TIME), "09:00");
        clickElement(By.xpath(ENTRY_RADIO_BUTTON_AM));
        clickElement(By.xpath(LEAVING_RADIO_BUTTON_AM));
        enterTextToElement(By.xpath(ENTRY_DATE), futureDate());
        enterTextToElement(By.xpath(LEAVING_DATE), currentDate());
        clickElement(By.xpath(CALCULATE_BUTTON));
        verifyMessageText(By.xpath(MESSAGE), "LEAVING DATE MUST BE AFTER ENTRY DATE");
    }

}
