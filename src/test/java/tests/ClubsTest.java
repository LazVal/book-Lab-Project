package tests;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.response.Response;
import models.club.*;
import models.login.LoginBodyModel;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.Assertions.assertThat;
import static tests.TestData.*;

@Epic("API тесты")
@Feature("Клубы")
@Story("Создание, изменение, удаление клуба")
@DisplayName("Тесты клубов")
public class ClubsTest extends BaseTest {
    private static String accessToken;

    @BeforeAll
    @DisplayName("Успешная авторизация пользователя")
    public static void loginPrepareTest() {
        LoginBodyModel loginData = new LoginBodyModel(USERNAME, PASSWORD);
        Response response = api.auth.register(loginData);
        accessToken = response.path("access");
    }

    @Test
    @DisplayName("Успешное создание клуба")
    public void createClubTest() {
        CreateClubRequestBodyModel createClub = new CreateClubRequestBodyModel(testData.bookTitle, testData.bookAuthor, testData.publicationYear,
                testData.description, testData.telegramChatLink);

        CreateClubResponseBodyModel responseBodyModel = api.club.createClub(createClub, accessToken);

        step("Проверка результата создания клуба", () ->
                assertThat(responseBodyModel.bookTitle()).isEqualTo(testData.bookTitle)
        );
    }

    @Test
    @DisplayName("Получение детальной информации о клубе")
    public void getClubTest() {
        CreateClubRequestBodyModel createClub = new CreateClubRequestBodyModel(testData.bookTitle, testData.bookAuthor, testData.publicationYear,
                testData.description, testData.telegramChatLink);

        CreateClubResponseBodyModel responseBodyModel = api.club.createClub(createClub, accessToken);
        int id = responseBodyModel.id();

        GetClubResponseModel getClubResponseModel = api.club.getClub(id);

        step("Проверка результата получения детальной информации о клубе", () ->
                assertThat(getClubResponseModel.bookTitle()).isEqualTo(testData.bookTitle)
        );
    }

    @Test
    @DisplayName("Изменение названия и автора книги")
    public void updateClubTest() {
        CreateClubRequestBodyModel createClub = new CreateClubRequestBodyModel(testData.bookTitle, testData.bookAuthor, testData.publicationYear,
                testData.description, testData.telegramChatLink);

        CreateClubResponseBodyModel responseBodyModel = api.club.createClub(createClub, accessToken);
        int id = responseBodyModel.id();

        UpdateClubRequestBodyModel updateClub = new UpdateClubRequestBodyModel(testData.newBookTitle, testData.newBookAuthor);
        UpdateClubResponseBodyModel updateClubResponseBodyModel = api.club.updateClub(id, updateClub, accessToken);

        step("Проверка результата изменения автора и названия книги", () -> {
            assertThat(updateClubResponseBodyModel.bookTitle()).isEqualTo(testData.newBookTitle);
            assertThat(updateClubResponseBodyModel.bookAuthors()).isEqualTo(testData.newBookAuthor);
        });

    }

    @Test
    @DisplayName("Удаление клуба")
    public void deleteClubTest() {
        CreateClubRequestBodyModel createClub = new CreateClubRequestBodyModel(testData.bookTitle, testData.bookAuthor, testData.publicationYear,
                testData.description, testData.telegramChatLink);

        CreateClubResponseBodyModel responseBodyModel = api.club.createClub(createClub, accessToken);
        int id = responseBodyModel.id();

        api.club.deleteClub(id, accessToken);

    }
}
