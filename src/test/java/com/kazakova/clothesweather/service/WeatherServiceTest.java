package com.kazakova.clothesweather.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URL;

@DisplayName("Service Weather должен")
public class WeatherServiceTest {

    @Test
    @DisplayName("Возвращать html-страницу")

    public void testGetHtmlPage() throws IOException {
        Document page = Jsoup.parse(new URL("https://pogoda.mail.ru/prognoz/moskva/"), 100000);

        Assertions.assertTrue(page.hasText());
        Assertions.assertEquals("#document", page.nodeName());
    }
}
