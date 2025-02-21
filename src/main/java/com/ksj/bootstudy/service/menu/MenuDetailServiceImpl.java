package com.ksj.bootstudy.service.menu;

import com.ksj.bootstudy.model.MenuDetail;
import com.ksj.bootstudy.repository.MenuDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MenuDetailServiceImpl implements MenuDetailService {

    @Autowired
    MenuDetailRepository menuDetailRepository;

    @Override
    public Optional<MenuDetail> findById(Long id) {
        return menuDetailRepository.findById(id);
    }

    @Override
    public List<MenuDetail> findAll() {
        return menuDetailRepository.findAll();
    }

    @Override
    public MenuDetail save(MenuDetail menuDetail) {
        return menuDetailRepository.save(menuDetail);
    }

    @Override
    public MenuDetail findByMenuDetailNameContainsIgnoreCase(String menuDetailName) {
        return menuDetailRepository.findByMenuDetailNameContainsIgnoreCase(menuDetailName);
    }

    @Override
    public void deleteById(Long id) {
        menuDetailRepository.deleteById(id);
    }

    @Override
    public List<MenuDetail> findByMenuId(String menuId) {
        return menuDetailRepository.findByMenuId(menuId);
    }


}
