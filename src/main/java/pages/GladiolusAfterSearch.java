package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

/**
 * В данном классе происходит поиск по слову "Гладиолус"
 * @see GladiolusBeforeSearch
 */
public class GladiolusAfterSearch extends GladiolusBeforeSearch {

    private List<WebElement> result;

    WebDriverWait wait;

    /**
     * Конструктор для создания страницы с результатами поиска
     * @param chromeDriver ссылка на браузер
     */
    public GladiolusAfterSearch(WebDriver chromeDriver) {
        super(chromeDriver);
        wait = new WebDriverWait(chromeDriver, Duration.ofSeconds(30));
    }

    /**
     * Метод для поиска всех результатов поиска на странице
     * @return список выведенных результатов поиска на странице
     */
    public List<WebElement> getResult() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//html")));
        result = chromeDriver.findElements(By.xpath("//h3[@class='LC20lb MBeuO DKV0Md']"));
        return result;
    }
}
