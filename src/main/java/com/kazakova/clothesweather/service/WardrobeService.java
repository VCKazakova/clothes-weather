package com.kazakova.clothesweather.service;


import com.kazakova.clothesweather.exception.ApiRequestException;
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
import java.util.Optional;

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
        List<Wardrobe> wardrobe = wardrobeRepository.findAll();
        if (wardrobe.isEmpty()) {
            throw new ApiRequestException("Dont't find any clothes");
        }
        return wardrobe;
    }

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Wardrobe> findAllBySeason(Season season) {
        return wardrobeRepository.findAllBySeason(season);
    }

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Optional<Wardrobe> findStuffById(Long id) {
        return wardrobeRepository.findById(id);
    }
}
