package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * В данном классе происхоодит ввод в поле ввода Яндекса заданного слова и его поиск
 */
public class OpenBeforeSearch {

    protected WebDriver chromeDriver;
    protected WebElement searchField;
//    protected WebElement searchButton;

    /**
     * Конструктор для создания страницы до начало поиска
     * @param chromeDriver указатель на браузер
     */
    public OpenBeforeSearch(WebDriver chromeDriver) {
        this.chromeDriver = chromeDriver;
        this.searchField = chromeDriver.findElement(By.id("text"));
//        this.searchButton = chromeDriver.findElement(By.xpath("//button[@class='button mini-suggest__button button_theme_search button_size_search i-bem button_js_inited']"));
    }

    /**
     * Метод для ввода слова в строку поисковика
     * @param word искомое слово
     */
    public void find(String word) {
        searchField.click();
        searchField.sendKeys(word);
        searchField.submit();
//        searchButton.click();
    }
}
