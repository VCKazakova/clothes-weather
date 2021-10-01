package com.kazakova.clothesweather.service;

import com.kazakova.clothesweather.form.UserForm;
import com.kazakova.clothesweather.model.User;
import com.kazakova.clothesweather.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.TestPropertySource;

@DisplayName("Service SignUp должен")
@DataJpaTest
@Import(SignUpServiceImpl.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestPropertySource(value = "/application.yml")
public class SignUpServiceImplTest {

    @Autowired
    private SignUpServiceImpl signUpService;

    @Autowired
    private TestEntityManager em;

    @Test
    @DisplayName("Вносить данные пользователя в базу")

    public void testSignUpUser() {
        UserForm userForm = new UserForm("Oleg", "oleg", "1234");

        signUpService.signUp(userForm);

        Assertions.assertEquals("Oleg", em.find(User.class, 3L).getName());
    }

}
