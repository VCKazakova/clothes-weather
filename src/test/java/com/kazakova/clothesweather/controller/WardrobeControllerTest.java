package com.kazakova.clothesweather.controller;

import com.kazakova.clothesweather.model.Season;
import com.kazakova.clothesweather.model.Style;
import com.kazakova.clothesweather.model.Type;
import com.kazakova.clothesweather.model.Wardrobe;
import com.kazakova.clothesweather.repository.WardrobeRepository;
import com.kazakova.clothesweather.service.WardrobeService;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
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
@DisplayName("WardrobeController должен")
@AutoConfigureMockMvc
//@WebMvcTest(WardrobeController.class)
@WithUserDetails("vika")
@TestPropertySource("/application-test.yml")
public class WardrobeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private WardrobeService wardrobeService;

    @Test
    @DisplayName("Возвращать страницу demi-season")
    public void testGetAllBySummer() throws Exception {

        Season season = Season.SUMMER;

        List<Wardrobe> clothes = new ArrayList<>();

        when(wardrobeService.findAllBySeason(season)).thenReturn(clothes);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/wardrobe/{season}", season);

        mockMvc.perform(request.accept("application/json"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("text/html;charset=UTF-8"))
                .andExpect(view().name("summer-season"))
                .andExpect(MockMvcResultMatchers.view().name("summer-season"))
                .andExpect(content().string(Matchers.containsString("Summer clothes")))
                .andExpect(model().attribute("wardrobe", Matchers.empty()))
                .andDo(print());
    }


    @Test
    @DisplayName("Возвращать страницу demi-season")
    public void testGetAllBySeason() throws Exception {

        Season season = Season.DEMI;

        Wardrobe stuff1 = new Wardrobe(1L, "Coat", Type.COAT, Style.CASUAL, Season.DEMI,
                "/home/vckazakova/JavaPractice/clothes-weather/src/main/resources/photos/1.jpeg");
        Wardrobe stuff2 = new Wardrobe(2L, "Boots", Type.SHOES, Style.CHIC, Season.DEMI,
                "/home/vckazakova/JavaPractice/clothes-weather/src/main/resources/photos/2.jpg");
        Wardrobe stuff3 = new Wardrobe(3L, "Dress", Type.DRESS, Style.CASUAL, Season.DEMI, "https://ae01.alicdn.com/kf/HTB1egF7bznuK1RkSmFPq6AuzFXa3/-.jpg");

        List<Wardrobe> clothes = new ArrayList<>();
        clothes.add(stuff1);
        clothes.add(stuff2);
        clothes.add(stuff3);

        when(wardrobeService.findAllBySeason(Season.DEMI)).thenReturn(clothes);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/wardrobe/{season}", season);

        mockMvc.perform(request.accept("application/json"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("text/html;charset=UTF-8"))
                .andExpect(view().name("demi-season"))
                .andExpect(MockMvcResultMatchers.view().name("demi-season"))
                .andExpect(content().string(Matchers.containsString("Demi clothes")))
                .andExpect(model().attribute("wardrobe", clothes))
                .andDo(print());

    }


}
