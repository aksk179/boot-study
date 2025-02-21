package com.ksj.bootstudy.model;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Data
@Entity
public class MenuDetail implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "menu_id", nullable = false)
    private String menuId;

    @Column(nullable = false)
    private String menuUrl;

    @Column(nullable = false)
    private String menuDetailName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns(value = {@JoinColumn(name = "menu_id", referencedColumnName = "menu_id", insertable = false, updatable = false)})
    @JsonBackReference
    private Menu menu;

    public MenuDetail() {}

    public MenuDetail(String menuId, String menuUrl, String menuDetailName) {
        this.menuId = menuId;
        this.menuUrl = menuUrl;
        this.menuDetailName = menuDetailName;
    }

    @Override
    public String toString() {
        return "id=" + id + ", menuUrl=" + menuUrl + ", menuDetailName=" + menuDetailName;
    }

    public Object orElse(Object o) {
        return o;
    }
}
