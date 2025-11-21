package com.example.productos.demo.mappers;

import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.stereotype.Component;

import com.example.productos.demo.dto.CategoriaRequestDto;
import com.example.productos.demo.dto.CategoriaResponseDto;
import com.example.productos.demo.models.CategoriaProductoEntity;

@Component
public class CategoriaMapper {
    
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
    
    public CategoriaProductoEntity toEntity(CategoriaRequestDto dto) {
        return CategoriaProductoEntity.builder()
                .nombre(dto.getNombre())
                .build();
    }
    
    public CategoriaResponseDto toDto(CategoriaProductoEntity entity) {
        return CategoriaResponseDto.builder()
                .idCategoria(entity.getIdCategoria())
                .nombre(entity.getNombre())
                .totalProductos(entity.getProductos() != null ? entity.getProductos().size() : 0)
                .createdAt(entity.getCreatedAt() != null ? entity.getCreatedAt().format(FORMATTER) : null)
                .updatedAt(entity.getUpdatedAt() != null ? entity.getUpdatedAt().format(FORMATTER) : null)
                .build();
    }
    
    public List<CategoriaResponseDto> toDtoList(List<CategoriaProductoEntity> entities) {
        return entities.stream()
                .map(this::toDto)
                .toList();
    }
}