package com.example.crud_secur.init;

import com.example.crud_secur.model.Role;
import com.example.crud_secur.model.User;
import com.example.crud_secur.services.RoleService;
import com.example.crud_secur.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class Init implements CommandLineRunner {
    private RoleService roleService;
    private UserService userService;

    @Autowired
    public Init(RoleService roleService, UserService userService) {
        this.roleService = roleService;
        this.userService = userService;
    }

    @Override
    public void run(String... args) {
        if (roleService.findByName("ROLE_ADMIN") == null) {
            roleService.saveRole(new Role(1L, "ROLE_ADMIN"));
        }

        if (roleService.findByName("ROLE_USER") == null) {
            roleService.saveRole(new Role(2L, "ROLE_USER"));
        }


        User admin = userService.getByEmail("admin@gmail.com");
        if (admin == null) {
            admin = new User(
                    "adminName",
                    "adminLastName",
                    33,
                    "admin@gmail.com",
                    "0000",
                    Collections.singleton(roleService.findByName("ROLE_ADMIN")));

            userService.saveUser(admin);
        }

        User user = userService.getByEmail("user@gmail.com");
        if (user == null) {
            user = new User(
                    "userName",
                    "userLastName",
                    23,
                    "user@gmail.com",
                    "0000",
                    Collections.singleton(roleService.findByName("ROLE_USER")));

            userService.saveUser(user);
        }
    }
}



