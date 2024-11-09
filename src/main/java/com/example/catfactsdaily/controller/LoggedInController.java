package com.example.catfactsdaily.controller;

import com.example.catfactsdaily.service.JwtService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@CrossOrigin
@RequestMapping("/logged-in")
@SecurityRequirement(name = "bearerAuth")
public class LoggedInController {

    private final JwtService jwtService;

    @Autowired
    public LoggedInController(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(@RequestHeader("Authorization") String token, Principal principal) {
        String cleanedToken = token.replace("Bearer ", "");
        Integer userIdFromToken = jwtService.extractUserIdFromPrincipal(principal);

        if (!jwtService.extractUserId(cleanedToken).equals(userIdFromToken)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized logout attempt.");
        }

        jwtService.invalidateToken(cleanedToken);

        return ResponseEntity.ok("User logged out successfully.");
    }
}
