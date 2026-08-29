package com.prashant.jwtAuth.controller;

import com.prashant.jwtAuth.dto.LoginRequest;
import com.prashant.jwtAuth.dto.RegisterRequest;
import com.prashant.jwtAuth.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<String> register(
            @Valid @RequestBody RegisterRequest request) {

        authService.register(request);

        return ResponseEntity.ok("User registered successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(
            @Valid @RequestBody LoginRequest request) {

        authService.login(request);

        return ResponseEntity.ok("Login successful");
    }
}