package POM;

import ValueObject.DeathRegistrationData;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DeathRegistrationServiceDataForm {

    @FindBy(id = "TextInputField-108")
    private WebElement fieldDateOfDeath;

    @FindBy(id = "TextInputField-109")
    private WebElement fieldPlaceOfDeath;

    @FindBy(xpath = "//button[text() = 'Завершить']")
    private WebElement buttonFinish;

    public DeathRegistrationServiceDataForm(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void fillInAllFields(DeathRegistrationData deathRegistration) {
        fieldDateOfDeath.sendKeys(deathRegistration.serviceDateOfDeath());
        fieldPlaceOfDeath.sendKeys(deathRegistration.servicePlaceOfDeath());
    }

    public void clickButtonFinish() {
        buttonFinish.click();
    }
}
