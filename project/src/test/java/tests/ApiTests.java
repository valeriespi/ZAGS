package tests;

import Api.ApiSpecs;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;
import io.qameta.allure.Story;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Epic("ЗАГС")
@Feature("API")
public class ApiTests {

    private static final Logger log = LogManager.getLogger(ApiTests.class);

    private static final String REQUEST_WEDDING = "wedding";
    private static final String STATUS_UNDER_CONSIDERATION = "under consideration";
    private static final String STATUS_APPROVED = "approved";
    private static final String KIND_OF_APPLICATION_MARRIAGE = "Получение свидетельства о браке";

    @Step("Формирование тела заявки на регистрацию брака")
    private Map<String, Object> weddingPayload() {
        Map<String, Object> body = new HashMap<>();
        body.put("mode", REQUEST_WEDDING);
        body.put("personalLastName", "Иванов");
        body.put("personalFirstName", "Иван");
        body.put("personalMiddleName", "Иванович");
        body.put("personalPhoneNumber", "12345678910");
        body.put("personalNumberOfPassport", "12345678");
        body.put("personalAddress", "Гродно");
        body.put("citizenLastName", "Иванов");
        body.put("citizenFirstName", "Иван");
        body.put("citizenMiddleName", "Иванович");
        body.put("citizenBirthDate", "2000-01-01");
        body.put("citizenNumberOfPassport", "12345678");
        body.put("citizenGender", "Male");
        body.put("citizenAddress", "Гродно");
        body.put("dateOfMarriage", "2000-01-01");
        body.put("newLastName", "Иванов");
        body.put("anotherPersonLastName", "Иванова");
        body.put("anotherPersonFirstName", "Катя");
        body.put("anotherPersonMiddleName", "Ивановна");
        body.put("birth_of_anotoherPerson", "2000-01-01");
        body.put("anotherPersonPassport", "12345678");
        body.put("birth_place", null);
        body.put("birth_mother", null);
        body.put("birth_father", null);
        body.put("birth_grandpa", null);
        body.put("birth_grandma", null);
        body.put("death_dateOfDeath", null);
        body.put("death_placeOfDeath", null);
        return body;
    }

    @Step("Формирование тела заявки на создание администратора")
    private Map<String, Object> adminPayload() {
        Map<String, Object> body = new HashMap<>();
        body.put("dateofbirth", "2000-01-01T00:00:00.000Z");
        body.put("personalFirstName", "Иван");
        body.put("personalLastName", "Иванов");
        body.put("personalMiddleName", "Иванович");
        body.put("personalNumberOfPassport", "12345678");
        body.put("personalPhoneNumber", "12345678910");
        return body;
    }

    @Step("Создание заявки через /sendUserRequest и получение её ID")
    private int createApplication() {
        Response response = RestAssured
                .given()
                .spec(ApiSpecs.REQUEST_SPEC)
                .body(weddingPayload())
                .post("/sendUserRequest")
                .then()
                .spec(ApiSpecs.RESPONSE_OK)
                .extract()
                .response();

        int applicationId = response.jsonPath().getInt("data.applicationid");
        Assertions.assertTrue(applicationId > 0, "ID заявки пуст");
        Assertions.assertTrue(response.jsonPath().getInt("data.applicantid") > 0, "ID заявителя пуст");
        Assertions.assertTrue(response.jsonPath().getInt("data.citizenid") > 0, "ID гражданина пуст");
        Assertions.assertTrue(response.jsonPath().getInt("data.merrigecertificateid") > 0, "ID свидетельства пуст");
        log.info("Создана заявка № {}", applicationId);
        return applicationId;
    }

    @Test
    @Story("POST /sendUserRequest (регистрация брака)")
    @Description("Проверяется, что заявка создаётся (200) и в ответе приходят все ID: заявителя, гражданина, заявки и свидетельства")
    @Severity(SeverityLevel.BLOCKER)
    public void sendUserRequestTest() {
        log.info("Старт API-теста создания заявки пользователя");
        createApplication();
        log.info("Заявка пользователя создана, все ID получены");
    }

    @Test
    @Story("GET /getApplications")
    @Description("Проверяется, что приходит непустой массив заявок и у каждой заявки ненулевой ID")
    @Severity(SeverityLevel.BLOCKER)
    public void applicationsListTest() {
        log.info("Старт API-теста списка заявок");

        List<Map<String, Object>> rows = RestAssured
                .given()
                .spec(ApiSpecs.REQUEST_SPEC)
                .get("/getApplications")
                .then()
                .spec(ApiSpecs.RESPONSE_OK)
                .extract()
                .path("data");
        Assertions.assertFalse(rows.isEmpty(), "Список заявок пуст");

        for (Map<String, Object> row : rows) {
            Object actualId = row.get("applicationid");
            Assertions.assertTrue(actualId instanceof Number number && number.intValue() > 0,
                    "В списке есть заявка с пустым ID");
        }
        log.info("Список заявок получен, заявок: {}, все ID ненулевые", rows.size());
    }

