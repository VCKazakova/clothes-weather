package com.kazakova.clothesweather.service;


import com.kazakova.clothesweather.model.Season;
import com.kazakova.clothesweather.model.Wardrobe;
import com.kazakova.clothesweather.repository.WardrobeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WardrobeService {

    @Autowired
    private final WardrobeRepository wardrobeRepository;

    @Transactional(rollbackFor = {SQLException.class})
    public Wardrobe createStuff(Wardrobe wardrobe) {
        return wardrobeRepository.save(wardrobe);
    }

    @Transactional(rollbackFor = {SQLException.class})
    public void deleteStuffById(Long id) {
        wardrobeRepository.deleteById(id);
    }

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Wardrobe> findAll() {
        return wardrobeRepository.findAll();
    }

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Wardrobe> findAllBySeason(Season season) {
        return wardrobeRepository.findAllBySeason(season);
    }


}
