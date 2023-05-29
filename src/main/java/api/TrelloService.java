package api;

import api.models.*;
import property.PropertyVars;
import property.Values;

import static api.specifications.RestAssuredSpecification.REQ_SPEC;
import static io.restassured.RestAssured.given;

public class TrelloService {
    Values values = PropertyVars.getProperty();

    public BoardResponse sendPostBoardAdd(BoardRequest body) {
        return given()
                .spec(REQ_SPEC)
                .body(body)
                .when()
                .post("/1/boards/?key=" + values.getUser_key() + "&token=" + Values.getUser_token())
                .then().log().all()
                .extract().as(BoardResponse.class);
    }

    public BoardListResponse sendPostBoardListAdd(BoardListRequest body){
        return given()
                .spec(REQ_SPEC)
                .body(body)
                .when()
                .post("/1/lists/?key=" + Values.getUser_key() + "&token=" + Values.getUser_token())
                .then().log().all()
                .extract().as(BoardListResponse.class);
    }
    public CardResponse sendPostCardAdd(CardRequest body){
        return given()
                .spec(REQ_SPEC)
                .body(body)
                .when()
                .post("/1/cards?key=" + Values.getUser_key() + "&token=" + Values.getUser_token())
                .then().log().all()
                .extract().as(CardResponse.class);
    }
}
