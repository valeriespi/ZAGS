package pom;

import valueObject.ValuesDeathRegistrationDataForm;
import io.qameta.allure.Step;
import webDriver.WaitHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PageDeathRegistrationServiceDataForm {

    private static final Logger log = LogManager.getLogger(PageDeathRegistrationServiceDataForm.class);

    @FindBy(xpath = "//label[contains(normalize-space(), 'Дата смерти')]/following::input[1]")
    private WebElement fieldDateOfDeath;

    @FindBy(xpath = "//label[contains(normalize-space(), 'Место смерти')]/following::input[1]")
    private WebElement fieldPlaceOfDeath;

    @FindBy(xpath = "//button[text() = 'Завершить']")
    private WebElement buttonFinish;

    private final WebDriver driver;

    public PageDeathRegistrationServiceDataForm(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @Step("Заполнение всех полей формы \"Данные услуги\"")
    public void fillInAllFields(ValuesDeathRegistrationDataForm deathRegistration) {
        log.info("Заполнение формы \"Данные услуги\"");
        fieldDateOfDeath.sendKeys(deathRegistration.serviceDateOfDeath());
        fieldPlaceOfDeath.sendKeys(deathRegistration.servicePlaceOfDeath());
        log.debug("Форма \"Данные услуги\" заполнена");
    }

    @Step("Нажатие кнопки \"Завершить\"")
    public void clickButtonFinish() {
        WaitHelper.waitForEnabled(driver, buttonFinish);
        buttonFinish.click();
        WaitHelper.waitForVisible(driver, By.xpath("//span[text() = 'Спасибо за обращение!']"));
    }
}
