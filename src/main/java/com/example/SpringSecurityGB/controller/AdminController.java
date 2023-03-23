package com.example.SpringSecurityGB.controller;


import com.example.SpringSecurityGB.enity.Role;
import com.example.SpringSecurityGB.enity.User;
import com.example.SpringSecurityGB.repository.RoleRepository;
import com.example.SpringSecurityGB.service.UserDetailsServiceCustom;
import com.example.SpringSecurityGB.service.UserServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Collection;

@RequestMapping("/admin")
@Controller
public class AdminController {
    private final UserServiceImpl userService;
    private final UserDetailsServiceCustom userDetailsServiceCustom;

    private final RoleRepository roleRepository;

    public AdminController(UserServiceImpl userService, UserDetailsServiceCustom userDetailsServiceCustom, RoleRepository roleRepository) {
        this.userService = userService;
        this.userDetailsServiceCustom = userDetailsServiceCustom;
        this.roleRepository = roleRepository;
    }


    @GetMapping()
    public String getUsers(Model model, Principal principal) {
        model.addAttribute("user", userDetailsServiceCustom.findByEmail(principal.getName()));
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("roles", roleRepository.findAll());
        model.addAttribute("edit_user", new User());
        model.addAttribute("new_user", new User());
        return "admin-page";
    }

    @GetMapping("/addNewUser")
    public String addNewUser(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "redirect:/admin";
    }

    @PostMapping("/saveUser")
    public String saveUser(@ModelAttribute("new_user") User user, @RequestParam(value = "rol", required = false) Collection<Role> roles) {
        user.setRoles(roles);
        userService.saveUser(user);
        return "redirect:/admin";
    }

    @PostMapping("/updateInfo")
    public String updateUser(@ModelAttribute("edit_user") User updatedUser, @RequestParam(value = "rol", required = false) Collection<Role> roles) {
        User user = userService.getUser(updatedUser.getId());
        if (roles != null) {
            updatedUser.setRoles(roles);
        } else {
            updatedUser.setRoles(user.getRoles());
        }
        userService.saveUser(updatedUser);
        return "redirect:/admin";

    }

    @PostMapping("/delete")
    public String delete(@RequestParam("id") Long id) {
        userService.removeUserById(id);
        return "redirect:/admin";

    }
}
