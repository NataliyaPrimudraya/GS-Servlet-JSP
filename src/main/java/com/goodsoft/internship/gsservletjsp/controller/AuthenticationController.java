package com.goodsoft.internship.gsservletjsp.controller;

import com.goodsoft.internship.gsservletjsp.auth.AuthenticationRequest;
import com.goodsoft.internship.gsservletjsp.auth.AuthenticationResponse;
import com.goodsoft.internship.gsservletjsp.auth.AuthenticationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin("http://localhost:4200")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@Valid @RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }

}
