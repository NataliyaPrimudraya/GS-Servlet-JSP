package com.goodsoft.internship.gsservletjsp.service;

import com.goodsoft.internship.gsservletjsp.entity.User;
import com.goodsoft.internship.gsservletjsp.enumeration.Role;
import jakarta.servlet.http.HttpServletRequest;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ValidityService {

    private static final String EMAIL_PATTERN = "^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$";
    private static final ValidityService validityService = new ValidityService();
    private String errorMessage = "Неверные данные";

    private ValidityService() {}

    public static ValidityService getInstance() {
        return validityService;
    }

    public boolean isUserRequestValid(HttpServletRequest req) {

        Map<String, String> params = new HashMap<>();
        params.put("login", req.getParameter("login"));
        params.put("password", req.getParameter("password"));
        params.put("email", req.getParameter("email"));
        params.put("surname", req.getParameter("surname"));
        params.put("name", req.getParameter("name"));
        params.put("patronymic", req.getParameter("patronymic"));
        params.put("birthdate", req.getParameter("birthdate"));
        params.put("role", req.getParameter("role"));

        for(String param : params.values())
            if (param.isEmpty()) {
                errorMessage = "Все поля должны быть заполнены";
                return false;
            }

        if(!params.get("email").matches(EMAIL_PATTERN)){
            errorMessage = "Неверный формат email";
            return false;
        }

        LocalDate birthdate;
        try{
            birthdate = LocalDate.parse(params.get("birthdate"), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        } catch(DateTimeParseException e){
            errorMessage = "Неверный формат даты рождения";
            return false;
        }

        if(!birthdate.isBefore(LocalDate.now())){
            errorMessage = "Неверное значение даты рождения";
            return false;
        }

        try {
            Role.valueOf(params.get("role"));
        } catch(IllegalArgumentException e){
            errorMessage = "Такой роли не существует";
            return false;
        }

        return true;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

}
