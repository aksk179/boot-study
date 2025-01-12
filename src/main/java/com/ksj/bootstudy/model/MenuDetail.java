package com.ksj.bootstudy.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class MenuDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)    //not null, unique
    private String menuDetailId;

    @Column(nullable = false)
    private String menuUrl;

    @Column(nullable = false)
    private String menuDetailName;

    public MenuDetail() {}

    public MenuDetail(String menuDetailId, String menuUrl, String menuDetailName) {
        this.menuDetailId = menuDetailId;
        this.menuUrl = menuUrl;
        this.menuDetailName = menuDetailName;
    }
}
