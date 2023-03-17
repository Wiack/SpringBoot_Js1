package com.example.crud_secur.services;


import com.example.crud_secur.model.Role;
import com.example.crud_secur.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

    List<User> getAllUsers();

    List<Role> getAllRoles();

    void saveUser(User user);

    User getUser(Long id);

    void updateUser(User updateUser);

    void deleteUser(Long id);

    User getByEmail(String email);

}
