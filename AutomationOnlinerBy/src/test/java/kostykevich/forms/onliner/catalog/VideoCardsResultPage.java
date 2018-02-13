package kostykevich.forms.onliner.catalog;

import org.openqa.selenium.By;
import webdriver.BaseForm;
import webdriver.elements.Label;

/**
 * Created by fd on 22.06.2016.
 */
public class VideoCardsResultPage extends BaseForm {
    //Локатор выхода продукта на рынок
    private  Label labelRelease = new Label(By.xpath("//td[contains(text(),'Дата выхода на рынок')]/following-sibling::td"), "Label Release");
    //Локатор наиминования продукта
    private Label lbNameProduct = new Label(By.xpath("//h2[@class='catalog-masthead__title']")) ;

    private String goodItem = "Good:";
    private String badItem = "Bad:";

    /**
     * Проверяем на каждой странице, что есть заголовок
     */
    public VideoCardsResultPage() {
        super(By.xpath("//h2[@class='catalog-masthead__title']"), "Page og product");
    }

    /**
     * Сравниваем наименование товара на странице и переданного из параметров
     * Сравниваем год выпуска товара на странице и переданного из параметров
     * Выводим в лог результат
     * @param release
     * @param name
     */
    public void assertParameters(String release, String name) {
        boolean flag = true;

        if (lbNameProduct.getText().contains(name))
            flag = true;

        String minYearString = labelRelease.getText();
        int year = Integer.parseInt(minYearString.substring(2, 4));
        int minYearLocal = Integer.parseInt(release.substring(2, 4));
        if (year > minYearLocal)
            flag = true;



        doAssert(flag, String.format("%s : Model: %s Year of release : %s", goodItem, lbNameProduct.getText(), labelRelease.getText()),
                       String.format("%s : Model: %s Year of release : %s", badItem, lbNameProduct.getText(), labelRelease.getText()));
    }
}
