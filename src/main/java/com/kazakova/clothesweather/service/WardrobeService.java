package com.kazakova.clothesweather.service;


import com.kazakova.clothesweather.model.Season;
import com.kazakova.clothesweather.model.Style;
import com.kazakova.clothesweather.model.Type;
import com.kazakova.clothesweather.model.Wardrobe;
import com.kazakova.clothesweather.repository.WardrobeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WardrobeService {

    @Autowired
    private final WardrobeRepository wardrobeRepository;

    public Wardrobe createStuff(Wardrobe wardrobe) {
        return wardrobeRepository.save(wardrobe);
    }

    public Optional<Wardrobe> findStuffById(Long id) {
        return wardrobeRepository.findById(id);
    }

    public List<Wardrobe> findAll() {
        return wardrobeRepository.findAll();
    }

    public void deleteStuffById(Long id) {
        wardrobeRepository.deleteById(id);
    }

    public List<Wardrobe> findAllBySeason(Season season) {
        return wardrobeRepository.findAllBySeason(season);
    }

    public List<Wardrobe> findAllByStyle(Style style) {
        return wardrobeRepository.findAllByStyle(style);
    }

    public List<Wardrobe> findAllByType(Type type) {
        return wardrobeRepository.findAllByType(type);
    }

    public List<Wardrobe> findAllPhotoBySeason(Season season) {
        return wardrobeRepository.findAllPhotoBySeason(season);
    }

    public List<Wardrobe> findAllPhotoByStyle(Style style) {
        return wardrobeRepository.findAllPhotoByStyle(style);
    }

    public List<Wardrobe> findAllPhotoByType(Type type) {
        return wardrobeRepository.findAllPhotoByType(type);
    }

    public List<Wardrobe> findAllPhotoBySeasonAndStyleAndType(Season season, Style style, Type type) {
        return wardrobeRepository.findAllPhotoBySeasonAndStyleAndType(season, style, type);
    }

}
