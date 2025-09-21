package com.estoque.estoque_api.config.bootstrapdata;

import com.estoque.estoque_api.model.Role;
import com.estoque.estoque_api.model.UserAccount;
import com.estoque.estoque_api.repository.RoleRepository;
import com.estoque.estoque_api.repository.UserAccountRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

public class BootStrap {

    @Bean
    CommandLineRunner seed(RoleRepository roleRepo, UserAccountRepository userRepo, PasswordEncoder enc) {
        return args -> {
            Role adminRole = roleRepo.findByName("ROLE_ADMIN").orElseGet(() -> {
                Role r = new Role();
                r.setName("ROLE_ADMIN");
                return roleRepo.save(r);
            });

            Role userRole = roleRepo.findByName("ROLE_USER").orElseGet(() -> {
                Role r = new Role();
                r.setName("ROLE_USER");
                return roleRepo.save(r);
            });

            userRepo.findByUsername("admin").orElseGet(() -> {
                var admin = new UserAccount();
                admin.setUsername("admin");
                admin.setPassword(enc.encode("admin123"));
                admin.setEnabled(true);
                admin.setAccountNonLocked(true);
                admin.getRoles().add(adminRole);
                admin.getRoles().add(userRole);
                return userRepo.save(admin);
            });
        };
    }

}
