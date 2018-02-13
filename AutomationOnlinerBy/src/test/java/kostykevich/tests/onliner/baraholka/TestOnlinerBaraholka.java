package kostykevich.tests.onliner.baraholka;

import kostykevich.forms.onliner.LoginPage;
import kostykevich.forms.onliner.MainPageLogin;
import kostykevich.forms.onliner.baraholka.AdPage;
import kostykevich.forms.onliner.baraholka.AddedAdPage;
import kostykevich.forms.onliner.baraholka.BaraholkaPage;
import kostykevich.forms.onliner.catalog.CatalogPage;
import kostykevich.forms.onliner.catalog.VideoCardsPage;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import webdriver.BaseTest;

/**
 * Created by fd on 29.06.2016.
 */
public class TestOnlinerBaraholka extends BaseTest {

    private String login;
    private String password;
    private String section ;
    private String header;
    private String description;
    private String price ;



    @BeforeTest
    @Parameters({"login","password", "section", "header", "description",  "price"})
    public void readParameters( String login, String password, String section, String header, String description, String price) {
        this.login = login;
        this.password = password;
        this.section = section;
        this.header = header;
        this.description = description;
        this.price = price;
    }

    public void runTest() {
        logger.step(1);
        MainPageLogin mainPageLogin = new MainPageLogin();
        mainPageLogin.logIn();
        LoginPage loginPage = new LoginPage();
        loginPage.inputDataUserAndEnter(login, password);
        loginPage.toBaraholka();

        logger.step(2);
        BaraholkaPage baraholkaPage = new BaraholkaPage();
        baraholkaPage.clickAd();

        logger.step(3);
        AdPage adPage = new AdPage();
        adPage.chooseSection(section);
        adPage.inputData(header, description, price);
        adPage.clickMyAd();

        logger.step(4);
        AddedAdPage addedAdPage = new AddedAdPage();
        addedAdPage.assertAd(header);
    }
}
