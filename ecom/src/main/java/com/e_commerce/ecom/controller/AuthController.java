package com.e_commerce.ecom.controller;


import com.e_commerce.ecom.domain.dto.AuthResponseDTO;
import com.e_commerce.ecom.domain.dto.UserDTO;
import com.e_commerce.ecom.service.AuthServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    @Autowired
    private AuthServiceImpl authService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@Valid @RequestBody UserDTO registerRequest) {
        authService.register(registerRequest);
        return ResponseEntity.ok("User registered successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@Valid @RequestBody UserDTO authCredentials) {
        AuthResponseDTO authResponse = authService.authenticate(authCredentials);
        return ResponseEntity.ok(authResponse);
    }
}
