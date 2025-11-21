package com.example.productos.demo.service;

import java.util.List;

import com.example.productos.demo.commons.ICrudCommonsDto;
import com.example.productos.demo.dto.CategoriaRequestDto;
import com.example.productos.demo.dto.CategoriaResponseDto;

public interface ICategoriaService extends ICrudCommonsDto<CategoriaRequestDto, CategoriaResponseDto, Long> {
    
    List<CategoriaResponseDto> findAll();
    
    CategoriaResponseDto findByNombre(String nombre);
    
    boolean existsByNombre(String nombre);
}