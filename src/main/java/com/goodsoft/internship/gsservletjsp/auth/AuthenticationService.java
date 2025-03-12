package com.goodsoft.internship.gsservletjsp.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.goodsoft.internship.gsservletjsp.auth.dto.*;
import com.goodsoft.internship.gsservletjsp.auth.jwt.JwtService;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final MyUserDetailsService userDetailsService;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getLogin(),
                        request.getPassword())
        );
        var userDetails = userDetailsService.loadUserByUsername(request.getLogin());
        var accessToken = jwtService.generateAccessToken(userDetails);
        var refreshToken = jwtService.generateRefreshToken(userDetails);
        return AuthenticationResponse.builder()
                .id(userDetails.getUser().getId())
                .login(userDetails.getUsername())
                .roles(userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    @SneakyThrows
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) {
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        final String refreshToken;
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return;
        }
        refreshToken = authHeader.substring(7);
        String username = null;
        try {
            username = jwtService.extractUsername(refreshToken);
        } catch (ExpiredJwtException e) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
        }
        if (username != null) {
            var userDetails = userDetailsService.loadUserByUsername(username);
            if (jwtService.isTokenValid(refreshToken, userDetails)) {
                var accessToken = jwtService.generateAccessToken(userDetails);
                var refreshTokenResponse = new RefreshTokenResponse(accessToken);
                response.setContentType("application/json");
                new ObjectMapper().writeValue(response.getOutputStream(), refreshTokenResponse);
            }
        }
    }

}
