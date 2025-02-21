package com.ksj.bootstudy.service.menu;

import com.ksj.bootstudy.model.MenuDetail;

import java.util.List;
import java.util.Optional;

public interface MenuDetailService {
    Optional<MenuDetail> findById(Long id);

    List<MenuDetail> findAll();

    MenuDetail save(MenuDetail menuDetail);

    MenuDetail findByMenuDetailNameContainsIgnoreCase(String menuDetailName);

    void deleteById(Long id);

    List<MenuDetail> findByMenuId(String menuId);
}
