package POM;

import ValueObject.BirthRegistrationData;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BirthRegistrationCitizenDataForm {

    @FindBy(id = "TextInputField-28")
    private WebElement fieldLastName;

    @FindBy(id = "TextInputField-29")
    private WebElement fieldName;

    @FindBy(id = "TextInputField-30")
    private WebElement fieldPatronymic;

    @FindBy(id = "TextInputField-31")
    private WebElement fieldDateOfBirth;

    @FindBy(id = "TextInputField-32")
    private WebElement fieldPassport;

    @FindBy(id = "TextInputField-33")
    private WebElement fieldGender;

    @FindBy(id = "TextInputField-34")
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