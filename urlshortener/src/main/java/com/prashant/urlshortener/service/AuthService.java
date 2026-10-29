package com.prashant.urlshortener.service;

import com.prashant.urlshortener.dto.LoginRequest;
import com.prashant.urlshortener.dto.LoginResponse;
import com.prashant.urlshortener.entity.User;
import com.prashant.urlshortener.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public LoginResponse login(LoginRequest request) {

        User user= userRepository
                .findByEmail(request.getEmail().trim().toLowerCase())
                .orElseThrow(()-> new ResponseStatusException(
                        HttpStatus.UNAUTHORIZED,
                        "Invalid email or password"
                ));

        if(!passwordEncoder.matches(
                request.getPassword(), user.getPasswordHash()
        )){
            throw new ResponseStatusException(
                    HttpStatus.UNAUTHORIZED,
                    "Invalid password"
            );
        }

        String token= jwtService.generateToken(user.getEmail());

        return new LoginResponse(
                token,
                "Bearer",
                3600
        );
    }
}
