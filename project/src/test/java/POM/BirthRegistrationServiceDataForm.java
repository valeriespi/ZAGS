package POM;

import ValueObject.BirthRegistrationData;
import io.qameta.allure.Step;
import WebDriver.WaitHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BirthRegistrationServiceDataForm {

    private static final Logger log = LogManager.getLogger(BirthRegistrationServiceDataForm.class);

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

    private final WebDriver driver;

    public BirthRegistrationServiceDataForm(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @Step("Заполнение всех полей формы \"Данные услуги\"")
    public void fillInAllFields(BirthRegistrationData birthRegistration) {
        WaitHelper.waitForVisible(driver, fieldPlaceOfBirth);
        log.info("Заполнение формы \"Данные услуги\"");
        fieldPlaceOfBirth.sendKeys(birthRegistration.servicePlaceOfBirth());
        fieldMother.sendKeys(birthRegistration.serviceMother());
        fieldFather.sendKeys(birthRegistration.serviceFather());
        fieldGrandmother.sendKeys(birthRegistration.serviceGrandmother());
        fieldGrandfather.sendKeys(birthRegistration.serviceGrandfather());
        log.debug("Форма \"Данные услуги\" заполнена");
    }

    @Step("Нажатие кнопки \"Завершить\"")
    public void clickButtonFinish() {
        log.info("Нажатие кнопки \"Завершить\"");
        buttonFinish.click();
    }
}
