package api.specifications;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import property.Values;

public class RestAssuredSpecification {
    public static final RequestSpecification REQ_SPEC =
            new RequestSpecBuilder()
                    .setBaseUri(Values.getUrl())
                    .setContentType(ContentType.JSON)
                    .build();


    public static final ResponseSpecification responseSpecOK200 =
            new ResponseSpecBuilder()
                    .expectStatusCode(200)
                    .build();


    public static final ResponseSpecification responseSpecError400 =
            new ResponseSpecBuilder()
                    .expectStatusCode(400)
                    .build();
}

