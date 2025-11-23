package com.example.productos.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.productos.demo.dto.UserRegisterDto;
import com.example.productos.demo.models.Role;
import com.example.productos.demo.models.User;
import com.example.productos.demo.models.UserRols;
import com.example.productos.demo.repository.IRoleRepository;
import com.example.productos.demo.repository.IUserRepository;
import com.example.productos.demo.repository.IUserRoleRepository;

import jakarta.annotation.security.PermitAll;
import lombok.RequiredArgsConstructor;


@RestController
@PermitAll
@RequestMapping("/auth")
@RequiredArgsConstructor
public class UserController {

    private IUserRepository userRepository;
    private IRoleRepository roleRepository;
    private IUserRoleRepository userRoleRepository;
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserRegisterDto dto) {

        if (userRepository.getByUserName(dto.getUsername()).isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("El usuario '" + dto.getUsername() + "' ya existe");
        }

        User newUser = User.builder()
                .username(dto.getUsername())
                .password(passwordEncoder.encode(dto.getPassword()))
                .enabled(true)
                .build();

        User savedUser = userRepository.save(newUser);

        String roleName = dto.getRole() != null && !dto.getRole().isEmpty() ? dto.getRole().toUpperCase() : "USER";

        Role role = roleRepository.findById(getRoleIdByName(roleName))
                .orElseThrow(() -> new RuntimeException("Rol no encontrado: " + roleName));

        UserRols userRole = UserRols.builder()
                .user(savedUser)
                .role(role)
                .build();

        userRoleRepository.save(userRole);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Usuario '" + savedUser.getUsername() + "' creado correctamente con rol " + roleName);
    }

    private Long getRoleIdByName(String roleName) {
        return roleRepository.findAll().stream()
                .filter(r -> r.getName().equalsIgnoreCase(roleName))
                .findFirst()
                .map(Role::getId)
                .orElseThrow(() -> new RuntimeException("Rol no encontrado: " + roleName));
    }
}