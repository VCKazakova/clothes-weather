package com.kazakova.clothesweather.controller;

import com.kazakova.clothesweather.model.Season;
import com.kazakova.clothesweather.model.Style;
import com.kazakova.clothesweather.model.Type;
import com.kazakova.clothesweather.model.Wardrobe;
import com.kazakova.clothesweather.service.WardrobeService;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(WardrobeController.class)
@WithUserDetails("vika")
@TestPropertySource(value = "/application-test.yml")
@ExtendWith(SpringExtension.class)
public class WardrobeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @InjectMocks
    private WardrobeService wardrobeService;

//    @Test
//    @DisplayName("Возвращать страницу summer-season")
//    public void testGetAllBySeason() throws Exception {
//
//        Season season = Season.SUMMER;
//        Wardrobe wardrobe = new Wardrobe(1L, "Hat", Type.HAT, Style.CASUAL, Season.SUMMER,
//                "https://ae01.alicdn.com/kf/HTB1egF7bznuK1RkSmFPq6AuzFXa3/-.jpg");
//
//        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/wardrobe/{season}", season);
//
//        mockMvc.perform(request.accept("application/json"))
//                .andExpect(status().isOk())
//                .andExpect(content().contentType("text/html;charset=UTF-8"))
//                .andExpect(view().name("summer-season"))
//                .andExpect(MockMvcResultMatchers.view().name("summer-season"))
//                .andExpect(content().string(Matchers.containsString("Summer clothes")))
//                .andDo(print());
//
//    }

    @Test
    public void shouldReturnViewWithPrefilledData() throws Exception {

        Wardrobe stuff1 = new Wardrobe(1L, "Coat", Type.COAT, Style.CASUAL, Season.DEMI,
                "/home/vckazakova/JavaPractice/clothes-weather/src/main/resources/photos/1.jpeg");

        Wardrobe stuff2 = new Wardrobe(2L, "Boots", Type.SHOES, Style.CHIC, Season.DEMI,
                "/home/vckazakova/JavaPractice/clothes-weather/src/main/resources/photos/2.jpg");

        Wardrobe stuff3 = new Wardrobe(3L, "Dress", Type.DRESS, Style.CASUAL, Season.DEMI, "https://ae01.alicdn.com/kf/HTB1egF7bznuK1RkSmFPq6AuzFXa3/-.jpg");

        when(wardrobeService.findAllBySeason(Season.DEMI)).thenReturn(Arrays.asList(stuff1, stuff2, stuff3));

        this.mockMvc
                .perform(get("/wardrobe{season}"))
                .andExpect(status().isOk())
                .andExpect(view().name("demi-season"))
                .andExpect(model().attribute("wardrobe", Matchers.arrayContaining(stuff1, stuff2, stuff3)));
//                .andExpect(model().attributeExists("wardrobe"));
    }


}
