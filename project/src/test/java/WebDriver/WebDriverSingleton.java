package WebDriver;

import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class WebDriverSingleton {

    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    @Step("Создание браузера и получение экземпляра WebDriver")
    public static WebDriver getInstance() {
        if (driver.get() == null) {
            driver.set(new ChromeDriver());
            driver.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
            Logger log = LogManager.getLogger(WebDriverSingleton.class);
            log.info("Браузер создан");
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