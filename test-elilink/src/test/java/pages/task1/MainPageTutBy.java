package pages.task1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Created by skort on 29.10.2016.
 */
public class MainPageTutBy {
    private WebDriver driver;

    private By locatorSearchTutBy = new By.ByXPath("//input[@id='search_from_str']");
    private By locatorBtnSearchTutBy = new By.ByXPath("//input[@name='search']");

    private WebElement element;

    public MainPageTutBy(WebDriver driver) {
        this.driver = driver;
    }

    /**
     *  Находим Search Text Box по локатору
     *  Вводим данные из xml передавая в параметры
     *  Находим локатор Кнопки, кликаем для поиска результата
     * @param text
     * @return
     */
    public WebElement inputSearchRequest(String text) {
        element = driver.findElement(locatorSearchTutBy);
        element.clear();
        element.sendKeys(text);

        element = driver.findElement(locatorBtnSearchTutBy);
        element.click();

        return element;
    }
}
