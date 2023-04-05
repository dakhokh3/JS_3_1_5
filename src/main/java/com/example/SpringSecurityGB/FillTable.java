package com.example.SpringSecurityGB;

import com.example.SpringSecurityGB.enity.Role;
import com.example.SpringSecurityGB.enity.User;
import com.example.SpringSecurityGB.repository.RoleRepository;
import com.example.SpringSecurityGB.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.HashSet;
import java.util.List;

@Component
public class FillTable implements CommandLineRunner {

    private final UserService userService;
    private final EntityManager entityManager;
    private final RoleRepository roleRepository;

    private final BCryptPasswordEncoder passwordEncoder;

    public FillTable(UserService userService, RoleRepository roleRepository, EntityManager entityManager, BCryptPasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.roleRepository = roleRepository;
        this.entityManager = entityManager;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    @Override
    public void run(String... args) throws Exception {
        Role ROLE_USER = new Role("ROLE_USER");
        roleRepository.save(ROLE_USER);
        Role ROLE_ADMIN = new Role("ROLE_ADMIN");
        roleRepository.save(ROLE_ADMIN);

        User user = new User();
        user.setFirstName("Ivan");
        user.setLastName("Ivanov");
        user.setUsername("user");
        user.setAge(33);
        user.setEmail("user@mail.com");
        user.setPassword(passwordEncoder.encode("user"));
        user.setRoles(new HashSet<>(List.of(ROLE_USER)));
        userService.saveUser(user);

        User user2 = new User();
        user2.setFirstName("Fedor");
        user2.setLastName("Fedorov");
        user2.setUsername("user2");
        user2.setAge(27);
        user2.setEmail("user@mail.com");
        user2.setPassword(passwordEncoder.encode("user2"));
        user2.setRoles(new HashSet<>(List.of(ROLE_USER)));
        userService.saveUser(user2);

        User admin = new User();
        admin.setFirstName("Petr");
        admin.setLastName("Petrov");
        admin.setUsername("admin");
        admin.setAge(45);
        admin.setEmail("admin@mail.com");
        admin.setPassword(passwordEncoder.encode("admin"));
        admin.setRoles(new HashSet<>(List.of(ROLE_USER, ROLE_ADMIN)));
        userService.saveUser(admin);
    }
}
