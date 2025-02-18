package com.goodsoft.internship.gsservletjsp.dto;

import com.goodsoft.internship.gsservletjsp.enumeration.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private Integer id;
    private String login;
    private String password;
    private String name;
    private Date birthdate;
    private Integer age;
    private BigDecimal salary;
    private List<Role> roles;
}
