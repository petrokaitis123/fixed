package com.qa.application;

import com.qa.application.models.role.ERole;
import com.qa.application.models.role.Role;
import com.qa.application.models.role.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

@Autowired
    private final RoleRepository roleRepository;

    public DataInitializer(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        // Check if roles exist, and if not, create them
        if (roleRepository.findByName(ERole.ROLE_USER).isEmpty()) {
            Role userRole = new Role();
            userRole.setName(ERole.ROLE_USER);
            roleRepository.save(userRole);
        }
        if (roleRepository.findByName(ERole.ROLE_MODERATOR).isEmpty()) {
            Role adminRole = new Role();
            adminRole.setName(ERole.ROLE_MODERATOR);
            roleRepository.save(adminRole);
        }

        if (roleRepository.findByName(ERole.ROLE_ADMIN).isEmpty()) {
            Role adminRole = new Role();
            adminRole.setName(ERole.ROLE_ADMIN);
            roleRepository.save(adminRole);
        }


    }

}
