package com.imgur;

import com.imgur.BaseTest;
import io.restassured.response.Response;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Класс для тестирования REST API портала imgur.com
 * Ответственный за создание класса - А.А. Дюжаков
 * Дата создания: 01.02.2022
 */

public class AccountTests extends BaseTest {

    @Test
    @DisplayName("Запрос стандартной информации о пользователе")
    void accountBaseTest() {

        given()
                .header("Authorization", token)
                .log()
                .method()
                .log()
                .uri()
                .when()
                .get("account/{username}", username)
                .prettyPeek()
                .then()
                .statusCode(200)
                .body("success", CoreMatchers.is(true))
                .body("data.url", equalTo(username));
    }

    @Test
    void accountSettingsTest() {
        Response response = given()
                .header("Authorization", token)
                .when()
                .get("https://api.imgur.com/3/account/me/settings")
                .prettyPeek()
                .then()
                .statusCode(200)
                .body("success", CoreMatchers.is(true))
                .body("data.account_url", equalTo("tinetoon"))
                .extract()
                .response();

        assertThat(response.jsonPath().get("data.email"), equalTo("kkpj6cmnsm@privaterelay.appleid.com"));
    }
}
