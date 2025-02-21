package com.ksj.bootstudy.controller;

import com.ksj.bootstudy.model.Menu;
import com.ksj.bootstudy.model.MenuDetail;
import com.ksj.bootstudy.service.bbs.BbsMainService;
import com.ksj.bootstudy.service.bbs.BbsMasterService;
import com.ksj.bootstudy.service.menu.MenuDetailService;
import com.ksj.bootstudy.service.menu.MenuService;
import com.ksj.bootstudy.vo.BbsAttachVO;
import com.ksj.bootstudy.vo.BbsCommentVO;
import com.ksj.bootstudy.vo.BbsMainVO;
import com.ksj.bootstudy.vo.BbsMasterVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Controller
@Slf4j
public class MenuPageController {

    @Autowired
    MenuService menuService;

    @Autowired
    MenuDetailService menuDetailService;

    @GetMapping(value = "/menu/{pageName}.page/{id}/update")
    public String dynamicPage(@PathVariable("pageName") String pageName, @PathVariable("id") Long id, Model model) {
        if ("create_menu".equals(pageName)) {
            Optional<Menu> menuOptional = menuService.findById(id);

            Menu menu;
            if (menuOptional.isPresent()) {
                menu = menuOptional.get();
            } else {
                menu = new Menu();
            }
            model.addAttribute("menu", menu);
            model.addAttribute("title", "메뉴 수정 화면");
        } else {
            Optional<MenuDetail> menuDetailOptional = menuDetailService.findById(id);

            MenuDetail menuDetail;
            if (menuDetailOptional.isPresent()) {
                menuDetail = menuDetailOptional.get();
            } else {
                menuDetail = new MenuDetail();
            }
            model.addAttribute("menuDetail", menuDetail);
            model.addAttribute("menuId", menuDetail.getMenuId());
            model.addAttribute("title", "상세메뉴 수정 화면");
        }

        model.addAttribute("btnName", "수정");
        return "/menu/" + pageName;
    }

    @GetMapping(value = "/menu/{pageName}.page/{menuId}")
    public String dynamicPage(@PathVariable("pageName") String pageName, @PathVariable("menuId") String menuId, Model model) {
        checkModel(pageName, model, menuId);
        return "/menu/" + pageName;
    }

    @GetMapping(value = "/menu/{pageName}.page")
    public String dynamicPage(@PathVariable("pageName") String pageName, Model model) {
        checkModel(pageName, model, "");
        return "/menu/" + pageName;
    }

    private void checkModel(String pageName, Model model, String menuId) {
        if (pageName.equals("menu_list")) {
            List<Menu> menuList = menuService.findAll();
            model.addAttribute("menuList", menuList);
            model.addAttribute("title", "메뉴 목록 화면");
        } else if (pageName.equals("create_menu")) {
            //등록
            Menu menu = new Menu();
            model.addAttribute("menu", menu);
            model.addAttribute("title", "메뉴 등록 화면");
            model.addAttribute("btnName", "등록");
        } else if (pageName.equals("detail_menu_list")) {
            List<MenuDetail> detailMenuList = menuDetailService.findByMenuId(menuId);
            model.addAttribute("detailMenuList", detailMenuList);
            model.addAttribute("menuId", menuId);
            model.addAttribute("title", "상세메뉴 목록 화면");
        } else if (pageName.equals("create_menu_detail")) {
            MenuDetail menuDetail = new MenuDetail();
            model.addAttribute("menuDetail", menuDetail);
            model.addAttribute("menuId", menuId);
            model.addAttribute("title", "상세메뉴 등록 화면");
            model.addAttribute("btnName", "등록");
        }
    }
}
