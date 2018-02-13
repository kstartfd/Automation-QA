package pages.task3;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Created by skort on 29.10.2016.
 */
public class MainPageDelta {

    private WebDriver driver;
    private WebElement element;

    private By locatorBookATrip = new By.ByXPath("//a[@id='navBookTrip']");
    private By locatorFlight = new By.ByXPath("//a[@id='book-air-content-trigger']");
    private By locatorRoundTrip = new By.ByXPath("//label[@id='roundTripBtn']");

    private By locatorWidget = new By.ByXPath("//div[contains (@class,'widget-body')]");

    private By locatorOriginCity = new By.ByXPath("//input[@id='originCity']");
    private By locatorDestinationCity = new By.ByXPath("//input[@id='destinationCity']");


    private By locatorDepartDate = new By.ByXPath("//input[@id='departureDate']");
    private By locatorReturnDate = new By.ByXPath("//input[@id='returnDate']");


    private By locatorExactDays = new By.ByXPath("//label[@id='exactDaysBtn']");
    private By locatorCash = new By.ByXPath("//label[@id='cashBtn']");

    private By locatorfindFlights = new By.ByXPath("//button[@id='findFlightsSubmit']");

    public MainPageDelta (WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Передаем параметры
     * Откуда отправляемся, Куда, Дату отправления, Дату возвращения
     * Проверяем, активно ли выпадающее меню, если да, то продолжаем действия
     * по вводу данных, если нет, открывает и продолжаем.
     * @param from
     * @param to
     * @param departDate
     * @param returnData
     */

    public void selectData(String from, String to, String departDate, String returnData) {

        if(isElementPresent(locatorWidget)) {
            navigateMenu(locatorFlight);
            navigateMenu(locatorRoundTrip);
        } else  {
            navigateMenu(locatorBookATrip);
            navigateMenu(locatorFlight);
            navigateMenu(locatorRoundTrip);
        }

        navigateTxtMenu(locatorOriginCity, from);
        navigateTxtMenu(locatorDestinationCity, to);

        navigateTxtMenu(locatorDepartDate, departDate);
        navigateTxtMenu(locatorReturnDate, returnData);


        navigateMenu(locatorExactDays);
        navigateMenu(locatorCash);

        navigateMenu(locatorfindFlights);

    }

    private WebElement navigateMenu(By loc) {
        element = driver.findElement(loc);
        element.click();
        return element;
    }

    private WebElement navigateTxtMenu(By loc, String text) {
        element = driver.findElement(loc);
        element.clear();
        element.sendKeys(text);
        return element;
    }

    private boolean isElementPresent(By by){
        try{
            driver.findElement(by);
            return true;
        }
        catch (NoSuchElementException e) {
            return false;
        }
    }

}
