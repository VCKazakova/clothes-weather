package com.kazakova.clothesweather.repository;


import com.kazakova.clothesweather.model.Season;
import com.kazakova.clothesweather.model.Wardrobe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WardrobeRepository extends JpaRepository<Wardrobe, Long> {

    List<Wardrobe> findAllBySeason(Season season);

}
