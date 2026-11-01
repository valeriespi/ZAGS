package POM;

import ValueObject.MarriageRegistrationData;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MarriageRegistrationCitizenDataForm {

    @FindBy(id = "TextInputField-7")
    private WebElement fieldLastName;

    @FindBy(id = "TextInputField-8")
    private WebElement fieldName;

    @FindBy(id = "TextInputField-9")
    private WebElement fieldPatronymic;

    @FindBy(id = "TextInputField-10")
    private WebElement fieldDateOfBirth;

    @FindBy(id = "TextInputField-11")
    private WebElement fieldPassport;

    @FindBy(id = "TextInputField-12")
    private WebElement fieldGender;

    @FindBy(id = "TextInputField-13")
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
