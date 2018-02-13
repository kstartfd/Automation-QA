package kostykevich.forms.onliner.baraholka;

import org.openqa.selenium.By;
import webdriver.BaseForm;
import webdriver.elements.Button;

/**
 * Created by fd on 29.06.2016.
 */
public class BaraholkaPage extends BaseForm {
    //Локатор кнопки "Разместить объявлениев Барахолке!"
    private Button btnBaraholka = new Button(By.xpath("//span[contains(.,'Разместить объявлениев Барахолке!')]"), "Button baraholka");

    //Проверяем что страница содержит заголово "Барахолка"
    public BaraholkaPage() {
        super(By.xpath("//h1[contains(.,'Барахолка')]"), "Baraholka Header");
    }

    /**
     * Нажимаем на кнопку размещения объявления
     */
    public void clickAd() {
        btnBaraholka.clickAndWait();
    }

}
