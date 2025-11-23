package com.example.productos.demo.dto;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRegisterDto {
    @Size(min = 4, max = 20, message = "El username debe tener entre 4 y 20 caracteres")
    private String username;

    @Size(min = 6, message = "La contraseña debe tener al menos 6 caracteres")
    private String password;

    private String role = "USER";
}
