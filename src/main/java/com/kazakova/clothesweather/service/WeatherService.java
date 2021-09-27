package com.kazakova.clothesweather.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class WeatherService {

    public static Document getPage() throws IOException {
        try {
            String url = "https://pogoda.mail.ru/prognoz/moskva/";
            Document page = Jsoup.parse(new URL(url), 100000);
            return page;
        } catch (Exception exception) {
            throw new IOException("The page doesn't found");
        }
    }


    public static Elements selectInformationAboutTodayWeather(Document page) {
        return Objects.requireNonNull(page.select("div[class=information__content]")
                        .first())
                .select("div[class=information__content__temperature]");
    }

    public String getTodayWeather() throws IOException {
        try {
            Elements values = selectInformationAboutTodayWeather(getPage());
            String todayTemperature = values.text();

            Elements span = values.select("span");
            List<String> spanList = span.stream().map(Node::toString).collect(Collectors.toList());
            String stringFromList = spanList.toString();


            String pattern = "([а-яА-Я]+)(.*)([а-яА-Я]+)";
            Pattern r = Pattern.compile(pattern);
            Matcher m = r.matcher(stringFromList);

            if (m.find()) {
                return "Погода на сегодня: " + todayTemperature + " " + m.group(0);
            } else throw new IOException("The weather doesn't find");
        } catch (Exception exception) {
            throw new IOException("The weather doesn't found");
        }
    }

    public int getTodayTemperature() throws IOException {
        try {
            String todayWeather = getTodayWeather();
            String[] splitTodayWeather = todayWeather.split(" ");
            String stringOfTemperature = splitTodayWeather[3];
            String stringOfTemperatureWithoutLastElem = stringOfTemperature.substring(0, stringOfTemperature.length() - 1);
            int i = Integer.parseInt(stringOfTemperatureWithoutLastElem);
            return i;
        } catch (Exception exception) {
            throw new IOException("The temprature doesn't found!");
        }
    }
}
