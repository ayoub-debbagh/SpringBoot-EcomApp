package com.e_commerce.ecom.service;


import com.e_commerce.ecom.domain.dto.UserDTO;
import com.e_commerce.ecom.domain.entity.User;

public interface UserService {

    User getUserByUsername(String username);

    UserDTO mapToDTO(User user);
}
