package com.example.productos.demo.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.example.productos.demo.commons.ICrudCommonsDto;
import com.example.productos.demo.dto.ProductoRequestDto;
import com.example.productos.demo.dto.ProductoResponseDto;

public interface IProductoService extends ICrudCommonsDto<ProductoRequestDto, ProductoResponseDto, Long> {
    
    List<ProductoResponseDto> findAll();
    
    List<ProductoResponseDto> findByCategoria(Long idCategoria);
    
    List<ProductoResponseDto> findProductosBajoStock(Integer cantidad);
    
    List<ProductoResponseDto> findAllProductosDto();
    
    Page<ProductoResponseDto> findAllProductosDtoPageable(Integer page, Integer size);
}
