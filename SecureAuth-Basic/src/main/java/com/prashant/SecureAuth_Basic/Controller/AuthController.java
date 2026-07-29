package com.prashant.SecureAuth_Basic.Controller;

import com.prashant.SecureAuth_Basic.Service.UserService;
import com.prashant.SecureAuth_Basic.dto.RegisterRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final UserService service;

    @PostMapping("/register")
    public ResponseEntity<String> register(
            @Valid @RequestBody RegisterRequest request
            ) {
        service.register(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body("User Registered successfully");
    }
}
