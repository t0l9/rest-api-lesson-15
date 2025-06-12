package tests;

import helpers.CustomAllureListener;
import io.qameta.allure.restassured.AllureRestAssured;
import models.lombok.LoginBodyLombokModel;
import models.lombok.LoginResponseLombokModel;
import org.junit.jupiter.api.Test;

import static helpers.CustomAllureListener.withCustomTemplates;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static specs.LoginSpec.loginResponseSpec;
import static specs.LoginSpec.registerRequestSpec;

public class LoginTests {

    @Test
    void successfulLoginTest() {

        LoginBodyLombokModel authData = new LoginBodyLombokModel();
        authData.setPassword("cityslicka");
        authData.setEmail("eve.holt@reqres.in");

        LoginResponseLombokModel response =
                step("Make response", () -> given(registerRequestSpec)

                .body(authData)
                .when()
                .post()

                .then()
                        .spec(loginResponseSpec)
                .extract().as(LoginResponseLombokModel.class));


        step("Check response", ()-> {
            assertEquals("QpwL5tke4Pnpja7X4", response.getToken());
        });

    }
}
