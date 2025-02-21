package com.ksj.bootstudy.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)    //not null, unique
    private String roleId;

    @Column(nullable = false)
    private String roleName;

    @ManyToMany
    @JoinTable(name = "role_menu_rel",
                joinColumns = @JoinColumn(name = "id_role"),
                inverseJoinColumns = @JoinColumn(name = "id_menu")
    )
    private List<Menu> menuList = new ArrayList<>();

    public Role() {}

    public Role(String roleId, String roleName) {
        this.roleId = roleId;
        this.roleName = roleName;
    }

    @Override
    public String toString() {
        return "id=" + id + ", roleId=" + roleId + ", roleName=" + roleName;
    }
}
