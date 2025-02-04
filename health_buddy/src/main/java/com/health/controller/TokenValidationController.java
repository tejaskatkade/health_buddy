package com.health.controller;


import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.health.security.JwtUtils;

import io.jsonwebtoken.Claims;

@RestController
@CrossOrigin
public class TokenValidationController {
    
    @Autowired
    private JwtUtils jwtUtils;

    @GetMapping("/validate-token")
    public ResponseEntity<?> validateToken(@RequestHeader("Authorization") String authorizationHeader) {
        try {
            // Extract the token from the "Bearer <token>" header
            if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authorization header missing or improperly formatted");
            }

            String token = authorizationHeader.substring(7);
            Claims claims = jwtUtils.validateJwtToken(token);
            
            // Check if the token is expired
            if (claims.getExpiration().before(new Date())) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token has expired");
            }

            // Retrieve user details
            String username = claims.getSubject();

            return ResponseEntity.ok("Token is valid for user: " + username);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid Token !!!");
        }
    }
}
