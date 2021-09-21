package com.kazakova.clothesweather.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/")
    public String helloPage() {
        return "hello-page";
    }
    @GetMapping("/home")
    public String homePage() {
        return "home";
    }
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/create-account")
    public String createAccount() {
        return "create-account";
    }

}
