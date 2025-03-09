package com.goodsoft.internship.gsservletjsp.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class AuthenticationService {

    @Autowired
    private MyUserDetailsService userDetailsService;

    public AuthenticationResponse authenticate(AuthenticationRequest request){
        MyUserDetails userDetails = userDetailsService.loadUserByUsername(request.getLogin());
        return AuthenticationResponse.builder()
                .id(userDetails.getUser().getId())
                .login(userDetails.getUsername())
                .roles(userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                .build();
    }

}
