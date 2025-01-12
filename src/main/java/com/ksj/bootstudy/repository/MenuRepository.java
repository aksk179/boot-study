package com.ksj.bootstudy.repository;

import com.ksj.bootstudy.model.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuRepository extends JpaRepository<Menu, Long> {
    Menu findByMenuNameContainsIgnoreCase(String menuName);
}
