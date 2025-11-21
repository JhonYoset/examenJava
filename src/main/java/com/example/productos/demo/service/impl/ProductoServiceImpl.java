package com.example.productos.demo.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.example.productos.demo.dto.ProductoRequestDto;
import com.example.productos.demo.dto.ProductoResponseDto;
import com.example.productos.demo.exception.ResourceNotFoundException;
import com.example.productos.demo.mappers.ProductoMapper;
import com.example.productos.demo.models.CategoriaProductoEntity;
import com.example.productos.demo.models.ProductoEntity;
import com.example.productos.demo.repository.ICategoriaRepository;
import com.example.productos.demo.repository.IProductoRepository;
import com.example.productos.demo.service.IProductoService;

public class ProductoServiceImpl implements IProductoService {
    
    private final IProductoRepository productoRepository = null;
    private final ICategoriaRepository categoriaRepository = null;
    private final ProductoMapper productoMapper = new ProductoMapper();
    
    @Override
    public ProductoResponseDto save(ProductoRequestDto dto) {
        CategoriaProductoEntity categoria = categoriaRepository.findById(dto.getIdCategoria())
                .orElseThrow(() -> new ResourceNotFoundException("No existe la categoría con ID: " + dto.getIdCategoria()));

                
        ProductoEntity producto = productoMapper.toEntity(dto);
        producto.setCategoria(categoria);
        
        ProductoEntity savedProducto = productoRepository.save(producto);
        return productoMapper.toDto(savedProducto);
    }
    
    @Override
    public ProductoResponseDto update(Long id, ProductoRequestDto dto) {
        
        ProductoEntity existing = productoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No existe el producto con ID: " + id));
        
        // Validar que la categoría existe
        CategoriaProductoEntity categoria = categoriaRepository.findById(dto.getIdCategoria())
                .orElseThrow(() -> new ResourceNotFoundException("No existe la categoría con ID: " + dto.getIdCategoria()));
        
        
        ProductoEntity producto = productoMapper.toEntity(dto);
        BeanUtils.copyProperties(producto, existing, "idProducto", "createdAt", "categoria");
        existing.setCategoria(categoria);
        existing.setUpdatedAt(LocalDateTime.now());
        
        ProductoEntity updatedProducto = productoRepository.save(existing);
        return productoMapper.toDto(updatedProducto);
    }
    
    @Override
    public ProductoResponseDto findById(Long id) {
        
        ProductoEntity producto = productoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No existe el producto con ID: " + id));
        
        return productoMapper.toDto(producto);
    }
    
    @Override
    public ProductoResponseDto delete(Long id) {
        
        ProductoEntity producto = productoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No existe el producto con ID: " + id));
        
        productoRepository.delete(producto);
        
        return productoMapper.toDto(producto);
    }
    
    @Override
    public List<ProductoResponseDto> findAll() {
        
        List<ProductoEntity> productos = productoRepository.findAll();
        return productoMapper.toDtoList(productos);
    }
    
    @Override
    public List<ProductoResponseDto> findByCategoria(Long idCategoria) {
        
        if (!categoriaRepository.existsById(idCategoria)) {
            throw new ResourceNotFoundException("No existe la categoría con ID: " + idCategoria);
        }
        
        return productoRepository.findProductosByCategoriaDto(idCategoria);
    }
    
    @Override
    public List<ProductoResponseDto> findProductosBajoStock(Integer cantidad) {
        
        List<ProductoEntity> productos = productoRepository.findProductosBajoStock(cantidad);
        return productoMapper.toDtoList(productos);
    }
        
    @Override
    public List<ProductoResponseDto> findAllProductosDto() {
        
        return productoRepository.findAllProductosDto();
    }
    
    @Override
    public Page<ProductoResponseDto> findAllProductosDtoPageable(Integer page, Integer size) {
        
        Sort sort = Sort.by(Sort.Direction.ASC, "idProducto");
        Pageable pageable = PageRequest.of(page, size, sort);
        
        return productoRepository.findAllProductosDtoPageable(pageable);
    }
    
    @Override
    public ProductoResponseDto actualizarStock(Long idProducto, Integer nuevoStock) {
        
        ProductoEntity producto = productoRepository.findById(idProducto)
                .orElseThrow(() -> new ResourceNotFoundException("No existe el producto con ID: " + idProducto));
        
        producto.setStock(nuevoStock);
        producto.setUpdatedAt(LocalDateTime.now());
        
        ProductoEntity updatedProducto = productoRepository.save(producto);
        return productoMapper.toDto(updatedProducto);
    }
    
    @Override
    public ProductoResponseDto cambiarEstado(Long idProducto, Boolean activo) {
        
        ProductoEntity producto = productoRepository.findById(idProducto)
                .orElseThrow(() -> new ResourceNotFoundException("No existe el producto con ID: " + idProducto));
        
        
        producto.setUpdatedAt(LocalDateTime.now());
        
        ProductoEntity updatedProducto = productoRepository.save(producto);
        return productoMapper.toDto(updatedProducto);
    }
}
