package POM;

import ValueObject.BirthRegistrationData;
import io.qameta.allure.Step;
import WebDriver.WaitHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BirthRegistrationCitizenDataForm {

    private static final Logger log = LogManager.getLogger(BirthRegistrationCitizenDataForm.class);

    @FindBy(xpath = "//label[contains(normalize-space(), 'Фамилия')]/following::input[1]")
    private WebElement fieldLastName;

    @FindBy(xpath = "//label[contains(normalize-space(), 'Имя')]/following::input[1]")
    private WebElement fieldName;

    @FindBy(xpath = "//label[contains(normalize-space(), 'Отчество')]/following::input[1]")
    private WebElement fieldPatronymic;

    @FindBy(xpath = "//label[contains(normalize-space(), 'Дата рождения')]/following::input[1]")
    private WebElement fieldDateOfBirth;

    @FindBy(xpath = "//label[contains(normalize-space(), 'Номер паспорта')]/following::input[1]")
    private WebElement fieldPassport;

    @FindBy(xpath = "//label[contains(normalize-space(), 'Пол')]/following::input[1]")
    private WebElement fieldGender;

    @FindBy(xpath = "//label[contains(normalize-space(), 'Адрес прописки')]/following::input[1]")
    private WebElement fieldAddress;

    @FindBy(xpath = "//button[text() = 'Далее']")
    private WebElement buttonNext;

    private final WebDriver driver;

    public BirthRegistrationCitizenDataForm(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @Step("Заполнение всех полей формы \"Данные гражданина\"")
    public void fillInAllFields(BirthRegistrationData birthRegistration) {
        WaitHelper.waitForVisible(driver, fieldLastName);
        log.info("Заполнение формы \"Данные гражданина\"");
        fieldLastName.sendKeys(birthRegistration.citizenLastName());
        fieldName.sendKeys(birthRegistration.citizenName());
        fieldPatronymic.sendKeys(birthRegistration.citizenPatronymic());
        fieldDateOfBirth.sendKeys(birthRegistration.citizenDateOfBirth());
        fieldPassport.sendKeys(birthRegistration.citizenPassport());
        fieldGender.sendKeys(birthRegistration.citizenGender());
        fieldAddress.sendKeys(birthRegistration.citizenAddress());
        log.debug("Форма \"Данные гражданина\" заполнена");
    }

    @Step("Нажатие кнопки \"Далее\"")
    public void clickButtonNext() {
        log.info("Нажатие кнопки \"Далее\"");
        buttonNext.click();
    }
}
