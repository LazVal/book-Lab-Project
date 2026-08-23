package tests;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.response.Response;
import models.login.LoginBodyModel;
import models.logout.BadRequestLogoutBodyResponseModel;
import models.logout.LogoutBodyModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.Assertions.assertThat;
import static tests.TestData.*;

@Epic("API тесты")
@Feature("Выход")
@Story("Выход из системы")
@DisplayName("Выход пользователя")
public class LogoutTest extends BaseTest {

    @Test
    @DisplayName("Успешный выход пользователя")
    public void successLogoutTest() {
        LoginBodyModel loginData = new LoginBodyModel(USERNAME, PASSWORD);

        Response response = api.auth.register(loginData);
        String refreshToken = response.path("refresh");

        step("Выход из учетки и проверка ответа 200", () -> {
            LogoutBodyModel logoutData = new LogoutBodyModel(refreshToken);
            api.logout.logout(logoutData);

        });
    }

    @Test
    @DisplayName("Ошибка 'поле не может быть пустым'")
    public void refreshTokenIsNullTest() {
        LogoutBodyModel logoutData = new LogoutBodyModel("");

        BadRequestLogoutBodyResponseModel badRequestLogoutBodyResponseModel = api.logout.logoutBad(logoutData);

        step("Проверка результата", () ->
                assertThat(badRequestLogoutBodyResponseModel.refresh().get(0)).isEqualTo(NOT_BE_BLANK_ERROR)
        );
    }
}
