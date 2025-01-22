package com.goodsoft.internship.gsservletjsp.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class User {
    private String login;
    private String password;
}

