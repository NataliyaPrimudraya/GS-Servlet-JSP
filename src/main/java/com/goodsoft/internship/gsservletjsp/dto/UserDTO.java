package com.goodsoft.internship.gsservletjsp.dto;

import com.goodsoft.internship.gsservletjsp.enumeration.Role;
import jakarta.validation.constraints.*;
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
    @NotNull(groups = Marker.OnUpdate.class)
    private Integer id;

    @NotBlank
    @Size(min = 3, max = 20)
    private String login;

    @NotBlank
    @Size(min = 8, max = 64)
    private String password;

    @NotBlank
    private String name;

    private Date birthdate;

    @NotNull
    @Min(19)
    private Integer age;

    @NotNull
    @DecimalMin(value = "726.00")
    @Digits(integer = 8, fraction = 2)
    private BigDecimal salary;

    @NotEmpty
    private List<Role> roles;
}
