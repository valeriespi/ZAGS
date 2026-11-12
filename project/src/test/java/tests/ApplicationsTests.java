package tests;

import Common.Config;
import POM.*;
import PageElement.ApplicationTable;
import ValueObject.AdminRegistrationData;
import ValueObject.ApplicantDataFormData;
import ValueObject.BirthRegistrationData;
import ValueObject.DeathRegistrationData;
import ValueObject.MarriageRegistrationData;
import ValueObject.TestData;
import WebDriver.WaitHelper;
import WebDriver.WebDriverSingleton;
import io.qameta.allure.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Epic("ЗАГС")
public class ApplicationsTests {

    private static final Logger log = LogManager.getLogger(ApplicationsTests.class);

    private WebDriver driver;
    MainPage mainPage;

    @BeforeEach
    public void setData() {
        driver = WebDriverSingleton.getInstance();
        driver.manage().window().maximize();
        driver.get(Config.standUrl());
        waitForMainPageLoaded();
        log.info("Тест запущен, главная страница открыта");

        mainPage = new MainPage(driver);
    }

    @Step("Ожидание полной загрузки главной страницы")
    private void waitForMainPageLoaded() {
        WaitHelper.waitForVisible(driver, By.xpath("//button[text() = 'Войти как пользователь']"));
        log.debug("Главная страница полностью загружена, кнопка входа кликабельна");
    }

    @AfterEach
    public void quitDriver() {
        WebDriverSingleton.quit();
        log.info("Тест завершён");
    }

    @Test
    @Feature("Регистрация заявок")
    @Story("Регистрация брака")
    @Description("Проверяет, что заявка на регистрацию брака успешно создаётся")
    @Severity(SeverityLevel.CRITICAL)
    public void marriageRegistrationTest() {
        log.info("Старт теста регистрации брака");

        ApplicantDataFormData applicantDataFormVO = TestData.applicantData();
        MarriageRegistrationData marriageRegistration = TestData.marriageRegistrationData();

        ApplicantDataForm applicantDataForm = new ApplicantDataForm(driver);
        ChoiceOfServicePage choiceOfServicePage = new ChoiceOfServicePage(driver);
        MarriageRegistrationCitizenDataForm marriageRegistrationCitizenDataForm =
                new MarriageRegistrationCitizenDataForm(driver);
        MarriageRegistrationServiceDataForm marriageRegistrationServiceDataForm =
                new MarriageRegistrationServiceDataForm(driver);

        mainPage.clickButtonLoginAsUser();
        log.debug("Пользователь вошёл, заполняется форма \"Данные заявителя\"");
        applicantDataForm.fillInAllFields(applicantDataFormVO);
        applicantDataForm.clickButtonNext();
        choiceOfServicePage.clickButtonMarriageRegistration();
        log.debug("Услуга \"Регистрация брака\" выбрана, заполняется форма \"Данные гражданина\"");
        marriageRegistrationCitizenDataForm.fillInAllFields(marriageRegistration);
        marriageRegistrationCitizenDataForm.clickButtonNext();
        log.debug("Заполняется форма \"Данные услуги\"");
        marriageRegistrationServiceDataForm.fillInAllFields(marriageRegistration);
        marriageRegistrationServiceDataForm.clickButtonFinish();
        log.debug("Форма \"Данные услуги\" отправлена, ожидается страница со статусом заявки");

        checkApplicationStatusPage();
    }

