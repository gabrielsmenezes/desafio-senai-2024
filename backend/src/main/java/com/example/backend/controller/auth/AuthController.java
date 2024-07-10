package com.example.backend.controller.auth;

import com.example.backend.service.TokenService;
import com.example.backend.service.auth.GetAllUsers;
import com.example.backend.service.auth.InsertUser;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/users")
public class AuthController {

    private final TokenService tokenService;
    private final AuthenticationManager authenticationManager;
    private final InsertUser insertUser;
    private final GetAllUsers getAllUsers;

    public AuthController(TokenService tokenService, AuthenticationManager authenticationManager, InsertUser insertUser, GetAllUsers getAllUsers) {
        this.tokenService = tokenService;
        this.authenticationManager = authenticationManager;
        this.insertUser = insertUser;
        this.getAllUsers = getAllUsers;
    }

    @PostMapping("/token")
    public String token(@RequestBody LoginRequest userLogin) throws AuthenticationException {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userLogin.username(), userLogin.password()));
        return tokenService.generateToken(authentication);
    }

    @PostMapping
    public ResponseEntity<UserDTO> insertUser(@RequestBody UserDTO userDTO) {
        UserDTO savedUser = this.insertUser.execute(userDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId()).toUri();
        return ResponseEntity.created(uri).body(savedUser);
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAll() {
        return ResponseEntity.ok(this.getAllUsers.execute());
    }

}
