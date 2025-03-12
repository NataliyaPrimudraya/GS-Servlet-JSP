package com.goodsoft.internship.gsservletjsp.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationResponse {
    private Integer id;
    private String login;
    private List<String> roles;
    private String accessToken;
    private String refreshToken;
}