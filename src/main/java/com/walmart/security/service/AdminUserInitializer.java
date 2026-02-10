package com.walmart.security.service;

import com.walmart.security.model.Users;
import com.walmart.security.repository.UserDetailsRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class AdminUserInitializer {
    @Bean
    public CommandLineRunner createAdminUser(UserDetailsRepository userDetailsRepository, PasswordEncoder passwordEncoder){
        return args -> {
            // Fix: Only create admin user if it does NOT exist
            if(userDetailsRepository.findByUsername("admin").isEmpty()){
                Users user = new Users();
                user.setUsername("admin");
                user.setPassword(passwordEncoder.encode("admin"));
                user.setRole("ROLE_ADMIN");
                userDetailsRepository.save(user);
                System.out.println("Successfully user saved.");
            }
        };
    }
}
