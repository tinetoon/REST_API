package com.imgur;

import com.imgur.dto.RootImageUpload;
import io.restassured.RestAssured;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.ResponseSpecification;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import static com.imgur.API.EndPoints.END_POINT_DELETE_IMAGE;
import static com.imgur.API.EndPoints.END_POINT_UPLOAD_IMAGE;
import static com.imgur.enums.Images.*;
import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

/**
 * Класс для тестирования REST API портала imgur.com
 * Раздел: Image
 * Ответственный за создание класса - А.А. Дюжаков
 * Дата создания: 27.02.2022
 */

public class ImagesTest extends BaseTest {

    static ResponseSpecification resSpecOk;

    @BeforeEach
    void positiveResponseSpecAssert() {

        resSpecOk = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectBody("success", CoreMatchers.is(true))
                .build();

        RestAssured.responseSpecification = resSpecOk;
    }

    @Test
    @DisplayName("Загрузка файла тестового изображения")
    void imageUploadFileTest() {

        Map<String, String> dataRes = new HashMap<>();

        dataRes = given()
                .multiPart("image", new File(IMAGE_FILE.getValue()))
                .multiPart("name", IMAGE_FILE_NAME.getValue())
                .when()
                .post(END_POINT_UPLOAD_IMAGE)
                .prettyPeek()
                .then()
                .extract()
                .response()
                .jsonPath()
                .getMap("data");

        // Устанавливаем значения переменой deletehash
        PROP.setProperty("test.image.delete.hash", dataRes.get("deletehash"));
    }

    @Test
    @DisplayName("Загрузка тестового изображения по URL")
    void imageUploadUrlTest() {

        RootImageUpload response = given()
                .multiPart("image", IMAGE_URL.getValue())
                .multiPart("name", IMAGE_URL_NAME.getValue())
                .when()
                .post(END_POINT_UPLOAD_IMAGE)
                .prettyPeek()
                .then()
                .extract()
                .body()
                .as(RootImageUpload.class);

        // Устанавливаем значения переменой deletehash
        PROP.setProperty("test.image.delete.hash",
                response
                .getDataImage()
                .getDeletehash());

        // Проверяем имя загруженного изображения
        assertThat(response.getDataImage().getName(), equalTo(IMAGE_URL_NAME.getValue()));
    }

    @AfterEach
    void tearDown() {
        given()
                .when()
                .delete(END_POINT_DELETE_IMAGE, PROP.getProperty("test.image.delete.hash"));
    }
}
