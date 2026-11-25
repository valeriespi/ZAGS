package stepsOfCucumberTests;

import Common.Config;
import pom.PageApplicantDataForm;
import pom.PageApplicationStatus;
import pom.PageChoiceOfService;
import pom.PageMain;
import pom.PageMarriageRegistrationCitizenDataForm;
import pom.PageMarriageRegistrationServiceDataForm;
import valueObject.ValuesMarriageRegistrationDataForm;
import valueObject.GenerateValues;
import webDriver.WebDriverSingleton;
import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;

public class MarriageRegistrationSteps {

    private static final Logger log = LogManager.getLogger(MarriageRegistrationSteps.class);
    private final WebDriver driver = WebDriverSingleton.getInstance();

    @Given("открыта главная страница ЗАГС и выполнен вход как пользователь")
    public void openSiteAndLoginAsUser() {
        driver.get(Config.standUrl());
        driver.manage().window().maximize();
        new PageMain(driver).clickButtonLoginAsUser();
        log.info("Открыт сайт ЗАГС, выполнен вход как пользователь");
    }

    @When("заполнена форма заявителя случайными данными и нажата кнопка Далее")
    public void fillApplicantForm() {
        PageApplicantDataForm applicantDataForm = new PageApplicantDataForm(driver);
        applicantDataForm.fillInAllFields(GenerateValues.applicantData());
        applicantDataForm.clickButtonNext();
        log.info("Форма заявителя заполнена случайными данными, нажата кнопка Далее");
    }

    @And("выбрана услуга регистрации брака и заполнены данные жениха и невесты")
    public void fillMarriageRegistrationData() {
        new PageChoiceOfService(driver).clickButtonMarriageRegistration();
        ValuesMarriageRegistrationDataForm marriageData = GenerateValues.marriageRegistrationData();
        PageMarriageRegistrationCitizenDataForm citizenDataForm = new PageMarriageRegistrationCitizenDataForm(driver);
        citizenDataForm.fillInAllFields(marriageData);
        citizenDataForm.clickButtonNext();
        PageMarriageRegistrationServiceDataForm serviceDataForm = new PageMarriageRegistrationServiceDataForm(driver);
        serviceDataForm.fillInAllFields(marriageData);
        serviceDataForm.clickButtonFinish();
        log.info("Услуга \"Регистрация брака\" выбрана, данные жениха и невесты заполнены, заявка отправлена");
    }

    @Then("на странице статуса отображается номер созданной заявки")
    public void checkApplicationNumberOnStatusPage() {
        PageApplicationStatus statusPage = new PageApplicationStatus(driver);
        Assertions.assertTrue(statusPage.isThanksTextDisplayed(), "Не отображается текст 'Спасибо за обращение!'");
        Assertions.assertFalse(statusPage.getApplicationNumber().equals("null"), "Номер заявки отображается как 'null'");
        log.info("Заявка создана, на странице статуса отображается номер: {}", statusPage.getApplicationNumber());
    }

    @After
    public void closeBrowser() {
        WebDriverSingleton.quit();
        log.info("Сценарий завершён, браузер закрыт");
    }
}
