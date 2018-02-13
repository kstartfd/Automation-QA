package org.kostykevich.forms;

import org.openqa.selenium.By;
import webdriver.BaseForm;
import webdriver.elements.Label;

/**
 * Created by fd on 18.06.2016.
 */
public class CatalogPage extends BaseForm {

    private Label labelPathToTVSet = new Label(By.linkText("Телевизоры"), "Open onliner.by TV page");

    /**
     * Проверяем, что на странице,
     * проверка по главному бар меню
     */
    public CatalogPage() {
        super(By.cssSelector(".catalog-bar-main"), " Catalog bar is present");
    }

    /**
     * Переходим в Телевизоры
     * @return
     */
    public TVPage openTVSet() {
        navigateBarPanel(labelPathToTVSet);
        return new TVPage();
    }

    private void navigateBarPanel(Label pathToTv) {
        pathToTv.click();
    }
}
