package com.kazakova.clothesweather.controller;


import com.kazakova.clothesweather.model.Season;
import com.kazakova.clothesweather.model.Wardrobe;
import com.kazakova.clothesweather.service.WardrobeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class WardrobeController {

    private final WardrobeService wardrobeService;

    @GetMapping("/wardrobe/{season}")
    public String getAllBySeason(@PathVariable("season") Season season, Model model) {
        log.info(">> WardrobeController getAllBySeason  season={}", season);
        List<Wardrobe> clothes = wardrobeService.findAllBySeason(season);
        model.addAttribute("wardrobe", clothes);
        log.info(">> WardrobeController getAllBySeason wardrobe={}", clothes);
        switch (season) {
            case DEMI:
                return "demi-season";
            case SUMMER:
                return "summer-season";
            case WINTER:
                return "winter-season";
            default:
                return "redirect:/";
        }
    }

    @GetMapping("/wardrobe")
    public String getAll(Model model) {
        log.info(">> WardrobeController getAll");
        List<Wardrobe> wardrobe = wardrobeService.findAll();
        model.addAttribute("wardrobe", wardrobe);
        log.info(">> WardrobeController getAll wardrobe={}", wardrobe);
        return "wardrobe";
    }


    @GetMapping("/wardrobe/create")
    public String createStuffForm(Wardrobe wardrobe) {
        log.info(">> WardrobeController createStuffForm");
        return "create";
    }

    @PostMapping("/wardrobe/create")
    public String createStuff(Wardrobe wardrobe) {
        log.info(">> WardrobeController createStuff wardrobe={}", wardrobe);
        Wardrobe newStuff = wardrobeService.createStuff(wardrobe);
        log.info(">> WardrobeController createStuff  stuff={}", newStuff);
        return "redirect:/wardrobe";
    }


    @GetMapping("/wardrobe/delete/{id}")
    public String deleteStuff(@PathVariable("id") Long id) {
        log.info(">> WardrobeController deleteStuff id={}", id);
        wardrobeService.deleteStuffById(id);
        return "redirect:/wardrobe";
    }

    @GetMapping("/wardrobe/update/{id}")
    public String updateStuffForm(@PathVariable("id") Long id, Model model) {
        log.info(">> WardrobeController updateStuffForm id={}", id);
        Wardrobe wardrobe = wardrobeService.findStuffById(id).orElseThrow();
        log.info(">> WardrobeController updateStuffForm wardrobe={}", wardrobe);
        model.addAttribute("wardrobe", wardrobe);
        return "update";
    }

    @PostMapping("/wardrobe/update")
    public String updateStuff(Wardrobe wardrobe) {
        log.info(">> WardrobeController updateStuff stuff={}", wardrobe);
        Wardrobe stuff = wardrobeService.createStuff(wardrobe);
        log.info(">> WardrobeController updateStuff stuff={}", stuff);
        return "redirect:/wardrobe";
    }
}
