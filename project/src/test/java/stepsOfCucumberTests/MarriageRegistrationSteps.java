package stepsOfCucumberTests;

import Common.Config;
import POM.ApplicantDataForm;
import POM.ApplicationStatusPage;
import POM.ChoiceOfServicePage;
import POM.MainPage;
import POM.MarriageRegistrationCitizenDataForm;
import POM.MarriageRegistrationServiceDataForm;
import ValueObject.MarriageRegistrationData;
import ValueObject.TestData;
import WebDriver.WebDriverSingleton;
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
        new MainPage(driver).clickButtonLoginAsUser();
        log.info("Открыт сайт ЗАГС, выполнен вход как пользователь");
    }

    @When("заполнена форма заявителя случайными данными и нажата кнопка Далее")
    public void fillApplicantForm() {
        ApplicantDataForm applicantDataForm = new ApplicantDataForm(driver);
        applicantDataForm.fillInAllFields(TestData.applicantData());
        applicantDataForm.clickButtonNext();
        log.info("Форма заявителя заполнена случайными данными, нажата кнопка Далее");
    }

    @And("выбрана услуга регистрации брака и заполнены данные жениха и невесты")
    public void fillMarriageRegistrationData() {
        new ChoiceOfServicePage(driver).clickButtonMarriageRegistration();
        MarriageRegistrationData marriageData = TestData.marriageRegistrationData();
        MarriageRegistrationCitizenDataForm citizenDataForm = new MarriageRegistrationCitizenDataForm(driver);
        citizenDataForm.fillInAllFields(marriageData);
        citizenDataForm.clickButtonNext();
        MarriageRegistrationServiceDataForm serviceDataForm = new MarriageRegistrationServiceDataForm(driver);
        serviceDataForm.fillInAllFields(marriageData);
        serviceDataForm.clickButtonFinish();
        log.info("Услуга \"Регистрация брака\" выбрана, данные жениха и невесты заполнены, заявка отправлена");
    }

    @Then("на странице статуса отображается номер созданной заявки")
    public void checkApplicationNumberOnStatusPage() {
        ApplicationStatusPage statusPage = new ApplicationStatusPage(driver);
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
