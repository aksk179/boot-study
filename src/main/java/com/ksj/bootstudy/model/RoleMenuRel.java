package com.ksj.bootstudy.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class RoleMenuRel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)    //not null, unique
    private String menuId;
}
