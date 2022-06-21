package ru.bellintegrator;

import io.qameta.allure.Feature;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import pages.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @version 1.1
 * В данном классе происходит непосредственно тестирование.
 * @see ru.bellintegrator.BaseTest
 */
public class Tests extends BaseTest {

    /**
     * Задание 1.1
     * В данном методе происходит тестирование с применением паттерна Page Object.
     * Метод вставляет слово "гладиолус" в поисковик Яндекса и проверяет, есть ли ссылка на Википедию
     * @param keyWords принимаюет искомое слово
     * @param result принимает искомый ресурс
     */
    @Feature("Проверка результатов поиска")
    @DisplayName("Проверка результатов поиска с помощью PO")
    @ParameterizedTest(name = "{displayName}: {arguments}")
    @CsvSource({"гладиолус, Википедия"})
    public void testFirst(String keyWords, String result) {
        chromeDriver.get("https://www.google.com/");
        GladiolusBeforeSearch beforeSearch = new GladiolusBeforeSearch(chromeDriver);
        GladiolusAfterSearch gladiolusAfterSearch = new GladiolusAfterSearch(chromeDriver);
        beforeSearch.find(keyWords);
        Assertions.assertTrue(gladiolusAfterSearch.getResult().stream().anyMatch(x-> x.getText().contains(result)),
                "Статья про " + keyWords + " на сайте " + result + " на странице найдена");
    }

    /**
     * Задание 1.2
     * В данном методе происходит тестирование с применением паттерна Page Object.
     * Метод вставляет слово "Отрытие" в поисковик Яндекса и проверяет, есть ли ссылка "Частным клиентам | Банк Открытие".
     * После метод переходит на сайт Открытия и проверяет курс валют.
     * @param keyWords принимаюет искомое слово
     * @param result принимает искомую ссылку
     */
    @Feature("Проверка результатов поиска")
    @DisplayName("Проверка результатов поиска с помощью PO")
    @ParameterizedTest(name = "{displayName}: {arguments}")
    @CsvSource({"Открытие, Частным клиентам | Банк Открытие"})
    public void testSecond(String keyWords, String result) {
        chromeDriver.get("https://yandex.ru/");
        OpenBeforeSearch beforeSearch = new OpenBeforeSearch(chromeDriver);
        OpenAfterSearch openAfterSearch = new OpenAfterSearch(chromeDriver);
        beforeSearch.find(keyWords);
        Assertions.assertTrue(openAfterSearch.getResult().stream().anyMatch(x-> x.getText().contains(result)),
                "Ссылка " + result + " на странице не найдена");
        for (WebElement element : openAfterSearch.getResult()) {
            if (element.getText().contains(result)) {
                element.click();
                break;
            }
        }
        List<String> tabs = new ArrayList<>(chromeDriver.getWindowHandles());
        chromeDriver.switchTo().window(tabs.get(1));
        Open open = new Open(chromeDriver);
        List<WebElement> rates = open.getRates();
        Assertions.assertTrue(Double.parseDouble(rates.get(0).getText().replace(",", "."))
                < Double.parseDouble(rates.get(1).getText().replace(",", ".")),
                "Банк покупает USD дороже, чем продает");
        Assertions.assertTrue(Double.parseDouble(rates.get(2).getText().replace(",", "."))
                        < Double.parseDouble(rates.get(3).getText().replace(",", ".")),
                "Банк покупает EUR дороже, чем продает");
    }

    /**
     * Задание 1.3
     * В данном методе происходит тестирование с применением паттерна Page Factory.
     * Метод вставляет слово "Таблица" в поисковик Яндекса и проверяет, есть ли ссылка "Таблица - Википедия".
     * После метод переходит на сайт Википедии и проверяет таблицу преподавателей.
     * @param keyWords принимаюет искомое слово
     * @param result принимает искомую ссылку
     */
    @Feature("Проверка результатов поиска")
    @DisplayName("Проверка результатов поиска с помощью PF")
    @ParameterizedTest(name = "{displayName}: {arguments}")
    @CsvSource({"таблица, Таблица — Википедия"})
    public void testThird(String keyWords, String result) {
        chromeDriver.get("https://yandex.ru/");
        PageFactoryTable pageFactoryTable = PageFactory.initElements(chromeDriver, PageFactoryTable.class);
        pageFactoryTable.find(keyWords);
        Assertions.assertTrue(pageFactoryTable.getResult().stream().anyMatch(x-> x.getText().contains(result)),
                "В результатах поиска нет ссылки на " + result);
        for (WebElement element : pageFactoryTable.getResult()) {
            if (element.getText().contains(result)) {
                element.click();
                break;
            }
        }
        List<String> tabs = new ArrayList<>(chromeDriver.getWindowHandles());
        chromeDriver.switchTo().window(tabs.get(tabs.size() - 1));
        Assertions.assertTrue(pageFactoryTable.getTable().getText().contains("Преподаватели кафедры программирования"));
        Assertions.assertTrue(pageFactoryTable.getTeachers().get(1).getText().equals("Сергей")
                && pageFactoryTable.getTeachers().get(2).getText().equals("Владимирович"),
                "Первый в списке не Сергей Владимирович");
        Assertions.assertTrue(pageFactoryTable.getTeachers().get(pageFactoryTable.getTeachers().size() - 2).getText().equals("Сергей")
                && pageFactoryTable.getTeachers().get(pageFactoryTable.getTeachers().size() - 1).getText().equals("Адамович"),
                "Последний в списке не Сергей Адамович");
    }
}
