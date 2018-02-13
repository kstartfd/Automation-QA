package pages.task2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Created by skort on 29.10.2016.
 */
public class MainPageGmail {

    private WebDriver driver;
    private WebElement btnEnterGmail;

    private By locatorEnterGmail = new By.ByXPath("//a[@id='gmail-sign-in']");

    public MainPageGmail(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Переходим на стр ввода логина
     * @return
     */
    public void enterGmail() {
        btnEnterGmail = driver.findElement(locatorEnterGmail);
        btnEnterGmail.click();
    }
}
