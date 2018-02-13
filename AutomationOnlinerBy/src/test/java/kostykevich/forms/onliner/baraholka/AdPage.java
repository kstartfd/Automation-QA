package kostykevich.forms.onliner.baraholka;

import org.openqa.selenium.By;
import webdriver.BaseForm;

import webdriver.elements.Button;
import webdriver.elements.ComboBox;
import webdriver.elements.TextBox;

/**
 * Created by fd on 29.06.2016.
 */
public class AdPage extends BaseForm {
    //Локатор комбобокса разделов"
    private ComboBox cmbSection = new ComboBox(By.xpath("//select[@name='f']"), "Select name");
    //Локатор текстбокса "Названия объявления"
    private TextBox txbHeader = new TextBox(By.xpath("//input[@name='subject']"), "Input header");
    //Локатор текстбокса "Описания"
    private TextBox txbDesc = new TextBox(By.xpath("//textarea[@name='message']"), "Desc");
    //Локатор текстбокса "Цены"
    private TextBox txbPrice = new TextBox(By.xpath("//input[@name='topic_price']"), "Price");
    //Локатор кнопки "Добавить объявление"
    private Button btnMyAd = new Button(By.xpath("//button[@name='formSubmitButton']"), "Add ad");

    /**
     * Проверяет по заголовку, что на странице создания объвления
     */
    public AdPage() {
        super(By.xpath("//h1[contains(.,'Новое объявление')]"), "Header New Ad");
    }

    /**
     * Выбор секции объявления, "Мобильные телефоны"
     * @param phone
     */
    public void chooseSection(String phone) {
        cmbSection.selectByVisibleText(phone);
    }


    /**
     * Ввод из параметров данных в текстбоксы
     * Заголовка объявления, описания и цены
      * @param header
     * @param description
     * @param price
     */
    public void inputData(String header, String description, String price) {
        txbHeader.type(header);
        txbDesc.type(description);
        txbPrice.type(price);
    }

    /**
     * Кпопка принятия создания объявления
     */
    public void clickMyAd() {
        btnMyAd.clickAndWait();
    }
}
