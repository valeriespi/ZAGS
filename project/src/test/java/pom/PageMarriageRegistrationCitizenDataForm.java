package pom;

import valueObject.ValuesMarriageRegistrationDataForm;
import io.qameta.allure.Step;
import webDriver.WaitHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PageMarriageRegistrationCitizenDataForm {

    private static final Logger log = LogManager.getLogger(PageMarriageRegistrationCitizenDataForm.class);

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

    public PageMarriageRegistrationCitizenDataForm(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @Step("Заполнение всех полей формы \"Данные гражданина\"")
    public void fillInAllFields(ValuesMarriageRegistrationDataForm marriageRegistration) {
        log.info("Заполнение формы \"Данные гражданина\"");
        fieldLastName.sendKeys(marriageRegistration.lastNameCitizenData());
        fieldName.sendKeys(marriageRegistration.nameCitizenData());
        fieldPatronymic.sendKeys(marriageRegistration.patronymicCitizenData());
        fieldDateOfBirth.sendKeys(marriageRegistration.dateOfBirthCitizenData());
        fieldPassport.sendKeys(marriageRegistration.passportCitizenData());
        fieldGender.sendKeys(marriageRegistration.genderCitizenData());
        fieldAddress.sendKeys(marriageRegistration.addressCitizenData());
        log.debug("Форма \"Данные гражданина\" заполнена");
    }

    @Step("Нажатие кнопки \"Далее\"")
    public void clickButtonNext() {
        WaitHelper.waitForEnabled(driver, buttonNext);
        buttonNext.click();
        WaitHelper.waitForVisible(driver, By.xpath("//label[contains(normalize-space(), 'Дата регистрации')]/following::input[1]"));
    }
}
