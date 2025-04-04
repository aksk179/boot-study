package com.ksj.bootstudy.service.role;

import com.ksj.bootstudy.model.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

public interface RoleService {
    Optional<Role> findById(Long id);

    List<Role> findAll();

    Role save(Role role);

    Role findByRoleNameContainsIgnoreCase(String roleName);

    void deleteById(Long id);

    Role findByRoleId(String roleId);
}
