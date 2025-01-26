package com.goodsoft.internship.gsservletjsp.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserRequest {
    private String id;
    private String login;
    private String password;
    private String email;
    private String surname;
    private String name;
    private String patronymic;
    private String birthdate;
    private String role;
}
