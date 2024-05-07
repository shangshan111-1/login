package com.example.loginapi.controller;

import com.example.loginapi.entity.User;
import com.example.loginapi.model.JwtAuthResponse;
import com.example.loginapi.model.LoginRequest;
import com.example.loginapi.service.impl.UserServiceImpl;
import com.example.loginapi.util.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final UserServiceImpl userService;
    private final JwtTokenProvider jwtTokenProvider;

    @Autowired
    public AuthController(UserServiceImpl userService, JwtTokenProvider jwtTokenProvider) {
        this.userService = userService;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @PostMapping("/login")
    public ResponseEntity<JwtAuthResponse> login(@RequestBody LoginRequest loginRequest) {
        User user = userService.login(loginRequest.getEmail(), loginRequest.getPassword());
        if (user != null) {
            String jwt = jwtTokenProvider.generateToken(user);
            return ResponseEntity.ok(new JwtAuthResponse(jwt, user.getEmail()));
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
}
