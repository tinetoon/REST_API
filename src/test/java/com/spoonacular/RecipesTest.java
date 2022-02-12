package com.spoonacular;

import com.fasterxml.jackson.databind.JsonNode;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Класс для тестирования REST API портала spoonacular.com
 * Ответственный за создание класса - А.А. Дюжаков
 * Дата создания: 09.02.2022
 */

public class RecipesTest {

    // Создаём поля класса для сохранения тестовой информации
    private static String apiKey;
    private static Properties prop;

    // Создаём экземпляр тестовых данных
    private static String host;

    @BeforeAll
    static void setHeaders() throws IOException {

        // Подключаем логирование в случае ошибки запроса
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();

        // Подключаем properties
        prop = new Properties();
        prop.load(new FileInputStream("src/test/resources/spoonacular.properties")); // Загружаем файл с помощью интерфейса FileInputStream

        // Инициализируем переменные значениями properties
        apiKey = prop.getProperty("api.key");
        host = prop.getProperty("host") + prop.getProperty("recipes");

//        given()
//                .log().all() // Логирование запроса
//                .when()

    }

    @Test
    @DisplayName("Авторизация")
    void authenticationTest() {
        given()
                .log().all() // Логирование запроса
                .param("apiKey", apiKey)
                .param("includeNutrition", true)
                .when()
                .get("https://api.spoonacular.com/recipes/716429/information")
                .prettyPeek() // Логирование ответа
                .then()
                .statusCode(200);
    }

    @Test
    @DisplayName("Поиск рецепта")
    void searchRecipesTest() throws IOException {
        String response = given()
//                .log().all() // Логирование запроса
                .log().method()
                .log().uri()
                .param("apiKey", apiKey)
//                .param("includeNutrition", true)
                .param("query", "pasta")
                .param("maxFat", 25)
                .param("number", 1)
//                .contentType("application/json")
//                .headers("Response Headers:", "Content-Type: application/json")
                .when()
                .get(host + "complexSearch")
//                .get("")
//                .prettyPeek() // Логирование ответа
                .then()
                .statusCode(200)
                .extract()
                .response()
                        .body()
                                .asString();

//        System.out.println("!!!INFO: Количество жира: " + response);

        JsonNode node = Json.parse(response);
        System.out.println("!!!INFO: Количество результатов поиска: " + node.get("totalResults"));
//        System.out.println("!!!INFO: Results: " + node.get("results"));
        System.out.println("!!!INFO: ID: " + node
                                                .get("results")
                                                .get(0)
                                                .get("id"));
        System.out.println("!!!INFO: Количество жира : " + node
                .get("results")
                .get(0)
                .get("nutrition")
                .get("nutrients")
                .get(0)
                .get("amount"));
//        System.out.println("!!!INFO: Количество жира: " + node.get("results.[0].nutrition.nutrients.amount").toString());

        Double amount = node
                .get("results")
                .get(0)
                .get("nutrition")
                .get("nutrients")
                .get(0)
                .get("amount")
                .asDouble();

        assertTrue(amount <= 25);
    }
}
