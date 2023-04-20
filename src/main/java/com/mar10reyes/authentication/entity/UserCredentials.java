package com.mar10reyes.authentication.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.mar10reyes.authentication.dto.UserCredentialsDTO;

import lombok.Data;

@Entity
@Data
public class UserCredentials {

    public UserCredentials(UserCredentialsDTO userCredentialsDTO) {
        this.username = userCredentialsDTO.getUsername();
        this.password = userCredentialsDTO.getPassword();
        this.mail     = userCredentialsDTO.getMail();
    }

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;
    private String username;
    private String password;
    private String mail;
}
