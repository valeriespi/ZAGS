package POM;

import ValueObject.MarriageRegistrationData;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MarriageRegistrationServiceDataForm {

    @FindBy(id = "TextInputField-14")
    private WebElement fieldRegistrationDate;

    @FindBy(id = "TextInputField-15")
    private WebElement fieldNewLastName;

    @FindBy(id = "TextInputField-16")
    private WebElement fieldSpousesLastName;

    @FindBy(id = "TextInputField-17")
    private WebElement fieldSpousesName;

    @FindBy(id = "TextInputField-18")
    private WebElement fieldSpousesPatronymic;

    @FindBy(id = "TextInputField-19")
    private WebElement fieldSpousesDateOfBirth;

    @FindBy(id = "TextInputField-20")
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
