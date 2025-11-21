package com.example.productos.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.productos.demo.models.CategoriaProductoEntity;

@Repository
public interface ICategoriaRepository extends JpaRepository<CategoriaProductoEntity, Long> {
    
    @Query("SELECT c FROM CategoriaProductoEntity c WHERE c.nombre = :nombre")
    Optional<CategoriaProductoEntity> findByNombre(@Param("nombre") String nombre);
    
    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM CategoriaProductoEntity c WHERE c.nombre = :nombre")
    boolean existsByNombre(@Param("nombre") String nombre);
}