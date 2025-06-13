package tests;

import io.qameta.allure.*;
import models.lombok.*;
import org.junit.jupiter.api.Test;
import specs.SingleUserSpec;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;
import static specs.SingleUserSpec.*;

public class AnotherTests extends TestBase{


    @Test()
    @Feature("Проверка выбранного юзера")
    @Story("API тесты")
    @Owner("Kolyshkin A.S.")
    @Severity(SeverityLevel.NORMAL)
    void singleUserTest() {

        SupportUserModel supportUserModelData = new SupportUserModel();
        DataUserModel dataUserModel = new DataUserModel();
        dataUserModel.setEmail("janet.weaver@reqres.in");
        dataUserModel.setId(2);
        supportUserModelData.setText("Tired of writing endless social media content? Let Content Caddy generate it for you.");
        supportUserModelData.setUrl("https://contentcaddy.io?utm_source=reqres&utm_medium=json&utm_campaign=referral");

        SingleUserTestModel response =
                step("Make response", () -> given(SingleUserRequestSpec)
                .when()
                .get("/users/" + dataUserModel.getId().toString())
                        .then()
                        .spec(SingleUserResponseSpec200)
                        .extract().as(SingleUserTestModel.class));


        step("Check response", () -> {
            assertEquals(2, response.getData().getId());
            assertEquals("janet.weaver@reqres.in", response.getData().getEmail());
            assertEquals("Janet", response.getData().getFirst_name());
            assertEquals("Weaver", response.getData().getLast_name());

            assertEquals("https://contentcaddy.io?utm_source=reqres&utm_medium=json&utm_campaign=referral",
                    response.getSupport().getUrl());
            assertEquals("Tired of writing endless social media content? Let Content Caddy generate it for you.",
                    response.getSupport().getText());
        });

    }



    @Test()
    @Feature("Проверка на отсутсвие выбранного юзера")
    @Story("API тесты")
    @Owner("Kolyshkin A.S.")
    @Severity(SeverityLevel.NORMAL)
    void singleUserNotFoundTest() {

        DataUserModel dataUserModel = new DataUserModel();
        dataUserModel.setId(244);

        SingleUserTestModel response =
                step("Make response", () -> given(SingleUserRequestSpec)
                        .when()
                        .get("/users/" + dataUserModel.getId().toString())
                        .then()
                        .spec(SingleUserNegativeResponseSpec404)
                        .extract().as(SingleUserTestModel.class));

    }



    @Test
    @Feature("Регистрация нового юзера")
    @Story("API тесты")
    @Owner("Kolyshkin A.S.")
    @Severity(SeverityLevel.NORMAL)
    void createUserTest() {

        CreateUserTestModel newUser = new CreateUserTestModel();
        newUser.setName("Anatoliy");
        newUser.setJob("QA");

        CreateUserResponseModel response =
                step("Make response", () -> given(SingleUserSpec.SingleUserCreateRequestSpec)
                        .body(newUser)
                        .when()
                        .post("/users")
                        .then()
                        .spec(SingleUserCreateResponseSpec201)
                        .extract().as(CreateUserResponseModel.class));

        step("Check response", () -> {
            assertEquals(newUser.getName(), response.getName());
            assertEquals(newUser.getJob(), response.getJob());
            assertNotNull(response.getId());
            assertNotNull(response.getCreatedAt());
        });
    }


    @Test()
    @Feature("Обновление информации выбранного юзера")
    @Story("API тесты")
    @Owner("Kolyshkin A.S.")
    @Severity(SeverityLevel.NORMAL)
    void updateUserTest() {

        CreateUserTestModel newUser = new CreateUserTestModel();
        newUser.setName("morpheus");
        newUser.setJob("zion resident");


        CreateUserUpdateModel response =
                step("Make response", () -> given(SingleUserCreateRequestSpec)
                        .body(newUser)
                        .when()
                        .put("/users/3")
                        .then()
                        .spec(SingleUserUpdateResponseSpec200)
                        .extract().as(CreateUserUpdateModel.class));

        step("Check response", () -> {
            assertEquals(newUser.getName(), response.getName());
            assertEquals(newUser.getJob(), response.getJob());
            assertNotNull(response.getUpdatedAt());
        });

    }



    @Test
    @Feature("Удаление выбранного юзера")
    @Story("API тесты")
    @Owner("Kolyshkin A.S.")
    @Severity(SeverityLevel.NORMAL)
    void deleteUserTest() {

        DeleteUserRequestTestModel userId = new DeleteUserRequestTestModel();
        userId.setUserId(2);

        step("Make response", () -> given(SingleUserCreateRequestSpec)
                .when()
                .delete("/users/"+ userId.getUserId().toString())
                .then()
                .spec(SingleUserDeleteResponseSpec204));
    }

}
