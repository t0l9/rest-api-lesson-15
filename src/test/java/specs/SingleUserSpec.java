package specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static helpers.CustomAllureListener.withCustomTemplates;
import static io.restassured.RestAssured.with;
import static io.restassured.filter.log.LogDetail.BODY;
import static io.restassured.filter.log.LogDetail.STATUS;
import static io.restassured.http.ContentType.JSON;

public class SingleUserSpec {

    public static RequestSpecification SingleUserRequestSpec = with()
            .filter(withCustomTemplates())
            .log().all()
            .header("x-api-key", "reqres-free-v1")
            .contentType(JSON)
            .baseUri("https://reqres.in/")
            .basePath("/api/users/");

    public static ResponseSpecification SingleUserResponseSpec = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .log(STATUS)
            .log(BODY)
            .build();

    public static ResponseSpecification SingleUserNegativeResponseSpec = new ResponseSpecBuilder()
            .expectStatusCode(404)
            .log(STATUS)
            .log(BODY)
            .build();
}
