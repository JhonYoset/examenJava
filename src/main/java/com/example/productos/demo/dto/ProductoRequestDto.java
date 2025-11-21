package com.example.productos.demo.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductoRequestDto {
    @NotBlank
    @Size(min = 3, message = "El nombre debe tener entre 3 y 200 caracteres")
    private String nombre;
    
    @Min(value = 0, message = "El stock no puede ser negativo")
    private Integer stock;
    
    @NotNull(message = "El precio no puede ser nulo")
    private BigDecimal precio;
   
    @NotNull(message = "La categoría es obligatoria")
    private Long idCategoria;


}
