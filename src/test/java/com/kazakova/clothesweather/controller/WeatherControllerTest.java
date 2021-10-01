package com.kazakova.clothesweather.controller;

import com.kazakova.clothesweather.model.Season;
import com.kazakova.clothesweather.model.Style;
import com.kazakova.clothesweather.model.Type;
import com.kazakova.clothesweather.model.Wardrobe;
import com.kazakova.clothesweather.service.WardrobeService;
import com.kazakova.clothesweather.service.WeatherService;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@DisplayName("WeatherController должен")
@AutoConfigureMockMvc
@WithUserDetails("vika")
@TestPropertySource("/application-test.yml")
public class WeatherControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private WeatherService weatherService;

    @Test
    @DisplayName("Возвращать страницу demi")
    public void testGetDemiPage() throws Exception {


    }


}
