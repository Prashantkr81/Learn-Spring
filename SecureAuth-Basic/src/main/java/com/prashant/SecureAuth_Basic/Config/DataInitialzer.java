package com.prashant.SecureAuth_Basic.Config;

import com.prashant.SecureAuth_Basic.Entity.User;
import com.prashant.SecureAuth_Basic.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitialzer implements CommandLineRunner {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args){

        if(repository.findByUsername("admin").isEmpty()){

            User admin = User.builder()
                    .username("admin")
                    .password(passwordEncoder.encode("admin123"))
                    .role("ADMIN")
                    .build();

            repository.save(admin);

            System.out.println("Admin User Created");

        }
    }
}
