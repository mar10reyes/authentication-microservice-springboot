package com.mar10reyes.authentication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mar10reyes.authentication.entity.UserCredentials;

@Repository
public interface UserCredentialsRepository extends JpaRepository<UserCredentials, Long>{
    
    boolean existsByUsername(String username);
    boolean existsByUsernameAndPassword(String username, String password);

}
