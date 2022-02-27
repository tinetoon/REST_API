package com.imgur;

import io.restassured.RestAssured;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.ResponseSpecification;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.lessThan;

/**
 * Класс для тестирования REST API портала imgur.com
 * Раздел: Account
 * Ответственный за создание класса - А.А. Дюжаков
 * Дата создания: 01.02.2022
 */

public class AccountTests extends BaseTest {

    static ResponseSpecification resSpec;

    @BeforeEach
    void positiveResponseSpecAssert() {

        resSpec = new ResponseSpecBuilder()
                .expectResponseTime(lessThan(3000l))
                .expectStatusCode(200)
                .expectBody("success", CoreMatchers.is(true))
                .build();

        RestAssured.responseSpecification = resSpec;
    }

    @Test
    @DisplayName("Запрос стандартной информации о пользователе")
    void accountBaseTest() {

        ResponseSpecification accountResSpec = resSpec
                .body("data.url", equalTo(username));

        given(reqSpec, accountResSpec)
                .get("account/{username}", username)
                .prettyPeek();
    }

    @Test
    @DisplayName("Запрос настроек учетной записи")
    void accountSettingsTest() {

        ResponseSpecification accountResSpec = resSpec
                .body("data.account_url", equalTo(username))
                .body("data.email", equalTo("kkpj6cmnsm@privaterelay.appleid.com"));

        given(reqSpec, accountResSpec)
                .get("https://api.imgur.com/3/account/me/settings")
                .prettyPeek()
                .then();
    }
}
