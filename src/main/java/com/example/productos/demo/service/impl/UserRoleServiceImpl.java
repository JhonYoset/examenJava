package com.example.productos.demo.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.productos.demo.models.UserRols;
import com.example.productos.demo.repository.IUserRoleRepository;
import com.example.productos.demo.service.IUserRoleService;

@Service
public class UserRoleServiceImpl implements IUserRoleService {


    private final IUserRoleRepository iUserRoleRepository;

    public UserRoleServiceImpl(IUserRoleRepository iUserRoleRepository) {
        this.iUserRoleRepository = iUserRoleRepository;
    }


    @Override
    public List<UserRols> getRolesByUser(Long userId) {
        return iUserRoleRepository.getRolesByUser(userId);
       
    }

}
