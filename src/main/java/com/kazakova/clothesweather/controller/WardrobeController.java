package com.kazakova.clothesweather.controller;


import com.kazakova.clothesweather.model.Season;
import com.kazakova.clothesweather.model.Wardrobe;
import com.kazakova.clothesweather.service.WardrobeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
public class WardrobeController {

    private final WardrobeService wardrobeService;

    public WardrobeController(WardrobeService wardrobeService) {
        this.wardrobeService = wardrobeService;
    }

    @GetMapping("/season/{season}")
    public List<Wardrobe> getAllBySeason(@PathVariable("season") Season season) {
        log.info(">> WardrobeController getAllBySeason  season={}", season);
        List<Wardrobe> wardrobe = wardrobeService.findAllBySeason(season);
        log.info(">> WardrobeController getAllBySeason wardrobe={}", wardrobe);
        return wardrobe;
    }
}
