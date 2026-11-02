package POM;

import ValueObject.MarriageRegistrationData;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MarriageRegistrationCitizenDataForm {

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

    public MarriageRegistrationCitizenDataForm(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void fillInAllFields(MarriageRegistrationData marriageRegistration) {
        fieldLastName.sendKeys(marriageRegistration.lastNameCitizenData());
        fieldName.sendKeys(marriageRegistration.nameCitizenData());
        fieldPatronymic.sendKeys(marriageRegistration.patronymicCitizenData());
        fieldDateOfBirth.sendKeys(marriageRegistration.dateOfBirthCitizenData());
        fieldPassport.sendKeys(marriageRegistration.passportCitizenData());
        fieldGender.sendKeys(marriageRegistration.genderCitizenData());
        fieldAddress.sendKeys(marriageRegistration.addressCitizenData());
    }

    public void clickButtonNext() {
        buttonNext.click();
    }
}
