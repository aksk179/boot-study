package com.ksj.bootstudy.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class MenuDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)    //Menu의 id랑 동일
    private int menuRefId;

    @Column(nullable = false)
    private String menuUrl;

    @Column(nullable = false)
    private String menuDetailName;

    public MenuDetail() {}

    public MenuDetail(int menuRefId, String menuUrl, String menuDetailName) {
        this.menuRefId = menuRefId;
        this.menuUrl = menuUrl;
        this.menuDetailName = menuDetailName;
    }
}
