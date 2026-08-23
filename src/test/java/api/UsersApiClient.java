package api;

import io.qameta.allure.Step;
import models.user.GetUserResponseBodyModel;
import models.user.UnauthorizedGetUserResponseBodyModel;
import models.user.UpdateUserRequestBodyModel;
import models.registration.lombok.RegistrationBodyLombokModel;
import models.registration.lombok.RegistrationResponseLombokModel;
import models.registration.lombok.WrongRegistrationResponseLombokModel;
import models.registration.records.ExistingUserResponseRecordsModel;
import models.registration.records.RegistrationBodyRecordsModel;
import models.registration.records.RegistrationResponseRecordsModel;

import static io.restassured.RestAssured.given;
import static specs.BaseSpec.baseRequestSpec;
import static specs.user.GetUserSpec.successGetUserResponseSpec;
import static specs.user.GetUserSpec.unauthorizedGetUserResponseSpec;
import static specs.registration.RegistrationSpec.*;


public class UsersApiClient {

    @Step("Отправка запроса Registration")
    public RegistrationResponseLombokModel register(RegistrationBodyLombokModel body) {
        return given(registrationRequestSpec)
                .body(body)
                .when()
                .post("/users/register/")
                .then()
                .spec(successRegistrationResponseSpec)
                .extract()// переключаемся на извлечение
                .as(RegistrationResponseLombokModel.class);// десериализуем в модель
    }

    @Step("Отправка запроса Registration")
    public RegistrationResponseRecordsModel registerRecord(RegistrationBodyRecordsModel body) {
        return given(registrationRequestSpec)
                .body(body)
                .when()
                .post("/users/register/")
                .then()
                .spec(successRegistrationResponseSpec)
                .extract()
                .as(RegistrationResponseRecordsModel.class);
    }

    @Step("Повторная отправка запроса Registration")

    public ExistingUserResponseRecordsModel registerExisting(RegistrationBodyRecordsModel body) {
        return given(registrationRequestSpec)
                .body(body)
                .when()
                .post("/users/register/")
                .then()
                .log().all()
                .spec(existingUserRegistrationResponseSpec)
                .extract()
                .as(ExistingUserResponseRecordsModel.class);
    }

    @Step("Отправка запроса Registration с пустым Username")
    public WrongRegistrationResponseLombokModel registerWrong(RegistrationBodyLombokModel body) {
        return given(registrationRequestSpec)
                .body(body)
                .when()
                .post("/users/register/")
                .then()
                .spec(existingUserRegistrationResponseSpec)
                .extract()
                .as(WrongRegistrationResponseLombokModel.class);
    }

    @Step("Отправка запроса Registration на неверный endPoint")
    public void registerNotAllowed(RegistrationBodyLombokModel body) {
        given(registrationRequestSpec)
                .body(body)
                .when()
                .post("/users/registers")
                .then()
                .spec(negativeRegistrationResponseSpec);
    }


    @Step("Отправка запроса на получение информации о пользователе")
    public GetUserResponseBodyModel getUser(String token) {
        return given(baseRequestSpec)
                .when()
                .auth().oauth2(token)
                .get("/users/me/")
                .then()
                .spec(successGetUserResponseSpec)
                .extract()
                .as(GetUserResponseBodyModel.class);
    }

    @Step("Отправка запроса на обновление данных пользователя")
    public GetUserResponseBodyModel updateUser(UpdateUserRequestBodyModel body, String token) {
        return given(baseRequestSpec)
                .body(body)
                .when()
                .auth().oauth2(token)
                .patch("/users/me/")
                .then()
                .spec(successGetUserResponseSpec)
                .extract()
                .as(GetUserResponseBodyModel.class);
    }

    @Step("Отправка запроса без хэдера Authorization")
    public UnauthorizedGetUserResponseBodyModel anauthUser() {
        return given(baseRequestSpec)
                .when()
                .auth().oauth2("")
                .get("/users/me/")
                .then()
                .spec(unauthorizedGetUserResponseSpec)
                .extract()
                .as(UnauthorizedGetUserResponseBodyModel.class);
    }

}
