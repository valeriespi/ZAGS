package POM;

import ValueObject.ApplicantDataFormData;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ApplicantDataForm {

    @FindBy(id = "TextInputField-1")
    private WebElement fieldLastName;

    @FindBy(id = "TextInputField-2")
    private WebElement fieldName;

    @FindBy(id = "TextInputField-3")
    private WebElement fieldPatronymic;

    @FindBy(id = "TextInputField-4")
    private WebElement fieldPhone;

    @FindBy(id = "TextInputField-5")
    private WebElement fieldPassport;

    @FindBy(id = "TextInputField-6")
    private WebElement fieldAddress;

    @FindBy(xpath = "//button[text() = 'Далее']")
    private WebElement buttonNext;

    public ApplicantDataForm(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void fillInAllFields(ApplicantDataFormData applicantDataForm) {
        fieldLastName.sendKeys(applicantDataForm.lastNameDataApplicant());
        fieldName.sendKeys(applicantDataForm.nameDataApplicant());
        fieldPatronymic.sendKeys(applicantDataForm.patronymicDataApplicant());
        fieldPhone.sendKeys(applicantDataForm.phoneDataApplicant());
        fieldPassport.sendKeys(applicantDataForm.passportDataApplicant());
        fieldAddress.sendKeys(applicantDataForm.addressDataApplicant());
    }

    public void clickButtonNext() {
        buttonNext.click();
    }
}