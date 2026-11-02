package POM;

import ValueObject.MarriageRegistrationData;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MarriageRegistrationServiceDataForm {

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

    public MarriageRegistrationServiceDataForm(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void fillInAllFields(MarriageRegistrationData marriageRegistration) {
        fieldRegistrationDate.sendKeys(marriageRegistration.dateOfRegistrationServiceData());
        fieldNewLastName.sendKeys(marriageRegistration.newLastNameServiceData());
        fieldSpousesLastName.sendKeys(marriageRegistration.spousesLastNameServiceData());
        fieldSpousesName.sendKeys(marriageRegistration.spousesNameServiceData());
        fieldSpousesPatronymic.sendKeys(marriageRegistration.spousesPatronymicServiceData());
        fieldSpousesDateOfBirth.sendKeys(marriageRegistration.spousesDateOfBirthServiceData());
        fieldSpousesPassport.sendKeys(marriageRegistration.spousesPassportServiceData());
    }

    public void clickButtonFinish() {
        buttonFinish.click();
    }
}
