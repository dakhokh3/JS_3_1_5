package com.example.SpringSecurityGB.service;


import com.example.SpringSecurityGB.enity.User;
import com.example.SpringSecurityGB.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    @Transactional
    public void saveUser(User user) {
        userRepository.save(user);
    }

    @Override
    public User getUser(Long id) {
        return userRepository.findById(id).get();
    }


    @Override
    @Transactional
    public void removeUserById(long id) {
        userRepository.deleteById(id);
    }


    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = findByEmail(email);
        if (user == null) throw new UsernameNotFoundException(String.format("User '%s' not found", email));
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), user.getAuthorities());
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

}
