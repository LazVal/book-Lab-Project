package api;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import models.login.LoginBodyModel;

import static io.restassured.RestAssured.given;
import static specs.login.LoginSpec.loginRequestSpec;
import static specs.login.LoginSpec.successResponseSpec;

/**
 * Общий API-клиент — единая точка доступа к клиентам эндпоинтов.
 */
public class ApiClient {
    public final UsersApiClient users = new UsersApiClient();
    public final AuthApiClient auth = new AuthApiClient();
    public final LogoutApiClient logout = new LogoutApiClient();
    public final ClubApiClient club = new ClubApiClient();

}
