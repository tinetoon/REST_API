package com.imgur;

import com.imgur.dto.RootImageUpload;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;

import static com.imgur.API.EndPoints.END_POINT_DELETE_IMAGE;
import static com.imgur.API.EndPoints.END_POINT_UPLOAD_IMAGE;
import static com.imgur.enums.Images.IMAGE_FILE;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

/**
 * Класс для тестирования REST API портала imgur.com
 * Раздел: Image
 * Ответственный за создание класса - А.А. Дюжаков
 * Дата создания: 27.02.2022
 */

public class ImageDeleteTest extends BaseTest {

    @BeforeEach
    void uploadImage() {

        RootImageUpload response = given()
                .multiPart("image", new File(IMAGE_FILE.getValue()))
                .when()
                .post(END_POINT_UPLOAD_IMAGE)
                .then()
                .extract()
                .body()
                .as(RootImageUpload.class);

        // Устанавливаем значения переменой deletehash
        PROP.setProperty("test.image.delete.hash",
                response
                        .getDataImage()
                        .getDeletehash());
    }

    @Test
    void deleteExistentImageTest() {
        given()
                .when()
                .delete(END_POINT_DELETE_IMAGE, PROP.getProperty("test.image.delete.hash"))
                .prettyPeek()
                .then()
                .body("data", CoreMatchers.is(true));
    }
}
