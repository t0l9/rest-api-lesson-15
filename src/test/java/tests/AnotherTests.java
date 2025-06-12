package tests;

import io.qameta.allure.*;
import models.lombok.CreateUserTestModel;
import models.lombok.DeleteUserTestModel;
import models.lombok.SingleUserTestModel;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Configuration.baseUrl;
import static helpers.CustomAllureListener.withCustomTemplates;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.is;

public class AnotherTests extends TestBase{


    @Test()
    @Feature("Проверка выбранного юзера")
    @Story("API тесты")
    @Owner("Kolyshkin A.S.")
    @Severity(SeverityLevel.NORMAL)
    void singleUserTest() {

        SingleUserTestModel data = new SingleUserTestModel();
        data.setUserEmail("janet.weaver@reqres.in");
        data.setUserId(2);
        data.setSupportText("Tired of writing endless social media content? Let Content Caddy generate it for you.");
        data.setSupportUrl("https://contentcaddy.io?utm_source=reqres&utm_medium=json&utm_campaign=referral");


        given()
                .header("x-api-key", "reqres-free-v1")
                .filter(withCustomTemplates())
                .log().uri()
                .log().body()
                .log().headers()
                .when()
                .get(data.getUserId().toString())
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("data.id", is(data.getUserId()))
                .body("data.email", is(data.getUserEmail()))
                .body("support.url", is(data.getSupportUrl()))
                .body("support.text", is(data.getSupportText()));
    }



    @Test()
    @Feature("Проверка на отсутсвие выбранного юзера")
    @Story("API тесты")
    @Owner("Kolyshkin A.S.")
    @Severity(SeverityLevel.NORMAL)
    void singleUserNotFoundTest() {

        SingleUserTestModel data = new SingleUserTestModel();
        data.setUserId(244);


        given()
                .header("x-api-key", "reqres-free-v1")
                .filter(withCustomTemplates())
                .log().uri()
                .log().body()
                .log().headers()
                .when()
                .get(baseUrl + "users/" + data.getUserId())
                .then()
                .log().status()
                .log().body()
                .statusCode(404);
    }



    @Test()
    @Feature("Регистрация нового юзера")
    @Story("API тесты")
    @Owner("Kolyshkin A.S.")
    @Severity(SeverityLevel.NORMAL)
    void createUserTest() {

        CreateUserTestModel newUser = new CreateUserTestModel();
        newUser.setName("morpheus");
        newUser.setJob("leader");

        given()
                .header("x-api-key", "reqres-free-v1")
                .filter(withCustomTemplates())
                .log().uri()
                .log().body()
                .log().headers()
                .body(newUser)
                .contentType(JSON)
                .when()
                .post(baseUrl + "users")
                .then()
                .log().status()
                .log().body()
                .statusCode(201)
                .body("name", is(newUser.getName()))
                .body("job", is(newUser.getJob()));

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


        given()
                .header("x-api-key", "reqres-free-v1")
                .filter(withCustomTemplates())
                .log().uri()
                .log().body()
                .log().headers()
                .body(newUser)
                .contentType(JSON)
                .when()
                .put(baseUrl + "users/3")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("name", is(newUser.getName()))
                .body("job", is(newUser.getJob()));

    }



    @Test()
    @Feature("Удаление выбранного юзера")
    @Story("API тесты")
    @Owner("Kolyshkin A.S.")
    @Severity(SeverityLevel.NORMAL)
    void deleteUserTest() {

        DeleteUserTestModel userId = new DeleteUserTestModel();
        userId.setUserId(2);


        given()
                .header("x-api-key", "reqres-free-v1")
                .filter(withCustomTemplates())
                .log().uri()
                .log().body()
                .log().headers()
                .when()
                .delete(baseUrl + "users/" + userId.getUserId())
                .then()
                .log().status()
                .log().body()
                .statusCode(204);
    }

}
