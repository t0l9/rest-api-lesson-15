package specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static helpers.CustomAllureListener.withCustomTemplates;
import static io.restassured.RestAssured.with;
import static io.restassured.filter.log.LogDetail.BODY;
import static io.restassured.filter.log.LogDetail.STATUS;
import static io.restassured.http.ContentType.JSON;

public class CreateUserSpec {

    public static RequestSpecification SingleUserCreateRequestSpec = with()
            .filter(withCustomTemplates())
            .log().all()
            .header("x-api-key", "reqres-free-v1")
            .contentType(JSON)
            .baseUri("https://reqres.in/")
            .basePath("/api/users/");

    public static ResponseSpecification SingleUserCreateResponseSpec201 = new ResponseSpecBuilder()
            .expectStatusCode(201)
            .log(STATUS)
            .log(BODY)
            .build();

    public static ResponseSpecification SingleUserUpdateResponseSpec200 = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .log(STATUS)
            .log(BODY)
            .build();

    public static ResponseSpecification SingleUserDeleteResponseSpec204 = new ResponseSpecBuilder()
            .expectStatusCode(204)
            .log(STATUS)
            .log(BODY)
            .build();
}
