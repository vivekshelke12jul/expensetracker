package com.vivek.expensetracker.controller;

import com.vivek.expensetracker.exchange.request.LoginRequest;
import com.vivek.expensetracker.exchange.request.RegisterRequest;
import com.vivek.expensetracker.exchange.response.AuthResponse;
import com.vivek.expensetracker.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public AuthResponse register(@RequestBody RegisterRequest request) {
        AuthResponse register = authService.register(request);
        System.out.println("Register response: " + register.getAccessToken());
        return register;
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody LoginRequest request) {
        return authService.login(request);
    }
}
