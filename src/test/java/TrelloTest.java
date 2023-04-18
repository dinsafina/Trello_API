import api.Board;
import api.BoardList;
import api.Card;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;

public class TrelloTest {
    private final static String URL = "https://api.trello.com";
    private final static String KEY = "f9032154a1cb5a5e458423edec037a08";
    private final static String TOKEN = "65663c8791c2d24b95238ac128db680a1aeb38a0edf891f681b477397e1617d3";

    private static final RequestSpecification REQ_SPEC =
            new RequestSpecBuilder()
                    .setBaseUri(URL)
                    .setContentType(ContentType.JSON)
                    .build();
    @Test
    public void checkSuccessfulBoardAdd(){
        Map<String, String> name = new HashMap<>();
        name.put("name", "Завтрак");
        Board boardResponse = given()
                .spec(REQ_SPEC)
                .body(name)
                .when()
                .post("/1/boards/?key=" + KEY + "&token=" + TOKEN)
                .then().log().all()
                .body("name", notNullValue())
                .extract().as(Board.class);
        Assertions.assertEquals("Завтрак", boardResponse.getName());
    }

    @Test
    public void checkSuccessfulListAdd() {
        Map<String, String> name = new HashMap<>();
        name.put("name", "Обед");
        Board boardResponse = given()
                .spec(REQ_SPEC)
                .body(name)
                .when()
                .post("/1/boards/?key=" + KEY + "&token="+ TOKEN)
                .then().log().all()
                .body("name", notNullValue())
                .extract().as(Board.class);
        Assertions.assertEquals("Обед", boardResponse.getName());

        Map<String, String> idBoard = new HashMap<>();
        idBoard.put("name", "Суп");
        BoardList listResponse = given()
                .spec(REQ_SPEC)
                .body(idBoard)
                .when()
                .post("/1/boards/"+ boardResponse.getId() +"/lists?key="+ KEY+ "&token=" + TOKEN)
                .then().log().all()
                .body("name", notNullValue())
                .extract().as(BoardList.class);
        Assertions.assertEquals("Суп", listResponse.getName());
    }

    @Test
    public void checkSuccessfulCardAdd() {
        Map<String, String> board = new HashMap<>();
        board.put("name", "Ужин");
        Board boardResponse = given()
                .spec(REQ_SPEC)
                .body(board)
                .when()
                .post("/1/boards/?key=" + KEY + "&token=" + TOKEN)
                .then().log().all()
                .body("name", notNullValue())
                .extract().as(Board.class);
        Assertions.assertEquals("Ужин", boardResponse.getName());

        Map<String, String> list = new HashMap<>();
        list.put("name", "Второе");
        list.put("idBoard", boardResponse.getId());
        BoardList listResponse = given()
                .spec(REQ_SPEC)
                .body(list)
                .when()
                .post("/1/lists/?key=" + KEY + "&token=" + TOKEN)
                .then().log().all()
                .body("name", notNullValue())
                .extract().as(BoardList.class);
        Assertions.assertEquals("Второе", listResponse.getName());

        Map<String, String> card = new HashMap<>();
        card.put("name", "Болоньезе");
        card.put("idList", listResponse.getId());
        Card cardResponse = given()
                .spec(REQ_SPEC)
                .body(card)
                .when()
                .post("/1/cards?key=" + KEY + "&token=" + TOKEN)
                .then().log().all()
                .body("name", notNullValue())
                .extract().as(Card.class);
        Assertions.assertEquals("Болоньезе", cardResponse.getName());
    }
}
