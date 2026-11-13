package POM;

import ValueObject.DeathRegistrationData;
import io.qameta.allure.Step;
import WebDriver.WaitHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DeathRegistrationServiceDataForm {

    private static final Logger log = LogManager.getLogger(DeathRegistrationServiceDataForm.class);

    @FindBy(xpath = "//label[contains(normalize-space(), 'Дата смерти')]/following::input[1]")
    private WebElement fieldDateOfDeath;

    @FindBy(xpath = "//label[contains(normalize-space(), 'Место смерти')]/following::input[1]")
    private WebElement fieldPlaceOfDeath;

    @FindBy(xpath = "//button[text() = 'Завершить']")
    private WebElement buttonFinish;

    private final WebDriver driver;

    public DeathRegistrationServiceDataForm(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @Step("Заполнение всех полей формы \"Данные услуги\"")
    public void fillInAllFields(DeathRegistrationData deathRegistration) {
        WaitHelper.waitForVisible(driver, fieldDateOfDeath);
        log.info("Заполнение формы \"Данные услуги\"");
        fieldDateOfDeath.sendKeys(deathRegistration.serviceDateOfDeath());
        fieldPlaceOfDeath.sendKeys(deathRegistration.servicePlaceOfDeath());
        log.debug("Форма \"Данные услуги\" заполнена");
    }

    @Step("Нажатие кнопки \"Завершить\"")
    public void clickButtonFinish() {
        log.info("Нажатие кнопки \"Завершить\"");
        WaitHelper.clickWithRetry(driver, buttonFinish);
    }
}
