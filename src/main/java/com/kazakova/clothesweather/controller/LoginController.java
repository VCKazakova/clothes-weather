package com.kazakova.clothesweather.controller;

import com.kazakova.clothesweather.form.UserForm;
import com.kazakova.clothesweather.service.SignUpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j
public class LoginController {

    @Autowired
    private SignUpService service;

    @GetMapping("/")
    public String getHelloPage() {
        log.info(">> LoginController getHelloPage");
        return "hello-page";
    }

    @GetMapping("/home")
    public String getHomePage() {
        log.info(">> LoginController getHomePage");
        return "home";
    }

    @GetMapping("/login")
    public String login() {
        log.info(">> LoginController login");
        return "login";
    }

    @GetMapping("/signUp")
    public String createAccount() {
        log.info(">> LoginController createAccount");
        return "signUp";
    }

    @PostMapping("/signUp")
    public String signUp(UserForm userForm) {
        log.info(">> LoginController signUp");
        service.signUp(userForm);
        return "redirect:/login";
    }

}
