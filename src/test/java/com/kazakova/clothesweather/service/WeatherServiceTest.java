package com.kazakova.clothesweather.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;

@DisplayName("Service Weather должен")
public class WeatherServiceTest {

    @Test
    @DisplayName("Возвращать html-страницу")
    public void testGetHtmlPage() throws IOException {
        Document page = Jsoup.parse(new URL("https://pogoda.mail.ru/prognoz/moskva/"), 100000);

        Assertions.assertTrue(page.hasText());
        Assertions.assertEquals("#document", page.nodeName());
    }


    @Test
    @DisplayName("Кидать исключение при невозможности вернуть страницу")
    public void testThrowIOExceptionWhenCannotGetThePage() throws IOException {
        Throwable thrown = catchThrowable(() -> {
            Jsoup.parse(new URL("https://pogoda.mail.ru/prognoz/moskv"), 100000);
        });
        assertThat(thrown).isInstanceOf(IOException.class);
        assertThat(thrown.getMessage()).isNotBlank();
    }

    @Test
    @DisplayName("Возвращать информацию о сегодняшней погоде")
    public void testGetInformationAboutTodayWeatherFromGettingHtmlPage() throws IOException {
        Document page = Jsoup.parse(new URL("https://pogoda.mail.ru/prognoz/moskva/"), 100000);
        String oneString = "<div class=\"information__content__temperature\">";
        Elements actualElements = (Objects.requireNonNull(page.select("div[class=information__content]")
                .first()))
                .select("div[class=information__content__temperature]");

        org.assertj.core.api.Assertions.assertThat(actualElements).isInstanceOf(Elements.class);
        assertThat(actualElements.toString()).containsAnyOf(oneString);
    }

    @Test
    @DisplayName("Возвращать строку с погодой")
    public void testGetStringWithTodayWeather() throws IOException {
        Elements actualElements = (Objects.requireNonNull(Jsoup.parse(new URL("https://pogoda.mail.ru/prognoz/moskva/"), 100000)
                .select("div[class=information__content]")
                .first()))
                .select("div[class=information__content__temperature]");
        String actualText = actualElements.text();

        assertThat(actualText).isInstanceOf(String.class).containsAnyOf("°");
    }

    @Test
    @DisplayName("Возвращать значение температуры")
    public void testGetIntWithTodayTemperature() throws IOException {
        Elements actualElements = (Objects.requireNonNull(Jsoup.parse(new URL("https://pogoda.mail.ru/prognoz/moskva/"), 100000)
                .select("div[class=information__content]")
                .first()))
                .select("div[class=information__content__temperature]");
        String actualText = actualElements.text();
        Integer actualInteger = Integer.parseInt(actualText.substring(0, actualText.length() - 1));

        assertThat(actualInteger).isInstanceOf(Integer.class);
    }

}
