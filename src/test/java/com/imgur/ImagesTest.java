package com.imgur;

import com.imgur.TestData.TestData;
import com.imgur.dto.Root;
import io.restassured.RestAssured;
import io.restassured.mapper.ObjectMapperType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

/**
 * Класс для тестирования REST API портала imgur.com
 * Ответственный за создание класса - А.А. Дюжаков
 * Дата создания: 01.02.2022
 */

public class ImagesTest {

    // Создаём поля класса для сохранения Headers & Properties
    private static Map<String, String> headersAuf = new HashMap<>();
    private static Map<String, String> headersClientId = new HashMap<>();
    private static Properties prop;

    // Создаём экземпляр тестовых данных
    private static TestData testData = new TestData();
    private static String host;
    private static String hostImage;

    private static String testImageHash;
    private static String testImageDeleteHash;
    private static String testImageDescription;
    private static String testImageName;
    private static String testImageLink;

    // Создаём объект файла для загрузки
    private static File testImage = new File("src/test/resources/twoPenguins.jpg");

    @BeforeAll
    static void setHeaders() throws IOException {
        headersAuf.put("Token", testData.getTOKEN());
        headersAuf.put("Authorization", testData.getCLIENT_ID());
//        headersAuf.put("User-Agent", "PostmanRuntime/7.29.0");
//        headersAuf.put("Accept", "*/*");
//        headersAuf.put("Accept-Encoding", "gzip, deflate, br");
//        headersAuf.put("Connection", "keep-alive");
        headersClientId.put("Authorization", testData.getCLIENT_ID());

        // Подключаем логирование в случае ошибки запроса
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();

        // Подключаем properties
        prop = new Properties();
        prop.load(new FileInputStream("src/test/resources/my.properties")); // Загружаем файл с помощью интерфейса FileInputStream

        // Инициализируем переменные значениями properties
        host = prop.getProperty("host");
        hostImage = prop.getProperty("host.image");

        testImageHash = prop.getProperty("test.image.hash");
        testImageDeleteHash = prop.getProperty("test.image.delete.hash");
        testImageDescription = prop.getProperty("test.image.description");
        testImageName = prop.getProperty("test.image.name");
        testImageLink = prop.getProperty("test.image.link");
    }

    @Test
    @DisplayName("Проверка доступности изображения на Yandex.disk")
    void getUrlTestImageYandex() {
        given()
                .when()
                .request("GET", testData.getURL_TEST_IMAGE_YANDEX())
                .then()
                .statusCode(200);
    }

    @Test
    @DisplayName("Загрузка тестового изображения")
    void testImageUpload() {

        Map<String, String> dataRes = new HashMap<>();

        dataRes = given()
//                .log().all() // Логирование запроса
                .log().method()
                .log().uri()
                .headers(headersAuf)
                .multiPart("image", testImage, "multipart/form-data")
                .param("type", "file")
                .param("name", "Two penguins")
                .param("description", "Two penguins - test image to test REST API")
                .when()
                .request("POST", "https://api.imgur.com/3/upload")
//                .prettyPeek() // Логирование ответа
                .then()
                .statusCode(200)
                .extract()
                .response()
                .jsonPath()
//                .getString("data.name")
                .getMap("data")
        ;

        System.out.println("INFO: Test image name: " + dataRes.get("name"));
        System.out.println("INFO: Test ID: " + dataRes.get("id"));

        assertThat(dataRes.get("name"), equalTo("Two penguins"));

        // Устанавливаем значения переменных
//        testData.setTestImageId(dataRes.get("id"));
//        testData.setTestImageDeleteHash(dataRes.get("deletehash"));
//        testData.setTestImageDescription(dataRes.get("description"));
//        testData.setTestImageName(dataRes.get("name"));
//        testData.setTestImageLink(dataRes.get("link"));
//        prop.setProperty("test.image.hash", dataRes.get("id"));
        prop.setProperty("test.image.hash", dataRes.get("id"));
        prop.setProperty("test.image.delete.hash", dataRes.get("deletehash"));
        prop.setProperty("test.image.description", dataRes.get("description"));
        prop.setProperty("test.image.name", dataRes.get("name"));
        prop.setProperty("test.image.link", dataRes.get("link"));

        System.out.println("!!!INFO: testImageHash (предыдущий): " + testImageHash);
        System.out.println("!!!INFO: testImageDelHash (предыдущий): " + testImageDeleteHash);

    }

    @Test
    @DisplayName("Проверка доступности картинки")
    void getImagePenguin() {
        String nameImage = given()
//                .log().all() // Логирование всего запроса
                .log().method()
                .log().uri()
                .headers(headersClientId)
                .when()
                .request("GET", (testData.getEndPointGetImage() + "IbjIRny"))
                .prettyPeek() // Логирование ответа
                .then()
                .statusCode(200)
                .extract()
                .response()
                .jsonPath()
                .getString("data.description");

        System.out.println("INFO: Описание картинки: " + nameImage);

    }

    @Test
    @DisplayName("Удаление тестового изображения")
    void imageDeletionAuthed() {
        given()
                .log().all() // Логирование всего запроса
                .headers(headersAuf)
                .when()
//                .delete(testData.getEndPointDelImage() + testData.getTestImageId())
                .delete(host + hostImage + "IbjIRny")
                .prettyPeek() // Логирование ответа
                .then()
                .statusCode(200);
    }

    /*given()
        .when()
        .request("", testData)
        .then()
        .statusCode(200);*/
}
