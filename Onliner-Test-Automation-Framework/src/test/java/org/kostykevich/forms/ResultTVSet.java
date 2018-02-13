package org.kostykevich.forms;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import webdriver.BaseForm;
import webdriver.elements.Label;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by fd on 21.06.2016.
 */
public class ResultTVSet extends BaseForm {

    private Label lbNameProduct = new Label(By.xpath("//h2[@class='catalog-masthead__title']")) ;
    private Label lbPrice = new Label(By.cssSelector(".b-offers-desc__info-price-value.b-offers-desc__info-price-value_primary")) ;
    private Label lbYear = new Label(By.xpath("//td[contains(.,'Дата выхода на рынок')]/following-sibling::td")) ;
    private Label lbDiagonal = new Label(By.xpath("//td[contains(.,'Диагональ экрана')]/following-sibling::td")) ;
    private boolean flag = true;
    private String goodItem = "It's Good Item: ";
    private String badItem = "It's Bad Item: ";

    public ResultTVSet() {
        super(By.xpath("//h2[@class='catalog-masthead__title']"), "Page of product");
    }

    protected void assertResultOnPage(String nameProduct, String minYear , String maxPrice, String minDiagonal, String maxDiagonal) {
        /**
         * Проверяем название продукта
         * если текст на проверяемой странице по лакатору
         * lbNameProduct содержит текст из параметров, то true
         */
        if (lbNameProduct.getText().contains(nameProduct)) {
            flag = true;

            /**
             * В minYearString передаем с локатора на проверяемой странице,
             * который должен быть от 2013 года.
             * В minYearLocal передаем данный с параметра, который берется с xml.
             * Проверяем, если найденый год на проверяемой странице больше,чем
             * передаваемый из параметров в переменную minYearLocal, то flag = true
             * Проверяем по двум последним числам.
             */
            String minYearString = lbYear.getText();
            int year = Integer.parseInt(minYearString.substring(2, 4));
            int minYearLocal = Integer.parseInt(minYear.substring(2, 4));
            if (year > minYearLocal)
                flag = true;

            /**
             * В maxPriceString передаем данные из проверяемой страницы.
             * В maxPriceLocal берем из параметров до 1000.
             * Если maxPriceString меньше , чем  maxPriceLocal, которое взято из параметров,
             * то flag = true
             * Берем 4 знака, заменяем запятую на точку.
             * Выводим в лог цену взятую со страницы, и цену с передаваемых параметров
             *
             */
            String maxPriceString = lbPrice.getText();
            double price = Double.parseDouble(maxPriceString.substring(0, 4).replace(',', '.'));
            double maxPriceLocal = Double.parseDouble(maxPrice.substring(0, 4).replace(',', '.'));
            if (price <= maxPriceLocal)
                flag = true;

            info(String.format("Price from page: %s - Price from parameters : %s" , price, maxPriceLocal));

            /**
             *Берем первые 2 знака,
             *Конвертим все в int , сравниваем со значением
             * полученным с проверяемой страницы.
             * Для точной проверки, идет вывод в консоль
             */
            String diagonal = lbDiagonal.getText();
            int diagonalFromPage = Integer.parseInt(diagonal.substring(0, 2));
            int diagonalMin = Integer.parseInt(minDiagonal.substring(0, 2));
            int diagonalMax = Integer.parseInt(maxDiagonal.substring(0, 2));
            if (diagonalFromPage > diagonalMin && diagonalFromPage < diagonalMax)
                flag = true;

            info(String.format("From page: %s,  Min from parameters : %s, Max from parameters : %s" , diagonalFromPage, diagonalMin, diagonalMax ));

            /**
             *
             */
            doAssert(flag,
                    String.format("%s : Model: %s Year of release : %s Price : %s",
                    goodItem, lbNameProduct.getText(), lbYear.getText(), lbPrice.getText()),
                    String.format("%s : Model: %s Year of release : %s Price : %s",
                    badItem, lbNameProduct.getText(), lbYear.getText(), lbPrice.getText()));

        }
    }
}
