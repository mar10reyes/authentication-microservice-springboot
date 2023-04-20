package com.mar10reyes.authentication.dto;

import lombok.Data;

@Data
public class UserCredentialsDTO {
    
    private String username;
    private String password;
    private String mail;

}
