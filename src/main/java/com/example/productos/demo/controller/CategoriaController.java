package com.example.productos.demo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.productos.demo.dto.CategoriaRequestDto;
import com.example.productos.demo.dto.CategoriaResponseDto;
import com.example.productos.demo.service.ICategoriaService;

import lombok.RequiredArgsConstructor;
@RestController
@RequiredArgsConstructor

@RequestMapping("/api/categorias")
public class CategoriaController {
    
    private final ICategoriaService categoriaService;
    
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<CategoriaResponseDto> create(@RequestBody CategoriaRequestDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(categoriaService.save(dto));
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<CategoriaResponseDto> update(
            @PathVariable Long id, @RequestBody CategoriaRequestDto dto) {
        return ResponseEntity.status(HttpStatus.OK).body(categoriaService.update(id, dto));
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<CategoriaResponseDto> getById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(categoriaService.findById(id));
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<CategoriaResponseDto> delete(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(categoriaService.delete(id));
    }
    
    @GetMapping
    public ResponseEntity<List<CategoriaResponseDto>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(categoriaService.findAll());
    }
    
    @GetMapping("/buscar")
    public ResponseEntity<CategoriaResponseDto> findByNombre(@RequestParam String nombre) {
        return ResponseEntity.status(HttpStatus.OK).body(categoriaService.findByNombre(nombre));
    }
}
