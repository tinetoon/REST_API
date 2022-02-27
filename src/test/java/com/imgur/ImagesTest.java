package com.imgur;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

/**
 * Класс для тестирования REST API портала imgur.com
 * Раздел: Image
 * Ответственный за создание класса - А.А. Дюжаков
 * Дата создания: 01.02.2022
 */

public class ImagesTest extends BaseTest {

    private static String imageDeleteHash;

    @Test
    @DisplayName("Загрузка тестового изображения")
    void imageUploadFileTest() {

        Map<String, String> dataRes = new HashMap<>();

        dataRes = given()
                .body(new File("src/test/resources/twoPenguins.jpg"))
                .when()
                .post("/image")
                .prettyPeek()
                .then()
                .extract()
                .response()
                .jsonPath()
                .getMap("data");

        // Устанавливаем значения переменой deletehash
        prop.setProperty("test.image.delete.hash", dataRes.get("deletehash"));
    }

    @AfterEach
    void tearDown() {
        given()
                .when()
                .delete("image/{imageHash}", prop.getProperty("test.image.delete.hash"));
    }
}
