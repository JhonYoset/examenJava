package com.example.productos.demo.service;

import java.util.List;



import com.example.productos.demo.models.UserRols;

public interface IUserRoleService {

    List<UserRols> getRolesByUser(Long userId);

}
