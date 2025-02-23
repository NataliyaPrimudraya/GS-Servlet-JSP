package com.goodsoft.internship.gsservletjsp.controller;

import com.goodsoft.internship.gsservletjsp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import static com.goodsoft.internship.gsservletjsp.config.Constants.USERS_LIST_PAGE;

@Controller
@RequestMapping("/userslist.jhtml")
public class UsersController {

    @Autowired
    private UserService userService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping
    public String listUsers(Model model) {
        model.addAttribute("users", userService.findAll());
        return USERS_LIST_PAGE;
    }

}
