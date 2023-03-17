package com.example.crud_secur.services;

import com.example.crud_secur.model.Role;
import com.example.crud_secur.repository.RoleRepository;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public List<Role> getAllRoles() {
        return new ArrayList<>(roleRepository.findAll());
    }

    @Override
    public Role findByName(String name) {
        return roleRepository.findByName(name);
    }

    @Override
    @Transactional
    public void saveRole(Role role) {
        roleRepository.save(role);
    }


}

