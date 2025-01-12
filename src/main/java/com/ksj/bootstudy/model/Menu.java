package com.ksj.bootstudy.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)    //not null, unique
    private String menuId;

    @Column(nullable = false)
    private String menuName;

    public Menu() {}

    public Menu(String menuId, String menuName) {
        this.menuId = menuId;
        this.menuName = menuName;
    }
}
