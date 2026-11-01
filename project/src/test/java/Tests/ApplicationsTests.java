package tests;

import POM.*;
import PageElement.ApplicationTable;
import ValueObject.AdminRegistrationData;
import ValueObject.ApplicantDataFormData;
import ValueObject.BirthRegistrationData;
import ValueObject.DeathRegistrationData;
import ValueObject.MarriageRegistrationData;
import WebDriver.WebDriverSingleton;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class ApplicationsTests {

    private WebDriver driver;
    MainPage mainPage;

    @BeforeEach
    public void setData() {
        driver = WebDriverSingleton.getInstance();
        driver.manage().window().maximize();
        driver.get("https://user:senlatest@regoffice.senla.eu");

        mainPage = new MainPage(driver);
    }

    @AfterEach
    public void quitDriver() {
        WebDriverSingleton.quit();
    }

    @Test
    public void marriageRegistrationTest() {
        ApplicantDataFormData applicantDataFormVO = new ApplicantDataFormData(
                "Иванов", "Иван", "Ivanovich",
                "12345678910", "12345678", "Гродно");

        MarriageRegistrationData marriageRegistration = new MarriageRegistrationData(
                "Иванов", "Иван", "Ivanovich",
                "01012000", "12345678", "Male",
                "Гродно", "01012000", "Иванов",
                "Иванова", "Катя", "Ivanovna",
                "01012000", "12345678");

        ApplicantDataForm applicantDataForm = new ApplicantDataForm(driver);
        ChoiceOfServicePage choiceOfServicePage = new ChoiceOfServicePage(driver);
        MarriageRegistrationCitizenDataForm marriageRegistrationCitizenDataForm =
                new MarriageRegistrationCitizenDataForm(driver);
        MarriageRegistrationServiceDataForm marriageRegistrationServiceDataForm =
                new MarriageRegistrationServiceDataForm(driver);

        mainPage.clickButtonLoginAsUser();
        applicantDataForm.fillInAllFields(applicantDataFormVO);
        choiceOfServicePage.clickButtonMarriageRegistration();
        marriageRegistrationCitizenDataForm.fillInAllFields(marriageRegistration);
        marriageRegistrationServiceDataForm.fillInAllFields(marriageRegistration);
        marriageRegistrationServiceDataForm.clickButtonFinish();

        checkApplicationStatusPage();
    }

    @Test
    public void birthRegistrationTest() {
        ApplicantDataFormData applicantDataFormVO = new ApplicantDataFormData(
                "Иванов", "Иван", "Ivanovich",
                "12345678910", "12345678", "Гродно");

        BirthRegistrationData birthRegistration = new BirthRegistrationData(
                "Иванов", "Иван", "Ivanovich",
                "01012000", "12345678", "Male",
                "Гродно", "Гродно", "Катя",
                "Иван", "Катя", "Иван");

        ApplicantDataForm applicantDataForm = new ApplicantDataForm(driver);
        ChoiceOfServicePage choiceOfServicePage = new ChoiceOfServicePage(driver);
        BirthRegistrationCitizenDataForm birthRegistrationCitizenDataForm =
                new BirthRegistrationCitizenDataForm(driver);
        BirthRegistrationServiceDataForm birthRegistrationServiceDataForm =
                new BirthRegistrationServiceDataForm(driver);

        mainPage.clickButtonLoginAsUser();
        applicantDataForm.fillInAllFields(applicantDataFormVO);
        choiceOfServicePage.clickButtonBirthRegistration();
        birthRegistrationCitizenDataForm.fillInAllFields(birthRegistration);
        birthRegistrationServiceDataForm.fillInAllFields(birthRegistration);
        birthRegistrationServiceDataForm.clickButtonFinish();

        checkApplicationStatusPage();
    }

    @Test
    public void deathRegistrationTest() {
        ApplicantDataFormData applicantDataFormVO = new ApplicantDataFormData(
                "Иванов", "Иван", "Ivanovich",
                "12345678910", "12345678", "Гродно");

        DeathRegistrationData deathRegistration = new DeathRegistrationData(
                "Иванов", "Иван", "Ivanovich",
                "Гродно", "01012000", "12345678",
                "Male", "01012000", "Гродно");

        ApplicantDataForm applicantDataForm = new ApplicantDataForm(driver);
        ChoiceOfServicePage choiceOfServicePage = new ChoiceOfServicePage(driver);
        DeathRegistrationCitizenDataForm deathRegistrationCitizenDataForm = new DeathRegistrationCitizenDataForm(driver);
        DeathRegistrationServiceDataForm deathRegistrationServiceDataForm = new DeathRegistrationServiceDataForm(driver);

        mainPage.clickButtonLoginAsUser();
        applicantDataForm.fillInAllFields(applicantDataFormVO);
        choiceOfServicePage.clickButtonDeathRegistration();
        deathRegistrationCitizenDataForm.fillInAllFields(deathRegistration);
        deathRegistrationServiceDataForm.fillInAllFields(deathRegistration);
        deathRegistrationServiceDataForm.clickButtonFinish();

        checkApplicationStatusPage();
    }

    private void checkApplicationStatusPage() {
        ApplicationStatusPage applicationStatusPage = new ApplicationStatusPage(driver);

        Assertions.assertTrue(applicationStatusPage.isThanksTextDisplayed(),
                "Не отображается текст 'Спасибо за обращение!'");
        Assertions.assertTrue(applicationStatusPage.isApplicationNumberTextDisplayed(),
                "Не отображается текст 'Ваша заявка № ... отправлена на рассмотрение.'");
        Assertions.assertTrue(applicationStatusPage.isRegistrationDateTextDisplayed(),
                "Не отображается текст 'Дата регистрации заявки ...'");
        Assertions.assertTrue(applicationStatusPage.isStatusTextDisplayed(),
                "Не отображается текст 'Статус заявки: ...'");

        String expectedRegistrationDate = LocalDate.now()
                .format(DateTimeFormatter.ofPattern("EEE MMM dd yyyy", Locale.ENGLISH));
        Assertions.assertEquals(expectedRegistrationDate, applicationStatusPage.getRegistrationDate(),
                "Дата регистрации заявки не соответствует текущей дате");

        String actualStatus = applicationStatusPage.getStatus();
        Assertions.assertFalse(actualStatus.contains("Null"), "Статус заявки равен Null");
        Assertions.assertFalse(actualStatus.isBlank(), "Статус заявки отсутствует");
    }

    @Test
    public void StatusCheckTest() {
        ApplicantDataFormData applicantDataFormVO = new ApplicantDataFormData(
                "Иванов", "Иван", "Ivanovich",
                "12345678910", "12345678", "Гродно");

        MarriageRegistrationData marriageRegistration = new MarriageRegistrationData(
                "Иванов", "Иван", "Ivanovich",
                "01012000", "12345678", "Male",
                "Гродно", "01012000", "Иванов",
                "Иванова", "Катя", "Ivanovna",
                "01012000", "12345678");

        AdminRegistrationData adminRegistration = new AdminRegistrationData(
                "Петров", "Петр", "Petrovich",
                "12345678910", "12345678", "01011980");

        ApplicantDataForm applicantDataForm = new ApplicantDataForm(driver);
        ChoiceOfServicePage choiceOfServicePage = new ChoiceOfServicePage(driver);
        MarriageRegistrationCitizenDataForm marriageRegistrationCitizenDataForm =
                new MarriageRegistrationCitizenDataForm(driver);
        MarriageRegistrationServiceDataForm marriageRegistrationServiceDataForm =
                new MarriageRegistrationServiceDataForm(driver);
        ApplicationStatusPage applicationStatusPage = new ApplicationStatusPage(driver);
        AdminDataForm adminDataForm = new AdminDataForm(driver);
        ApplicationTable applicationTable = new ApplicationTable(driver);

        mainPage.clickButtonLoginAsUser();
        applicantDataForm.fillInAllFields(applicantDataFormVO);
        choiceOfServicePage.clickButtonMarriageRegistration();
        marriageRegistrationCitizenDataForm.fillInAllFields(marriageRegistration);
        marriageRegistrationServiceDataForm.fillInAllFields(marriageRegistration);
        marriageRegistrationServiceDataForm.clickButtonFinish();

        String applicationNumber = applicationStatusPage.getApplicationNumber();
        String statusFromStatusPage = applicationStatusPage.getStatus();

        mainPage.clickButtonLoginAsAdmin();

        adminDataForm.fillInAllFields(adminRegistration);
        adminDataForm.clickButtonNext();

        String actualStatus = applicationTable.getApplicationStatus(applicationNumber);

        Assertions.assertEquals(statusFromStatusPage.replace(".", ""), actualStatus.replace(".", ""),
                "Статус заявки " + applicationNumber + " из таблицы не совпал со статусом со страницы 'Статус заявки'");
    }
}
