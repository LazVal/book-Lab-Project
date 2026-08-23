package api;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import models.login.InvalidLoginBodyResponseModel;
import models.login.LoginBodyModel;
import models.login.SuccessfulLoginBodyResponseModel;
import models.login.WrongLoginBodyResponseModel;

import static io.restassured.RestAssured.given;
import static specs.login.LoginSpec.*;

public class AuthApiClient {
    @Step("Отправка запроса на авторизацию")
    public SuccessfulLoginBodyResponseModel login(LoginBodyModel body) {
        return given(loginRequestSpec)
                .body(body)
                .when()
                .post("/auth/token/")
                .then()
                .spec(successResponseSpec)
                .extract().as(SuccessfulLoginBodyResponseModel.class);
    }

    @Step("Отправка запроса на авторизацию с неверным паролем")
    public WrongLoginBodyResponseModel loginWrong(LoginBodyModel body) {
        return given(loginRequestSpec)
                .body(body)
                .when()
                .post("/auth/token/")
                .then()
                .spec(wrongLoginResponseSpec)
                .extract().as(WrongLoginBodyResponseModel.class);
    }

    @Step("Отправка запроса на авторизацию с пустыми данными")
    public InvalidLoginBodyResponseModel loginInvalid(LoginBodyModel body) {
        return given(loginRequestSpec)
                .body(body)
                .when()
                .post("/auth/token/")
                .then()
                .spec(invalidLoginResponseSpec)
                .extract().as(InvalidLoginBodyResponseModel.class);
    }

    @Step("Отправка запроса, ошибка 405")
    public void loginNotAllowed(LoginBodyModel body) {
        given()
                .body(body)
                .when()
                .post("/auth/token/")
                .then()
                .spec(notAllowedLoginResponseSpec);
    }

    @Step("Успешная авторизация и получение токена")
    public Response register(LoginBodyModel body) {
        return given(loginRequestSpec)
                .body(body)
                .when()
                .post("/auth/token/")
                .then()
                .spec(successResponseSpec)
                .extract()
                .response();
    }
}
