package com.example.backend.service.auth;

import com.example.backend.controller.auth.UserDTO;
import com.example.backend.repository.UserSecurityRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetAllUsers {
    private final UserSecurityRepository userSecurityRepository;
    private final ModelMapper mapper;

    public GetAllUsers(UserSecurityRepository userSecurityRepository, ModelMapper mapper) {
        this.userSecurityRepository = userSecurityRepository;
        this.mapper = mapper;
    }

    public List<UserDTO> execute() {
        return this.userSecurityRepository.findAll().stream().map(userSecurity -> this.mapper.map(userSecurity, UserDTO.class)).toList();
    }
}
