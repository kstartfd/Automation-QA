package kostykevich.forms.onliner;

import org.openqa.selenium.By;
import webdriver.BaseForm;
import webdriver.elements.BaseElement;
import webdriver.elements.Button;
import webdriver.elements.Label;
import webdriver.elements.TextBox;

/**
 * Created by fd on 22.06.2016.
 */
public class LoginPage extends BaseForm {
    //локатор кнопки Входа
    private Button buttonUserLogin = new Button(By.xpath("//button[@type='submit']"), "Login");
    //локатор поля ввода логина
    private TextBox textBoxLogin = new TextBox(By.xpath("//input[contains (@class, 'auth-box__input') and contains (@type, 'text')]"), "Login data");
    //локатор поля ввода пароля
    private TextBox textBoxPassword = new TextBox(By.xpath("//input[@type='password']"), "Password data");
    //локатор "Каталога"
    private Label labelCatalog = new Label(By.xpath("//a[@class='b-main-navigation__link']//span[text()='Каталог']"), "Catalog Link");
    //локатор "Барахолки"
    private Label labelBaraholka = new Label(By.xpath("//span[contains(.,'Барахолка')]"), "Catalog Link");


    public LoginPage() {
        super(By.xpath("//button[@type='submit']"), "Login page");
    }

    /**
     * Ввод логина и пароля, и клик по кнопки "Войти"
     * @param login
     * @param password
     */
    public void inputDataUserAndEnter(String login, String password){
        textBoxLogin.type(login);
        textBoxPassword.type(password);
        buttonUserLogin.clickAndWait();
    }
    /**
     * Нажатие по сслыке "Каталог"
     */
    public void toCatalog(){
        navigateTopMenu(labelCatalog);
    }
    /**
     * Нажатие по сслыке "Барахолка"
     */
    public void toBaraholka(){
        navigateTopMenu(labelBaraholka);
    }
    /**
     * Навигация по меню
     */
    private void navigateTopMenu(BaseElement e) {
        e.clickAndWait();
    }

}
