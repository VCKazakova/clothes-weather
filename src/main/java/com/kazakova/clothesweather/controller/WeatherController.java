package com.kazakova.clothesweather.controller;

import com.kazakova.clothesweather.model.Season;
import com.kazakova.clothesweather.model.Wardrobe;
import com.kazakova.clothesweather.service.WardrobeService;
import com.kazakova.clothesweather.service.WeatherService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;
import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
public class WeatherController {
    // TODO убрать
    @Autowired
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

        // TODO убать дубликат логики, вынетсина сервис
        if (temperature >= -5 && temperature <= 17) {
            List<Wardrobe> wardrobe = wardrobeService.findAllBySeason(Season.DEMI);
            model.addAttribute("wardrobe", wardrobe);
            log.info(">> WeatherController getClothesByTodayWeather wardrobe={}", wardrobe);
            return "demi";
        } else if (temperature <= -6 && temperature >= -45) {
            List<Wardrobe> wardrobe = wardrobeService.findAllBySeason(Season.WINTER);
            model.addAttribute("wardrobe", wardrobe);
            log.info(">> WeatherController getClothesByTodayWeather wardrobe={}", wardrobe);
            return "winter";
        } else if (temperature >= 18 && temperature <= 45) {
            List<Wardrobe> wardrobe = wardrobeService.findAllBySeason(Season.SUMMER);
            model.addAttribute("wardrobe", wardrobe);
            log.info(">> WeatherController getClothesByTodayWeather wardrobe={}", wardrobe);
            return "summer";
        }


        return "redirect:/";
    }

}
