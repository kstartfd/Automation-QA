package tests.task2;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.*;
import pages.task2.*;
import tests.task1.TestTutBy;

import java.util.concurrent.TimeUnit;

/**
 * Created by skort on 29.10.2016.
 */
public class TestGmail {
    private WebDriver driver;
    private String name;
    private String password;

    private String search;
    private String actual;

    private String whom;
    private String subject;
    private String mail;


    private static final Logger logger = Logger.getLogger(TestTutBy.class);

    /**
     * @param  url  tut.by
     */
    @BeforeClass(alwaysRun = true)
    @Parameters({"url"})
    public void setUp(String url) throws Exception {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(url);
        driver.manage().window().maximize();
        logger.info("Open site");
    }

    @BeforeTest
    @Parameters({"name", "password", "search", "actual", "whom", "subject", "mail"})
    public void readParam(String name, String password, String search, String actual, String whom,
                          String subject, String mail) {
        this.name = name;
        this.password = password;
        this.search = search;
        this.actual = actual;
        this.whom = whom;
        this.subject = subject;
        this.mail = mail;
    }

    @Test
    public void testGmail () {

        MainPageGmail mainPageGmail = new MainPageGmail(driver);
        logger.info("Open account desk");
        mainPageGmail.enterGmail();


        LoginPage loginPage = new LoginPage(driver);
        logger.info("Enter name and password");
        loginPage.inputLoginData(name, password);

        EmailPage emailPage = new EmailPage(driver);
        logger.info("Verify parts of mail");
        emailPage.verifyPartsMail();


        InboxPage inboxPage = new InboxPage(driver);
        logger.info("Search result and equals actual result.");
        inboxPage.checkInbox(search, actual);


        WriteMailPage writeMailPage = new WriteMailPage(driver);
        logger.info("Write mail.");
        writeMailPage.writeMailAndLogout(whom, subject, mail);
        logger.info("Input data, send mail and logout.");

        logger.info(
                "************************************\n" +
                "************TEST COMPLETE***********\n" +
                "************************************");
    }

    /**
     * Close browser
     */
    @AfterClass
    public void tearDown () throws Exception {
        driver.quit();
    }
}
