package pages.task2;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Created by skort on 31.10.2016.
 */
public class WriteMailPage {

    private WebDriver driver;
    private WebElement element;

    private By locatorBtnWriteMail = new By.ByXPath("//div[contains(@gh,'cm')]");
    private By locatorDialogMail = new By.ByXPath("//div[@role='dialog']");
    private By locatorTxtTo = new By.ByXPath("//textarea[@aria-label='Кому']");
    private By locatorTxtSubject = new By.ByXPath("//input[@name='subjectbox']");
    private By locatorTxtAriaLabel = new By.ByXPath("//div[@aria-label='Тело письма']");
    private By locatorBtnSend = new By.ByXPath("//div[text()='Отправить']");
    private By locatorLabelAcc = new By.ByXPath("//a/span[@class='gb_8a gbii']");
    private By locatorWindowInformAcc = new By.ByXPath("//div[@aria-label='Информация об аккаунте']");
    private By locatorBtnLogout = new By.ByXPath("//a[@id='gb_71']");


    private static final Logger logger = Logger.getLogger(WriteMailPage.class);

    public WriteMailPage(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Передаем в параметрах адрес кому отправляем письма, тему и содержание,
     * кликаем отправить, далее кликаем выйти.
     * @param whom
     * @param subject
     * @param mail
     */

    public void writeMailAndLogout(String whom, String subject, String mail) {
        navigateMenu(locatorBtnWriteMail);
        isElementPresent(locatorDialogMail);
        navigateTxtMenu(locatorTxtTo, whom);
        navigateTxtMenu(locatorTxtSubject, subject);
        navigateTxtMenu(locatorTxtAriaLabel, mail);
        navigateMenu(locatorBtnSend);
        navigateMenu(locatorLabelAcc);
        isElementPresent(locatorWindowInformAcc);
        navigateMenu(locatorBtnLogout);
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
            logger.info(e.toString());
            return false;
        }
    }
}
