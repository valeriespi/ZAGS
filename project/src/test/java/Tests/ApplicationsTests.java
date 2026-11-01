package Tests;

import POM.*;
import ValueObject.ApplicantDataFormData;
import ValueObject.BirthRegistrationData;
import ValueObject.DeathRegistrationData;
import ValueObject.MarriageRegistrationData;
import WebDriver.WebDriverSingleton;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

public class ApplicationsTests {

    private WebDriver driver;

    @BeforeEach
    public void setData() {
        driver = WebDriverSingleton.getInstance();
        driver.manage().window().maximize();
        driver.get("https://user:senlatest@regoffice.senla.eu");

        MainPage mainPage = new MainPage(driver);
    }

    @AfterEach
    public void quitDriver() {
        WebDriverSingleton.quit();
    }

    @Test
    public void marriageRegistrationTest() {
        ApplicantDataFormData applicantDataFormVO = new ApplicantDataFormData(
                "Иванов", "Иван", "Иванович",
                "12345678910", "12345678", "Гродно");

        MarriageRegistrationData marriageRegistration = new MarriageRegistrationData(
                "Иванов", "Иван", "Иванович",
                "01012000", "12345678", "Male",
                "Гродно", "01012000", "Иванов",
                "Иванова", "Катя", "Ивановна",
                "01012000", "12345678");

        ApplicantDataForm applicantDataForm = new ApplicantDataForm(driver);
        ChoiceOfServicePage choiceOfServicePage = new ChoiceOfServicePage(driver);
        MarriageRegistrationCitizenDataForm marriageRegistrationCitizenDataForm =
                new MarriageRegistrationCitizenDataForm(driver);
        MarriageRegistrationServiceDataForm marriageRegistrationServiceDataForm =
                new MarriageRegistrationServiceDataForm(driver);

        applicantDataForm.fillInAllFields(applicantDataFormVO);
        choiceOfServicePage.clickButtonMarriageRegistration();
        marriageRegistrationCitizenDataForm.fillInAllFields(marriageRegistration);
        marriageRegistrationServiceDataForm.fillInAllFields(marriageRegistration);
    }

    @Test
    public void birthRegistrationTest() {
        ApplicantDataFormData applicantDataFormVO = new ApplicantDataFormData(
                "Иванов", "Иван", "Иванович",
                "12345678910", "12345678", "Гродно");

        BirthRegistrationData birthRegistration = new BirthRegistrationData(
                "Иванов", "Иван", "Иванович",
                "01012000", "12345678", "Male",
                "Гродно", "Гродно", "Катя",
                "Иван", "Катя", "Иван");

        ApplicantDataForm applicantDataForm = new ApplicantDataForm(driver);
        ChoiceOfServicePage choiceOfServicePage = new ChoiceOfServicePage(driver);
        BirthRegistrationCitizenDataForm birthRegistrationCitizenDataForm =
                new BirthRegistrationCitizenDataForm(driver);
        BirthRegistrationServiceDataForm birthRegistrationServiceDataForm =
                new BirthRegistrationServiceDataForm(driver);

        applicantDataForm.fillInAllFields(applicantDataFormVO);
        choiceOfServicePage.clickButtonBirthRegistration();
        birthRegistrationCitizenDataForm.fillInAllFields(birthRegistration);
        birthRegistrationServiceDataForm.fillInAllFields(birthRegistration);
    }

    @Test
    public void deathRegistrationTest() {
        ApplicantDataFormData applicantDataFormVO = new ApplicantDataFormData(
                "Иванов", "Иван", "Иванович",
                "12345678910", "12345678", "Гродно");

        DeathRegistrationData deathRegistration = new DeathRegistrationData(
                "Иванов", "Иван", "Иванович",
                "Гродно", "01012000", "12345678",
                "Male", "01012000", "Гродно");

        ApplicantDataForm applicantDataForm = new ApplicantDataForm(driver);
        ChoiceOfServicePage choiceOfServicePage = new ChoiceOfServicePage(driver);
        DeathRegistrationCitizenDataForm deathRegistrationCitizenDataForm = new DeathRegistrationCitizenDataForm(driver);
        DeathRegistrationServiceDataForm deathRegistrationServiceDataForm = new DeathRegistrationServiceDataForm(driver);

        applicantDataForm.fillInAllFields(applicantDataFormVO);
        choiceOfServicePage.clickButtonDeathRegistration();
        deathRegistrationCitizenDataForm.fillInAllFields(deathRegistration);
        deathRegistrationServiceDataForm.fillInAllFields(deathRegistration);
    }

    @Test
    public void StatusCheckTest() {

    }
}
