package tests;

import Db.Applicant;
import Db.Application;
import Db.Citizen;
import Db.DbConnector;
import Db.Staff;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

@Epic("ЗАГС")
@Feature("БД")
public class DbTests {

    private static final SessionFactory SESSION_FACTORY = new Configuration()
            .addProperties(DbConnector.properties())
            .setProperty("hibernate.hbm2ddl.auto", "none")
            .addAnnotatedClass(Applicant.class)
            .addAnnotatedClass(Citizen.class)
            .addAnnotatedClass(Application.class)
            .addAnnotatedClass(Staff.class)
            .buildSessionFactory();

    @Test
    @Story("Клиенты")
    @Description("Создание клиента в БД через Hibernate и проверка сохранённых данных")
    public void clientCreationTest() {
        Assumptions.assumeTrue(DbConnector.isReachable(), "БД недоступна, тест пропущен");

        int applicantId;
        int citizenId;
        int applicationId;
        try (Session session = SESSION_FACTORY.openSession()) {
            session.beginTransaction();

            Applicant applicant = new Applicant()
                    .setSurname("Иванов")
                    .setName("Иван")
                    .setMiddlename("Иванович")
                    .setPassportNumber("12345678")
                    .setPhoneNumber("12345678910")
                    .setRegistrationAddress("Гродно");
            Citizen citizen = new Citizen()
                    .setSurname("Иванов")
                    .setName("Иван")
                    .setMiddlename("Иванович")
                    .setPassportNumber("12345678")
                    .setDateOfBirth("2000-01-01")
                    .setGender("Male")
                    .setRegistrationAddress("Гродно");
            session.persist(applicant);
            session.persist(citizen);

            Application application = new Application()
                    .setCitizenId(citizen.getCitizenId())
                    .setApplicantId(applicant.getApplicantId())
                    .setKindOfApplication("Получение свидетельства о браке")
                    .setChannel("web");
            session.persist(application);
            session.getTransaction().commit();

            applicantId = applicant.getApplicantId();
            citizenId = citizen.getCitizenId();
            applicationId = application.getApplicationId();
        }

        try (Session session = SESSION_FACTORY.openSession()) {
            Applicant savedApplicant = session.find(Applicant.class, applicantId);
            Citizen savedCitizen = session.find(Citizen.class, citizenId);
            Application savedApplication = session.find(Application.class, applicationId);

            Assertions.assertNotNull(savedApplicant, "Заявитель не найден в БД");
            Assertions.assertEquals("Иванов", savedApplicant.getSurname());
            Assertions.assertEquals("Иван", savedApplicant.getName());
            Assertions.assertEquals("Иванович", savedApplicant.getMiddlename());
            Assertions.assertEquals("12345678", savedApplicant.getPassportNumber());
            Assertions.assertEquals("12345678910", savedApplicant.getPhoneNumber());
            Assertions.assertEquals("Гродно", savedApplicant.getRegistrationAddress());

            Assertions.assertNotNull(savedCitizen, "Гражданин не найден в БД");
            Assertions.assertEquals("Иванов", savedCitizen.getSurname());
            Assertions.assertEquals("Иван", savedCitizen.getName());
            Assertions.assertEquals("Иванович", savedCitizen.getMiddlename());
            Assertions.assertEquals("12345678", savedCitizen.getPassportNumber());
            Assertions.assertEquals("2000-01-01", savedCitizen.getDateOfBirth());
            Assertions.assertEquals("Male", savedCitizen.getGender());
            Assertions.assertEquals("Гродно", savedCitizen.getRegistrationAddress());

            Assertions.assertNotNull(savedApplication, "Заявка не найдена в БД");
            Assertions.assertEquals(citizenId, savedApplication.getCitizenId());
            Assertions.assertEquals(applicantId, savedApplication.getApplicantId());
            Assertions.assertEquals("Получение свидетельства о браке", savedApplication.getKindOfApplication());
            Assertions.assertEquals("under consideration", savedApplication.getStatusOfApplication());
            Assertions.assertEquals("web", savedApplication.getChannel());
        }
    }

    @Test
    @Story("Администраторы")
    @Description("Создание администратора в БД через Hibernate и проверка сохранённой строки через JDBC")
    public void adminCreationTest() {
        Assumptions.assumeTrue(DbConnector.isReachable(), "БД недоступна, тест пропущен");

        long staffId;
        try (Session session = SESSION_FACTORY.openSession()) {
            session.beginTransaction();
            Staff staff = new Staff()
                    .setSurname("Иванов")
                    .setName("Иван")
                    .setMiddlename("Иванович")
                    .setDateOfBirth(LocalDate.of(2000, 1, 1))
                    .setPassportNumber("12345678")
                    .setPhoneNumber("12345678910");
            session.persist(staff);
            session.getTransaction().commit();
            staffId = staff.getStaffId();
        }
        Assertions.assertTrue(staffId > 0, "Hibernate не вернул ID администратора");

        try (Connection connection = DbConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "SELECT * FROM reg_office.staff WHERE staffid = ?")) {
            statement.setLong(1, staffId);
            ResultSet row = statement.executeQuery();
            Assertions.assertTrue(row.next(), "Администратор не найден в БД");
            Assertions.assertEquals("Иванов", row.getString("surname"));
            Assertions.assertEquals("Иван", row.getString("name"));
            Assertions.assertEquals("Иванович", row.getString("middlename"));
            Assertions.assertEquals("2000-01-01", row.getString("dateofbirth"));
            Assertions.assertEquals("12345678", row.getString("passportnumber"));
            Assertions.assertEquals("12345678910", row.getString("phonenumber"));
        } catch (SQLException e) {
            Assertions.fail("Ошибка обращения к БД", e);
        }
    }
}
