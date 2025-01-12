package com.ksj.bootstudy.repository;

import com.ksj.bootstudy.model.MenuDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuDetailRepository extends JpaRepository<MenuDetail, Long> {
    MenuDetail findByMenuDetailNameContainsIgnoreCase(String menuDetailName);
}
