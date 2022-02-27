package com.imgur;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Базовый абстрактный класс для тестирования REST API портала imgur.com
 * Ответственный за создание класса - А.А. Дюжаков
 * Дата создания: 27.02.2022
 */

public abstract class BaseTest {

    // Поля класса
    protected static Properties prop;
    protected static String host;
    protected static String username;
    protected static String token;

    @BeforeAll
    static void setUp() throws IOException {

        prop = new Properties();
        prop.load(new FileInputStream("src/test/resources/my.properties"));
        host = prop.getProperty("host");
        username = prop.getProperty("username");
        token = prop.getProperty("auth.token");

        // !!! Разобраться с кодом
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        RestAssured.baseURI = host;
    }
}
