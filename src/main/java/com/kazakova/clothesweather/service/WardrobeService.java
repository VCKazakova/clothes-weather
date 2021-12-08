package com.kazakova.clothesweather.service;


import com.kazakova.clothesweather.exception.ApiRequestException;
import com.kazakova.clothesweather.model.Season;
import com.kazakova.clothesweather.model.Wardrobe;
import com.kazakova.clothesweather.repository.WardrobeRepository;
import lombok.NoArgsConstructor;
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
    // TODO убрать лишнее
    @Autowired
    private final WardrobeRepository wardrobeRepository;

    @Transactional(rollbackFor = {SQLException.class})
    public Wardrobe createStuff(Wardrobe wardrobe) {
        if (wardrobe == null) {
            throw new ApiRequestException("The entity mustn't be null");
        }
        return wardrobeRepository.save(wardrobe);
    }

    @Transactional(rollbackFor = {SQLException.class})
    public void deleteStuffById(Long id) {
        if (id == null) {
            throw new ApiRequestException("The id mustn't be null");
        }
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

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Optional<Wardrobe> findStuffById(Long id) {
        if (id == null) {
            throw new ApiRequestException("The id mustn't be null");
        }
        return wardrobeRepository.findById(id);
    }
}
