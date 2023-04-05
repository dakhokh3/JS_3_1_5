package com.example.SpringSecurityGB.controller;

import com.example.SpringSecurityGB.enity.User;
import com.example.SpringSecurityGB.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController

public class UserRestController {

    private final UserService userService;

    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "api/user")
    public ResponseEntity<User> userPage(Principal principal) {
        User user = userService.findByEmail(principal.getName());
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

}
