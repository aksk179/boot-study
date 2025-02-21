package com.ksj.bootstudy.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Menu implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "menu_id", nullable = false, unique = true)    //not null, unique
    private String menuId;

    @Column(nullable = false)
    private String menuName;

    @ManyToMany(mappedBy = "menuList")
    private List<Role> roleList = new ArrayList<>();

    @OneToMany(mappedBy = "menu", fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<MenuDetail> menuDetailList = new ArrayList<>();

    public Menu() {}

    public Menu(String menuId, String menuName) {
        this.menuId = menuId;
        this.menuName = menuName;
    }

    @Override
    public String toString() {
        return "id=" + id + ", menuId=" + menuId + ", menuName=" + menuName;
    }
}
