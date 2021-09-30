package com.kazakova.clothesweather.service;

import com.kazakova.clothesweather.form.UserForm;
import com.kazakova.clothesweather.model.User;
import com.kazakova.clothesweather.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.Optional;

@DisplayName("Service SignUp должен")
@DataJpaTest
@Import({SignUpServiceImpl.class, UserRepository.class})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class SignUpServiceImplTest {

    @Autowired
    private SignUpServiceImpl signUpService;

    @Autowired
    private UserRepository userRepository;

    @Test
    @DisplayName("Сохранять пользователя в базу")
    @Builder
    public void testSaveUserToDataBase() {
        UserForm userForm = new UserForm("Oleg", "oleg", "1234");

        signUpService.signUp(userForm);

        Optional<User> optionalUser = userRepository.findOneByLogin(userForm.getLogin());
        AssertionsForClassTypes.assertThat(optionalUser).isPresent();
        String actualUser = optionalUser.get().getName();

        Assertions.assertEquals("Oleg", actualUser);

    }

}
