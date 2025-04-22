package com.bankily.point.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bankily.point.payload.JWTAuthResponse;
import com.bankily.point.payload.LoginDto;
import com.bankily.point.payload.RegisterDto;
import com.bankily.point.service.AuthService;


//@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:4200"})
@CrossOrigin(origins = { "http://localhost:3000", "http://localhost:2024"}, allowCredentials = "true" ,allowedHeaders = "**" , exposedHeaders = "token")
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    // Build Login REST API
    @PostMapping(value = {"/login", "/signin"})
    public ResponseEntity<JWTAuthResponse> login(@RequestBody LoginDto loginDto){
        String token = authService.login(loginDto);

        JWTAuthResponse jwtAuthResponse = new JWTAuthResponse();
        jwtAuthResponse.setAccessToken(token);

        return ResponseEntity.ok(jwtAuthResponse);
    }

    // Build Register REST API
    @PostMapping(value = {"/register", "/signup"})
    public ResponseEntity<String> register(@RequestBody RegisterDto registerDto){
        String response = authService.register(registerDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    
    // Build Register REST API
    @GetMapping(value = {"/existuser", "/existuser"})
    public ResponseEntity<Boolean> users(){
        boolean response = authService.allUsers().size() > 0;
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}