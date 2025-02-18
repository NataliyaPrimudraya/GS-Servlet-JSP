package com.goodsoft.internship.gsservletjsp.web.controller;

import com.goodsoft.internship.gsservletjsp.dto.Marker;
import com.goodsoft.internship.gsservletjsp.dto.UserDTO;
import com.goodsoft.internship.gsservletjsp.entity.User;
import com.goodsoft.internship.gsservletjsp.enumeration.Role;
import com.goodsoft.internship.gsservletjsp.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Objects;

import static com.goodsoft.internship.gsservletjsp.config.Constants.*;

@Controller
@RequestMapping("/loginedit.jhtml")
public class LoginEditController {

    @Autowired
    private UserService userService;
    @Autowired
    private ConversionService conversionService;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    @GetMapping
    public String getLoginEditPage(@RequestParam(name = "id", required = false) Integer id,
                                   @RequestParam(name = "action", required = false) String action,
                                   Model model) {
        model.addAttribute("rolesList", Arrays.asList(Role.values()));

        if (Objects.equals(action, "add")) {
            model.addAttribute("user", new UserDTO());
            return LOGIN_EDIT_PAGE;
        } else if (id != null && userService.findById(id) != null) {
            if (Objects.equals(action, "delete")) {
                userService.delete(id);
                return "redirect:" + USERS_LIST_PAGE + ".jhtml";
            } else {
                model.addAttribute("user", conversionService.convert(userService.findById(id), UserDTO.class));
                return LOGIN_EDIT_PAGE;
            }
        } else return "redirect:" + USERS_LIST_PAGE + ".jhtml";
    }

    @Validated(Marker.OnUpdate.class)
    @PostMapping
    public String editLogin(@ModelAttribute("user") @Valid UserDTO userDTO,
                            BindingResult bindingResult, Model model) {
        model.addAttribute("rolesList", Arrays.asList(Role.values()));
        if (bindingResult.hasErrors()) {
            return LOGIN_EDIT_PAGE;
        } else {
            User user = conversionService.convert(userDTO, User.class);
            if (userService.findById(userDTO.getId()) != null) {
                userService.update(user);
            }
            return "redirect:" + USERS_LIST_PAGE + ".jhtml";
        }
    }

    @Validated(Marker.OnCreate.class)
    @PostMapping(params = "id==''")
    public String addLogin(@ModelAttribute("user") @Valid UserDTO userDTO,
                           BindingResult bindingResult, Model model) {
        model.addAttribute("rolesList", Arrays.asList(Role.values()));
        if (bindingResult.hasErrors()) {
            return LOGIN_EDIT_PAGE;
        } else {
            User user = conversionService.convert(userDTO, User.class);
            userService.save(user);
            return "redirect:" + USERS_LIST_PAGE + ".jhtml";
        }
    }
}
