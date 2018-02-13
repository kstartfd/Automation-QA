package tests.task3;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.*;
import pages.task2.EmailPage;
import pages.task2.InboxPage;
import pages.task2.LoginPage;
import pages.task2.MainPageGmail;
import pages.task3.*;
import tests.task1.TestTutBy;

import java.util.concurrent.TimeUnit;

/**
 * Created by skort on 29.10.2016.
 */
public class TestDelta {
    private WebDriver driver;

    private String from;
    private String to;
    private String departDate;
    private String returnDate;
    private String firstName;
    private String middleName;
    private String lastName;

    private String emergencyFirstName;
    private String emergencyLastName;
    private String emergencyPhoneNumber;

    private String phoneNumber;
    private String email;



    private static final Logger logger = Logger.getLogger(TestDelta.class);
    /**
     * @param  url  tut.by
     */
    @BeforeClass(alwaysRun = true)
    @Parameters({"url"})
    public void setUp(String url) throws Exception {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.get(url);
        driver.manage().window().maximize();
        logger.info("Open site");
    }

    @BeforeTest
    @Parameters({"from", "to", "departDate", "returnDate", "firstName", "middleName",
            "lastName", "emergencyFirstName", "emergencyLastName", "emergencyPhoneNumber",
            "phoneNumber" , "email"})
    public void readParam(String from, String to, String departDate, String returnDate,
                          String firstName, String middleName, String lastName,
                          String emergencyFirstName,String emergencyLastName, String emergencyPhoneNumber,
                          String phoneNumber, String email) {
        this.from = from;
        this.to = to;
        this.departDate = departDate;
        this.returnDate = returnDate;

        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;

        this.emergencyFirstName = emergencyFirstName;
        this.emergencyLastName = emergencyLastName;
        this.emergencyPhoneNumber = emergencyPhoneNumber;

        this.phoneNumber = phoneNumber;
        this.email = email;
    }



    @Test
    public void testDelta () {
        MainPageDelta mainPageDelta = new MainPageDelta(driver);
        logger.info("Input data.");
        mainPageDelta.selectData(from, to, departDate, returnDate);

        String data = String.format("City From: %s, City To: %s, Depart Date: %s, Return Date: %s", from, to, departDate, returnDate);
        logger.info(data);

        TicketsSelectionPage ticketsSelectionPage = new TicketsSelectionPage(driver);

        TripSummaryPage tripSummaryPage = ticketsSelectionPage.selectTickets();
        logger.info("Select tickets.");

        tripSummaryPage.tripSummarySubmit();
        logger.info("Click Continue.");

        PassengerInfoPage passengerInfoPage = new PassengerInfoPage(driver);
        logger.info("Fill in all required Passenger Information fields.");
        passengerInfoPage.fillPassengerInformationFields(firstName, middleName, lastName,
                                                         emergencyFirstName, emergencyLastName,
                                                         emergencyPhoneNumber, phoneNumber, email);

        String dataPassenger = String.format("First Name: %s, Middle Name: %s, Last Name: %s,\n" +
                        "Emergency First Name: %s, Emergency Last Name: %s, Emergency Phone Number: %s,\n" +
                        "Phone number: %s, Email: %s",
                                             firstName, middleName, lastName,
                                             emergencyFirstName, emergencyLastName, emergencyPhoneNumber,
                                             phoneNumber, email);
        logger.info(dataPassenger);

        logger.info("Click continue.");

        CreditCardInfoPage creditCardInfoPage = new CreditCardInfoPage(driver);
        logger.info("Check Button 'CompletePurchase'");
        creditCardInfoPage.checkBtnCompletePurchase();

        logger.info("\n********************************************************\n" +
                    "*********************Test Complete.*********************\n" +
                    "********************************************************");

    }

    /**
     * Close browser
     */
    @AfterClass
    public void tearDown () throws Exception {
        driver.quit();
    }
}
