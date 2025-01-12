package com.ksj.bootstudy.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)    //not null, unique
    private String roleId;

    @Column(nullable = false)
    private String roleName;

    public Role() {}

    public Role(String roleId, String roleName) {
        this.roleId = roleId;
        this.roleName = roleName;
    }
}
