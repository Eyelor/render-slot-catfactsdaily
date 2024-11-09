package com.example.catfactsdaily.controller;

import com.example.catfactsdaily.dto.UserDTO;
import com.example.catfactsdaily.entity.User;
import com.example.catfactsdaily.response.LoginResponse;
import com.example.catfactsdaily.response.SignupResponse;
import com.example.catfactsdaily.service.AuthenticationService;
import com.example.catfactsdaily.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RequestMapping("/auth")
@RestController
public class AuthenticationController {

    private final JwtService jwtService;

    private final AuthenticationService authenticationService;

    @Autowired
    public AuthenticationController(JwtService jwtService, AuthenticationService authenticationService) {
        this.jwtService = jwtService;
        this.authenticationService = authenticationService;
    }

    @PostMapping("/signup")
    public ResponseEntity<SignupResponse> register(@RequestBody UserDTO registerUserDto) {
        User registeredUser = authenticationService.signup(registerUserDto);

        SignupResponse signupResponse = new SignupResponse();
        signupResponse.setName(registeredUser.getName());

        return ResponseEntity.ok(signupResponse);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticate(@RequestBody UserDTO loginUserDto) {
        User authenticatedUser = authenticationService.authenticate(loginUserDto);

        String jwtToken = jwtService.generateToken(authenticatedUser, authenticatedUser.getUserId());

        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setName(authenticatedUser.getName());
        loginResponse.setToken(jwtToken);
        loginResponse.setExpiresIn(jwtService.getExpirationTime());
        return ResponseEntity.ok(loginResponse);
    }
}
