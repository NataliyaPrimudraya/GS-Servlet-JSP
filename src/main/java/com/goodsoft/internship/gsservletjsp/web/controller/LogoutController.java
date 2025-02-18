package com.goodsoft.internship.gsservletjsp.web.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import static com.goodsoft.internship.gsservletjsp.config.Constants.LOGIN_PAGE;

@Controller
@RequestMapping("/logout.jhtml")
public class LogoutController {

    @GetMapping
    public String logout(HttpServletRequest req) {
        HttpSession session = req.getSession();
        session.invalidate();
        return "redirect:" + LOGIN_PAGE + ".jhtml";
    }
}
