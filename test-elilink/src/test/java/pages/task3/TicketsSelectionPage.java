package pages.task3;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

/**
 * Created by skort on 29.10.2016.
 */
public class TicketsSelectionPage {

    private WebDriver driver;
    private WebElement element;

    private By locatorDepartTicket = new By.ByXPath("//button[@id='0_0_0']");
    private By locatorReturnTicket = new By.ByXPath("//button[@id='0_0_0' and contains (@class, 'primary priceBtn')]");

    public TicketsSelectionPage (WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Выбираем 1 билет, ждем пока прогрузиться и найдет
     * локатор со 2 билетом, далее выбираем второй билет
     * и перемещаемся на следующую страницу
     * @return
     */
    public TripSummaryPage selectTickets() {
        navigateMenu(locatorDepartTicket);

        WebDriverWait wait = new WebDriverWait(driver, WebDriverWait.DEFAULT_SLEEP_TIMEOUT);
        wait.until(ExpectedConditions.elementToBeClickable(locatorReturnTicket));

        navigateMenu(locatorReturnTicket);

        return new TripSummaryPage(driver);

    }

    private WebElement navigateMenu(By loc) {
        element = driver.findElement(loc);
        element.click();
        return element;
    }

}
