package com.goodsoft.internship.gsservletjsp.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class UserRequest {
    private String id;
    private String login;
    private String password;
    private String name;
    private String birthdate;
    private String age;
    private String salary;
    private List<String> roles;
}
