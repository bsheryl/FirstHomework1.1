package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class Open {

    private WebDriver chromeDriver;

    private List<WebElement> rates;

    WebDriverWait wait;

    /**
     * Конструктор для создания страницы с результатами поиска
     * @param chromeDriver ссылка на браузер
     */
    public Open(WebDriver chromeDriver) {
        this.chromeDriver = chromeDriver;
        wait = new WebDriverWait(chromeDriver, Duration.ofSeconds(30));
    }


    /**
     * Метод для поиска курсов валют
     * @return список курсов валют
     */
    public List<WebElement> getRates() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//html")));
        rates = chromeDriver.findElements(By.xpath("//span[@class='main-page-exchange__rate']"));
        return rates;
    }
}
