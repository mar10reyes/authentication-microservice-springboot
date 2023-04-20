package com.mar10reyes.authentication.service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mar10reyes.authentication.dto.UserCredentialsDTO;
import com.mar10reyes.authentication.entity.UserCredentials;
import com.mar10reyes.authentication.repository.UserCredentialsRepository;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class UserAuthenticationService {
    
    @Autowired
    private UserCredentialsRepository userCredentialsRepository;

    private final long EXPIRATION_IN_MILIS = 1000*60*60;

    public UserAuthenticationService() {}

    public boolean registerUserCredentials(UserCredentialsDTO userCredentialsDTO) {
        
        try {
            UserCredentials userCredentials = new UserCredentials(userCredentialsDTO);
            if(userCredentialsRepository.existsByUsername(userCredentialsDTO.getUsername())) return false;
            userCredentialsRepository.save(userCredentials);
            return true;
        } catch (Exception e) {
            System.out.println("ex: "+e.getMessage());
            return false;
        }
    }

    public String login(UserCredentialsDTO userCredentialsDTO) {
        try {
            if(userCredentialsRepository.existsByUsernameAndPassword(userCredentialsDTO.getUsername(), userCredentialsDTO.getPassword())){
                return getToken(userCredentialsDTO); 
            } else {
                return null;
            }
        } catch (Exception e) {
            System.out.println("ex: "+e.getMessage());
            return null;
        }
    }

    private String getToken(UserCredentialsDTO userCredentials) {
        
        Map<String, Object> claims = new HashMap<>();

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userCredentials.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + this.EXPIRATION_IN_MILIS))
                .signWith(getSignKey(), SignatureAlgorithm.HS256).compact();
    }

    private Key getSignKey() {
        byte[] key =  Decoders.BASE64.decode("5A7234753778214125442A472D4A614E645267556B58703273357638792F423F");
        return Keys.hmacShaKeyFor(key);
    }

}
