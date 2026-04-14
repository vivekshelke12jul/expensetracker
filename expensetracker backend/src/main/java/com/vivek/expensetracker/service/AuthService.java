package com.vivek.expensetracker.service;


import com.vivek.expensetracker.exchange.request.LoginRequest;
import com.vivek.expensetracker.exchange.request.RegisterRequest;
import com.vivek.expensetracker.exchange.response.AuthResponse;
import com.vivek.expensetracker.model.AppUser;
import com.vivek.expensetracker.model.Role;
import com.vivek.expensetracker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserRepository userRepository;

    @Autowired
    JwtService jwtService;

    @Autowired
    AuthenticationManager authenticationManager;

    public AuthResponse register(RegisterRequest request) {


        AppUser user = AppUser.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();

        userRepository.save(user);

        String jwtToken = jwtService.generateToken(user.getUsername());
        userRepository.save(user);
        return AuthResponse.builder()
                .accessToken(jwtToken)
                .build();

    }

    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        String jwtToken = jwtService.generateToken(request.getUsername());
        return AuthResponse.builder()
                .accessToken(jwtToken)
                .build();
    }
}
