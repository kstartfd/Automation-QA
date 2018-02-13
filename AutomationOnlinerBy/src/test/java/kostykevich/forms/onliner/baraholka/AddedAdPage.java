package kostykevich.forms.onliner.baraholka;

import org.openqa.selenium.By;
import webdriver.BaseForm;
import webdriver.elements.Label;

/**
 * Created by fd on 29.06.2016.
 */
public class AddedAdPage extends BaseForm {
    //Локатор заголовка объявления
    private Label lbHeader = new Label(By.xpath("//h1[@class='m-title-i title']"), "Header");

    /**
     * Проверяем что находимся в активном обьявлении, по кнопке "Ответить"
     */
    public AddedAdPage() {
        super(By.xpath("//span[contains(.,'Ответить')]"), "Button Answer");
    }

    /**
     * Если заголовок и передаваемый параметр соответствует,
     * выводим в лог "Объявление добавлено успешно" ,
     * иначе "Ошибка"
     * @param header
     */
    public void assertAd(String header) {

        if(lbHeader.getText().contains(header)) {
            info("Объявление добавлено успешно.");
        } else {
            info("Ошибка");
        }

    }
}
