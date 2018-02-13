package pages.task2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Created by skort on 29.10.2016.
 */
public class LoginPage {

    private WebDriver driver;
    private WebElement element;

    private By locatorNameGmail = new By.ByXPath("//input[@id='Email']");
    private By locatorBtnFather = new By.ByXPath("//input[@id='next']");
    private By locatorPasswordGmail = new By.ByXPath("//input[@id='Passwd']");
    private By locatorBtnEnterInGmail = new By.ByXPath("//input[@id='signIn']");


    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Вводим данные логина, кликаем далее,
     * вводим пароль, далее, переходим на след страницу
     * @param name
     * @param password
     */

    public void inputLoginData(String name, String password) {
        element = driver.findElement(locatorNameGmail);
        element.clear();
        element.sendKeys(name);

        element = driver.findElement(locatorBtnFather);
        element.click();

        element = driver.findElement(locatorPasswordGmail);
        element.clear();
        element.sendKeys(password);

        element = driver.findElement(locatorBtnEnterInGmail);
        element.click();
    }

}
