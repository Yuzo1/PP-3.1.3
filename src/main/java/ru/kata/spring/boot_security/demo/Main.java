package ru.kata.spring.boot_security.demo;

import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.entity.Role;
import ru.kata.spring.boot_security.demo.entity.User;
import ru.kata.spring.boot_security.demo.repository.RoleRepository;
import ru.kata.spring.boot_security.demo.repository.UserRepository;


import javax.annotation.PostConstruct;
import java.util.ArrayList;


    @Component
    public class Main {
        private final RoleRepository roleRepository;

        private final UserRepository userRepository;

        public Main(RoleRepository roleRepository, UserRepository userRepository) {
            this.roleRepository = roleRepository;
            this.userRepository = userRepository;
        }

        @PostConstruct
        public void init() {

            Role roleUser = new Role("ROLE_USER");
            Role roleAdmin = new Role("ROLE_ADMIN");

            var userRoles = new ArrayList<Role>();
            var adminRoles = new ArrayList<Role>();

            userRoles.add(roleUser);
            adminRoles.add(roleAdmin);

            roleRepository.save(roleUser);
            roleRepository.save(roleAdmin);

            // Пароль - имя юзера
            User admin = new User("admin", "admin@mail.ru", "$2a$12$O4yM6Ys6UyrtRxoW7oDkWut4FnrRr9IU/zOwGHaN86.W9.T3zNk/O\n");
            User user = new User("user", "user@mail.ru", "$2a$12$YeS5H9ONxOJIsb6rMw0z0etwqPgZUe4IlI8qWArs8Nwp9LZ1G1Afe\n");

            user.setRoles(userRoles);
            admin.setRoles(adminRoles);

            userRepository.save(user);
            userRepository.save(admin);
        }
}
