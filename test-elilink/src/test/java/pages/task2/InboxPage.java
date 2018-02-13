package pages.task2;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by skort on 29.10.2016.
 */
public class InboxPage {

    private WebDriver driver;

    private By locatorInboxMails = new By.ByXPath("html/body/div[7]/div[3]/div/div[2]/div[1]/div[2]/div/div/div/div[2]/div/div[1]/div/div[1]/div[7]/div/div[1]/div[2]/div/table/tbody/tr/td[6]/div/div/div");

    private static final Logger logger = Logger.getLogger(InboxPage.class);

    public InboxPage(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Передаем параменты того что будем искать в почте, слово "Selenium",
     * и передаем, что актуальным результатом у нас будет 3 слова.
     * В List<WebElement> links заносим всю найденую информацию,
     * Проходимся циклом по результатам применяя  регулярку, добавляем найденную инфу
     * в List<String> mailResult в виде текста, далее добавляем в List<List<String>> results,
     * сравниваем найденые результаты с актуальными, переходим на другую страницу, написания письма.
     *
     * @param search
     * @param actual
     */
    public void checkInbox(String search, String actual) {
        Pattern pattern = Pattern.compile(search);
        List<WebElement> links = driver.findElements(locatorInboxMails);

        List<String> mailResult = new ArrayList<>();

        List<List<String>> results = new ArrayList<>();

        for (WebElement result : links) {
            Matcher m = pattern.matcher(result.getText());
            if (m.find()) {
                mailResult.add(result.getText());
                results.add(new ArrayList<>(mailResult));
            }
        }

        int resultSize = results.size();
        int actualResult = Integer.parseInt(actual);

        String testResult = String.format("Results which was found by request %d\n%s", results.size(), results);

        if (resultSize == actualResult) {
            String actResult = String.format("Search result size: %d equals Actual result size: %d", resultSize, actualResult);
            logger.info(actResult);
            logger.info(String.format("%s", testResult));
        } else {
            String notActResult = String.format("Search result size: %d not equals Actual result size: %d", resultSize, actualResult);
            logger.info(notActResult);
            logger.info(String.format("%s", testResult));
        }
    }
}
