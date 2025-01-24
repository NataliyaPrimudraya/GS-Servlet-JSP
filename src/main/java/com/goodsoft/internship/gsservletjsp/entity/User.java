package com.goodsoft.internship.gsservletjsp.entity;

import com.goodsoft.internship.gsservletjsp.enumeration.Role;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class User {
    private int id;
    private String login;
    private String password;
    private String email;
    private String surname;
    private String name;
    private String patronymic;
    private LocalDate birthdate;
    private Role role;
}

