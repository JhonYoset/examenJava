package com.example.productos.demo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CategoriaRequestDto {
    @NotBlank(message = "El nombre de la categoría no puede estar vacío")
    @Size(min = 2, message = "El nombre debe tener entre 2 y 100 caracteres")
    private String nombre;
}
