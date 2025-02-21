package com.ksj.bootstudy.repository;

import com.ksj.bootstudy.model.MenuDetail;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MenuDetailRepository extends JpaRepository<MenuDetail, Long> {
    MenuDetail findByMenuDetailNameContainsIgnoreCase(String menuDetailName);

    @Query("SELECT md FROM MenuDetail md LEFT JOIN FETCH md.menu WHERE md.menuId = :menuId")
    List<MenuDetail> findByMenuId(@Param("menuId") String menuId);
}
