package com.qa.application.models.role.repository;

import com.qa.application.models.role.ERole;
import com.qa.application.models.role.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,Integer> {
    Optional<Role> findByName(ERole name);
    Role findRoleByName(ERole name);
}
