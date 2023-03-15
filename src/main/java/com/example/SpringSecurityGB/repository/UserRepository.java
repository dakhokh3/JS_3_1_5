package com.example.SpringSecurityGB.repository;

import com.example.SpringSecurityGB.enity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    User findByUsername(String username);

//    @Override
//    List<User> findAll();
//
//    @Override
//    void deleteById(Long id);
//
//    @Override
//    <S extends User> S save(S user);
//
//    @Override
//    Optional<User> findById(Long id);
}
