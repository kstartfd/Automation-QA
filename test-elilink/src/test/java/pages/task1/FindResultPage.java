package pages.task1;


import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Created by fd on 16.06.2016.
 */
public class FindResultPage {

    private WebDriver driver;

    private By findRequestResult = By.xpath("//li[contains (@class, 'b-results__li')]/h3/a");

    private static Logger logger = Logger.getLogger(FindResultPage.class);

    public FindResultPage(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Передаем в метод, простую регулярку, то что будем искать из xml,
     * ищем элементы по локатору List<WebElement> links.
     * Выводим размер найденых результатов,
     * Проходимся циклом по найдеым результатам, если ничего не нашло,
     * выбрасывает WebDriverException,
     * иначе резуьтаты поиска.
     * @param search
     */
    public void getResult(String search) {
        Pattern pattern = Pattern.compile(search);
        List<WebElement> links = driver.findElements(findRequestResult);

        List<String> results = new ArrayList<>();

        String testResult = String.format("Results which was found by request %d", links.size());

        logger.info(String.format("%s", testResult));

        for (WebElement result : links) {
            Matcher m = pattern.matcher(result.getText());
            if (m.find()) {
                results.add(result.getAttribute("href"));
            }
        }
        if (results.isEmpty()) {
            throw new WebDriverException();
        } else {
            for (String url : results) {
                driver.navigate().to(url);
            }
        }

    }
}

