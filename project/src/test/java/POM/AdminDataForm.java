package POM;

import ValueObject.AdminRegistrationData;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AdminDataForm {

    @FindBy(id = "TextInputField-123")
    private WebElement fieldLastName;

    @FindBy(id = "TextInputField-124")
    private WebElement fieldName;

    @FindBy(id = "TextInputField-125")
    private WebElement fieldPatronymic;

    @FindBy(id = "TextInputField-126")
    private WebElement fieldPhone;

    @FindBy(id = "TextInputField-127")
    private WebElement fieldPassport;

    @FindBy(id = "TextInputField-128")
    private WebElement fieldBirthDate;

    @FindBy(xpath = "//button[text() = 'Далее']")
    private WebElement buttonNext;

    public AdminDataForm(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void fillInAllFields(AdminRegistrationData adminRegistration) {
        fieldLastName.sendKeys(adminRegistration.lastNameAdmin());
        fieldName.sendKeys(adminRegistration.nameAdmin());
        fieldPatronymic.sendKeys(adminRegistration.patronymicAdmin());
        fieldPhone.sendKeys(adminRegistration.phoneAdmin());
        fieldPassport.sendKeys(adminRegistration.passportAdmin());
        fieldBirthDate.sendKeys(adminRegistration.dateOfBirthAdmin());
    }

    public void clickButtonNext() {
        buttonNext.click();
    }
}