package com.example.crud_secur.services;

import com.example.crud_secur.model.Role;
import com.example.crud_secur.model.User;
import com.example.crud_secur.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleService roleService;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, RoleService roleService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleService = roleService;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public List<Role> getAllRoles() {
        return roleService.getAllRoles();
    }

    @Override
    public User getUser(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public User getByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    @Transactional
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void saveUser(User user) {
        user = addUserRole(user);

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);

    }

    @Override
    @Transactional
    public void updateUser(User updateUser) {
        User user = addUserRole(updateUser);

        if (user.getPassword() == null || user.getPassword().isBlank()) {
            user.setPassword(user.getPassword());
        } else {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        userRepository.save(updateUser);
    }


    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
                mapRolesToAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }

    public User addUserRole(User user) {
        Role USR = roleService.findByName("ROLE_USER");
        Role ADM = roleService.findByName("ROLE_ADMIN");

        Set<Role> roleForSet = new HashSet<>();
        roleForSet.add(USR);
        System.out.println(user.getRoles().toString());
        for (Role r : user.getRoles()) {
            if (r.getId() == 1) {
                roleForSet.add(ADM);
                System.out.println(user.getRoles().toString());
            }
        }
        ;
        user.setRoles(roleForSet);

        return user;
    }
}
