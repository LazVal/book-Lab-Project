package tests;

import io.qameta.allure.Story;
import models.login.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.Assertions.assertThat;
import static tests.TestData.*;

@DisplayName("Авторизация")
@Story("Авторизация/ошибки авторизации")
public class LoginTest extends BaseTest {

    @Test
    @DisplayName("Успешная авторизация пользователя")
    public void successfulLoginTest() {

        LoginBodyModel loginData = new LoginBodyModel(USERNAME, PASSWORD);

        SuccessfulLoginBodyResponseModel successfulLoginBodyResponseModel = api.auth.login(loginData);

        step("Проверка токена", () -> {
            assertThat(successfulLoginBodyResponseModel.access()).startsWith(EXPECTED_TOKEN);
            assertThat(successfulLoginBodyResponseModel.access()).startsWith(EXPECTED_TOKEN);
            assertThat(successfulLoginBodyResponseModel.access()).isNotEqualTo(successfulLoginBodyResponseModel.refresh());
        });
    }

    @Test
    @DisplayName("Получение ошибки 'Invalid username or password'")
    public void invalidLoginTest() {

        LoginBodyModel loginData = new LoginBodyModel(USERNAME, WRONG_PASSWORD);

        WrongLoginBodyResponseModel loginResponse = api.auth.loginWrong(loginData);

        step("Проверка результата", () -> {
            assertThat(loginResponse.detail()).isEqualTo(INVALID_USERNAME_ERROR);
        });

    }


    @Test
    @DisplayName("Получение ошибки 'Поле не может быть пустым'")
    public void blankFieldLoginTest() {

        LoginBodyModel loginData = new LoginBodyModel(BLANK_USERNAME, BLANK_PASSWORD);

        InvalidLoginBodyResponseModel invalidLoginResponse = api.auth.loginInvalid(loginData);

        String actualUsernameError = invalidLoginResponse.username().get(0);
        String actualPasswordError = invalidLoginResponse.password().get(0);

        step("Проверка результата", () -> {

            assertThat(actualUsernameError).isEqualTo(NOT_BE_BLANK_ERROR);
            assertThat(actualPasswordError).isEqualTo(NOT_BE_BLANK_ERROR);
        });

    }

    @Test
    @DisplayName("Получение ошибки '405'")
    public void notAllowedLoginTest() {

        LoginBodyModel loginData = new LoginBodyModel(BLANK_USERNAME, BLANK_PASSWORD);
        api.auth.loginNotAllowed(loginData);

    }
}
