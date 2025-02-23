package com.goodsoft.internship.gsservletjsp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import static com.goodsoft.internship.gsservletjsp.config.Constants.LOGIN_PAGE;

@Controller
@RequestMapping("/login.jhtml")
public class LoginController {

    @GetMapping
    public String login() {
        return LOGIN_PAGE;
    }

}
