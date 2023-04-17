import api.Specifications;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
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
    @Test
    public void checkSuccessfulBoardAdd(){
        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.requestSpecOK200());
        Map<String, String> name = new HashMap<>();
        name.put("name", "Завтрак");
        Response response = given()
                .body(name)
                .when()
                .post("/1/boards/?key=" + KEY + "&token=" + TOKEN)
                .then().log().all()
                .body("name", notNullValue())
                .extract().response();
        JsonPath jsonPath = response.jsonPath();
        String boardName = jsonPath.get("name");
        Assertions.assertEquals("Завтрак", boardName);
    }

    @Test
    public void checkSuccessfulListAdd() {
        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.requestSpecOK200());
        Map<String, String> name = new HashMap<>();
        name.put("name", "Обед");
        Response boardResponse = given()
                .body(name)
                .when()
                .post("/1/boards/?key=" + KEY + "&token="+ TOKEN)
                .then().log().all()
                .body("name", notNullValue())
                .extract().response();
        JsonPath boardJsonPath = boardResponse.jsonPath();
        String boardName = boardJsonPath.get("name");
        String boardId = boardResponse.path("id");
        Assertions.assertEquals("Обед", boardName);

        Map<String, String> idBoard = new HashMap<>();
        idBoard.put("name", "Суп");
        Response listResponse = given()
                .body(idBoard)
                .when()
                .post("/1/boards/"+ boardId +"/lists?key="+ KEY+ "&token=" + TOKEN)
                .then().log().all()
                .body("name", notNullValue())
                .extract().response();
        JsonPath listJsonPath = listResponse.jsonPath();
        String listName = listJsonPath.get("name");
        Assertions.assertEquals("Суп", listName);
    }

    @Test
    public void checkSuccessfulCardAdd() {
        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.requestSpecOK200());
        Map<String, String> board = new HashMap<>();
        board.put("name", "Ужин");
        Response boardResponse = given()
                .body(board)
                .when()
                .post("/1/boards/?key=" + KEY + "&token=" + TOKEN)
                .then().log().all()
                .body("name", notNullValue())
                .extract().response();
        JsonPath boardJsonPath = boardResponse.jsonPath();
        String boardName = boardJsonPath.get("name");
        String boardId = boardResponse.path("id");
        Assertions.assertEquals("Ужин", boardName);

        Map<String, String> list = new HashMap<>();
        list.put("name", "Второе");
        list.put("idBoard", boardId);
        Response listResponse = given()
                .body(list)
                .when()
                .post("/1/lists/?key=" + KEY + "&token=" + TOKEN)
                .then().log().all()
                .body("name", notNullValue())
                .extract().response();
        JsonPath listJsonPath = listResponse.jsonPath();
        String listName = listJsonPath.get("name");
        String listId = listJsonPath.get("id");
        Assertions.assertEquals("Второе", listName);

        Map<String, String> card = new HashMap<>();
        card.put("name", "Болоньезе");
        card.put("idList", listId);
        Response cardResponse = given()
                .body(card)
                .when()
                .post("/1/cards?key=" + KEY + "&token=" + TOKEN)
                .then().log().all()
                .body("name", notNullValue())
                .extract().response();
        JsonPath jsonPath = cardResponse.jsonPath();
        String cardNameFromJson = jsonPath.get("name");
        Assertions.assertEquals("Болоньезе", cardNameFromJson);
    }
}
