package tests;

import models.registration.lombok.RegistrationBodyLombokModel;
import models.registration.lombok.RegistrationResponseLombokModel;
import models.registration.lombok.WrongRegistrationResponseLombokModel;
import models.registration.records.ExistingUserResponseRecordsModel;
import models.registration.records.RegistrationBodyRecordsModel;
import models.registration.records.RegistrationResponseRecordsModel;
import net.datafaker.Faker;
import org.junit.jupiter.api.*;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static specs.registration.RegistrationSpec.*;
import static tests.TestData.NOT_BE_BLANK_ERROR;
import static tests.TestData.USER_ALREADY_EXIST_ERROR;

public class RegistrationTest extends BaseTest {

    String USERNAME;
    String PASSWORD;

    @BeforeEach
    public void prepareTestData() {
        Faker faker = new Faker();
        USERNAME = faker.name().firstName();
        PASSWORD = faker.name().firstName();
    }

    @Test
    @DisplayName("Успешная регистрация пользователя")
    public void successfulRegistrationTest_with_lombok() {
        RegistrationBodyLombokModel data = new RegistrationBodyLombokModel();
        data.setUsername(USERNAME);
        data.setPassword(PASSWORD);

        //RegistrationBodyLombokModel data = new RegistrationBodyLombokModel(USERNAME, PASSWORD);
        RegistrationResponseLombokModel registrationResponse = api.users.register(data);

        step("Проверка username", () -> {
            assertEquals(USERNAME, registrationResponse.getUsername());
        });
    }


    @Test
    @DisplayName("Получение ошибки 'Пользователь уже существует'")
    public void existingUserTest() {
        RegistrationBodyRecordsModel data = new RegistrationBodyRecordsModel(USERNAME, PASSWORD);

        RegistrationResponseRecordsModel firstRegistrationResponse = api.users.registerRecord(data);

        step("Проверка ответа (200) ", () -> {
            assertThat(firstRegistrationResponse.username()).isEqualTo(USERNAME);
        });

        ExistingUserResponseRecordsModel secondRegistrationResponse = api.users.registerExisting(data);

        step("Проверка получения ошибки", () -> {
            assertThat(secondRegistrationResponse.username().get(0)).isEqualTo(USER_ALREADY_EXIST_ERROR);
        });
    }

    @Test
    @DisplayName("Получение ошибки 'Поле не может быть пустым'")
    public void invalidUsername400Test() {
        RegistrationBodyLombokModel data = new RegistrationBodyLombokModel();
        data.setUsername(" ");
        data.setPassword(PASSWORD);

        WrongRegistrationResponseLombokModel wrongRegistrationResponseLombokModel = api.users.registerWrong(data);

        step("Проверка получения ощибки 400", () -> {
            assertThat(wrongRegistrationResponseLombokModel.getUsername().get(0)).isEqualTo(NOT_BE_BLANK_ERROR);
        });
    }

    @Test
    @Tag("404 not Found")
    @DisplayName("Получение ошибки 404 not Found")
    public void negativeRegistration404Test() {
        RegistrationBodyLombokModel data = new RegistrationBodyLombokModel();
        data.setUsername(USERNAME);
        data.setPassword(PASSWORD);
        api.users.registerNotAllowed(data);
    }
}
