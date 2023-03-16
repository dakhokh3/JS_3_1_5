package com.example.SpringSecurityGB.controller;

import com.example.SpringSecurityGB.enity.User;
import com.example.SpringSecurityGB.service.UserDetailsServiceCustom;
import com.example.SpringSecurityGB.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@RequestMapping("/user")
@Controller
public class UserController {

    private final UserDetailsServiceCustom userDetailsServiceCustom;

    public UserController(UserDetailsServiceCustom userDetailsServiceCustom) {
        this.userDetailsServiceCustom = userDetailsServiceCustom;
    }

    @GetMapping()
    public String userInfo(Principal principal, Model model) {
        User userInfo = userDetailsServiceCustom.findByUsername(principal.getName());
        model.addAttribute("user_info", userInfo);
        return "user-page";
    }
}
