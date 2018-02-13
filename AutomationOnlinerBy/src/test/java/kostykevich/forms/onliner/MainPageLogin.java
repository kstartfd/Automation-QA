package kostykevich.forms.onliner;

import org.openqa.selenium.By;
import webdriver.BaseForm;
import webdriver.elements.Button;


/**
 * Created by fd on 18.06.2016.
 */
public class MainPageLogin extends BaseForm {
    //локатор кнопки Входа на главной странице
    private Button logInButton = new Button(By.xpath("//div[@class='auth-bar__item auth-bar__item--text']"), "Login Button");

    /**
     * Проверяет, что страница содержит
     * панель для входа в аккаунт
     */
    public MainPageLogin() {
        super(By.xpath("//div[@id='userbar']"), "User bar");
    }

    /**
     * Нажатие по кнопке "Вход"
     */
    public void logIn(){
        navigateBarMenu(logInButton);
    }

    /**
     * Навигация по бар меню
     * @param loginButton
     */
    private void navigateBarMenu(Button loginButton) {
        loginButton.clickAndWait();
    }


}
