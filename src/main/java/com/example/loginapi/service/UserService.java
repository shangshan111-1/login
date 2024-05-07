package com.example.loginapi.service;

import com.example.loginapi.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User register(     String email,
                       String password );
    User login(String email, String password);
    User getById(Long id);
}
