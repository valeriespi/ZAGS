package pom;

import valueObject.ValuesBirthRegistrationDataForm;
import io.qameta.allure.Step;
import webDriver.WaitHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PageBirthRegistrationServiceDataForm {

    private static final Logger log = LogManager.getLogger(PageBirthRegistrationServiceDataForm.class);

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

    public PageBirthRegistrationServiceDataForm(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @Step("Заполнение всех полей формы \"Данные услуги\"")
    public void fillInAllFields(ValuesBirthRegistrationDataForm birthRegistration) {
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
        WaitHelper.waitForEnabled(driver, buttonFinish);
        buttonFinish.click();
        WaitHelper.waitForVisible(driver, By.xpath("//span[text() = 'Спасибо за обращение!']"));
    }
}
