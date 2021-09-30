package com.kazakova.clothesweather.controller;

import com.kazakova.clothesweather.form.UserForm;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@DisplayName("LoginController должен")
@AutoConfigureMockMvc
@WithUserDetails("vika")
public class LoginControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private LoginController loginController;


    @Test
    @DisplayName("Проверять авторизацию пользователя")
    public void testCheckUserAutorization() throws Exception {
        this.mockMvc.perform(get("/home"))
                .andDo(print())
                .andExpect(authenticated());
    }

    @Test
    @DisplayName("Возвращать страницу hello-page")
    public void testGetHelloPage() throws Exception {

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/");

        mockMvc.perform(request.accept("application/json"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("text/html;charset=UTF-8"))
                .andExpect(view().name("hello-page"))
                .andExpect(MockMvcResultMatchers.view().name("hello-page"))
                .andExpect(content().string(Matchers.containsString("Hello, dear friend!")))
                .andDo(print());
    }

    @Test
    @DisplayName("Возвращать страницу home")
    public void testGetHomePage() throws Exception {

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/home");

        mockMvc.perform(request.accept("application/json"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("text/html;charset=UTF-8"))
                .andExpect(view().name("home"))
                .andExpect(MockMvcResultMatchers.view().name("home"))
                .andExpect(content().string(Matchers.containsString("Please, choose the way!")))
                .andDo(print());
    }

    @Test
    @DisplayName("Возвращать страницу login")
    public void testGetLoginPage() throws Exception {
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/login");
        mockMvc.perform(request.accept("application/json"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("text/html;charset=UTF-8"))
                .andExpect(view().name("login"))
                .andExpect(MockMvcResultMatchers.view().name("login"))
                .andExpect(content().string(Matchers.containsString("Please, login!")))
                .andDo(print());
    }

    @Test
    @DisplayName("Возвращать страницу signUp")
    public void testGetSignUpPage() throws Exception {
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/signUp");
        mockMvc.perform(request.accept("application/json"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("text/html;charset=UTF-8"))
                .andExpect(view().name("signUp"))
                .andExpect(MockMvcResultMatchers.view().name("signUp"))
                .andExpect(content().string(Matchers.containsString("Please, signUp!")))
                .andDo(print());
    }

    @Test
    @DisplayName("Регистрировать пользователя")
    public void testSignUpUser() throws Exception {
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/signUp");

        UserForm userForm = new UserForm();
        userForm.setName("Oleg");
        userForm.setLogin("oleg");
        userForm.setPassword("1234");

        mockMvc.perform(request.content(String.valueOf(userForm))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Oleg"))
                .andDo(print());
    }

}