    @Test
    @Feature("Регистрация заявок")
    @Story("Регистрация рождения")
    @Description("Проверяет, что заявка на регистрацию рождения успешно создаётся")
    @Severity(SeverityLevel.CRITICAL)
    public void birthRegistrationTest() {
        log.info("Старт теста регистрации рождения");

        ApplicantDataFormData applicantDataFormVO = TestData.applicantData();
        BirthRegistrationData birthRegistration = TestData.birthRegistrationData();

        ApplicantDataForm applicantDataForm = new ApplicantDataForm(driver);
        ChoiceOfServicePage choiceOfServicePage = new ChoiceOfServicePage(driver);
        BirthRegistrationCitizenDataForm birthRegistrationCitizenDataForm =
                new BirthRegistrationCitizenDataForm(driver);
        BirthRegistrationServiceDataForm birthRegistrationServiceDataForm =
                new BirthRegistrationServiceDataForm(driver);

        mainPage.clickButtonLoginAsUser();
        log.debug("Пользователь вошёл, заполняется форма \"Данные заявителя\"");
        applicantDataForm.fillInAllFields(applicantDataFormVO);
        applicantDataForm.clickButtonNext();
        choiceOfServicePage.clickButtonBirthRegistration();
        log.debug("Услуга \"Регистрация рождения\" выбрана, заполняется форма \"Данные гражданина\"");
        birthRegistrationCitizenDataForm.fillInAllFields(birthRegistration);
        birthRegistrationCitizenDataForm.clickButtonNext();
        log.debug("Заполняется форма \"Данные услуги\"");
        birthRegistrationServiceDataForm.fillInAllFields(birthRegistration);
        birthRegistrationServiceDataForm.clickButtonFinish();
        log.debug("Форма \"Данные услуги\" отправлена, ожидается страница со статусом заявки");

        checkApplicationStatusPage();
    }

    @Test
    @Feature("Регистрация заявок")
    @Story("Регистрация смерти")
    @Description("Проверяет, что заявка на регистрацию смерти успешно создаётся")
    @Severity(SeverityLevel.CRITICAL)
    public void deathRegistrationTest() {
        log.info("Старт теста регистрации смерти");

        ApplicantDataFormData applicantDataFormVO = TestData.applicantData();
        DeathRegistrationData deathRegistration = TestData.deathRegistrationData();

        ApplicantDataForm applicantDataForm = new ApplicantDataForm(driver);
        ChoiceOfServicePage choiceOfServicePage = new ChoiceOfServicePage(driver);
        DeathRegistrationCitizenDataForm deathRegistrationCitizenDataForm = new DeathRegistrationCitizenDataForm(driver);
        DeathRegistrationServiceDataForm deathRegistrationServiceDataForm = new DeathRegistrationServiceDataForm(driver);

        mainPage.clickButtonLoginAsUser();
        log.debug("Пользователь вошёл, заполняется форма \"Данные заявителя\"");
        applicantDataForm.fillInAllFields(applicantDataFormVO);
        applicantDataForm.clickButtonNext();
        choiceOfServicePage.clickButtonDeathRegistration();
        log.debug("Услуга \"Регистрация смерти\" выбрана, заполняется форма \"Данные гражданина\"");
        deathRegistrationCitizenDataForm.fillInAllFields(deathRegistration);
        deathRegistrationCitizenDataForm.clickButtonNext();
        log.debug("Заполняется форма \"Данные услуги\"");
        deathRegistrationServiceDataForm.fillInAllFields(deathRegistration);
        deathRegistrationServiceDataForm.clickButtonFinish();
        log.debug("Форма \"Данные услуги\" отправлена, ожидается страница со статусом заявки");

        checkApplicationStatusPage();
    }

