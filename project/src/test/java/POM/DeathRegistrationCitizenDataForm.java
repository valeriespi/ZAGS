package POM;

import ValueObject.DeathRegistrationData;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DeathRegistrationCitizenDataForm {

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

    public DeathRegistrationCitizenDataForm(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void fillInAllFields(DeathRegistrationData deathRegistration) {
        fieldLastName.sendKeys(deathRegistration.citizenLastName());
        fieldName.sendKeys(deathRegistration.citizenName());
        fieldPatronymic.sendKeys(deathRegistration.citizenPatronymic());
        fieldDateOfBirth.sendKeys(deathRegistration.citizenDateOfBirth());
        fieldPassport.sendKeys(deathRegistration.citizenPassport());
        fieldGender.sendKeys(deathRegistration.citizenGender());
        fieldAddress.sendKeys(deathRegistration.citizenAddress());
    }

    public void clickButtonNext() {
        buttonNext.click();
    }
}