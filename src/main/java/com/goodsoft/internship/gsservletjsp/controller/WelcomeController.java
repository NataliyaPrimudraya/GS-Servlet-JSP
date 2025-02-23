package com.goodsoft.internship.gsservletjsp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import static com.goodsoft.internship.gsservletjsp.config.Constants.*;

@Controller
@RequestMapping("/welcome.jhtml")
public class WelcomeController {

    @GetMapping
    public String welcome() {
        return WELCOME_PAGE;
    }
}
