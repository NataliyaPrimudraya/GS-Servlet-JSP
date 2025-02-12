package com.goodsoft.internship.gsservletjsp.entity;

import com.goodsoft.internship.gsservletjsp.enumeration.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private int id;
    private String login;
    private String password;
    private String name;
    private Date birthdate;
    private int age;
    private BigDecimal salary;
    private List<Role> roles;
}

