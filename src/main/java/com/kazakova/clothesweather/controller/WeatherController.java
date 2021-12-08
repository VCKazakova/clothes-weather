package com.kazakova.clothesweather.controller;

import com.kazakova.clothesweather.model.Season;
import com.kazakova.clothesweather.model.Wardrobe;
import com.kazakova.clothesweather.service.WardrobeService;
import com.kazakova.clothesweather.service.WeatherService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class WeatherController {

    private final WeatherService weatherService;
    private final WardrobeService wardrobeService;

    @GetMapping("/weather/today")
    public String getClothesByTodayWeather(Model model, Model weather) throws IOException {
        log.info(">> WeatherController getClothesByTodayWeather");

        int temperature = weatherService.getTodayTemperature();
        log.info(">> WeatherController getTodayTemperature temperature={}", temperature);

        String weatherToday = weatherService.getTodayWeather();
        log.info(">> WeatherController getTodayWeather weatherToday={}", weatherToday);

        weather.addAttribute("weatherToday", weatherToday);

        Season currentSeason = weatherService.getWardrobeByWeather(temperature);

        List<Wardrobe> wardrobe = wardrobeService.findAllBySeason(currentSeason);
        model.addAttribute("wardrobe", wardrobe);
        log.info("<< WeatherController getClothesByTodayWeather wardrobe={}", wardrobe);

        if (currentSeason == Season.DEMI)
            return "demi";
        else if (currentSeason == Season.SUMMER)
            return "summer";
        else if (currentSeason == Season.WINTER)
            return "winter";
        else return "redirect:/";
    }
}

