package pages.task3;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Created by skort on 29.10.2016.
 */
public class TripSummaryPage {

    private WebDriver driver;
    private WebElement element;

    private By locatorTripSummarySubmitBtn = new By.ByXPath("//button[@id='tripSummarySubmitBtn']");

    public TripSummaryPage (WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Кликаем продолжить
     */
    public void tripSummarySubmit() {
        element = driver.findElement(locatorTripSummarySubmitBtn);
        element.click();
    }

}
