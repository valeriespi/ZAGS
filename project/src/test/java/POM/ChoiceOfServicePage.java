package POM;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ChoiceOfServicePage {

    @FindBy(xpath = "//button[text() = 'Регистрация брака']")
    private WebElement buttonMarriageRegistration;

    @FindBy(xpath = "//button[text() = 'Регистрация рождения']")
    private WebElement buttonBirthRegistration;

    @FindBy(xpath = "//button[text() = 'Регистрация смерти']")
    private WebElement buttonDeathRegistration;

    public ChoiceOfServicePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void clickButtonMarriageRegistration() {
        buttonMarriageRegistration.click();
    }

    public void clickButtonBirthRegistration() {
        buttonBirthRegistration.click();
    }

    public void clickButtonDeathRegistration() {
        buttonDeathRegistration.click();
    }
}