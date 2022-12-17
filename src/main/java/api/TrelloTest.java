package api;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;

public class TrelloTest {
    private final static String URL = "https://api.trello.com";

    @Test
    public void checkSuccessfulBoardAdd() {
        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.requestSpecOK200());
        Response response = given()
                .when()
                .post("/1/boards/?name=Backlog&key=f9032154a1cb5a5e458423edec037a08&token=65663c8791c2d24b95238ac128db680a1aeb38a0edf891f681b477397e1617d3")
                .then().log().all()
                .body("name", notNullValue())
                .extract().response();
        JsonPath jsonPath = response.jsonPath();
        String cardsName = jsonPath.get("name");
        Assert.assertEquals("Backlog", cardsName);
    }

    @Test
    public void checkSuccessfulListAdd() {
        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.requestSpecOK200());
        Response response = given()
                .when()
                .post("/1/boards/639db632d1b6da01cee749d1/lists?name=Погладить котика&key=f9032154a1cb5a5e458423edec037a08&token=65663c8791c2d24b95238ac128db680a1aeb38a0edf891f681b477397e1617d3&id=639db632d1b6da01cee749d1")
                .then().log().all()
                .body("name", notNullValue())
                .extract().response();
        JsonPath jsonPath = response.jsonPath();
        String listName = jsonPath.get("name");
        Assert.assertEquals("Погладить котика", listName);
    }

    @Test
    public void checkSuccessfulCardAdd() {
        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.requestSpecOK200());
        Map<String, String> name = new HashMap<>();
        name.put("name", "Ежики пушистые");
        Response response = given()
                .body(name)
                .when()
                .post("/1/cards?idList=639db686d5e86b00e55730a0&key=f9032154a1cb5a5e458423edec037a08&token=65663c8791c2d24b95238ac128db680a1aeb38a0edf891f681b477397e1617d3")
                .then().log().all()
                .body("name", notNullValue())
                .extract().response();
        JsonPath jsonPath = response.jsonPath();
        String cardsName = jsonPath.get("name");
        Assert.assertEquals("Ежики пушистые", cardsName);
    }
}
