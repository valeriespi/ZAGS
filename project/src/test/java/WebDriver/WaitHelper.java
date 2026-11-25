package WebDriver;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import java.time.Duration;

public class WaitHelper {

    private static final Duration TIMEOUT = Duration.ofSeconds(30);

    private WaitHelper() {
    }

    @Step("Ожидание доступности элемента: {element}")
    public static void waitForEnabled(WebDriver driver, WebElement element) {
        new FluentWait<>(driver)
                .withTimeout(TIMEOUT)
                .pollingEvery(Duration.ofMillis(500))
                .ignoring(NoSuchElementException.class, StaleElementReferenceException.class)
                .until(d -> element.isEnabled());
    }

    @Step("Ожидание появления элемента: {locator}")
    public static void waitForVisible(WebDriver driver, By locator) {
        new FluentWait<>(driver)
                .withTimeout(TIMEOUT)
                .pollingEvery(Duration.ofMillis(500))
                .ignoring(NoSuchElementException.class)
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
}
