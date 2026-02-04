package com.eohs.safetymonitoring.security;

import com.eohs.safetymonitoring.entity.Role;
import com.eohs.safetymonitoring.repository.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataSeeder implements CommandLineRunner {

    private final RoleRepository roleRepository;

    public DataSeeder(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void run(String... args) {
        createRoleIfNotExists("ROLE_WORKER");
        createRoleIfNotExists("ROLE_ENGINEER");
        createRoleIfNotExists("ROLE_MANAGEMENT");
    }

    private void createRoleIfNotExists(String roleName) {
        roleRepository.findByName(roleName).orElseGet(() -> roleRepository.save(new Role(roleName)));
    }
}
