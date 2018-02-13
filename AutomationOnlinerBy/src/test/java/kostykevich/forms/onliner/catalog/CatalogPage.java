package kostykevich.forms.onliner.catalog;

import org.openqa.selenium.By;
import webdriver.BaseForm;
import webdriver.elements.Label;

/**
 * Created by fd on 22.06.2016.
 */
public class CatalogPage extends BaseForm {
    //локатор "Компьютеры и сети"
    private Label labelComputers = new Label(By.cssSelector(".catalog-navigation-classifier__item-icon.catalog-navigation-classifier__item-icon_id_2"), "Tab Computers");
    //локатор "Видеокарты"
    private Label labelVideoCards = new Label(By.xpath("//a[@title='Видеокарты']"), "Video cards");

    /**
     * Првоеряет, содержит  ли страница
     * бар меню каталога
     */
    public CatalogPage() {
        super(By.xpath("//div[@class='catalog-bar-main']"), "Catalog bar main menu");
    }

    /**
     * Открытие бар меню и переход на
     * видеокарты
     */
    public void openBarMenu() {
        toVideoCards(labelComputers);
    }
    private void toVideoCards(Label openBarMenu){
        openBarMenu.clickAndWait();
        navigateBarMenu(labelVideoCards);
    }

    private void navigateBarMenu(Label video) {
        video.clickAndWait();
    }
}
