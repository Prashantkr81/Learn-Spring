package com.prashant.JWT_Auth.service;

import com.prashant.JWT_Auth.dto.RegisterRequest;
import com.prashant.JWT_Auth.entity.User;
import com.prashant.JWT_Auth.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class AuthServiceImplTest {

    @Test
    void registerWithExistingUsernameShouldThrowConflict() {
        UserRepository userRepository = mock(UserRepository.class);
        PasswordEncoder passwordEncoder = mock(PasswordEncoder.class);
        AuthServiceImpl authService = new AuthServiceImpl(userRepository, passwordEncoder);

        RegisterRequest request = new RegisterRequest();
        request.setUsername("existingUser");
        request.setPassword("secret");

        when(userRepository.findByUsername("existingUser")).thenReturn(Optional.of(new User()));

        UsernameAlreadyExistsException exception = assertThrows(UsernameAlreadyExistsException.class,
                () -> authService.register(request));

        verify(userRepository, never()).save(any(User.class));
    }
}
