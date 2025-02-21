package com.ksj.bootstudy.repository;

import com.ksj.bootstudy.model.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MenuRepository extends JpaRepository<Menu, Long> {
    @Query("SELECT DISTINCT m, d FROM Menu m LEFT JOIN m.menuDetailList d ")
    List<Menu> findAllWithMenuDetail();

    Menu findByMenuNameContainsIgnoreCase(String menuName);
}
