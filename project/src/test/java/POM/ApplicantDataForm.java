package POM;

import ValueObject.ApplicantDataFormData;
import io.qameta.allure.Step;
import WebDriver.WaitHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ApplicantDataForm {

    private static final Logger log = LogManager.getLogger(ApplicantDataForm.class);

    @FindBy(xpath = "//label[contains(normalize-space(), 'Фамилия')]/following::input[1]")
    private WebElement fieldLastName;

    @FindBy(xpath = "//label[contains(normalize-space(), 'Имя')]/following::input[1]")
    private WebElement fieldName;

    @FindBy(xpath = "//label[contains(normalize-space(), 'Отчество')]/following::input[1]")
    private WebElement fieldPatronymic;

    @FindBy(xpath = "//label[contains(normalize-space(), 'Телефон')]/following::input[1]")
    private WebElement fieldPhone;

    @FindBy(xpath = "//label[contains(normalize-space(), 'Номер паспорта')]/following::input[1]")
    private WebElement fieldPassport;

    @FindBy(xpath = "//label[contains(normalize-space(), 'Адрес прописки')]/following::input[1]")
    private WebElement fieldAddress;

    @FindBy(xpath = "//button[text() = 'Далее']")
    private WebElement buttonNext;

    private final WebDriver driver;

    public ApplicantDataForm(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @Step("Заполнение всех полей формы \"Данные заявителя\"")
    public void fillInAllFields(ApplicantDataFormData applicantDataForm) {
        WaitHelper.waitForVisible(driver, fieldLastName);
        log.info("Заполнение формы \"Данные заявителя\"");
        fieldLastName.sendKeys(applicantDataForm.lastNameDataApplicant());
        fieldName.sendKeys(applicantDataForm.nameDataApplicant());
        fieldPatronymic.sendKeys(applicantDataForm.patronymicDataApplicant());
        fieldPhone.sendKeys(applicantDataForm.phoneDataApplicant());
        fieldPassport.sendKeys(applicantDataForm.passportDataApplicant());
        fieldAddress.sendKeys(applicantDataForm.addressDataApplicant());
        log.debug("Форма \"Данные заявителя\" заполнена");
    }

    @Step("Нажатие кнопки \"Далее\"")
    public void clickButtonNext() {
        log.info("Нажатие кнопки \"Далее\"");
        WaitHelper.clickWithRetry(driver, buttonNext);
    }
}
