import io.qameta.allure.*;
import org.junit.jupiter.api.Test;

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
        Integer userId = 2;
        String userEmail = "janet.weaver@reqres.in";
        String supportUrl = "https://contentcaddy.io?utm_source=reqres&utm_medium=json&utm_campaign=referral";
        String supportText = "Tired of writing endless social media content? Let Content Caddy generate it for you.";

        given()
                .log().uri()
                .when()
                .get("users/" + userId)
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("data.id", is(userId))
                .body("data.email", is(userEmail))
                .body("support.url", is(supportUrl))
                .body("support.text", is(supportText));
    }

    @Test()
    @Feature("Проверка на отсутсвие выбранного юзера")
    @Story("API тесты")
    @Owner("Kolyshkin A.S.")
    @Severity(SeverityLevel.NORMAL)
    void singleUserNotFoundTest() {
        Integer userId = 234;

        given()
                .header("x-api-key", "reqres-free-v1")
                .log().uri()
                .when()
                .get("users/" + userId)
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

        String newUser = "{\n" +
                "    \"name\": \"morpheus\",\n" +
                "    \"job\": \"leader\"\n" +
                "}";

        given()
                .header("x-api-key", "reqres-free-v1")
                .body(newUser)
                .contentType(JSON)
                .log().uri()
                .when()
                .post("users")
                .then()
                .log().status()
                .log().body()
                .statusCode(201)
                .body("name", is("morpheus"))
                .body("job", is("leader"));

    }

    @Test()
    @Feature("Обновление информации выбранного юзера")
    @Story("API тесты")
    @Owner("Kolyshkin A.S.")
    @Severity(SeverityLevel.NORMAL)
    void updateUserTest() {

        String newUser = "{\n" +
                "    \"name\": \"morpheus\",\n" +
                "    \"job\": \"zion resident\"\n" +
                "}";

        given()
                .header("x-api-key", "reqres-free-v1")
                .body(newUser)
                .contentType(JSON)
                .log().uri()
                .when()
                .put("users/3")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("name", is("morpheus"))
                .body("job", is("zion resident"));

    }



    @Test()
    @Feature("Удаление выбранного юзера")
    @Story("API тесты")
    @Owner("Kolyshkin A.S.")
    @Severity(SeverityLevel.NORMAL)
    void deleteUserTest() {
        Integer userId = 2;

        given()
                .header("x-api-key", "reqres-free-v1")
                .log().uri()
                .when()
                .delete("users/" + userId)
                .then()
                .log().status()
                .log().body()
                .statusCode(204);
    }

}
