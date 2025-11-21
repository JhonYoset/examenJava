package com.example.productos.demo.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.example.productos.demo.models.Role;

public interface IRoleRepository extends JpaRepository<Role, Long> {


}