    @Test
    @Story("GET /getApplStatus/{applicationId}")
    @Description("Проверяется, что по ID свежесозданной заявки приходят её тип ('Получение свидетельства о браке') и статус 'На рассмотрении'")
    @Severity(SeverityLevel.BLOCKER)
    public void applicationStatusTest() {
        log.info("Старт API-теста статуса заявки");
        int applicationId = createApplication();

        Response response = RestAssured
                .given()
                .spec(ApiSpecs.REQUEST_SPEC)
                .get("/getApplStatus/" + applicationId)
                .then()
                .spec(ApiSpecs.RESPONSE_OK)
                .extract()
                .response();
        Assertions.assertEquals(KIND_OF_APPLICATION_MARRIAGE,
                response.jsonPath()
                        .getString("data.kindofapplication"),
                "Тип заявки не соответствует регистрации брака");
        Assertions.assertEquals(STATUS_UNDER_CONSIDERATION,
                response.jsonPath()
                        .getString("data.statusofapplication"),
                "Новая заявка должна быть на рассмотрении");
        log.info("Заявка № {} на рассмотрении: '{}'", applicationId, KIND_OF_APPLICATION_MARRIAGE);
    }

    @Test
    @Story("POST /sendAdminRequest")
    @Description("Проверяется, что администратор создаётся (200) и в ответе приходит его ненулевой staffid")
    @Severity(SeverityLevel.NORMAL)
    public void sendAdminRequestTest() {
        log.info("Старт API-теста создания администратора");
        int staffId = RestAssured
                .given()
                .spec(ApiSpecs.REQUEST_SPEC)
                .body(adminPayload())
                .post("/sendAdminRequest")
                .then()
                .spec(ApiSpecs.RESPONSE_OK)
                .extract()
                .path("data.staffid");
        Assertions.assertTrue(staffId > 0, "ID администратора пуст");
        log.info("Администратор создан, staffId = {}", staffId);
    }

    @Test
    @Story("POST /requestProcess")
    @Description("Проверяется, что статус заявки меняется на 'Одобрена': и в ответе /requestProcess, и повторным GET /getApplStatus; также сверяется тип заявки")
    @Severity(SeverityLevel.CRITICAL)
    public void requestProcessTest() {
        log.info("Старт API-теста обработки заявки администратором");
        int applicationId = createApplication();

        int staffId = RestAssured
                .given()
                .spec(ApiSpecs.REQUEST_SPEC)
                .body(adminPayload())
                .post("/sendAdminRequest")
                .then()
                .spec(ApiSpecs.RESPONSE_OK)
                .extract()
                .path("data.staffid");
        Assertions.assertTrue(staffId > 0, "ID администратора пуст");
        log.debug("Администратор создан, staffId = {}", staffId);

        Map<String, Object> body = new HashMap<>();
        body.put("applId", applicationId);
        body.put("staffid", staffId);
        body.put("action", STATUS_APPROVED);

        Response processResponse = RestAssured
                .given()
                .spec(ApiSpecs.REQUEST_SPEC)
                .body(body)
                .post("/requestProcess")
                .then()
                .spec(ApiSpecs.RESPONSE_OK)
                .extract()
                .response();
        Assertions.assertEquals(STATUS_APPROVED,
                processResponse.jsonPath().getString("data.statusofapplication"),
                "Заявка не одобрена");
        Assertions.assertEquals(KIND_OF_APPLICATION_MARRIAGE,
                processResponse.jsonPath().getString("data.kindofapplication"),
                "Тип заявки не соответствует регистрации брака");
        log.info("Заявка № {} одобрена администратором {}", applicationId, staffId);

        Response statusAfter = RestAssured
                .given()
                .spec(ApiSpecs.REQUEST_SPEC)
                .get("/getApplStatus/" + applicationId)
                .then()
                .spec(ApiSpecs.RESPONSE_OK)
                .extract()
                .response();
        Assertions.assertEquals(STATUS_APPROVED,
                statusAfter.jsonPath().getString("data.statusofapplication"),
                "Статус заявки не сохранился после одобрения");
        log.info("Статус 'approved' подтверждён для заявки № {}", applicationId);
    }
}
