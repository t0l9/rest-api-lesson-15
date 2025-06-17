package specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static helpers.CustomAllureListener.withCustomTemplates;
import static io.restassured.RestAssured.with;
import static io.restassured.http.ContentType.JSON;

public class SingleUserSpec {

    public static RequestSpecification RequestSpec = with()
            .filter(withCustomTemplates())
            .log().all()
            .header("x-api-key", "reqres-free-v1")
            .contentType(JSON);


    public static ResponseSpecification ResponseSpec200 = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .log(LogDetail.ALL)
            .build();


    public static ResponseSpecification SingleUserNegativeResponseSpec404 = new ResponseSpecBuilder()
            .expectStatusCode(404)
            .log(LogDetail.ALL)
            .build();


    public static ResponseSpecification SingleUserCreateResponseSpec201 = new ResponseSpecBuilder()
            .expectStatusCode(201)
            .log(LogDetail.ALL)
            .build();


    public static ResponseSpecification SingleUserDeleteResponseSpec204 = new ResponseSpecBuilder()
            .expectStatusCode(204)
            .log(LogDetail.ALL)
            .build();
}
