package com.goodsoft.internship.gsservletjsp.web.controller;

import com.goodsoft.internship.gsservletjsp.entity.User;
import com.goodsoft.internship.gsservletjsp.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static com.goodsoft.internship.gsservletjsp.config.Constants.*;

@Controller
@RequestMapping("/login.jhtml")
public class LoginController {

    @Autowired
    private UserService userService;
    private static final String ACTION_PARAM = "action";
    private static final String LOGIN_ACTION_PERFORM = "login";

    @GetMapping
    public String login() {
        return LOGIN_PAGE;
    }

    @PostMapping
    public String login(@RequestParam(name = ACTION_PARAM, required = false) String action,
                        @RequestParam(name = "login", required = false) String login,
                        @RequestParam(name = "password", required = false) String password,
                        Model model, HttpServletRequest req) {
        if (LOGIN_ACTION_PERFORM.equals(action)) {
            User user = userService.findByLoginAndPassword(login, password);
            if (user != null) {
                req.getSession().setAttribute(USER_INFO_KEY, user);
                return "redirect:" + WELCOME_PAGE + ".jhtml";
            } else {
                model.addAttribute("errorMessage", "Неверный логин или пароль");
            }
        }
        return LOGIN_PAGE;
    }
}
