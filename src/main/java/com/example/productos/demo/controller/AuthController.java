package com.example.productos.demo.controller;


import org.springframework.security.core.Authentication;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.productos.demo.Jwt.JwtUtil;
import com.example.productos.demo.exception.ValidatedRequestException;
import com.example.productos.demo.models.User;
import com.example.productos.demo.service.IUserService;

import jakarta.annotation.security.PermitAll;
import lombok.RequiredArgsConstructor;

@RestController
@PermitAll
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

 
    
    private AuthenticationManager authenticationManager;

    private JwtUtil jwtUtil;



    public AuthController(JwtUtil jwtUtil, AuthenticationManager authenticationManager) {
        this.jwtUtil = jwtUtil;
        this.authenticationManager = authenticationManager;

    }

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password) {
        // Aquí deberías validar el usuario y contraseña contra tu base de datos o sistema
        // if ("user".equals(username) && "admin".equals(password)) {
        //     return jwtUtil.generateToken(username);
        // }

        try {
            Authentication auth = this.authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password)
            );

            
            List<String> roles = auth.getAuthorities().stream()
                .map(r -> r.getAuthority())
                .toList();
            //return jwtUtil.generateToken(username);
            return jwtUtil.generateToken(username,roles);
            
        } catch (Exception e) {
            System.err.println(e.getMessage());
            throw new ValidatedRequestException("Credenciales inválidas");
        }



    }

    


}
