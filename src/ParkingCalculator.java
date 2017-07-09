import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;


public class ParkingCalculator {
    public static final String LOT_COMBO_BOX = ".//*[@id='Lot']";
    public static final String ENTRY_TIME = ".//*[@id='EntryTime']";
    public static final String LEAVING_TIME = ".//*[@id='ExitTime']";
    public static final String ENTRY_DATE = ".//*[@id='EntryDate']";
    public static final String LEAVING_DATE = ".//*[@id='ExitDate']";
    public static final String ENTRY_RADIO_BUTTON_AM = "html/body/form/table/tbody/tr[2]/td[2]/font/input[2]";
    public static final String ENTRY_RADIO_BUTTON_PM = "html/body/form/table/tbody/tr[2]/td[2]/font/input[3]";
    public static final String LEAVING_RADIO_BUTTON_AM = "html/body/form/table/tbody/tr[3]/td[2]/font/input[2]";
    public static final String LEAVING_RADIO_BUTTON_PM = "html/body/form/table/tbody/tr[3]/td[2]/font/input[3]";
    public static final String CALCULATE_BUTTON = "html/body/form/input[2]";
    public static final String COST_VALUE = "html/body/form/table/tbody/tr[4]/td[2]/span[1]/font/b";
    public static final String DAYS_HOURS_MINUTES = "html/body/form/table/tbody/tr[4]/td[2]/span[2]/font/b";
    public static final String MESSAGE = "html/body/form/table/tbody/tr[4]/td[2]/span/font/b";


    public static final String URL = "http://adam.goucher.ca/parkcalc/";

    public static WebDriver driver;

    @Before
    public void initiateDriver() {
        System.setProperty("webdriver.chrome.driver", "/home/recsouza/Downloads/Drivers/chromedriver");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @After
    public void afterTest() {
        driver.quit();
    }

    public void clickElement (By elementId) throws Exception {
        WebElement element = driver.findElement(elementId);
        element.click();
    }

    public void enterTextToElement(By elementId, String text) throws Exception {
        WebElement element = driver.findElement(elementId);
        element.clear();
        element.sendKeys(text);
    }

    public void selectComboLot(String value) throws Exception {
        WebElement combo = driver.findElement(By.xpath(LOT_COMBO_BOX));
        Select lot = new Select(combo);
        lot.selectByVisibleText(value);
    }

    public String currentDate() throws Exception {
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        Date date = new Date();
        String dateFormatted = dateFormat.format(date);
        return dateFormatted;
    }

    public String futureDate() throws Exception {
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        Date date = new Date((new Date()).getTime() + (10 * 86400000));
        String dateFormatted = dateFormat.format(date);
        return dateFormatted;
    }

    public void verifyElementEquals(By elementId, String compareValue) throws Exception{
        WebElement element = driver.findElement(elementId);
        if (!(element.getText().trim().equals(compareValue))) {
            Assert.fail("Element not found or value is wrong " + element);
        }

    }

    public void verifyInputEqualsElementValue(By elementId, String compareValue) throws Exception{
        WebElement element = driver.findElement(elementId);
        if (!(element.getAttribute("value").equals(compareValue))) {
            Assert.fail("Element not found or value did not persist " + element);
        }
    }

    public void verifyMessageText(By elementId, String compareValue) throws Exception {
        WebElement element = driver.findElement(elementId);
        if (!(element.getText().trim().equals(compareValue))) {
            Assert.fail(compareValue + " " + element);
        }
    }
//    public void verificationListAsserts()
//    {
//        if(assertsError.size() > 0)
//        {
//            Assert.fail(String.valueOf(assertsError));
//        }
//    }


}