package com.dev.web.blogging.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/home")
    public String home() {
        return "home";
    }

    @GetMapping("/logout-success")
    public String logoutSuccess() {
        return "logout-success";
    }
}
