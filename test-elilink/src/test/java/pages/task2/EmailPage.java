package pages.task2;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;


/**
 * Created by skort on 29.10.2016.
 */
public class EmailPage {

    private WebDriver driver;
    private WebElement element;

    private By locatorInbox = new By.ByXPath("html/body/div[7]/div[3]/div/div[2]/div[1]/div/div[1]/div[2]/div/div/div[2]/div/div[1]/div[1]/div/div[1]");
    private By locatorMainNotSorted = new By.ByXPath("//div[contains(text(),'Несортированные')]");
    private By locatorSent = new By.ByXPath("//a[contains(.,'Отправленные')]");
    private By locatorOpenItem = new By.ByXPath("//span[@role='button']");
    private By locatorMainSpam = new By.ByXPath("//a[@title='Спам']");
    private By locatorTxtSearch = new By.ByXPath("html/body/div[7]/div[3]/div/div[1]/div[4]/div[1]/div[1]/div[3]/div/div/div/form/fieldset[2]/div/div/div[2]/table/tbody/tr/td/table/tbody/tr/td/div/input");

    private static final Logger logger = Logger.getLogger(EmailPage.class);

    public EmailPage(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Переходим на "Входящие", проверяем по Табу "Несортированные",
     * что находимся во "Входящих".
     * Далее переходим в "Исходящие", проверяем что находимся в
     * "Исходящие" по верхнему Тесктбоксу, в нем должна появиться надпись
     * "in:sent", далее открываем остальные части gmail, переходим в "Спам",
     *  проверяем что находимся в "Спам" по верхнему Тесктбоксу, в нем должна появиться надпись
     * "in:spam".
     * Далее преходим во "Входящие" как на новую страницу.
     */

    public void verifyPartsMail() {

        navigateMailPart(locatorInbox);

        String forNotSorted = driver.findElement(locatorMainNotSorted).getText();
        isElementPresent(locatorMainNotSorted, forNotSorted.toString());



        navigateMailPart(locatorSent);

        waiting(5000);

        String forTxtBoxSent = driver.findElement(locatorTxtSearch).getAttribute("value");
        isElementPresent(locatorTxtSearch, forTxtBoxSent.toString());

        navigateMailPart(locatorOpenItem);


        navigateMailPart(locatorMainSpam);

        waiting(5000);

        String forTxtBoxSpam = driver.findElement(locatorTxtSearch).getAttribute("value");
        isElementPresent(locatorTxtSearch, forTxtBoxSpam.toString());

        navigateMailPart(locatorInbox);

    }

    /**
     * Метод навигации по кнопкам, лейблам, принимает параметр локатора
     * @param locator
     * @return
     */
    private WebElement navigateMailPart(By locator) {
        element = driver.findElement(locator);
        element.click();
        return element;
    }

    /**
     * Првоеряем присутствует ли элемент на странице
     * Передаваемые параметры локатор, и  текст используется для текст бокса
     * @param by
     * @param loc
     * @return
     */
    private boolean isElementPresent(By by, String loc){
        try {
            driver.findElement(by);
            logger.info(loc);
            return true;
        }
        catch (NoSuchElementException e) {
            logger.info(e.toString());
            return false;
        }
    }


    /**
     * Используется для остановки всех действий на странице.
     * Используется, чтобы isElementPresent успел проверить
     * присутствует ли текст в верхнем текст боксе, например, при переходе во Исходящие,
     * проверяет in:sent
     * Передается параметр в время на какое будет остановлены действия
     * @param sec
     */
    private void waiting(long sec) {
        try {
            Thread.sleep(sec);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
