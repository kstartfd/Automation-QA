package org.kostykevich.forms;

import org.openqa.selenium.By;
import webdriver.BaseForm;
import webdriver.elements.Label;


/**
 * Created by fd on 18.06.2016.
 */
public class MainPage extends BaseForm {

    private Label labelPathToCatalog = new Label(By.cssSelector("span.b-main-navigation__text")," Open onliner.by catalog");
    private Label labelOnlinerLogo = new Label(By.cssSelector(".onliner_logo.retina-off")," Onliner.by Logo");


    public MainPage() {
        super(By.xpath("//div[@class='b-top-actions']"), " Main page  is present");
    }

    /**
     * Проверяем, есть ли лого на сайте
     */
    public void assertLogo(){
        browser.waitForPageToLoad();
        assert(labelOnlinerLogo.isPresent());
    }
    /**
     * Переходим на страницу с каталогом
     *
     * @return
     */
    public CatalogPage openCatalog() {
        navigateMenu(labelPathToCatalog);
        return new CatalogPage();
    }

    private void navigateMenu(Label pathToCatalog) {
        pathToCatalog.click();
    }

}
