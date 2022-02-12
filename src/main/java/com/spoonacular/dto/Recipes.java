package com.spoonacular.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Клас для парсинга ответа сервера c помощью библиотеки jackson
 * Ответственный за создание класса - А.А. Дюжаков
 * Дата создания: 10.02.2022
 */

@JsonIgnoreProperties(ignoreUnknown = true) // Игнорируем лишние поля из строки JSON
public class Recipes {

    // Поля класса
    @JsonProperty("id")
    private String recipesId;
    @JsonProperty("title")
    private String recipesName;
    @JsonProperty("image")
    private String recipesUrlImage;
    @JsonProperty("nutrition")
    private Nutrition nutrition;

    // Пустой конструктор (для работы с библиотекой jackson)
    public Recipes() {
    }

    //Конструктор
    public Recipes(String recipesId, String recipesName, String recipesUrlImage, Nutrition nutrition) {
        this.recipesId = recipesId;
        this.recipesName = recipesName;
        this.recipesUrlImage = recipesUrlImage;
        this.nutrition = nutrition;
    }
}
