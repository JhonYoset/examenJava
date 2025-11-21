package com.example.productos.demo.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.example.productos.demo.dto.CategoriaRequestDto;
import com.example.productos.demo.dto.CategoriaResponseDto;
import com.example.productos.demo.exception.BadRequestException;
import com.example.productos.demo.exception.ResourceNotFoundException;
import com.example.productos.demo.mappers.CategoriaMapper;
import com.example.productos.demo.models.CategoriaProductoEntity;
import com.example.productos.demo.repository.ICategoriaRepository;
import com.example.productos.demo.service.ICategoriaService;

import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class CategoriaServiceImpl implements ICategoriaService {
    
    private final ICategoriaRepository categoriaRepository;
    private final CategoriaMapper categoriaMapper = new CategoriaMapper();
    
    @Override
    public CategoriaResponseDto save(CategoriaRequestDto dto) {
        
        if (categoriaRepository.existsByNombre(dto.getNombre())) {
            throw new BadRequestException("Ya existe una categoría con el nombre: " + dto.getNombre());
        }
        
        CategoriaProductoEntity categoria = categoriaMapper.toEntity(dto);
        CategoriaProductoEntity savedCategoria = categoriaRepository.save(categoria);
        return categoriaMapper.toDto(savedCategoria);
    }
    
    @Override
    public CategoriaResponseDto update(Long id, CategoriaRequestDto dto) {
        
        CategoriaProductoEntity existing = categoriaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No existe la categoría con ID: " + id));
        
        if (!existing.getNombre().equals(dto.getNombre()) && 
            categoriaRepository.existsByNombre(dto.getNombre())) {
            throw new BadRequestException("Ya existe una categoría con el nombre: " + dto.getNombre());
        }
        
        CategoriaProductoEntity categoria = categoriaMapper.toEntity(dto);
        BeanUtils.copyProperties(categoria, existing, "idCategoria", "createdAt", "productos");
        existing.setUpdatedAt(LocalDateTime.now());
        
        CategoriaProductoEntity updatedCategoria = categoriaRepository.save(existing);
        
        return categoriaMapper.toDto(updatedCategoria);
    }
    
    @Override
    public CategoriaResponseDto findById(Long id) {
        
        CategoriaProductoEntity categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No existe la categoría con ID: " + id));
        
        return categoriaMapper.toDto(categoria);
    }
    
    @Override
    public CategoriaResponseDto delete(Long id) {
        
        CategoriaProductoEntity categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No existe la categoría con ID: " + id));
        
        if (categoria.getProductos() != null && !categoria.getProductos().isEmpty()) {
            throw new BadRequestException("No se puede eliminar la categoría porque tiene productos asociados");
        }
        
        categoriaRepository.delete(categoria);
        
        return categoriaMapper.toDto(categoria);
    }
    
    @Override
    public List<CategoriaResponseDto> findAll() {
        
        List<CategoriaProductoEntity> categorias = categoriaRepository.findAll();
        return categoriaMapper.toDtoList(categorias);
    }
    
    @Override
    public CategoriaResponseDto findByNombre(String nombre) {
        
        CategoriaProductoEntity categoria = categoriaRepository.findByNombre(nombre)
                .orElseThrow(() -> new ResourceNotFoundException("No existe la categoría con nombre: " + nombre));
        
        return categoriaMapper.toDto(categoria);
    }
    
    @Override
    public boolean existsByNombre(String nombre) {
        return categoriaRepository.existsByNombre(nombre);
    }
}
