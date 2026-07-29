package com.prashant.SecureAuth_Basic.Controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PublicController {

    @GetMapping("/")
    public String home() {

        return "Welcome to SecureAuth Basic";
    }
}