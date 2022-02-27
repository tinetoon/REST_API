package com.imgur.enums;

import lombok.Getter;

public enum Images {

    IMAGE_FILE("src/test/resources/twoPenguins.jpg"),
    IMAGE_FILE_NAME("Two penguins"),
    IMAGE_URL("https://images11.domashnyochag.ru/upload/img_cache/20a/20a1dbc01926f789c01c371113377c27_ce_2370x1580x0x0_cropped_666x444.jpg"),
    IMAGE_URL_NAME("Raccoon");

    @Getter
    private String value;

    Images(String value) {
        this.value = value;
    }
}
