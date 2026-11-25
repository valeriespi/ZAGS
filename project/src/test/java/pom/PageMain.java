package pom;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import webDriver.WaitHelper;

public class PageMain {

    @FindBy(xpath = "//button[text() = 'Войти как пользователь']")
    private WebElement buttonLoginAsUser;

    @FindBy(xpath = "//button[text() = 'Войти как администратор']")
    private WebElement buttonLoginAsAdmin;

    private final WebDriver driver;

    public PageMain(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @Step("Ожидание доступности кнопок входа на главной странице")
    public void waitForButtonsEnabled() {
        WaitHelper.waitForVisible(driver, By.xpath("//button[text() = 'Войти как пользователь']"));
        WaitHelper.waitForVisible(driver, By.xpath("//button[text() = 'Войти как администратор']"));
        WaitHelper.waitForEnabled(driver, buttonLoginAsUser);
        WaitHelper.waitForEnabled(driver, buttonLoginAsAdmin);
    }

    @Step("Нажатие кнопки \"Войти как пользователь\"")
    public void clickButtonLoginAsUser() {
        WaitHelper.waitForEnabled(driver, buttonLoginAsUser);
        buttonLoginAsUser.click();
        WaitHelper.waitForVisible(driver, By.xpath("//label[contains(normalize-space(), 'Фамилия')]/following::input[1]"));
    }

    @Step("Нажатие кнопки \"Войти как администратор\"")
    public void clickButtonLoginAsAdmin() {
        WaitHelper.waitForEnabled(driver, buttonLoginAsAdmin);
        buttonLoginAsAdmin.click();
        WaitHelper.waitForVisible(driver, By.xpath("//label[contains(normalize-space(), 'Фамилия')]/following::input[1]"));
    }
}
