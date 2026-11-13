package POM;

import ValueObject.MarriageRegistrationData;
import io.qameta.allure.Step;
import WebDriver.WaitHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MarriageRegistrationServiceDataForm {

    private static final Logger log = LogManager.getLogger(MarriageRegistrationServiceDataForm.class);

    @FindBy(xpath = "//label[contains(normalize-space(), 'Дата регистрации')]/following::input[1]")
    private WebElement fieldRegistrationDate;

    @FindBy(xpath = "//label[contains(normalize-space(), 'Новая фамилия')]/following::input[1]")
    private WebElement fieldNewLastName;

    @FindBy(xpath = "//label[contains(normalize-space(), 'Фамилия супруга/и')]/following::input[1]")
    private WebElement fieldSpousesLastName;

    @FindBy(xpath = "//label[contains(normalize-space(), 'Имя супруга/и')]/following::input[1]")
    private WebElement fieldSpousesName;

    @FindBy(xpath = "//label[contains(normalize-space(), 'Отчество супруга/и')]/following::input[1]")
    private WebElement fieldSpousesPatronymic;

    @FindBy(xpath = "//label[contains(normalize-space(), 'Дата рождения супруга/и')]/following::input[1]")
    private WebElement fieldSpousesDateOfBirth;

    @FindBy(xpath = "//label[contains(normalize-space(), 'Номер паспорта супруга/и')]/following::input[1]")
    private WebElement fieldSpousesPassport;

    @FindBy(xpath = "//button[text() = 'Завершить']")
    private WebElement buttonFinish;

    private final WebDriver driver;

    public MarriageRegistrationServiceDataForm(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @Step("Заполнение всех полей формы \"Данные услуги\"")
    public void fillInAllFields(MarriageRegistrationData marriageRegistration) {
        WaitHelper.waitForVisible(driver, fieldRegistrationDate);
        log.info("Заполнение формы \"Данные услуги\"");
        fieldRegistrationDate.sendKeys(marriageRegistration.dateOfRegistrationServiceData());
        fieldNewLastName.sendKeys(marriageRegistration.newLastNameServiceData());
        fieldSpousesLastName.sendKeys(marriageRegistration.spousesLastNameServiceData());
        fieldSpousesName.sendKeys(marriageRegistration.spousesNameServiceData());
        fieldSpousesPatronymic.sendKeys(marriageRegistration.spousesPatronymicServiceData());
        fieldSpousesDateOfBirth.sendKeys(marriageRegistration.spousesDateOfBirthServiceData());
        fieldSpousesPassport.sendKeys(marriageRegistration.spousesPassportServiceData());
        log.debug("Форма \"Данные услуги\" заполнена");
    }

    @Step("Нажатие кнопки \"Завершить\"")
    public void clickButtonFinish() {
        log.info("Нажатие кнопки \"Завершить\"");
        WaitHelper.clickWithRetry(driver, buttonFinish);
    }
}
