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
public class Root {

    // Поля класса
    @JsonProperty("results")
    private List<ListResults> listResults;
    @JsonProperty("number")
    private Integer recipesCount;
    @JsonProperty("totalResults")
    private Integer totalResults;

    // Пустой конструктор (для работы с библиотекой jackson)
    public Root() {
    }

    // Конструктор
    public Root(List<ListResults> listResults, Integer recipesCount, Integer totalResults) {
        this.listResults = listResults;
        this.recipesCount = recipesCount;
        this.totalResults = totalResults;
    }
}
