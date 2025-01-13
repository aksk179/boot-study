package com.ksj.bootstudy.controller;

import com.ksj.bootstudy.model.Menu;
import com.ksj.bootstudy.service.bbs.BbsMainService;
import com.ksj.bootstudy.service.bbs.BbsMasterService;
import com.ksj.bootstudy.service.menu.MenuService;
import com.ksj.bootstudy.vo.BbsAttachVO;
import com.ksj.bootstudy.vo.BbsCommentVO;
import com.ksj.bootstudy.vo.BbsMainVO;
import com.ksj.bootstudy.vo.BbsMasterVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@Slf4j
public class MenuPageController {

    @Autowired
    MenuService menuService;

    @RequestMapping(value = "/menu/{pageName}.page/{id}")
    public String dynamicPage(@PathVariable("pageName") String pageName, @PathVariable("id") int id, Model model) {
        checkModel(pageName, model, id);
        return "/menu/" + pageName;
    }

    @RequestMapping(value = "/menu/{pageName}.page")
    public String dynamicPage(@PathVariable("pageName") String pageName, Model model) {
        checkModel(pageName, model, 0);
        return "/menu/" + pageName;
    }

    private void checkModel(String pageName, Model model, int id) {
        if (pageName.equals("menu_list")) {
            List<Menu> menuList = menuService.findAll();
            model.addAttribute("menuList", menuList);
            model.addAttribute("title", "메뉴 목록 화면");
        } else if (pageName.equals("create_menu")) {
            if (id > 0) {
                //수정
//                Menu menu

            } else {
                //등록
                Menu menu = new Menu();
                model.addAttribute("menu", menu);
                model.addAttribute("title", "메뉴 등록 화면");
                model.addAttribute("btnName", "등록");
            }
        } else if (pageName.equals("create_menu_detail")) {
            if (id == 1) {
                Menu menu = menuService.findById(Long.valueOf(id)).orElse(new Menu());
                model.addAttribute("menu", menu);
                model.addAttribute("title", "메뉴 상세 등록 화면");
            }
        }
    }
}
