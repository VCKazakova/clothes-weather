package com.kazakova.clothesweather.controller;

import com.kazakova.clothesweather.model.Season;
import com.kazakova.clothesweather.model.Style;
import com.kazakova.clothesweather.model.Type;
import com.kazakova.clothesweather.model.Wardrobe;
import com.kazakova.clothesweather.service.WardrobeService;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
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
@WithUserDetails("vika")
@TestPropertySource("/application-test.yml")
public class WardrobeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private WardrobeService wardrobeService;

    @Test
    @DisplayName("Возвращать страницу summer-season")
    public void testGetAllBySummerSeason() throws Exception {

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
    public void testGetAllByDemiSeason() throws Exception {

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

    @Test
    @DisplayName("Возвращать весь wardrobe")
    public void testGetAllWardrobe() throws Exception {
        Wardrobe stuff1 = new Wardrobe(1L, "Coat", Type.COAT, Style.CASUAL, Season.DEMI,
                "/home/vckazakova/JavaPractice/clothes-weather/src/main/resources/photos/1.jpeg");
        Wardrobe stuff2 = new Wardrobe(2L, "Boots", Type.SHOES, Style.CHIC, Season.DEMI,
                "/home/vckazakova/JavaPractice/clothes-weather/src/main/resources/photos/2.jpg");
        Wardrobe stuff3 = new Wardrobe(3L, "Dress", Type.DRESS, Style.CASUAL, Season.DEMI, "https://ae01.alicdn.com/kf/HTB1egF7bznuK1RkSmFPq6AuzFXa3/-.jpg");

        List<Wardrobe> clothes = new ArrayList<>();
        clothes.add(stuff1);
        clothes.add(stuff2);
        clothes.add(stuff3);

        when(wardrobeService.findAll()).thenReturn(clothes);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/wardrobe");

        mockMvc.perform(request.accept("application/json"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("text/html;charset=UTF-8"))
                .andExpect(view().name("wardrobe"))
                .andExpect(MockMvcResultMatchers.view().name("wardrobe"))
                .andExpect(content().string(Matchers.containsString("All your clothes here")))
                .andExpect(model().attribute("wardrobe", clothes))
                .andDo(print());

    }

    @Test
    @DisplayName("Возвращать форму для создания вещи")
    public void testGetCreateStuffForm() throws Exception {

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/wardrobe/create");

        mockMvc.perform(request.accept("application/json"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("text/html;charset=UTF-8"))
                .andExpect(view().name("create"))
                .andExpect(MockMvcResultMatchers.view().name("create"))
                .andExpect(content().string(Matchers.containsString("Create stuff")))
                .andDo(print());

    }

    @Test
    @DisplayName("Создавать вещь и возвращать страницу wardrobe")
    public void testCreateStuffAndRedirectToWardrobe() throws Exception {

        Wardrobe wardrobe = new Wardrobe();
        wardrobe.setStuff("Pants");
        wardrobe.setType(Type.BOTTOM);
        wardrobe.setStyle(Style.CASUAL);
        wardrobe.setSeason(Season.WINTER);
        wardrobe.setUrl("http://vk.com/vika_ub/photo");

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/wardrobe/create");

        mockMvc.perform(request.content(String.valueOf(wardrobe))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrlTemplate("/wardrobe"));
    }

    @Test
    @DisplayName("Удалять вещь и возвращать страницу wardrobe")
    public void testDeleteStuffAndRedirectToWardrobe() throws Exception {

        Long id = 1L;

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/wardrobe/delete/{id}", id);

        mockMvc.perform(request.accept("application/json"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrlTemplate("/wardrobe"));
    }

    @Test
    @DisplayName("Возвращать форму для обновления вещи")
    public void testGetUpdateStuffForm() throws Exception {

        Long id = 3L;
        Wardrobe stuff = new Wardrobe(3L, "Dress", Type.DRESS, Style.CASUAL, Season.DEMI, "https://ae01.alicdn.com/kf/HTB1egF7bznuK1RkSmFPq6AuzFXa3/-.jpg");

        when(wardrobeService.findStuffById(id)).thenReturn(java.util.Optional.of(stuff));

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/wardrobe/update/{id}", id);

        mockMvc.perform(request.accept("application/json"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("text/html;charset=UTF-8"))
                .andExpect(view().name("update"))
                .andExpect(MockMvcResultMatchers.view().name("update"))
                .andExpect(content().string(Matchers.containsString("Please, update information about stuff!")))
                .andDo(print());

    }

    @Test
    @DisplayName("Обновлять вещь и возвращать страницу wardrobe")
    public void testUpdateStuffAndRedirectToWardrobe() throws Exception {


        Wardrobe wardrobe = new Wardrobe();
        wardrobe.setStuff("Pants");
        wardrobe.setType(Type.BOTTOM);
        wardrobe.setStyle(Style.CASUAL);
        wardrobe.setSeason(Season.WINTER);
        wardrobe.setUrl("http://vk.com/vika_ub/photo");

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/wardrobe/update");

        mockMvc.perform(request.content(String.valueOf(wardrobe))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrlTemplate("/wardrobe"));
    }


}
