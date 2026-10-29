package com.prashant.urlshortener.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


public class PasswordHashGenerator {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        String password = "Password123";
        System.out.println(encoder.encode(password));
    }
}

