package WebDriver;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WaitHelper {

    private static final Duration TIMEOUT = Duration.ofSeconds(20);

    private WaitHelper() {
    }

    @Step("Ожидание видимости элемента по локатору")
    public static void waitForVisible(WebDriver driver, By locator) {
        new WebDriverWait(driver, TIMEOUT).until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    @Step("Ожидание видимости элемента формы")
    public static void waitForVisible(WebDriver driver, WebElement element) {
        new WebDriverWait(driver, TIMEOUT).until(ExpectedConditions.visibilityOf(element));
    }

    @Step("Ожидание кликабельности элемента")
    public static void waitForClickable(WebDriver driver, WebElement element) {
        new WebDriverWait(driver, TIMEOUT).until(ExpectedConditions.elementToBeClickable(element));
    }

    @Step("Клик по элементу с повторной попыткой, если он перекрыт оверлеем")
    public static void clickWithRetry(WebDriver driver, WebElement element) {
        try {
            element.click();
        } catch (ElementClickInterceptedException e) {
            new WebDriverWait(driver, TIMEOUT).until(d -> {
                try {
                    element.click();
                    return true;
                } catch (ElementClickInterceptedException intercepted) {
                    return false;
                }
            });
        }
    }
}
