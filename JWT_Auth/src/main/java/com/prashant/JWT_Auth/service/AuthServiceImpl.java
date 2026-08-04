package com.prashant.JWT_Auth.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.prashant.JWT_Auth.dto.RegisterRequest;
import com.prashant.JWT_Auth.entity.User;
import com.prashant.JWT_Auth.exception.UsernameAlreadyExistsException;
import com.prashant.JWT_Auth.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void register(RegisterRequest request){

        if(userRepository.findByUsername(request.getUsername()).isPresent()){
            throw new UsernameAlreadyExistsException("Username already exists");
        }

        User user= new User();
        user.setUsername(request.getUsername());

        user.setPassword(
                passwordEncoder.encode(request.getPassword())
        );  

        user.setRole("USER");

        userRepository.save(user);
    }
}
