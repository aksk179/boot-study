package com.ksj.bootstudy.service.menu;

import com.ksj.bootstudy.model.Menu;
import com.ksj.bootstudy.repository.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    MenuRepository menuRepository;

    @Override
    public Optional<Menu> findById(Long id) {
        return menuRepository.findById(id);
    }

    @Override
    public List<Menu> findAll() {
        return menuRepository.findAll();
    }

    @Override
    public Menu save(Menu menu) {
        return menuRepository.save(menu);
    }

    @Override
    public Menu findByMenuNameContainsIgnoreCase(String menuName) {
        return menuRepository.findByMenuNameContainsIgnoreCase(menuName);
    }

    @Override
    public void deleteById(Long id) {
        menuRepository.deleteById(id);
    }
}