    @Step("Проверка сообщений на странице успешной регистрации заявки")
    private void checkApplicationStatusPage() {
        WaitHelper.waitForVisible(driver, By.xpath("//span[text() = 'Спасибо за обращение!']"));
        ApplicationStatusPage applicationStatusPage = new ApplicationStatusPage(driver);
        log.debug("Открыта страница со статусом заявки, проверяются все сообщения");

        Assertions.assertTrue(applicationStatusPage.isThanksTextDisplayed(),
                "Не отображается текст 'Спасибо за обращение!'");
        Assertions.assertTrue(applicationStatusPage.isApplicationNumberTextDisplayed(),
                "Не отображается текст 'Ваша заявка № ... отправлена на рассмотрение.'");
        Assertions.assertTrue(applicationStatusPage.isRegistrationDateTextDisplayed(),
                "Не отображается текст 'Дата регистрации заявки ...'");
        Assertions.assertTrue(applicationStatusPage.isStatusTextDisplayed(),
                "Не отображается текст 'Статус заявки: ...'");
        Assertions.assertFalse(applicationStatusPage.getApplicationNumber().equals("null"),
                "Номер заявки отображается как 'null'");
        log.info("Все сообщения статусной страницы отображаются корректно");

        String expectedRegistrationDate = LocalDate.now()
                .format(DateTimeFormatter.ofPattern("EEE MMM dd yyyy", Locale.ENGLISH));
        Assertions.assertEquals(expectedRegistrationDate, applicationStatusPage.getRegistrationDate(),
                "Дата регистрации заявки не соответствует текущей дате");
        log.info("Дата регистрации заявки совпала с текущей датой: {}", expectedRegistrationDate);

        String actualStatus = applicationStatusPage.getStatus();
        Assertions.assertFalse(actualStatus.contains("Null"), "Статус заявки равен Null");
        Assertions.assertFalse(actualStatus.isBlank(), "Статус заявки отсутствует");
        log.info("Проверка страницы со статусом заявки завершена, статус: '{}'", actualStatus);
    }

    @Test
    @Feature("Проверка статуса заявок от имени администратора")
    @Story("Проверка статуса заявки регистрации брака")
    @Description("Проверяет, что статус заявки на регистрацию брака в таблице администрирования заявок совпадает " +
            "со статусом заявки на странице успешной регистрации заявки")
    @Severity(SeverityLevel.NORMAL)
    public void StatusCheckMarriageTest() {
        log.info("Старт теста проверки статуса заявки брака");

        ApplicantDataFormData applicantDataFormVO = TestData.applicantData();
        MarriageRegistrationData marriageRegistration = TestData.marriageRegistrationData();

        ApplicantDataForm applicantDataForm = new ApplicantDataForm(driver);
        ChoiceOfServicePage choiceOfServicePage = new ChoiceOfServicePage(driver);
        MarriageRegistrationCitizenDataForm marriageRegistrationCitizenDataForm =
                new MarriageRegistrationCitizenDataForm(driver);
        MarriageRegistrationServiceDataForm marriageRegistrationServiceDataForm =
                new MarriageRegistrationServiceDataForm(driver);
        ApplicationStatusPage applicationStatusPage = new ApplicationStatusPage(driver);

        mainPage.clickButtonLoginAsUser();
        log.debug("Пользователь вошёл, заполняется форма \"Данные заявителя\"");
        applicantDataForm.fillInAllFields(applicantDataFormVO);
        applicantDataForm.clickButtonNext();
        choiceOfServicePage.clickButtonMarriageRegistration();
        log.debug("Услуга \"Регистрация брака\" выбрана, заполняется форма \"Данные гражданина\"");
        marriageRegistrationCitizenDataForm.fillInAllFields(marriageRegistration);
        marriageRegistrationCitizenDataForm.clickButtonNext();
        log.debug("Заполняется форма \"Данные услуги\"");
        marriageRegistrationServiceDataForm.fillInAllFields(marriageRegistration);
        marriageRegistrationServiceDataForm.clickButtonFinish();
        log.debug("Форма \"Данные услуги\" отправлена, ожидается страница со статусом заявки");

        String applicationNumber = waitRealApplicationNumber(applicationStatusPage);
        log.info("Номер заявки на странице со статусом заявки: {}", applicationNumber);
        String statusFromStatusPage = applicationStatusPage.getStatus();

        String actualStatus = checkStatusInAdminTable(applicationNumber);

        log.info("Сравнение статусов заявки № {}: страница со статусом заявки'{}', таблица администрирования заявок '{}'",
                applicationNumber, statusFromStatusPage, actualStatus);

        Assertions.assertEquals(statusFromStatusPage.replace(".", ""),
                actualStatus.replace(".", ""),
                "Статус заявки " + applicationNumber +
                        " из таблицы не совпал со статусом со страницы 'Статус заявки'");
        log.info("Статусы заявки № {} совпали", applicationNumber);
    }

