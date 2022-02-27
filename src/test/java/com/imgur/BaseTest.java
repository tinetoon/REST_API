package com.imgur;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeAll;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static org.hamcrest.Matchers.lessThan;

/**
 * Базовый абстрактный класс для тестирования REST API портала imgur.com
 * Ответственный за создание класса - А.А. Дюжаков
 * Дата создания: 27.02.2022
 */

public abstract class BaseTest {

    // Поля класса
    protected static final Properties PROP = getProp();

    // Базовая спецификация запроса
    protected static RequestSpecification reqBaseSpec;

    // Базовая спецификация ответа
    protected static ResponseSpecification positiveResBaseSpec;

    @BeforeAll
    static void setUp() {

        reqBaseSpec = new RequestSpecBuilder()
                .setBaseUri(PROP.getProperty("host"))
                .addHeader("Authorization", PROP.getProperty("auth.token"))
                .log(LogDetail.URI)
                .log(LogDetail.METHOD)
                .build();

        positiveResBaseSpec = new ResponseSpecBuilder()
                .expectResponseTime(lessThan(3000l))
                .expectStatusCode(200)
                .expectBody("success", CoreMatchers.is(true))
                .build();

        // !!! Разобраться с кодом
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        RestAssured.requestSpecification = reqBaseSpec;
        RestAssured.responseSpecification = positiveResBaseSpec;
//        RestAssured.baseURI = host;
    }

    public static Properties getProp() {
        Properties prop = new Properties();

        try {
            prop.load(new FileInputStream("src/test/resources/my.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return prop;
    }
}
