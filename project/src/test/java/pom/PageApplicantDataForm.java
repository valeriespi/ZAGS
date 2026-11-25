package pom;

import valueObject.ValuesApplicantDataForm;
import io.qameta.allure.Step;
import webDriver.WaitHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PageApplicantDataForm {

    private static final Logger log = LogManager.getLogger(PageApplicantDataForm.class);

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

    public PageApplicantDataForm(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @Step("Заполнение всех полей формы \"Данные заявителя\"")
    public void fillInAllFields(ValuesApplicantDataForm applicantDataForm) {
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
        WaitHelper.waitForEnabled(driver, buttonNext);
        buttonNext.click();
        WaitHelper.waitForVisible(driver, By.xpath("//button[contains(normalize-space(), 'Регистрация')]"));
    }
}
