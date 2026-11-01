package POM;

import ValueObject.DeathRegistrationData;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DeathRegistrationCitizenDataForm {

    @FindBy(id = "TextInputField-101")
    private WebElement fieldLastName;

    @FindBy(id = "TextInputField-102")
    private WebElement fieldName;

    @FindBy(id = "TextInputField-103")
    private WebElement fieldPatronymic;

    @FindBy(id = "TextInputField-104")
    private WebElement fieldDateOfBirth;

    @FindBy(id = "TextInputField-105")
    private WebElement fieldPassport;

    @FindBy(id = "TextInputField-106")
    private WebElement fieldGender;

    @FindBy(id = "TextInputField-107")
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