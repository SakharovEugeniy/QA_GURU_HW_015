package api.REQRES;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;


public class SingleUserTest extends BaseTest{

    @Test
    @DisplayName("Проверка пользователя с id = 2")
    void statusCode200Test() {

        given()
                .contentType(JSON)
                .header("x-api-key", "reqres-free-v1")
                .log().uri()
                .when()
                .get("/users/2")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("data.id", equalTo(2))
                .body("data.first_name", equalTo("Janet"))
                .body("data.last_name", equalTo("Weaver"));
    }

    @Test
    @DisplayName("Проверка отсутствия пользователя с id = 2222")
    void dataIDTest() {

        given()
                .contentType(JSON)
                .header("x-api-key", "reqres-free-v1")
                .log().uri()
                .when()
                .get("/users/222")
                .then()
                .log().status()
                .statusCode(404);
    }

    @Test
    @DisplayName("Проверка соответствия номера в суфиксе в имени поля avatar со значением поля id")
    void dataAvatarSuffixEqualIdTest() {

        given()
                .contentType(JSON)
                .header("x-api-key", "reqres-free-v1")
                .log().uri()
                .when()
                .get("/users/2")
                .then()
                .log().status()
                .log().body()
                .body("data.id", equalTo(2))
                .body("data.avatar", containsString("2"));
    }
}
