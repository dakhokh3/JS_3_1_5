package com.example.SpringSecurityGB.service;


import com.example.SpringSecurityGB.enity.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public interface UserService extends UserDetailsService {
    void saveUser(User user);

    User getUser(Long id);

    void removeUserById(long id);

    List<User> getAllUsers();

    @Override
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;

    public User findByEmail(String email);

}
