package pom;

import valueObject.ValuesAdminRegistrationDataForm;
import io.qameta.allure.Step;
import webDriver.WaitHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PageAdminRegistrationDataForm {

    private static final Logger log = LogManager.getLogger(PageAdminRegistrationDataForm.class);

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

    @FindBy(xpath = "//label[contains(normalize-space(), 'Дата рождения')]/following::input[1]")
    private WebElement fieldBirthDate;

    @FindBy(xpath = "//button[text() = 'Далее']")
    private WebElement buttonNext;

    private final WebDriver driver;

    public PageAdminRegistrationDataForm(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @Step("Заполнение всех полей формы \"Данные администратора\"")
    public void fillInAllFields(ValuesAdminRegistrationDataForm adminRegistration) {
        log.info("Заполнение формы \"Данные администратора\"");
        fieldLastName.sendKeys(adminRegistration.lastNameAdmin());
        fieldName.sendKeys(adminRegistration.nameAdmin());
        fieldPatronymic.sendKeys(adminRegistration.patronymicAdmin());
        fieldPhone.sendKeys(adminRegistration.phoneAdmin());
        fieldPassport.sendKeys(adminRegistration.passportAdmin());
        fieldBirthDate.sendKeys(adminRegistration.dateOfBirthAdmin());
        log.debug("Форма \"Данные администратора\" заполнена");
    }

    @Step("Нажатие кнопки \"Далее\"")
    public void clickButtonNext() {
        WaitHelper.waitForEnabled(driver, buttonNext);
        buttonNext.click();
        WaitHelper.waitForVisible(driver, By.xpath("//th[contains(normalize-space(), 'Заявитель')]/ancestor::table[1]"));
    }
}
