package POM;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import WebDriver.WaitHelper;

public class ChoiceOfServicePage {

    @FindBy(xpath = "//button[text() = 'Регистрация брака']")
    private WebElement buttonMarriageRegistration;

    @FindBy(xpath = "//button[text() = 'Регистрация рождения']")
    private WebElement buttonBirthRegistration;

    @FindBy(xpath = "//button[text() = 'Регистрация смерти']")
    private WebElement buttonDeathRegistration;

    private final WebDriver driver;

    public ChoiceOfServicePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @Step("Нажатие кнопки \"Регистрация брака\"")
    public void clickButtonMarriageRegistration() {
        WaitHelper.waitForEnabled(driver, buttonMarriageRegistration);
        buttonMarriageRegistration.click();
        WaitHelper.waitForVisible(driver, By.xpath("//label[contains(normalize-space(), 'Фамилия')]/following::input[1]"));
    }

    @Step("Нажатие кнопки \"Регистрация рождения\"")
    public void clickButtonBirthRegistration() {
        WaitHelper.waitForEnabled(driver, buttonBirthRegistration);
        buttonBirthRegistration.click();
        WaitHelper.waitForVisible(driver, By.xpath("//label[contains(normalize-space(), 'Фамилия')]/following::input[1]"));
    }

    @Step("Нажатие кнопки \"Регистрация смерти\"")
    public void clickButtonDeathRegistration() {
        WaitHelper.waitForEnabled(driver, buttonDeathRegistration);
        buttonDeathRegistration.click();
        WaitHelper.waitForVisible(driver, By.xpath("//label[contains(normalize-space(), 'Фамилия')]/following::input[1]"));
    }
}
