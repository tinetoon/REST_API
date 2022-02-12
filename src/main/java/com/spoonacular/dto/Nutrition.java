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
public class Nutrition {

    // Поле класса
    @JsonProperty("nutrients")
    private List<Nutrients> nutrients;

    // Пустой конструктор (для работы с библиотекой jackson)
    public Nutrition() {
    }

    // Конструктор
    public Nutrition(List<Nutrients> nutrients) {
        this.nutrients = nutrients;
    }
}
