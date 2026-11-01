package POM;

import ValueObject.BirthRegistrationData;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BirthRegistrationServiceDataForm {

    @FindBy(id = "TextInputField-89")
    private WebElement fieldPlaceOfBirth;

    @FindBy(id = "TextInputField-90")
    private WebElement fieldMother;

    @FindBy(id = "TextInputField-91")
    private WebElement fieldFather;

    @FindBy(id = "TextInputField-92")
    private WebElement fieldGrandmother;

    @FindBy(id = "TextInputField-93")
    private WebElement fieldGrandfather;

    @FindBy(xpath = "//button[text() = 'Завершить']")
    private WebElement buttonFinish;

    public BirthRegistrationServiceDataForm(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void fillInAllFields(BirthRegistrationData birthRegistration) {
        fieldPlaceOfBirth.sendKeys(birthRegistration.servicePlaceOfBirth());
        fieldMother.sendKeys(birthRegistration.serviceMother());
        fieldFather.sendKeys(birthRegistration.serviceFather());
        fieldGrandmother.sendKeys(birthRegistration.serviceGrandmother());
        fieldGrandfather.sendKeys(birthRegistration.serviceGrandfather());
    }

    public void clickButtonFinish() {
        buttonFinish.click();
    }
}
