package com.ksj.bootstudy.service.menu;

import com.ksj.bootstudy.model.Menu;

import java.util.List;
import java.util.Optional;

public interface MenuService {
    Optional<Menu> findById(Long id);

    List<Menu> findAll();

    Menu save(Menu menu);

    Menu findByMenuNameContainsIgnoreCase(String menuName);

    void deleteById(Long id);
}
