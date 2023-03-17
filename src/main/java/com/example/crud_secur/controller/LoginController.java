package com.example.crud_secur.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/user")
    public String user() {
        return "user";
    }

    @GetMapping("/admin")
    public String admin() {
        return "admin";
    }
}
