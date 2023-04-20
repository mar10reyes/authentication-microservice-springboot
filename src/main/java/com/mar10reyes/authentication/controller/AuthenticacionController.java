package com.mar10reyes.authentication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mar10reyes.authentication.dto.AuthenticationResponse;
import com.mar10reyes.authentication.dto.UserCredentialsDTO;
import com.mar10reyes.authentication.service.UserAuthenticationService;

@RestController
@RequestMapping("api/v1/authentication")
public class AuthenticacionController {

    @Autowired
    private UserAuthenticationService userAuthenticationService;
    
    public AuthenticacionController() {

    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserCredentialsDTO userCredentials) {
        boolean wasCreated = userAuthenticationService.registerUserCredentials(userCredentials);
        HttpStatus responseStatus = HttpStatus.CREATED;
        if(!wasCreated) {
            responseStatus = HttpStatus.BAD_REQUEST;
        }

        return new ResponseEntity<String>("", responseStatus);
    }

    @GetMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody UserCredentialsDTO userCredentials) {
        
        AuthenticationResponse response = new AuthenticationResponse();
        
        String token = userAuthenticationService.login(userCredentials);
        response.setToken(token);

        boolean wasSuccessfull = token == null || token.isEmpty() ? false : true;
        response.setWasSuccessfull(wasSuccessfull);
        
        return new ResponseEntity<AuthenticationResponse>(response, HttpStatus.OK);
    }

}
