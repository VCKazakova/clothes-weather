package com.kazakova.clothesweather.controller;


import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.core.StringContains.containsString;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@DisplayName("Тесты аутентификации")
@AutoConfigureMockMvc
@TestPropertySource(value = "/application.yml")
public class LoginTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private LoginController loginController;

    @Test
    @DisplayName("Проверка на null")
    public void testCheckThatContollerIsNotNull() throws Exception {
        Assertions.assertThat(loginController).isNotNull();
    }

    @Test
    @DisplayName("Возвращать страницу hello-page")
    public void testReturnHelloHtmlPage() throws Exception {
        this.mockMvc.perform(get("/"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Hello, dear friend!")));
    }

    @Test
    @DisplayName("Проверять авторизацию пользователя")
    public void testCheckAuthorization() throws Exception {
        this.mockMvc.perform(get("/home"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("http://localhost/login"));
    }

    @Test
    @DisplayName("Проверять корректность логина")
    public void testCheckLoginIsCorrect() throws Exception {
        this.mockMvc.perform(formLogin().user("vika").password("1234"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/home"));
    }

    @Test
    @DisplayName("Проверять запрет на доступ")
    public void testCheckBadCredentials() throws Exception {
        this.mockMvc.perform(post("/login").param("user", "Ivan"))
                .andDo(print())
                .andExpect(status().isForbidden());
    }



}
