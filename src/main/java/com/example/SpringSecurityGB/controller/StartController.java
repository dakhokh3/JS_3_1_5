package com.example.SpringSecurityGB.controller;

import com.example.SpringSecurityGB.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class StartController {

    private final UserService userDetailsServiceCustom;

    public StartController(UserService userDetailsServiceCustom) {
        this.userDetailsServiceCustom = userDetailsServiceCustom;
    }

    @GetMapping("/admin")
    public String getUsers(Model model, Principal principal) {
        model.addAttribute("user", userDetailsServiceCustom.findByEmail(principal.getName()));
        return "admin-page";
    }

    @GetMapping("/user")
    public String userInfo(Principal principal, Model model) {
        model.addAttribute("authenticated_user", userDetailsServiceCustom.findByEmail(principal.getName()));
        return "user-page";
    }


}
