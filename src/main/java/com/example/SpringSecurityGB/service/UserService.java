package com.example.SpringSecurityGB.service;


import com.example.SpringSecurityGB.enity.User;

import java.util.List;

public interface UserService {
    void saveUser(User user);

    User getUser(Long id);

    void removeUserById(long id);

    List<User> getAllUsers();



}
