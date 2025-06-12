package tests;

import models.LoginBodyModel;
import models.LoginResponseModel;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoginTests {

    @Test
    void successfulLoginTest() {

        LoginBodyModel authData = new LoginBodyModel();
        authData.setPassword("cityslicka");
        authData.setEmail("eve.holt@reqres.in");

        LoginResponseModel response = given()
                .header("x-api-key", "reqres-free-v1")
                .body(authData)
                .contentType(JSON)
                .log().uri()

                .when()
                .post("https://reqres.in/api/login")

                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .extract().as(LoginResponseModel.class);

        assertEquals("QpwL5tke4Pnpja7X4", response.getToken());
    }
}
