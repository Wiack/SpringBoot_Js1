package com.example.crud_secur.services;

import com.example.crud_secur.model.Role;

import java.util.List;

public interface RoleService {

    List<Role> getAllRoles();

    Role findByName(String name);

    void saveRole(Role role);
}
