package api.REQRES;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.equalTo;

public class ChangesWithUserTest extends BaseTest {

    @Test
    @DisplayName("Проверка создания пользователя")
    void createUserTest() {
        given()
                .body("{\"name\": \"morpheus\", \"job\": \"leader\"}")
                .contentType(JSON)
                .header("x-api-key", "reqres-free-v1")
                .log().uri()
                .when()
                .post("/users")
                .then()
                .log().status()
                .log().body()
                .statusCode(201)
                .body("name", equalTo("morpheus"))
                .body("job", equalTo("leader"));
    }

    @Test
    @DisplayName("Проверка возможности создания пользователя с пустыми полями")
    void createEmptyUserTest() {
        given()
                .body("{\"name\": \"\", \"job\": \"\"}")
                .contentType(JSON)
                .header("x-api-key", "reqres-free-v1")
                .log().uri()
                .when()
                .post("/users")
                .then()
                .log().status()
                .log().body()
                .statusCode(201)
                .body("name", equalTo(""))
                .body("job", equalTo(""));
    }

    @Test
    @DisplayName("Проверка изменения поля job у существующего пользователя")
    void updateUserTest() {
        given()
                .body("{\"name\": \"morpheus\", \"job\": \"zion resident\"}")
                .contentType(JSON)
                .header("x-api-key", "reqres-free-v1")
                .log().uri()
                .when()
                .put("/users/2")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("name", equalTo("morpheus"))
                .body("job", equalTo("zion resident"));
    }

    @Test
    @DisplayName("Проверка удаления существующего пользователя")
    void deleteUserTest() {
        given()
                .contentType(JSON)
                .header("x-api-key", "reqres-free-v1")
                .log().uri()
                .when()
                .delete("/users/2")
                .then()
                .log().status()
                .log().body()
                .statusCode(204);
    }
}
