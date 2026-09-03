package com.prashant.jwtAuth.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @GetMapping("/user/profile")
    public String profile(Authentication authentication) {

        return "Welcome " + authentication.getName();
    }
}