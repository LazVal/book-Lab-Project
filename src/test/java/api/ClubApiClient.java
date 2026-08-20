package api;

import io.qameta.allure.Step;
import models.club.*;

import static io.restassured.RestAssured.given;
import static specs.club.CreateClubSpec.*;


public class ClubApiClient {
    @Step("Отправка запроса на создание клуба")
    public CreateClubResponseBodyModel createClub(CreateClubRequestBodyModel body, String token) {
        return given(CreateClubRequestSpec)
                .body(body)
                .when()
                .auth().oauth2(token)
                .post("/clubs/")
                .then()
                .spec(successCreateResponseClubSpec)
                .extract().as(CreateClubResponseBodyModel.class);
    }

    @Step("Отправка запроса на получение информации о клубе")
    public GetClubResponseModel getClub(int id) {
        return given(CreateClubRequestSpec)
                .when()
                .get("/clubs/" + id)
                .then()
                .spec(successGetResponseClubSpec)
                .extract()
                .as(GetClubResponseModel.class);
    }

    @Step("Обновление названия и автора книги")
    public UpdateClubResponseBodyModel updateClub(int id, UpdateClubRequestBodyModel body, String token) {
        return given(CreateClubRequestSpec)
                .body(body)
                .when()
                .auth().oauth2(token)
                .patch("/clubs/" + id + "/")
                .then()
                .spec(successUpdateResponseClubSpec)
                .extract().as(UpdateClubResponseBodyModel.class);
    }

    @Step("Удаление клуба")
    public void deleteClub(int id, String token) {
        given(CreateClubRequestSpec)
                .when()
                .auth().oauth2(token)
                .delete("/clubs/" + id + "/")
                .then()
                .spec(successDeleteResponseClubSpec);
    }
}
