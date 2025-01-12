package com.ksj.bootstudy.repository;

import com.ksj.bootstudy.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByRoleNameContainsIgnoreCase(String roleName);
}
