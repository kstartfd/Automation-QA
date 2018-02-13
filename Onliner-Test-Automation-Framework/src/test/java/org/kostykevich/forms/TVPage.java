package org.kostykevich.forms;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import webdriver.BaseForm;
import webdriver.elements.CheckBox;
import webdriver.elements.ComboBox;
import webdriver.elements.Label;
import webdriver.elements.TextBox;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fd on 18.06.2016.
 */
public class TVPage extends BaseForm {

    private Label productName = new Label(By.xpath("//div[@class='schema-product__title']/a") , "Product name");

    private CheckBox checkBoxSamsung = new CheckBox(By.xpath("//div[@class='schema-filter']/div[1]/div[1]/div[2]/ul/li[2]/label/span[1]/span"), "Check samsung item");

    private TextBox textBoxMaxPrice = new TextBox(By.xpath("(//input[@type='text'])[3]"), "Max Price 1000");
    private TextBox textBoxMinYear = new TextBox(By.xpath(".//*[@id='schema-filter']/div[1]/div[3]/div[3]/div/div[1]/input"), "Min year 2013");

    private ComboBox comboBoxMinDiag = new ComboBox(By.xpath(String.format("//select[contains(@class, 'schema-filter-control__item') and contains(@data-bind, 'facet.value.from')]")), "Min Diagonal 39");
    private ComboBox comboBoxMaxDiag = new ComboBox(By.xpath(String.format("//select[contains(@class,'schema-filter-control__item') and contains(@data-bind, 'facet.value.to')]")), "Max Diagonal 42");

    private long timeout = 5;

    private String symbol = "\"";
    /**
     * Проверяем что мы на
     * странице с телевизорами
     * по главному хедеру
     */
    public TVPage() {
        super(By.cssSelector(".schema-header__title"), "Title TV is present");
    }

    /**
     *
     * Передаем параметры,
     * кликаем по чекбоксу определенного товара,
     * вводим максимальную цену, минимальный год выпуска,
     * выбираем минимальную и
     * максимальную диагональ телевизоров.
     * @param maxPrice
     * @param minYear
     * @param minDiagonal
     * @param maxDiagonal
     */
    public void findTVItem (String maxPrice, String minYear, String minDiagonal, String maxDiagonal) {
        checkBoxSamsung.check();
        textBoxMaxPrice.type(maxPrice);
        textBoxMinYear.type(minYear);
        comboBoxMinDiag.selectByVisibleText(String.format("%s%s", minDiagonal, symbol));
        comboBoxMaxDiag.selectByVisibleText(String.format("%s%s", maxDiagonal, symbol));

        waitingResults();
    }

    /**
     * Проверяем найденый результат, берем ссылку результата
     * и переходим по ней, открыв страницу с товаром, создавая при этом объект класса
     * ResultTVSet и в нем проверяем переданные параметры, с результатами
     * найденными на странице
     * @param nameProduct
     * @param minYear
     * @param maxPrice
     * @param minDiagonal
     * @param maxDiagonal
     */
    public void assertFoundResult(String nameProduct, String minYear, String maxPrice, String minDiagonal, String maxDiagonal) {
        for (String url : productName.getAttributeElements()) {
            browser.navigate(url);
            ResultTVSet resultTVSet = new ResultTVSet();
            resultTVSet.assertResultOnPage(nameProduct, minYear, maxPrice, minDiagonal, maxDiagonal);
            browser.backToPreviousPage();
            waitingResults();
        }
    }

    /**
     * Ждем пока прогрузиться страница, при применении фильтра
     * или при переходе на другую стр
     * без метода выскакивает эксепшен
     * stale element reference: element is not attached to the page document
     */
    private void waitingResults() {
        waiting(timeout);
    }

}