    @Test
    @Feature("Проверка статуса заявок от имени администратора")
    @Story("Проверка статуса заявки регистрации рождения")
    @Description("Проверяет, что статус заявки на регистрацию рождения в таблице администрирования заявок совпадает " +
            "со статусом заявки на странице успешной регистрации заявки")
    @Severity(SeverityLevel.NORMAL)
    public void StatusCheckBirthTest() {
        log.info("Старт теста проверки статуса заявки рождения");

        ApplicantDataFormData applicantDataFormVO = TestData.applicantData();
        BirthRegistrationData birthRegistration = TestData.birthRegistrationData();

        ApplicantDataForm applicantDataForm = new ApplicantDataForm(driver);
        ChoiceOfServicePage choiceOfServicePage = new ChoiceOfServicePage(driver);
        BirthRegistrationCitizenDataForm birthRegistrationCitizenDataForm =
                new BirthRegistrationCitizenDataForm(driver);
        BirthRegistrationServiceDataForm birthRegistrationServiceDataForm =
                new BirthRegistrationServiceDataForm(driver);
        ApplicationStatusPage applicationStatusPage = new ApplicationStatusPage(driver);

        mainPage.clickButtonLoginAsUser();
        log.debug("Пользователь вошёл, заполняется форма \"Данные заявителя\"");
        applicantDataForm.fillInAllFields(applicantDataFormVO);
        applicantDataForm.clickButtonNext();
        choiceOfServicePage.clickButtonBirthRegistration();
        log.debug("Услуга \"Регистрация рождения\" выбрана, заполняется форма \"Данные гражданина\"");
        birthRegistrationCitizenDataForm.fillInAllFields(birthRegistration);
        birthRegistrationCitizenDataForm.clickButtonNext();
        log.debug("Заполняется форма \"Данные услуги\"");
        birthRegistrationServiceDataForm.fillInAllFields(birthRegistration);
        birthRegistrationServiceDataForm.clickButtonFinish();
        log.debug("Форма \"Данные услуги\" отправлена, ожидается страница со статусом заявки");

        String applicationNumber = waitRealApplicationNumber(applicationStatusPage);
        log.info("Номер заявки: {}", applicationNumber);
        String statusFromStatusPage = applicationStatusPage.getStatus();

        String actualStatus = checkStatusInAdminTable(applicationNumber);

        log.info("Сравнение статусов заявки № {}: страница \"Статус заявки\"'{}', таблица администрирования заявок '{}'",
                applicationNumber, statusFromStatusPage, actualStatus);

        Assertions.assertEquals(statusFromStatusPage.replace(".", ""),
                actualStatus.replace(".", ""),
                "Статус заявки " + applicationNumber +
                        " из таблицы не совпал со статусом со страницы 'Статус заявки'");
        log.info("Статусы заявки № {} совпали", applicationNumber);
    }

