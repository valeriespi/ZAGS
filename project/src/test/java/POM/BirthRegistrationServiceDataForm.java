package POM;

import ValueObject.BirthRegistrationData;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BirthRegistrationServiceDataForm {

    @FindBy(xpath = "//label[contains(normalize-space(), 'Место рождения')]/following::input[1]")
    private WebElement fieldPlaceOfBirth;

    @FindBy(xpath = "//label[contains(normalize-space(), 'Мать')]/following::input[1]")
    private WebElement fieldMother;

    @FindBy(xpath = "//label[contains(normalize-space(), 'Отец')]/following::input[1]")
    private WebElement fieldFather;

    @FindBy(xpath = "//label[contains(normalize-space(), 'Бабушка')]/following::input[1]")
    private WebElement fieldGrandmother;

    @FindBy(xpath = "//label[contains(normalize-space(), 'Дедушка')]/following::input[1]")
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
