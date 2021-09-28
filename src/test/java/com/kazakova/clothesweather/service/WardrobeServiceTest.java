package com.kazakova.clothesweather.service;

import com.kazakova.clothesweather.model.Season;
import com.kazakova.clothesweather.model.Style;
import com.kazakova.clothesweather.model.Type;
import com.kazakova.clothesweather.model.Wardrobe;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.Optional;

@DisplayName("Service Wardrobe должен")
@DataJpaTest
@Import(WardrobeService.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestPropertySource(value = "/application-test.yml")
public class WardrobeServiceTest {

    private static final String STUFF = "Dress";
    private static final String URL = "https://a.lmcdn.ru/img600x866/M/P/MP002XW038G9_12894379_2_v1_2x.jpg";
    private static final Long ID = 1L;
    private static final Integer EXPECTED_NUMBER_OF_STUFFS = 2;
    private static final Integer EXPECTED_NUMBER_OF_ALL_STUFFS = 3;
    private static final Integer EXPECTED_NUMBER_OF_STUFFS_BY_DEMI = 3;
    private static final String EXPECTED_STUFF = "Coat";

    @Autowired
    private WardrobeService wardrobeService;

    @Test
    @DisplayName("Создавать wardrobe")
    @Transactional(rollbackFor = {SQLException.class})
    public void testCreateWardrobe() {
        Wardrobe wardrobe = new Wardrobe();
        wardrobe.setStuff(STUFF);
        wardrobe.setType(Type.DRESS);
        wardrobe.setStyle(Style.CHIC);
        wardrobe.setSeason(Season.SUMMER);
        wardrobe.setUrl(URL);

        wardrobeService.createStuff(wardrobe);

        Optional<Wardrobe> optionalWardrobe = wardrobeService.findStuffById(wardrobe.getId());
        AssertionsForClassTypes.assertThat(optionalWardrobe).isPresent();
        String actualStuff = optionalWardrobe.get().getStuff();

        Assertions.assertEquals(STUFF, actualStuff);
    }

    @Test
    @DisplayName("Удалять wardrobe по id")
    @Transactional(rollbackFor = {SQLException.class})
    public void testDeleteWardrobeById() {
        wardrobeService.deleteStuffById(ID);
        Integer actualNumberOfStuffs = wardrobeService.findAll().size();

        Assertions.assertEquals(EXPECTED_NUMBER_OF_STUFFS, actualNumberOfStuffs);
    }

    @Test
    @DisplayName("Находить все части wardrobe")
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public void testFindAllStuffsOfWardrobe() {
        Integer actualNumberOfAllStuffs = wardrobeService.findAll().size();

        Assertions.assertEquals(EXPECTED_NUMBER_OF_ALL_STUFFS, actualNumberOfAllStuffs);
    }

    @Test
    @DisplayName("Находить все части wardrobe по сезону")
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public void testFindAllStuffsOfWardrobeBySeason() {
        Integer actualNumberOfStuffsByDemi = wardrobeService.findAllBySeason(Season.DEMI).size();

        Assertions.assertEquals(EXPECTED_NUMBER_OF_STUFFS_BY_DEMI, actualNumberOfStuffsByDemi);
    }

    @Test
    @DisplayName("Находить вещь по id")
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public void testGetStuffById() {
        Optional<Wardrobe> optionalWardrobe = wardrobeService.findStuffById(ID);
        AssertionsForClassTypes.assertThat(optionalWardrobe).isPresent();

        String actualStuffOfWardrobe = optionalWardrobe.get().getStuff();

        Assertions.assertEquals(EXPECTED_STUFF, actualStuffOfWardrobe);
    }

}
