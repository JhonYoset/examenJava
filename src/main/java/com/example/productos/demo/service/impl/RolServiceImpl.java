package com.example.productos.demo.service.impl;

import java.util.List;

import com.example.productos.demo.models.UserRols;
import com.example.productos.demo.repository.IUserRoleRepository;
import com.example.productos.demo.service.IRoleSevice;

public class RolServiceImpl implements IRoleSevice {

    private final IUserRoleRepository iUserRoleRepository;

    public RolServiceImpl(IUserRoleRepository iUserRoleRepository) {
        this.iUserRoleRepository = iUserRoleRepository;
    }

    @Override
    public List<String> getRolesByUserId(Long userId) {
        List<UserRols> userRoles = iUserRoleRepository.getRolesByUser(userId);
        List<String> roleNames = userRoles.stream().map( userRol -> {
            return userRol.getRole().getName();
        })
        .toList();

        return roleNames;
    }
    
}
