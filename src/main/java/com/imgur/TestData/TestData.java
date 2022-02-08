package com.imgur.TestData;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.imgur.dto.Root;

/**
 * Базовый класс для тестовых данных
 * Ответственный за создание класса - А.А. Дюжаков
 * Дата создания: 01.02.2022
 */

public class TestData {

    // Тестовые данные для портала imgur.com
    private final String TOKEN = "Bearer 696c2cd91f3214505e4b99a8c7091a3846ca1a92";
    private final String CLIENT_ID = "Client-ID 0e02eec0a06ca91";
//    private final String CLIENT_ID = "0e02eec0a06ca91";
    private final String URL_TEST_IMAGE_YANDEX = "https://disk.yandex.ru/i/Bzqbj-DAxxidGQ";
    private final String NAME_TEST_IMAGE = "Два пингвина";

    // Переменные тестовых изображений
    private String idImage = "7m7fhtZ";

    // Эндпоинты для тестов
    private String endPointImageUpload = "https://api.imgur.com/3/upload";
    private String endPointGetImage = "https://api.imgur.com/3/image/";
    private String endPointDelImage = "https://api.imgur.com/3/image/";

    // Геттеры на поля класса
    public String getTOKEN() {
        return TOKEN;
    }
    public String getCLIENT_ID() {
        return CLIENT_ID;
    }
    public String getURL_TEST_IMAGE_YANDEX() {
        return URL_TEST_IMAGE_YANDEX;
    }
    public String getEndPointImageUpload() {
        return endPointImageUpload;
    }
    public String getEndPointGetImage() {
        return endPointGetImage;
    }
    public String getEndPointDelImage() {
        return endPointDelImage;
    }
    public String getNAME_TEST_IMAGE() {
        return NAME_TEST_IMAGE;
    }

    // Геттеры для переменных
    public String getIdImage() {
        return idImage;
    }

}
