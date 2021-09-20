package com.kazakova.clothesweather.controller;


import com.kazakova.clothesweather.model.Season;
import com.kazakova.clothesweather.model.Wardrobe;
import com.kazakova.clothesweather.service.WardrobeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class WardrobeController {

    @Autowired
    private final WardrobeService wardrobeService;

    @GetMapping("/season/{season}")
    public List<Wardrobe> getAllBySeason(@PathVariable("season") Season season) {
        log.info(">> WardrobeController getAllBySeason  season={}", season);
        List<Wardrobe> wardrobe = wardrobeService.findAllBySeason(season);
        log.info(">> WardrobeController getAllBySeason wardrobe={}", wardrobe);
        return wardrobe;
    }
}
