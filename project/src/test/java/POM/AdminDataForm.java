package POM;

import ValueObject.AdminRegistrationData;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AdminDataForm {

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

    public AdminDataForm(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void fillInAllFields(AdminRegistrationData adminRegistration) {
        fieldLastName.sendKeys(adminRegistration.lastNameAdmin());
        fieldName.sendKeys(adminRegistration.nameAdmin());
        fieldPatronymic.sendKeys(adminRegistration.patronymicAdmin());
        fieldPhone.sendKeys(adminRegistration.phoneAdmin());
        fieldPassport.sendKeys(adminRegistration.passportAdmin());
        fieldBirthDate.sendKeys(adminRegistration.dateOfBirthAdmin());
    }

    public void clickButtonNext() {
        buttonNext.click();
    }
}