package pages.task3;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.util.List;

/**
 * Created by skort on 29.10.2016.
 */
public class PassengerInfoPage {

    private WebDriver driver;
    private WebElement element;

    private By locatorMenuPrefix = new By.ByXPath("//li[contains(.,'Mr') and @id='ui-id-3']");
    private By locatorMenuSpanPrefix = new By.ByXPath("//span[@id='prefix0-button']");

    private By locatorTxtBoxFirstName = new By.ByXPath("//input[@id='firstName0']");
    private By locatorTxtBoxMiddleName = new By.ByXPath("//input[@id='middlename0']");
    private By locatorTxtBoxLastName = new By.ByXPath("//input[@id='lastName0']");

    private By locatorMenuSpanGender = new By.ByXPath("//span[@id='gender0-button']");
    private By locatorMenuGender = new By.ByXPath("//li[contains(.,'Male')]");

    private By locatorMenuSpanDateOfBirth = new By.ByXPath("//span[@id='month0-button']");
    private By locatorMenuDateOfBirth  = new By.ByXPath("//li[contains(.,'July')]");

    private By locatorMenuSpanDayOfBirth  = new By.ByXPath("//span[@id='day0-button']");
    private By locatorMenuDayOfBirth  = new By.ByXPath("//li[contains(.,'25')]");

    private By locatorMenuSpanYearOfBirth  = new By.ByXPath("//span[@id='year0-button']");
    private By locatorMenuYearOfBirth  = new By.ByXPath("//li[contains(.,'1989')]");


    private By locatorEmergencyFirstName = new By.ByXPath("//input[@id='emgcFirstName_0']");
    private By locatorEmergencyLastName = new By.ByXPath("//input[@id='emgcLastName_0']");

    private By locatorEmergencySpanCountry = new By.ByXPath("//span[@id='emgcCountry_0-button']");
    private By locatorEmergencyCountry = new By.ByXPath("//ul[@id='emgcCountry_0-menu']/li[19]");


    private By locatorEmergencyPhoneNumber = new By.ByXPath("//input[@id='emgcPhoneNumber_0']");


    private By locatorSpanDeviceType = new By.ByXPath("//span[@id='deviceType-button']");
    private By locatorDeviceType = new By.ByXPath("//li[contains(.,'Home')]");

    private By locatorSpanCountry = new By.ByXPath("//span[@id='countryCode0-button']");
    private By locatorCountry = new By.ByXPath("//ul[@id='countryCode0-menu']/li[19]");

    private By locatorTxtPhoneNumber = new By.ByXPath("//input[@id='telephoneNumber0']");

    private By locatorTxtEmail = new By.ByXPath("//input[@id='email']");
    private By locatorTxtConfirmEmailAddress = new By.ByXPath("//input[@id='reEmail']");


    private By locatorBtnpaxReviewPurchaseBtn = new By.ByXPath("//button[@id='paxReviewPurchaseBtn']");



    public PassengerInfoPage(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Передаем все параметры, выбираем нужную информацию,
     * кликаем далее и переходим к 7 пункту, так как Step 6 - Trip Extras page (step depends on input info),
     * нет на сайте.
     * @param firstName
     * @param middleName
     * @param lastName
     * @param emergencyFirstName
     * @param emergencyLastName
     * @param emergencyPhoneNumber
     * @param phoneNumber
     * @param email
     */

    public void fillPassengerInformationFields (String firstName, String middleName, String lastName, String emergencyFirstName,
                                                String emergencyLastName, String emergencyPhoneNumber,
                                                String phoneNumber, String email) {

        navigateDropDown(locatorMenuSpanPrefix, locatorMenuPrefix);
        navigateTextBoxMenu(locatorTxtBoxFirstName, firstName);
        navigateTextBoxMenu(locatorTxtBoxMiddleName, middleName);
        navigateTextBoxMenu(locatorTxtBoxLastName, lastName);

        navigateDropDown(locatorMenuSpanGender, locatorMenuGender);
        navigateDropDown(locatorMenuSpanDateOfBirth,  locatorMenuDateOfBirth);
        navigateDropDown(locatorMenuSpanDayOfBirth,  locatorMenuDayOfBirth);
        navigateDropDown(locatorMenuSpanYearOfBirth ,  locatorMenuYearOfBirth);

        navigateTextBoxMenu(locatorEmergencyFirstName, emergencyFirstName);
        navigateTextBoxMenu(locatorEmergencyLastName, emergencyLastName);
        navigateDropDown(locatorEmergencySpanCountry, locatorEmergencyCountry);
        navigateTextBoxMenu(locatorEmergencyPhoneNumber, emergencyPhoneNumber);

        navigateDropDown(locatorSpanDeviceType, locatorDeviceType);
        navigateDropDown(locatorSpanCountry, locatorCountry);

        navigateTextBoxMenu(locatorTxtPhoneNumber, phoneNumber);
        navigateTextBoxMenu(locatorTxtEmail, email);
        navigateTextBoxMenu(locatorTxtConfirmEmailAddress, email);

        navigateMenu(locatorBtnpaxReviewPurchaseBtn);

    }

    /**
     * Принимает параметры из двух локаторов,
     * первый открывает Комбобокс, второй по локатору
     * выбирает нужную инфу
     * @param loc
     * @param loc2
     * @return
     */
    private WebElement navigateDropDown(By loc, By loc2) {

        WebElement triggerDropDown = driver.findElement(loc);
        triggerDropDown.click();

        triggerDropDown = driver.findElement(loc2);
        triggerDropDown.click();

        return element;
    }

    private WebElement navigateTextBoxMenu(By loc, String text) {
        element = driver.findElement(loc);
        element.clear();
        element.sendKeys(text);
        return element;
    }


    private WebElement navigateMenu(By loc) {
        element = driver.findElement(loc);
        element.click();
        return element;
    }
}
