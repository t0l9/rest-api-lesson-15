package tests;

import models.lombok.LoginBodyLombokModel;
import models.lombok.LoginResponseLombokModel;
import org.junit.jupiter.api.Test;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static specs.SingleUserSpec.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoginTests extends TestBase {

    @Test
    void successfulLoginTest() {

        LoginBodyLombokModel authData = new LoginBodyLombokModel();
        authData.setPassword("cityslicka");
        authData.setEmail("eve.holt@reqres.in");

        LoginResponseLombokModel response =
                step("Make response", () -> given(RequestSpec)

                .body(authData)
                .when()
                .post("/login")

                .then()
                        .spec(ResponseSpec200)
                .extract().as(LoginResponseLombokModel.class));


        step("Check response", ()-> {
            assertEquals("QpwL5tke4Pnpja7X4", response.getToken());
        });

    }
}
