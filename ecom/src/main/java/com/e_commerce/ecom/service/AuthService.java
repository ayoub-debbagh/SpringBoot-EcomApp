package com.e_commerce.ecom.service;

import com.e_commerce.ecom.domain.dto.AuthResponseDTO;
import com.e_commerce.ecom.domain.dto.UserDTO;

public interface AuthService {
    void register(UserDTO userDTO);
    AuthResponseDTO authenticate(UserDTO userDTO);
}
