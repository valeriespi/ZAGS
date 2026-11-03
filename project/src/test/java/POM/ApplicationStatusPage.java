package POM;

import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import WebDriver.WaitHelper;

public class ApplicationStatusPage {

    private static final Logger log = LogManager.getLogger(ApplicationStatusPage.class);

    @FindBy(xpath = "//span[text() = 'Спасибо за обращение!']")
    private WebElement textThanks;

    @FindBy(xpath = "//span[contains(text(), 'Ваша заявка №')]")
    private WebElement textApplicationNumber;

    @FindBy(xpath = "//span[contains(text(), 'Дата регистрации заявки')]")
    private WebElement textRegistrationDate;

    @FindBy(xpath = "//span[contains(text(), 'Статус заявки:')]")
    private WebElement textApplicationStatus;

    @FindBy(xpath = "//button[text() = 'Обновить']")
    private WebElement buttonRefresh;

    @FindBy(xpath = "//button[text() = 'Создать новую заявку']")
    private WebElement buttonCreateNewApplication;

    private final WebDriver driver;

    public ApplicationStatusPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @Step("Нажатие кнопки \"Обновить\"")
    public void clickButtonRefresh() {
        WaitHelper.waitForClickable(driver, buttonRefresh);
        log.info("Нажатие кнопки \"Обновить\"");
        buttonRefresh.click();
    }

    @Step("Нажатие кнопки \"Создать новую заявку\"")
    public void clickButtonCreateNewApplication() {
        WaitHelper.waitForClickable(driver, buttonCreateNewApplication);
        log.info("Нажатие кнопки \"Создать новую заявку\"");
        buttonCreateNewApplication.click();
    }

    @Step("Извлечение номера заявки из текста")
    public String getApplicationNumber() {
        String fullText = textApplicationNumber.getText();
        String result = fullText.substring(fullText.indexOf("№") + 1, fullText.indexOf("отправлена")).trim();
        log.info("Номер заявки: {}", result);
        return result;
    }

    @Step("Извлечение даты регистрации заявки из текста")
    public String getRegistrationDate() {
        String fullText = textRegistrationDate.getText();
        String result = fullText.substring(fullText.indexOf("заявки") + "заявки".length()).trim();
        log.info("Дата регистрации заявки: {}", result);
        return result;
    }

    @Step("Извлечение статуса заявки из текста")
    public String getStatus() {
        String fullText = textApplicationStatus.getText();
        String result = fullText.substring(fullText.indexOf(":") + 1).trim();
        log.info("Статус заявки: {}", result);
        return result;
    }

    @Step("Проверка отображения текста \"Спасибо за обращение!\"")
    public boolean isThanksTextDisplayed() {
        boolean displayed = textThanks.isDisplayed();
        log.info("Текст 'Спасибо за обращение!' отображается = {}", displayed);
        return displayed;
    }

    @Step("Проверка отображения текста с номером заявки")
    public boolean isApplicationNumberTextDisplayed() {
        boolean displayed = textApplicationNumber.isDisplayed();
        log.info("Текст с номером заявки отображается = {}", displayed);
        return displayed;
    }

    @Step("Проверка отображения текста с датой регистрации заявки")
    public boolean isRegistrationDateTextDisplayed() {
        boolean displayed = textRegistrationDate.isDisplayed();
        log.info("Текст с датой регистрации заявки отображается = {}", displayed);
        return displayed;
    }

    @Step("Проверка отображения текста со статусом заявки")
    public boolean isStatusTextDisplayed() {
        boolean displayed = textApplicationStatus.isDisplayed();
        log.info("Текст со статусом заявки отображается = {}", displayed);
        return displayed;
    }
}
