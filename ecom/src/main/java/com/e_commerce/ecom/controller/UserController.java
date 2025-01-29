package com.e_commerce.ecom.controller;


import com.e_commerce.ecom.domain.dto.UserDTO;
import com.e_commerce.ecom.domain.entity.User;
import com.e_commerce.ecom.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{username}")
    public ResponseEntity<UserDTO> getUserByUsername (@PathVariable String username) {
        User user = userService.getUserByUsername(username);
        return ResponseEntity.ok(userService.mapToDTO(user));
    }


}
