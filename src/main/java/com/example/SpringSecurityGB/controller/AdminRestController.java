package com.example.SpringSecurityGB.controller;

import com.example.SpringSecurityGB.enity.Role;
import com.example.SpringSecurityGB.enity.User;
import com.example.SpringSecurityGB.exception_handling.NoSuchUserException;
import com.example.SpringSecurityGB.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api")
public class AdminRestController {


    private final UserService userService;

    public AdminRestController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/admin/users")
    public ResponseEntity<List<User>> getUsers() {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @PostMapping("/admin/users")
    public ResponseEntity<User> addUser(@RequestBody User user) {
        userService.saveUser(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PutMapping("/admin/users")
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        if (user.getRoles().isEmpty()) {
            Set<Role> roles = (Set<Role>) userService.findByEmail(user.getUsername()).getRoles();
            user.setRoles(roles);
        }
        userService.saveUser(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @DeleteMapping("/admin/users/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        if (userService.getUser(id) == null)
            throw new NoSuchUserException("There is no user with id = " + id);
        userService.removeUserById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/admin/users/{id}")
    public ResponseEntity<User> userInfo(@PathVariable Long id) {
        return new ResponseEntity<>(userService.getUser(id), HttpStatus.OK);
    }
}
