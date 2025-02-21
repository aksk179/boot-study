package com.ksj.bootstudy.controller;

import com.ksj.bootstudy.model.Menu;
import com.ksj.bootstudy.model.MenuDetail;
import com.ksj.bootstudy.model.Role;
import com.ksj.bootstudy.service.menu.MenuDetailService;
import com.ksj.bootstudy.service.menu.MenuService;
import com.ksj.bootstudy.service.role.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@Slf4j
public class RolePageController {

    @Autowired
    RoleService roleService;

    @Autowired
    MenuService menuService;

    @GetMapping(value = "/role/{pageName}.page/{id}/update")
    public String dynamicPage(@PathVariable("pageName") String pageName, @PathVariable("id") Long id, Model model) {
        if ("create_role".equals(pageName)) {
            Optional<Role> roleOptional = roleService.findById(id);

            Role role;
            if (roleOptional.isPresent()) {
                role = roleOptional.get();
            } else {
                role = new Role();
            }
            model.addAttribute("role", role);
            model.addAttribute("title", "권한 수정 화면");
        }

        model.addAttribute("btnName", "수정");
        return "/role/" + pageName;
    }

    @GetMapping(value = "/role/{pageName}.page/{roleId}")
    public String dynamicPage(@PathVariable("pageName") String pageName, @PathVariable("roleId") String roleId, Model model) {
        checkModel(pageName, model, roleId);
        return "/role/" + pageName;
    }

    @GetMapping(value = "/role/{pageName}.page")
    public String dynamicPage(@PathVariable("pageName") String pageName, Model model) {
        checkModel(pageName, model, "");
        return "/role/" + pageName;
    }

    private void checkModel(String pageName, Model model, String roleId) {
        if (pageName.equals("role_list")) {
            List<Role> roleList = roleService.findAll();
            model.addAttribute("roleList", roleList);
            model.addAttribute("title", "권한 목록 화면");
        } else if (pageName.equals("create_role")) {
            //등록
            Role role = new Role();
            model.addAttribute("role", role);
            model.addAttribute("title", "권한 등록 화면");
            model.addAttribute("btnName", "등록");
        }
        else if (pageName.equals("role_menu_rel")) {
            Role role = roleService.findByRoleId(roleId);
            List<Menu> assignedMenus = role.getMenuList();
            model.addAttribute("assignedMenus", assignedMenus);

            List<Menu> allMenus = menuService.findAll();

            List<Menu> temp = new ArrayList<>();

            for (Menu menu : allMenus) {
                if (!checkAssignedMenu(menu, assignedMenus)) {
                    temp.add(menu);
                }
            }

            model.addAttribute("allMenus", temp);
            model.addAttribute("role", role);
//            model.addAttribute("title", "상세메뉴 목록 화면");
        }
//        else if (pageName.equals("create_menu_detail")) {
//            MenuDetail menuDetail = new MenuDetail();
//            model.addAttribute("menuDetail", menuDetail);
//            model.addAttribute("menuId", menuId);
//            model.addAttribute("title", "상세메뉴 등록 화면");
//            model.addAttribute("btnName", "등록");
//        }
    }

    private boolean checkAssignedMenu(Menu menu, List<Menu> assignedMenus) {
        for (Menu menu1 : assignedMenus) {
            if (menu.getMenuId().equals(menu1.getMenuId())) {
                return true;
            }
        }
        return false;
    }
}
