package com.kazakova.clothesweather.repository;


import com.kazakova.clothesweather.model.Season;
import com.kazakova.clothesweather.model.Style;
import com.kazakova.clothesweather.model.Type;
import com.kazakova.clothesweather.model.Wardrobe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public interface WardrobeRepository extends JpaRepository<Wardrobe, Long> {

    List<Wardrobe> findAllBySeason(Season season);
    List<Wardrobe> findAllByStyle(Style style);
    List<Wardrobe> findAllByType(Type type);
    List<Wardrobe> findAllPhotoBySeason(Season season);
    List<Wardrobe> findAllPhotoByStyle(Style style);
    List<Wardrobe> findAllPhotoByType(Type type);
    List<Wardrobe> findAllPhotoBySeasonAndStyleAndType(Season season, Style style, Type type);
}
