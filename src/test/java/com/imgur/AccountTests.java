package com.imgur;

import com.imgur.dto.RootUserInfo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Properties;

import static com.imgur.API.EndPoints.END_POINT_ACCOUNT_BASE;
import static com.imgur.API.EndPoints.END_POINT_ACCOUNT_SETTINGS;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Класс для тестирования REST API портала imgur.com
 * Раздел: Account
 * Ответственный за создание класса - А.А. Дюжаков
 * Дата создания: 27.02.2022
 */

public class AccountTests extends BaseTest {

    private static Properties prop;

    @BeforeEach
    void positiveResponseSpecAssert() {

        prop = getProp();
    }

    @Test
    @DisplayName("Запрос стандартной информации о пользователе")
    void accountBaseTest() {

        RootUserInfo response = given(reqBaseSpec, positiveResBaseSpec)
                .get(END_POINT_ACCOUNT_BASE, prop.getProperty("username"))
                .prettyPeek()
                .then()
                .extract()
                .body()
                .as(RootUserInfo.class);

        // Проверяем имя загруженного изображения
        assertThat(response.getDataUser().getUrl(), equalTo("tinetoon"));
    }

    @Test
    @DisplayName("Запрос настроек учетной записи")
    void accountSettingsTest() {

        RootUserInfo response = given(reqBaseSpec, positiveResBaseSpec)
                .get(END_POINT_ACCOUNT_SETTINGS)
                .prettyPeek()
                .then()
                .extract()
                .body()
                .as(RootUserInfo.class);

        // Проверяем имя загруженного изображения
        assertThat(response.getDataUser().getAccountUrl(), equalTo("tinetoon"));
        assertThat(response.getDataUser().getEmail(), equalTo("kkpj6cmnsm@privaterelay.appleid.com"));
    }
}
