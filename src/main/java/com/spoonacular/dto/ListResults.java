package com.spoonacular.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Клас для парсинга ответа сервера c помощью библиотеки jackson
 * Ответственный за создание класса - А.А. Дюжаков
 * Дата создания: 10.02.2022
 */

@JsonIgnoreProperties(ignoreUnknown = true) // Игнорируем лишние поля из строки JSON
public class ListResults {

    // Поле класса
    @JsonProperty("")
    private Recipes main;

    // Пустой конструктор (для работы с библиотекой jackson)
    public ListResults() {
    }

    // Конструктор
    public ListResults(Recipes main) {
        this.main = main;
    }
}
