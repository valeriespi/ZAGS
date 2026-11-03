package POM;

import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import WebDriver.WaitHelper;

public class MainPage {

    private static final Logger log = LogManager.getLogger(MainPage.class);

    @FindBy(xpath = "//button[text() = 'Войти как пользователь']")
    private WebElement buttonLoginAsUser;

    @FindBy(xpath = "//button[text() = 'Войти как администратор']")
    private WebElement buttonLoginAsAdmin;

    @FindBy(xpath = "//button[text() = 'Заказать справку']")
    private WebElement buttonOrderCertificate;

    private final WebDriver driver;

    public MainPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @Step("Нажатие кнопки \"Войти как пользователь\"")
    public void clickButtonLoginAsUser() {
        WaitHelper.waitForClickable(driver, buttonLoginAsUser);
        log.info("Главная страница: нажатие кнопки \"Войти как пользователь\"");
        buttonLoginAsUser.click();
    }

    @Step("Нажатие кнопки \"Войти как администратор\"")
    public void clickButtonLoginAsAdmin() {
        WaitHelper.waitForClickable(driver, buttonLoginAsAdmin);
        log.info("Главная страница: нажатие кнопки \"Войти как администратор\"");
        buttonLoginAsAdmin.click();
    }

    @Step("Нажатие кнопки \"Заказать справку\"")
    public void clickButtonOrderCertificate() {
        WaitHelper.waitForClickable(driver, buttonOrderCertificate);
        log.info("Главная страница: нажатие кнопки \"Заказать справку\"");
        buttonOrderCertificate.click();
    }
}
