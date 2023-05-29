import api.TrelloService;
import api.models.*;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TrelloTest {
    private final TrelloService trelloService = new TrelloService();

    @Test
    public void checkSuccessfulBoardAdd() {
        final BoardRequest boardRequest = new BoardRequest();
        String generatedString = RandomStringUtils.randomAlphabetic(10);
        boardRequest.setName(generatedString);
        final BoardResponse boardResponse = trelloService.sendPostBoardAdd(boardRequest);
        Assertions.assertEquals(generatedString, boardResponse.getName());
        System.out.println("generatedString is:" +generatedString);
    }

    @Test
    public void checkSuccessfulListAdd() {
        final BoardRequest boardRequest = new BoardRequest();
        String generatedStringBoard = RandomStringUtils.randomAlphabetic(10);
        boardRequest.setName(generatedStringBoard);
        final BoardResponse boardResponse = trelloService.sendPostBoardAdd(boardRequest);
        Assertions.assertEquals(generatedStringBoard, boardResponse.getName());

        final BoardListRequest boardListRequest = new BoardListRequest();
        String generatedStringList = RandomStringUtils.randomAlphabetic(10);
        boardListRequest.setName(generatedStringList);
        final BoardListResponse boardListResponse = trelloService.sendPostBoardListAdd(boardListRequest);
        Assertions.assertEquals(generatedStringList, boardListResponse.getName());
    }

    @Test
    public void checkSuccessfulCardAdd() {
        final BoardRequest boardRequest = new BoardRequest();
        String generatedStringBoard = RandomStringUtils.randomAlphabetic(10);
        boardRequest.setName(generatedStringBoard);
        final BoardResponse boardResponse = trelloService.sendPostBoardAdd(boardRequest);
        Assertions.assertEquals(generatedStringBoard, boardResponse.getName());

        final BoardListRequest boardListRequest = new BoardListRequest();
        String generatedStringList = RandomStringUtils.randomAlphabetic(10);
        boardListRequest.setName(generatedStringList);
        final BoardListResponse boardListResponse = trelloService.sendPostBoardListAdd(boardListRequest);
        Assertions.assertEquals(generatedStringList, boardListResponse.getName());

        final CardRequest cardRequest = new CardRequest();
        String generatedStringCard = RandomStringUtils.randomAlphabetic(10);
        cardRequest.setName(generatedStringCard);
        final CardResponse cardResponse = trelloService.sendPostCardAdd(cardRequest);
        Assertions.assertEquals(generatedStringCard, cardResponse.getName());
    }

//    @Test
//    public void checkSuccessfulCardAdd() {
//        Map<String, String> board = new HashMap<>();
//        board.put("name", "Ужин");
//        BoardResponse boardResponse = given()
//                .spec(requestSpecification)
//                .body(board)
//                .when()
//                .post("/1/boards/?key=" + Values.getUser_key() + "&token=" + Values.getUser_token())
//                .then().log().all()
//                .body("name", notNullValue())
//                .extract().as(BoardResponse.class);
//        Assertions.assertEquals("Ужин", boardResponse.getName());
//
//        Map<String, String> list = new HashMap<>();
//        list.put("name", "Второе");
//        list.put("idBoard", boardResponse.getId());
//        BoardListResponse listResponse = given()
//                .spec(requestSpecification)
//                .body(list)
//                .when()
//                .post("/1/lists/?key=" + Values.getUser_key() + "&token=" + Values.getUser_token())
//                .then().log().all()
//                .body("name", notNullValue())
//                .extract().as(BoardListResponse.class);
//        Assertions.assertEquals("Второе", listResponse.getName());
//
//        Map<String, String> card = new HashMap<>();
//        card.put("name", "Болоньезе");
//        card.put("idList", listResponse.getId());
//        CardResponse cardResponse = given()
//                .spec(requestSpecification)
//                .body(card)
//                .when()
//                .post("/1/cards?key=" + Values.getUser_key() + "&token=" + Values.getUser_token())
//                .then().log().all()
//                .body("name", notNullValue())
//                .extract().as(CardResponse.class);
//        Assertions.assertEquals("Болоньезе", cardResponse.getName());
//    }
}
