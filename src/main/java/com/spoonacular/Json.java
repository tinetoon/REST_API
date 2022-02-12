package com.spoonacular;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * Клас для парсинга ответа сервера c помощью библиотеки jackson
 * Ответственный за создание класса - А.А. Дюжаков
 * Дата создания: 10.02.2022
 */

public class Json {

    // Создаём объект с помощью библиотеки jackson для доступа к данным Json строки по ключу
    private static ObjectMapper objectMapper = getDefaultObjectMapper();

    private static ObjectMapper getDefaultObjectMapper() {
        ObjectMapper defaultObjectMapper = new ObjectMapper();
        return defaultObjectMapper;
    }

    public static JsonNode parse(String src) throws IOException {
        return objectMapper.readTree(src);
    }
}
