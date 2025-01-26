package com.e_commerce.ecom.service.impl;

import com.e_commerce.ecom.domain.dto.UserDTO;
import com.e_commerce.ecom.domain.entity.User;
import com.e_commerce.ecom.repository.UserRepository;
import com.e_commerce.ecom.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public UserDTO registerUser(UserDTO userDTO) {
        if(userRepository.findByEmail(userDTO.getEmail()).isPresent()) {
            throw new RuntimeException("Email already exists");
        }

        User user = User.builder()
                .username(userDTO.getUsername())
                .email(userDTO.getEmail())
                .password(passwordEncoder.encode(userDTO.getPassword()))
                .roles(Collections.singleton("ROLE_USER"))
                .build();

        userRepository.save(user);

        userDTO.setPassword(null);
        return userDTO;

    }

    @Override
    public UserDTO getUserByUsername(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return mapToDTO(user);
    }

    private UserDTO mapToDTO(User user) {
        return UserDTO.builder()
                .username(user.getUsername())
                .email(user.getEmail())
                .build();
    }

}
