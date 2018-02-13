package pages.task3;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import tests.task3.TestDelta;

/**
 * Created by skort on 30.10.2016.
 */
public class CreditCardInfoPage {
    private WebDriver driver;
    private static WebElement element;

    private By locatorBtnCompletePurchase = new By.ByXPath("//button[@id='continue_button']");

    private static final Logger logger = Logger.getLogger(CreditCardInfoPage.class);

    public CreditCardInfoPage(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Проверяем, кликабельна ли кнопка, если да
     * то тест возвратит True
     */
    public void checkBtnCompletePurchase() {

        element = driver.findElement(locatorBtnCompletePurchase);

        if (isClickable(element)) {
            logger.info("Button 'CompletePurchase' is active");
        } else {
            logger.info("Button 'CompletePurchase' is not active");
        }
    }

    /**
     * Передаем елемент, который будет проверяться
     * @param el
     * @return
     */
    private boolean isClickable(WebElement el) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, 5);
            wait.until(ExpectedConditions.elementToBeClickable(el));
            return true;
        } catch (Exception e) {
            return false;
        }

    }
}
