package com.example.productos.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.productos.demo.dto.ProductoResponseDto;
import com.example.productos.demo.models.ProductoEntity;

@Repository
public interface IProductoRepository extends JpaRepository<ProductoEntity, Long> {
    
    @Query("SELECT p FROM ProductoEntity p WHERE p.categoria.idCategoria = :idCategoria")
    List<ProductoEntity> findByCategoria(@Param("idCategoria") Long idCategoria);
    
    @Query("SELECT p FROM ProductoEntity p WHERE p.codigoProducto = :codigo")
    Optional<ProductoEntity> findByCodigoProducto(@Param("codigo") String codigo);
    
    @Query("SELECT p FROM ProductoEntity p WHERE p.stock < :cantidad")
    List<ProductoEntity> findProductosBajoStock(@Param("cantidad") Integer cantidad);

    /* DTO */
    @Query("""
        SELECT new com.example.productos.demo.dto.ProductoResponseDto(
            p.idProducto,
            p.nombre,
            p.precio,
            p.stock,
            p.categoria.idCategoria,
            c.nombre,
            p.createdAt,
            p.updatedAt
        )
        FROM ProductoEntity p
        JOIN CategoriaProductoEntity c ON p.categoria.idCategoria = c.idCategoria
        """)
    List<ProductoResponseDto> findAllProductosDto();

    @Query("""
        SELECT new com.example.productos.demo.dto.ProductoResponseDto(
            p.idProducto,
            p.nombre,
            p.precio,
            p.stock,
            p.categoria.idCategoria,
            c.nombre,
            p.createdAt,
            p.updatedAt
        )
        FROM ProductoEntity p
        JOIN CategoriaProductoEntity c ON p.categoria.idCategoria = c.idCategoria
        """)
    Page<ProductoResponseDto> findAllProductosDtoPageable(Pageable pageable);

    @Query("""
        SELECT new com.example.productos.demo.dto.ProductoResponseDto(
            p.idProducto,
            p.nombre,
            p.precio,
            p.stock,
            p.categoria.idCategoria,
            c.nombre,
            p.createdAt,
            p.updatedAt
        )
        FROM ProductoEntity p
        JOIN CategoriaProductoEntity c ON p.categoria.idCategoria = c.idCategoria
        WHERE c.idCategoria = :idCategoria
        """)
    List<ProductoResponseDto> findProductosByCategoriaDto(@Param("idCategoria") Long idCategoria);
}