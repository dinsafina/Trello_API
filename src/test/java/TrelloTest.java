import api.TrelloService;
import api.models.Board;
import api.models.BoardList;
import api.models.Card;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import property.PropertyVars;
import property.Values;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.requestSpecification;
import static org.hamcrest.Matchers.notNullValue;

public class TrelloTest {
    private final TrelloService trelloService = new TrelloService();
//    @Test
//    public void checkSuccessfulBoardAdd(){
//        final RequestBody body = new RequestBody();
//        body.setName("Завтрак");
//        final Board boardResponse = trelloService.sendPostBoardAdd(body);
//        Assertions.assertEquals("Завтрак", boardResponse.getName());
//    }
static Values values = PropertyVars.getProperty();
    public static RequestSpecification requestSpecification;
    public static RequestSpecBuilder requestSpecBuilder;

    @BeforeAll
    static void beforeClass() {
        requestSpecBuilder = new RequestSpecBuilder();
        requestSpecBuilder.setBaseUri((values.getUrl()));
        requestSpecBuilder.setContentType(ContentType.JSON);
        requestSpecification = requestSpecBuilder.build();
    }
    @Test
    public void checkSuccessfulBoardAdd(){
        //Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.requestSpecOK200());
        Map<String, String> name = new HashMap<>();
        name.put("name", "Завтрак");
        Board boardResponse = given()
                .spec(requestSpecification)
                .body(name)
                .when()
                .post("/1/boards/?key=" + values.getUser_key() + "&token=" + values.getUser_token())
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
                .spec(requestSpecification)
                .body(name)
                .when()
                .post("/1/boards/?key=" + values.getUser_key() + "&token="+ values.getUser_token())
                .then().log().all()
                .body("name", notNullValue())
                .extract().as(Board.class);
        Assertions.assertEquals("Обед", boardResponse.getName());

        Map<String, String> idBoard = new HashMap<>();
        idBoard.put("name", "Суп");
        BoardList listResponse = given()
                .spec(requestSpecification)
                .body(idBoard)
                .when()
                .post("/1/boards/"+ boardResponse.getId() +"/lists?key="+ values.getUser_key() + "&token=" + values.getUser_token())
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
                .spec(requestSpecification)
                .body(board)
                .when()
                .post("/1/boards/?key=" + values.getUser_key() + "&token=" + values.getUser_token())
                .then().log().all()
                .body("name", notNullValue())
                .extract().as(Board.class);
        Assertions.assertEquals("Ужин", boardResponse.getName());

        Map<String, String> list = new HashMap<>();
        list.put("name", "Второе");
        list.put("idBoard", boardResponse.getId());
        BoardList listResponse = given()
                .spec(requestSpecification)
                .body(list)
                .when()
                .post("/1/lists/?key=" + values.getUser_key() + "&token=" + values.getUser_token())
                .then().log().all()
                .body("name", notNullValue())
                .extract().as(BoardList.class);
        Assertions.assertEquals("Второе", listResponse.getName());

        Map<String, String> card = new HashMap<>();
        card.put("name", "Болоньезе");
        card.put("idList", listResponse.getId());
        Card cardResponse = given()
                .spec(requestSpecification)
                .body(card)
                .when()
                .post("/1/cards?key=" + values.getUser_key() + "&token=" + values.getUser_token())
                .then().log().all()
                .body("name", notNullValue())
                .extract().as(Card.class);
        Assertions.assertEquals("Болоньезе", cardResponse.getName());
    }
}
