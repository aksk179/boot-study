package com.ksj.bootstudy.repository;

import com.ksj.bootstudy.model.Role;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByRoleNameContainsIgnoreCase(String roleName);

    @Query("SELECT r FROM Role r LEFT JOIN r.menuList WHERE roleId = :roleId")
    Role findByRoleId(@Param("roleId") String roleId);
}
