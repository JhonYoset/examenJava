package com.example.productos.demo.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CategoriaResponseDto {
    private Long idCategoria;
    private String nombre;
    private String createdAt;
    private String updatedAt;
    private Integer totalProductos;
}
