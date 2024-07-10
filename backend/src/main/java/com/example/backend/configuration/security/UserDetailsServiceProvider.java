package com.example.backend.configuration.security;

import com.example.backend.repository.UserSecurityRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

@Component
public class UserDetailsServiceProvider {
    @Bean
    public UserDetailsService userDetailsService(UserSecurityRepository userSecurityRepository) {
        return username -> userSecurityRepository.findByUsername(username).orElseThrow();
    }
}
