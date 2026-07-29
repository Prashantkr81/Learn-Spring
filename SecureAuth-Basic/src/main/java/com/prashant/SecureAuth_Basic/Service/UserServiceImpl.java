package com.prashant.SecureAuth_Basic.Service;

import com.prashant.SecureAuth_Basic.Entity.User;
import com.prashant.SecureAuth_Basic.Repository.UserRepository;
import com.prashant.SecureAuth_Basic.dto.RegisterRequest;
import com.prashant.SecureAuth_Basic.exception.UsernameAlreadyExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void register(RegisterRequest request){

        if(repository.findByUsername(request.getUsername()).isPresent()){
            throw new UsernameAlreadyExistsException("Username already exists");
        }

        User user= User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .role("USER")
                .enabled(true)
                .build();

        repository.save(user);
    }
}
