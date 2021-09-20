package com.kazakova.clothesweather.controller;

import org.springframework.stereotype.Controller;

@Controller
public class LoginController {

    public String helloPage() {
        return "/";
    }

    public String homePage() {
        return "home";
    }

    public String login() {
        return "login";
    }

    public String createAccount() {
        return "create-account";
    }

}
