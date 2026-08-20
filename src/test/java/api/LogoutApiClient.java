package api;


import io.qameta.allure.Step;
import models.login.LoginBodyModel;
import models.logout.BadRequestLogoutBodyResponseModel;
import models.logout.LogoutBodyModel;


import static io.restassured.RestAssured.given;
import static specs.login.LoginSpec.loginRequestSpec;
import static specs.login.LoginSpec.successResponseSpec;
import static specs.logout.LogoutSpec.*;


public class LogoutApiClient {

    @Step("Отправка запроса logout")
    public void logout(LogoutBodyModel body) {
        given(logoutRequestSpec)
                .body(body)
                .when()
                .post("/auth/logout/")
                .then()
                .spec(successLogoutResponseSpec);
    }

    @Step("Отправка запроса logout с пустым RefreshToken")
    public BadRequestLogoutBodyResponseModel logoutBad (LogoutBodyModel body) {
        return given(logoutRequestSpec)
                .body(body)
                .when()
                .post("/auth/logout/")
                .then()
                .spec(isNullLogoutResponseSpec)
                .extract()
                .as(BadRequestLogoutBodyResponseModel.class);
    }

}
