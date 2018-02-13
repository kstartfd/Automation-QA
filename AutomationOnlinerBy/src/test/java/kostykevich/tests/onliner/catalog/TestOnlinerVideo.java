package kostykevich.tests.onliner.catalog;

import kostykevich.forms.onliner.LoginPage;
import kostykevich.forms.onliner.MainPageLogin;
import kostykevich.forms.onliner.catalog.CatalogPage;
import kostykevich.forms.onliner.catalog.VideoCardsPage;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import webdriver.BaseTest;

/**
 * Created by fd on 22.06.2016.
 */
public class TestOnlinerVideo extends BaseTest {

    private String release;
    private String name;
    private String login;
    private String password;

    @BeforeTest
    @Parameters({"release", "name", "login", "password"})
    public void readParameters(String release, String name, String login, String password) {
        this.release = release;
        this.name = name;
        this.login = login;
        this.password = password;
    }

    public void runTest() {
        logger.step(1);
        MainPageLogin mainPageLogin = new MainPageLogin();
        mainPageLogin.logIn();

        LoginPage loginPage = new LoginPage();
        loginPage.inputDataUserAndEnter(login, password);

        logger.step(2);
        loginPage.toCatalog();

        CatalogPage catalogPage = new CatalogPage();
        catalogPage.openBarMenu();

        logger.step(3);
        VideoCardsPage videoCardsPage = new VideoCardsPage();
        videoCardsPage.sortByNew();

        logger.step(4);
        videoCardsPage.assertRelease(release, name);
    }
}
