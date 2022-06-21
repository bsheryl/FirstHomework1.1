package ru.bellintegrator;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

/**
 * Класс для работы с браузером
 */
public class BaseTest {

    protected WebDriver chromeDriver;

    /**
     * Данный метод открывает страницу в браузере.
     * Метод выполняется перед каждым тестом.
     */
    @BeforeEach
    public void openWindow() {
        System.setProperty("webdriver.chrome.driver", System.getenv("CHROME_DRIVER"));
        chromeDriver = new ChromeDriver();
        chromeDriver.manage().window().maximize();
        chromeDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }

    /**
     * Данный метод закрывает браузер полностью.
     * Метод выполняется после каждого теста.
     */
    @AfterEach
    public void closeWindow() {
        chromeDriver.quit();
    }
}
