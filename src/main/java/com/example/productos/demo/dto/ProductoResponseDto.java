package com.example.productos.demo.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductoResponseDto {
    private Long idProducto;
    private String nombre;
    private BigDecimal precio;
    private Integer stock;
    private Long idCategoria;
    private String nombreCategoria;
    private String codigoProducto;
    private String createdAt;
    private String updatedAt;
    
    
    public ProductoResponseDto(Long idProducto, String nombre, 
                              BigDecimal precio, Integer stock, Long idCategoria, 
                              String nombreCategoria, String codigoProducto,
                              java.time.LocalDateTime createdAt, java.time.LocalDateTime updatedAt) {
        this.idProducto = idProducto;
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
        this.idCategoria = idCategoria;
        this.nombreCategoria = nombreCategoria;
        this.codigoProducto = codigoProducto;
        this.createdAt = createdAt != null ? createdAt.toString() : null;
        this.updatedAt = updatedAt != null ? updatedAt.toString() : null;
    }
}
