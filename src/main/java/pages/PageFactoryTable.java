package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.List;

public class PageFactoryTable {

    private WebDriver chromeDriver;

    @FindBy(how = How.ID, id = "text")
    WebElement searchField;

    @FindBy(how = How.XPATH, using = "//button[@class='button mini-suggest__button button_theme_search button_size_search i-bem button_js_inited']")
    WebElement searchButton;

    @FindBy(how = How.XPATH, using = "//span[@class='OrganicTitleContentSpan organic__title']")
    List<WebElement> result;

    @FindBy(how = How.XPATH, using = "//*[contains(text(),'Преподаватели кафедры программирования')]")
    WebElement table;

    @FindBy(how = How.XPATH, using = "//table[@class='wikitable']/caption[contains(text(),'Преподаватели')]/../tbody/tr/td")
    List<WebElement> teachers;

    /**
     * Конструктор для создания страницы с результатами поиска
     * @param chromeDriver ссылка на браузер
     */
    public PageFactoryTable(WebDriver chromeDriver) {
        this.chromeDriver = chromeDriver;
    }

    /**
     * Метод для ввода слова в строку поисковика
     * @param word искомое слово
     */
    public void find(String word) {
        searchField.click();
        searchField.sendKeys(word);
        searchButton.click();
    }

    public List<WebElement> getResult() {
        return result;
    }

    public WebElement getTable() {
        return table;
    }

    public List<WebElement> getTeachers() {
        return teachers;
    }
}
