package com.e_commerce.ecom.domain.dto;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDTO {
    private String username;
    private String email;

    @JsonIgnore
    private String password;
}
