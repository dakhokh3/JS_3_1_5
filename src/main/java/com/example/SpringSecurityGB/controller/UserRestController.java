package com.example.SpringSecurityGB.controller;

import com.example.SpringSecurityGB.enity.User;
import com.example.SpringSecurityGB.service.UserDetailsServiceCustom;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController

public class UserRestController {

    private final UserDetailsServiceCustom userService;

    public UserRestController(UserDetailsServiceCustom userService) {
        this.userService = userService;
    }

    @GetMapping(value = "api/user")
    public ResponseEntity<User> userPage(Principal principal) {
        User user = userService.findByEmail(principal.getName());
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

}
