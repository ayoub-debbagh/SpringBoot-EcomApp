package com.e_commerce.ecom.service;


import com.e_commerce.ecom.domain.dto.UserDTO;

public interface UserService {
    UserDTO registerUser(UserDTO userDTO);
    UserDTO getUserByUsername(String username);
}
