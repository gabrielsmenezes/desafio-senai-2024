package com.example.backend.service.auth;

import com.example.backend.controller.auth.UserDTO;
import com.example.backend.domain.UserSecurity;
import com.example.backend.repository.UserSecurityRepository;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class InsertUser {
    private final UserSecurityRepository repository;
    private final ModelMapper mapper;
    private final PasswordEncoder passwordEncoder;

    public InsertUser(UserSecurityRepository repository, ModelMapper mapper, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.mapper = mapper;
        this.passwordEncoder = passwordEncoder;
    }

    public UserDTO execute(UserDTO userDTO) {
        UserSecurity userSecurity = this.mapper.map(userDTO, UserSecurity.class);
        userSecurity.setPassword(this.passwordEncoder.encode(userSecurity.getPassword()));
        userSecurity = this.repository.save(userSecurity);
        return this.mapper.map(userSecurity, UserDTO.class);
    }
}
