package tests.task1;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.*;
import pages.task1.FindResultPage;
import pages.task1.MainPageTutBy;

import java.io.File;
import java.util.concurrent.TimeUnit;

/**
 * Created by skort on 29.10.2016.
 */
public class TestTutBy {

    private WebDriver driver;
    private String request;
    private String search;

    private static final Logger logger = Logger.getLogger(TestTutBy.class);

    /**
     * Кроссбраузерность практически не работает, в фаерфоксе
     * тесты проходят хорошо, в данном случае показан пример
     * реализации кроссбраузерных тестов
     * Считываем параметры до класса
     * @param browser передаем из сьюты (firefox, ie or chrome)
     * @param url сайт на который будем переходит
     * @throws Exception
     */
    @BeforeClass(alwaysRun = true)
    @Parameters({"browser", "url"})
    public void setUp(String browser, String url) throws Exception {
        if(browser.equalsIgnoreCase("firefox")) {
            driver = new FirefoxDriver();
        } else if (browser.equalsIgnoreCase("ie")) {
            System.setProperty("webdriver.ie.driver", File.separator + "test-elilink" + File.separator +
                    "src" + File.separator + "test" + File.separator + "resources" + File.separator + "IEDriverServer.exe");
            driver = new InternetExplorerDriver();
        } else if (browser.equalsIgnoreCase("chrome")) {
            System.setProperty("webdriver.ie.driver", File.separator + "test-elilink" + File.separator +
                    "src" + File.separator + "test" + File.separator + "resources" + File.separator + "chromedriver.exe");
            driver = new ChromeDriver();
        }

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(url);
        logger.info("Open site");
    }

    /**
     * Считываем параметры до теста
     * @param request
     * @param search
     */
    @BeforeTest
    @Parameters({"request", "search"})
    private void readParam(String request, String search) {
        this.request = request;
        this.search = search;
    }

    @Test
    public void testTutByFindResults () {
        MainPageTutBy mainPageTutBy = new MainPageTutBy(driver);
        logger.info("Got access by a main page.");

        mainPageTutBy.inputSearchRequest(request);
        logger.info("Input request data in text field and find result.");

        FindResultPage resultPage = new FindResultPage(driver);
        logger.info("Find correct result.");

        resultPage.getResult(search);
        logger.info("The end of test.");
    }

    /**
     * Close browser
     */
    @AfterClass
    public void tearDown () throws Exception {
        driver.quit();
    }
}
