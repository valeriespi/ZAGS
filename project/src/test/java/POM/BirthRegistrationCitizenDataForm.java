package POM;

import ValueObject.BirthRegistrationData;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BirthRegistrationCitizenDataForm {

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

    public BirthRegistrationCitizenDataForm(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void fillInAllFields(BirthRegistrationData birthRegistration) {
        fieldLastName.sendKeys(birthRegistration.citizenLastName());
        fieldName.sendKeys(birthRegistration.citizenName());
        fieldPatronymic.sendKeys(birthRegistration.citizenPatronymic());
        fieldDateOfBirth.sendKeys(birthRegistration.citizenDateOfBirth());
        fieldPassport.sendKeys(birthRegistration.citizenPassport());
        fieldGender.sendKeys(birthRegistration.citizenGender());
        fieldAddress.sendKeys(birthRegistration.citizenAddress());
    }

    public void clickButtonNext() {
        buttonNext.click();
    }
}
