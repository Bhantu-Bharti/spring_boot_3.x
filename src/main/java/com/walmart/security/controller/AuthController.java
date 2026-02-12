package com.walmart.security.controller;

import com.walmart.security.model.AuthRequest;
import com.walmart.security.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/token")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/auth")
    public String authenticate(@RequestBody AuthRequest authRequest){

        try{
            authenticationManager
                    .authenticate(
                            new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
            return jwtUtil
                    .generateToken(authRequest.getUsername());

        } catch (Exception e) {
            return "Authentication failed. Please check your credentials and try again.";
        }
    }
}
