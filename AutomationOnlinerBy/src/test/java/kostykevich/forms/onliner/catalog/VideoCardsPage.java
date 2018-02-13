package kostykevich.forms.onliner.catalog;

import org.openqa.selenium.By;
import webdriver.BaseForm;
import webdriver.elements.CheckBox;
import webdriver.elements.Label;


/**
 * Created by fd on 22.06.2016.
 */
public class VideoCardsPage extends BaseForm {
    //Локатор открытия правого подменю
    private Label labelOpenRightMenu = new Label(By.xpath("//a[@class='schema-order__link']"), "Open right submenu");
    //Локатор "Новые"
    private Label labelSelectNewItem = new Label(By.xpath("//div[@class='schema-order__item']//span[text()='Новые']"), "Select New Item");
    //Локатор чекбокса производителя MSI
    private CheckBox checkboxMSI = new CheckBox(By.xpath("//label[@class='schema-filter__checkbox-item']/span[text()='MSI']"), "Checkbox MSI");
    //Локатор чекбокса производителя чипа Nvidia
    private CheckBox checkboxNvidia = new CheckBox(By.xpath("//label[@class='schema-filter__checkbox-item']/span[text()='NVIDIA']"), "CheckBox Nvidia");
    //Локатор чекбокса размера шины 256 бит
    private CheckBox checkboxTire = new CheckBox(By.xpath("//span[contains(.,'256 бит')]"), "CheckBox Tire");
    //Локатор результата вывода на страницу
    private Label labelResultVideocards = new Label(By.xpath("//div[@class='schema-product__title']/a"));

    /**
     * Проверяет сожержит ли страница
     * Заголовок "Видеокарты"
     */
    public VideoCardsPage() {
        super(By.xpath("//h1[text()='Видеокарты']"), "Page video cards");
    }

    /**
     * Сортируем по "Новые"
     * Ставим чексбокс производителя MSI,
     * производителя чипа Nvidia,
     * шину 256 бит
     *
     */
    public void sortByNew() {
        labelOpenRightMenu.clickAndWait();
        labelSelectNewItem.clickAndWait();
        navigateLeftMenu(checkboxMSI);
        navigateLeftMenu(checkboxNvidia);
        navigateLeftMenu(checkboxTire);
    }

    /**
     * Навигация по чекбоксам
     * @param chkbx
     */
    private void navigateLeftMenu(CheckBox chkbx){
        chkbx.checkAndWait();
    }

    /**
     * принимаем параметры их xml
     * Заносим результат-ссылки с локатора в String
     * и проходим циклом по ним, открывая каждый товар на новой странице,
     * проверяя его на соответствие
     * @param release
     * @param name
     */
    public void assertRelease(String release, String name) {
        for (String url : labelResultVideocards.getAttributeElements()) {
            browser.navigate(url);
            VideoCardsResultPage videoCardsResultPage = new VideoCardsResultPage();
            videoCardsResultPage.assertParameters(release, name);
        }
    }
}
