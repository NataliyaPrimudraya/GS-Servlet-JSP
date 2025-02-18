package com.goodsoft.internship.gsservletjsp.web.controller;

import com.goodsoft.internship.gsservletjsp.dto.UserDTO;
import com.goodsoft.internship.gsservletjsp.entity.User;
import com.goodsoft.internship.gsservletjsp.enumeration.Role;
import com.goodsoft.internship.gsservletjsp.service.UserService;
import com.goodsoft.internship.gsservletjsp.service.ValidationService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static com.goodsoft.internship.gsservletjsp.config.Constants.*;

@Controller
@RequestMapping("/loginedit.jhtml")
public class LoginEditController {

    @Autowired
    private UserService userService;
    @Autowired
    private ValidationService validationService;
    @Autowired
    private ConversionService conversionService;

    @GetMapping
    public String getLoginEditPage(@RequestParam(name = "id", required = false) Integer id,
                                   @RequestParam(name = "action", required = false) String action,
                                   Model model) {
        model.addAttribute("rolesList", Arrays.asList(Role.values()));

        if (Objects.equals(action, "add")) {
            model.addAttribute("user", new UserDTO());
        } else if (id != null && userService.findById(id) != null) {
            if (Objects.equals(action, "delete")) {
                userService.delete(id);
                return "redirect:" + USERS_LIST_PAGE + ".jhtml";
            } else {
                model.addAttribute("user", conversionService.convert(userService.findById(id), UserDTO.class));
            }
        }
        return LOGIN_EDIT_PAGE;
    }

    @PostMapping
    public String editLogin(@RequestParam(name = "id", required = false) int userId,
                            @ModelAttribute("userDTO") UserDTO dto,
                            Model model, HttpServletRequest req) {
        model.addAttribute("rolesList", Arrays.asList(Role.values()));

        List<String> errors = new ArrayList<>(validationService.validateUserRequest(req));
        if (!errors.isEmpty()) {
            model.addAttribute("errorMessages", errors);
            return LOGIN_EDIT_PAGE;
        } else {
            User user = conversionService.convert(dto, User.class);
            if (userId == 0) {
                userService.save(user);
            } else if (userService.findById(userId) != null) {
                userService.update(user);
            }
        }
        return "redirect:" + USERS_LIST_PAGE + ".jhtml";
    }
}
