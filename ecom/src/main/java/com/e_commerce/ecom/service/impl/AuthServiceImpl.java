package com.e_commerce.ecom.service;


import com.e_commerce.ecom.domain.dto.AuthResponseDTO;
import com.e_commerce.ecom.domain.dto.UserDTO;
import com.e_commerce.ecom.domain.entity.User;
import com.e_commerce.ecom.repository.UserRepository;
import com.e_commerce.ecom.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtTokenUtil;


    public void register (UserDTO registerRequest) {
        String username = registerRequest.getUsername();
        if(userRepository.existsByUsername(username)) {
            throw new IllegalArgumentException("Username is already in use");
        }

        if (userRepository.existsByEmail(registerRequest.getEmail())) {
            throw new IllegalArgumentException("Email is taken");
        }

        User user = new User();
        user.setUsername(username);
        user.setEmail(registerRequest.getEmail());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setRoles(Set.of("ROLE_USER"));

        userRepository.save(user);
    }

    public AuthResponseDTO authenticate(UserDTO authCredentials) {
        User user = userRepository.findByUsername(authCredentials.getUsername())
                .orElseThrow(() -> new IllegalArgumentException("Invalid Username or Password"));

        if (!passwordEncoder.matches(authCredentials.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("Invalid username or password");
        }

        String token = jwtTokenUtil.generateToken(user.getUsername());
        return new AuthResponseDTO(token);

    }
}