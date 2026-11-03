package POM;

import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import WebDriver.WaitHelper;

public class ChoiceOfServicePage {

    private static final Logger log = LogManager.getLogger(ChoiceOfServicePage.class);

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
        WaitHelper.waitForClickable(driver, buttonMarriageRegistration);
        log.info("Выбор услуги: нажатие кнопки \"Регистрация брака\"");
        buttonMarriageRegistration.click();
    }

    @Step("Нажатие кнопки \"Регистрация рождения\"")
    public void clickButtonBirthRegistration() {
        WaitHelper.waitForClickable(driver, buttonBirthRegistration);
        log.info("Выбор услуги: нажатие кнопки \"Регистрация рождения\"");
        buttonBirthRegistration.click();
    }

    @Step("Нажатие кнопки \"Регистрация смерти\"")
    public void clickButtonDeathRegistration() {
        WaitHelper.waitForClickable(driver, buttonDeathRegistration);
        log.info("Выбор услуги: нажатие кнопки \"Регистрация смерти\"");
        buttonDeathRegistration.click();
    }
}
