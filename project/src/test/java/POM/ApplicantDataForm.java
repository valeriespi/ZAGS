package POM;

import ValueObject.ApplicantDataFormData;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ApplicantDataForm {

    @FindBy(xpath = "//label[contains(normalize-space(), 'Фамилия')]/following::input[1]")
    private WebElement fieldLastName;

    @FindBy(xpath = "//label[contains(normalize-space(), 'Имя')]/following::input[1]")
    private WebElement fieldName;

    @FindBy(xpath = "//label[contains(normalize-space(), 'Отчество')]/following::input[1]")
    private WebElement fieldPatronymic;

    @FindBy(xpath = "//label[contains(normalize-space(), 'Телефон')]/following::input[1]")
    private WebElement fieldPhone;

    @FindBy(xpath = "//label[contains(normalize-space(), 'Номер паспорта')]/following::input[1]")
    private WebElement fieldPassport;

    @FindBy(xpath = "//label[contains(normalize-space(), 'Адрес прописки')]/following::input[1]")
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