package com.example.productos.demo.mappers;

import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.stereotype.Component;

import com.example.productos.demo.dto.ProductoRequestDto;
import com.example.productos.demo.dto.ProductoResponseDto;
import com.example.productos.demo.models.ProductoEntity;

@Component
public class ProductoMapper {
    
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
    
    public ProductoEntity toEntity(ProductoRequestDto dto) {
        return ProductoEntity.builder()
                .nombre(dto.getNombre())
                .precio(dto.getPrecio())
                .stock(dto.getStock())
                .build();
    }
    
    public ProductoResponseDto toDto(ProductoEntity entity) {
        return ProductoResponseDto.builder()
                .idProducto(entity.getIdProducto())
                .nombre(entity.getNombre())
                .precio(entity.getPrecio())
                .stock(entity.getStock())
                .idCategoria(entity.getCategoria() != null ? entity.getCategoria().getIdCategoria() : null)
                .nombreCategoria(entity.getCategoria() != null ? entity.getCategoria().getNombre() : null)
                .createdAt(entity.getCreatedAt() != null ? entity.getCreatedAt().format(FORMATTER) : null)
                .updatedAt(entity.getUpdatedAt() != null ? entity.getUpdatedAt().format(FORMATTER) : null)
                .build();
    }
    
    public List<ProductoResponseDto> toDtoList(List<ProductoEntity> entities) {
        return entities.stream()
                .map(this::toDto)
                .toList();
    }
}