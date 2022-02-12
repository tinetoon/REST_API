package com.spoonacular.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Клас для парсинга ответа сервера c помощью библиотеки jackson
 * Ответственный за создание класса - А.А. Дюжаков
 * Дата создания: 10.02.2022
 */

@JsonIgnoreProperties(ignoreUnknown = true) // Игнорируем лишние поля из строки JSON
public class Nutrients {

    // Поля класса
    @JsonProperty("name")
    private String nutrientsName;
    @JsonProperty("amount")
    private Double nutrientsAmount;
    @JsonProperty("unit")
    private String unit;
}
