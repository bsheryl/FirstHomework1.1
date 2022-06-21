package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * В данном классе происхоодит ввод в поле ввода Google заданного слова и его поиск
 */
public class GladiolusBeforeSearch {

    protected WebDriver chromeDriver;
    protected WebElement searchField;
//    protected WebElement searchButton;

    /**
     * Конструктор для создания страницы до начало поиска
     * @param chromeDriver указатель на браузер
     */
    public GladiolusBeforeSearch(WebDriver chromeDriver) {
        this.chromeDriver = chromeDriver;
//        this.searchField = chromeDriver.findElement(By.id("text"));
        this.searchField = chromeDriver.findElement(By.xpath("//input[@title='Поиск']"));
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
