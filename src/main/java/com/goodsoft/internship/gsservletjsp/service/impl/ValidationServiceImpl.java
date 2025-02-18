package com.goodsoft.internship.gsservletjsp.service.impl;

import com.goodsoft.internship.gsservletjsp.enumeration.Role;
import com.goodsoft.internship.gsservletjsp.service.UserService;
import com.goodsoft.internship.gsservletjsp.service.ValidationService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
public class ValidationServiceImpl implements ValidationService {

    @Autowired
    private UserService userService;

    @Override
    public List<String> validateUserRequest(HttpServletRequest req) {
        List<String> errorMessages = new ArrayList<>();

        if (Objects.equals(req.getParameter("login"), "")) {
            errorMessages.add("Введите логин");
        } else {
            int userId = 0;
            if (!Objects.equals(req.getParameter("id"), "")) userId = Integer.parseInt(req.getParameter("id"));
            if (userService.isLoginTaken(userId, req.getParameter("login"))) errorMessages.add("Логин занят");
        }

        if (Objects.equals(req.getParameter("password"), "")) {
            errorMessages.add("Введите пароль");
        }

        if (Objects.equals(req.getParameter("name"), "")) {
            errorMessages.add("Введите имя");
        }

        if (Objects.equals(req.getParameter("birthdate"), "")) {
            errorMessages.add("Введите дату рождения");
        } else {
            LocalDate birthdate = null;
            try {
                birthdate = LocalDate.parse(req.getParameter("birthdate"), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            } catch (DateTimeParseException e) {
                errorMessages.add("Неверный формат даты рождения");
            }
            if (birthdate != null && !birthdate.isBefore(LocalDate.now()))
                errorMessages.add("Неверное значение даты рождения");
        }

        if (Objects.equals(req.getParameter("age"), "")) {
            errorMessages.add("Введите возраст");
        } else {
            try {
                if (Integer.parseInt(req.getParameter("age")) <= 18)
                    errorMessages.add("Возраст должен быть больше 18");
            } catch (NumberFormatException e) {
                errorMessages.add("Неверное значение возраста");
            }
        }

        if (Objects.equals(req.getParameter("salary"), "")) {
            errorMessages.add("Введите зарплату");
        }

        try {
            List<String> roles = Arrays.asList(req.getParameterValues("roles"));
            roles.forEach(r -> Role.valueOf(r.toUpperCase()));
        } catch (NullPointerException e) {
            errorMessages.add("Выберите роль");
        } catch (IllegalArgumentException e) {
            errorMessages.add("Такой роли не существует");
        }

        return errorMessages;
    }
}