    @Test
    @Feature("Проверка статуса заявок от имени администратора")
    @Story("Проверка статуса заявки регистрации смерти")
    @Description("Проверяет, что статус заявки на регистрацию смерти в таблице администрирования заявок совпадает " +
            "со статусом заявки на странице успешной регистрации заявки")
    @Severity(SeverityLevel.NORMAL)
    public void StatusCheckDeathTest() {
        log.info("Старт теста проверки статуса заявки смерти");

        ApplicantDataFormData applicantDataFormVO = TestData.applicantData();
        DeathRegistrationData deathRegistration = TestData.deathRegistrationData();

        ApplicantDataForm applicantDataForm = new ApplicantDataForm(driver);
        ChoiceOfServicePage choiceOfServicePage = new ChoiceOfServicePage(driver);
        DeathRegistrationCitizenDataForm deathRegistrationCitizenDataForm = new DeathRegistrationCitizenDataForm(driver);
        DeathRegistrationServiceDataForm deathRegistrationServiceDataForm = new DeathRegistrationServiceDataForm(driver);
        ApplicationStatusPage applicationStatusPage = new ApplicationStatusPage(driver);

        mainPage.clickButtonLoginAsUser();
        log.debug("Пользователь вошёл, заполняется форма \"Данные заявителя\"");
        applicantDataForm.fillInAllFields(applicantDataFormVO);
        applicantDataForm.clickButtonNext();
        choiceOfServicePage.clickButtonDeathRegistration();
        log.debug("Услуга \"Регистрация смерти\" выбрана, заполняется форма \"Данные гражданина\"");
        deathRegistrationCitizenDataForm.fillInAllFields(deathRegistration);
        deathRegistrationCitizenDataForm.clickButtonNext();
        log.debug("Заполняется форма \"Данные услуги\"");
        deathRegistrationServiceDataForm.fillInAllFields(deathRegistration);
        deathRegistrationServiceDataForm.clickButtonFinish();
        log.debug("Форма \"Данные услуги\" отправлена, ожидается страница со статусом заявки");

        String applicationNumber = waitRealApplicationNumber(applicationStatusPage);
        log.info("Номер заявки: {}", applicationNumber);
        String statusFromStatusPage = applicationStatusPage.getStatus();

        String actualStatus = checkStatusInAdminTable(applicationNumber);

        log.info("Сравнение статусов заявки № {}: страница \"Статус заявки\"'{}', таблица администрирования заявок '{}'",
                applicationNumber, statusFromStatusPage, actualStatus);

        Assertions.assertEquals(statusFromStatusPage.replace(".", ""),
                actualStatus.replace(".", ""),
                "Статус заявки " + applicationNumber +
                        " из таблицы не совпал со статусом со страницы 'Статус заявки'");
        log.info("Статусы заявки № {} совпали", applicationNumber);
    }

    @Step("Ожидание появления номера заявки после регистрации")
    private String waitRealApplicationNumber(ApplicationStatusPage applicationStatusPage) {
        applicationStatusPage.clickButtonRefresh();
        String applicationNumber = applicationStatusPage.getApplicationNumber();
        if (applicationNumber.isBlank() || applicationNumber.equals("null")) {
            log.warn("Номер заявки недоступен ('{}'), включаются повторные попытки обновления", applicationNumber);
        }
        int attempts = 0;
        while ((applicationNumber.isBlank() || applicationNumber.equals("null")) && attempts < 10) {
            attempts++;
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
            applicationStatusPage.clickButtonRefresh();
            applicationNumber = applicationStatusPage.getApplicationNumber();
        }
        log.info("Номер заявки получен: '{}' (попыток обновления: {})", applicationNumber, attempts);
        return applicationNumber;
    }

    @Step("Вход как администратор и поиск статуса заявки в таблице администрирования заявок")
    private String checkStatusInAdminTable(String applicationNumber) {
        AdminDataForm adminDataForm = new AdminDataForm(driver);
        ApplicationTable applicationTable = new ApplicationTable(driver);

        AdminRegistrationData adminRegistration = TestData.adminData();

        log.info("Переход на главную страницу и вход как администратор");
        driver.get(Config.standUrl());
        waitForMainPageLoaded();
        mainPage.clickButtonLoginAsAdmin();

        adminDataForm.fillInAllFields(adminRegistration);
        adminDataForm.clickButtonNext();

        log.info("Поиск статуса заявки № {} в таблице администрирования", applicationNumber);
        return applicationTable.getApplicationStatus(applicationNumber);
    }
}
