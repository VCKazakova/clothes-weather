package com.kazakova.clothesweather.convert;

import com.kazakova.clothesweather.model.Season;

import org.springframework.core.convert.converter.Converter;

public class StringToSeasonConverter implements Converter<String, Season> {

    @Override
    public Season convert(String source) {
        return Season.decode(source);
    }

}
