package WebDriver;

import Common.Config;
import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class WebDriverSingleton {

    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    @Step("Создание браузера и получение экземпляра WebDriver")
    public static WebDriver getInstance() {
        if (driver.get() == null) {
            Logger log = LogManager.getLogger(WebDriverSingleton.class);
            if (Boolean.parseBoolean(System.getProperty("selenoid", "false"))) {
                log.info("Браузер создаётся в Selenoid: {}", Config.selenoidUrl());
                try {
                    driver.set(new RemoteWebDriver(new URL(Config.selenoidUrl()), new ChromeOptions()));
                } catch (MalformedURLException e) {
                    throw new IllegalStateException("Некорректный URL Selenoid: " + Config.selenoidUrl(), e);
                }
            } else {
                log.info("Создан локальный браузер Chrome");
                driver.set(new ChromeDriver());
            }
            driver.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        }
        return driver.get();
    }

    @Step("Закрытие браузера")
    public static void quit() {
        if (driver.get() != null) {
            try {
                driver.get().quit();
                LogManager.getLogger(WebDriverSingleton.class).info("Браузер закрыт");
            } catch (Exception e) {
                LogManager.getLogger(WebDriverSingleton.class)
                        .error("Ошибка при закрытии браузера", e);
            }
            driver.remove();
        }
    }
}
