package com.example.crud_secur.controller;

import com.example.crud_secur.model.Role;

import com.example.crud_secur.model.User;
import com.example.crud_secur.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api")
public class AdminController {

    private final UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/admin")
    public ResponseEntity<List<User>> showAllUsers() {
        List<User> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);

    }

    @GetMapping("/roles")
    public ResponseEntity<List<Role>> getAllRoles() {
        return new ResponseEntity<>(userService.getAllRoles(), HttpStatus.OK);
    }

    @GetMapping("/admin/{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.getUser(id);
    }

    @GetMapping("/user")
    public ResponseEntity<User> getUserByEmail(Principal principal) {
        System.out.println(principal.getName());
        return new ResponseEntity<>(userService.getByEmail(principal.getName()), HttpStatus.OK);
    }

    @PostMapping("/admin")
    public ResponseEntity<User> saveNewUser(@RequestBody User user) {
        userService.saveUser(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/admin/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping("/admin/{id}")
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        userService.updateUser(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}


