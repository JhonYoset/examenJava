package com.example.productos.demo.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.productos.demo.dto.ProductoRequestDto;
import com.example.productos.demo.dto.ProductoResponseDto;
import com.example.productos.demo.service.IProductoService;

import lombok.RequiredArgsConstructor;
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/productos")
public class ProductoController {
    
    private final IProductoService productoService = null;
    
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    @PostMapping
    public ResponseEntity<ProductoResponseDto> create( @RequestBody ProductoRequestDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(productoService.save(dto));
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<ProductoResponseDto> update(
            @PathVariable Long id, 
            @RequestBody ProductoRequestDto dto) {
        return ResponseEntity.status(HttpStatus.OK).body(productoService.update(id, dto));
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<ProductoResponseDto> getById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(productoService.findById(id));
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<ProductoResponseDto> delete(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(productoService.delete(id));
    }
    
    @GetMapping
    public ResponseEntity<List<ProductoResponseDto>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(productoService.findAll());
    }
    
    @GetMapping("/categoria/{idCategoria}")
    public ResponseEntity<List<ProductoResponseDto>> getByCategoria(@PathVariable Long idCategoria) {
        return ResponseEntity.status(HttpStatus.OK).body(productoService.findByCategoria(idCategoria));
    }
    
    @GetMapping("/bajo-stock")
    public ResponseEntity<List<ProductoResponseDto>> getProductosBajoStock(
            @RequestParam(defaultValue = "10") Integer cantidad) {
        return ResponseEntity.status(HttpStatus.OK).body(productoService.findProductosBajoStock(cantidad));
    }
    
    @GetMapping("/dto")
    public ResponseEntity<List<ProductoResponseDto>> getAllDto() {
        return ResponseEntity.status(HttpStatus.OK).body(productoService.findAllProductosDto());
    }
    
    @GetMapping("/dto-pageable")
    public ResponseEntity<Page<ProductoResponseDto>> getAllDtoPageable(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size) {
        return ResponseEntity.status(HttpStatus.OK).body(productoService.findAllProductosDtoPageable(page, size));
    }
    
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    @PatchMapping("/{id}/stock")
    public ResponseEntity<ProductoResponseDto> updateStock(
            @PathVariable Long id,
            @RequestParam Integer stock) {
        return ResponseEntity.status(HttpStatus.OK).body(productoService.actualizarStock(id, stock));
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("/{id}/estado")
    public ResponseEntity<ProductoResponseDto> cambiarEstado(
            @PathVariable Long id,
            @RequestParam Boolean activo) {
        return ResponseEntity.status(HttpStatus.OK).body(productoService.cambiarEstado(id, activo));
    }
}