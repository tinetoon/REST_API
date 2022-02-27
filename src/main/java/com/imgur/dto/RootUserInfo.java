package com.imgur.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * Клас для парсинга ответа сервера c помощью библиотеки jackson
 * Ответственный за создание класса - А.А. Дюжаков
 * Дата создания: 27.02.2022
 */

@Data
@JsonIgnoreProperties(ignoreUnknown = true) // Игнорируем лишние поля из строки JSON
public class RootUserInfo {

    @JsonProperty("data")
    private DataUser dataUser;
    @JsonProperty("success")
    private Boolean success;
}
