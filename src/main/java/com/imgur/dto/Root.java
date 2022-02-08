package com.imgur.dto;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;

import java.util.List;

/**
 * Клас для парсинга ответа сервера c помощью библиотеки jackson
 * Ответственный за создание класса - А.А. Дюжаков
 * Дата создания: 06.02.2022
 */

@JsonIgnoreProperties(ignoreUnknown = true) // Игнорируем лишние поля из строки JSON
public class Root {

    // Поля класса
    @JsonProperty("id")
    private String testImageId;
    @JsonProperty("deletehash")
    private String testImageDeleteHash;
    @JsonProperty("description")
    private String testImageDescription;
    @JsonProperty("name")
    private String testImageName;
    @JsonProperty("link")
    private String testImageLink;

    // Пустой конструктор (для работы с библиотекой jackson)
    public Root() {
    }

    // Конструктор
    public Root(String testImageId,
                String testImageDeleteHash,
                String testImageDescription,
                String testImageName,
                String testImageLink) {
        this.testImageId = testImageId;
        this.testImageDeleteHash = testImageDeleteHash;
        this.testImageDescription = testImageDescription;
        this.testImageName = testImageName;
        this.testImageLink = testImageLink;
    }

    // Геттеры
    @JsonGetter("id")
    public String getTestImageId() {
        return testImageId;
    }
    @JsonGetter("deletehash")
    public String getTestImageDeleteHash() {
        return testImageDeleteHash;
    }
    @JsonGetter("description")
    public String getTestImageDescription() {
        return testImageDescription;
    }
    @JsonGetter("name")
    public String getTestImageName() {
        return testImageName;
    }
    @JsonGetter("link")
    public String getTestImageLink() {
        return testImageLink;
    }

    // Сеттеры
    @JsonSetter("id")
    public void setTestImageId(String testImageId) {
        this.testImageId = testImageId;
    }
    @JsonSetter("deletehash")
    public void setTestImageDeleteHash(String testImageDeleteHash) {
        this.testImageDeleteHash = testImageDeleteHash;
    }
    @JsonSetter("description")
    public void setTestImageDescription(String testImageDescription) {
        this.testImageDescription = testImageDescription;
    }
    @JsonSetter("name")
    public void setTestImageName(String testImageName) {
        this.testImageName = testImageName;
    }
    @JsonSetter("link")
    public void setTestImageLink(String testImageLink) {
        this.testImageLink = testImageLink;
    }
}
