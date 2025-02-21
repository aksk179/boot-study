package com.ksj.bootstudy.controller.menu;

import com.ksj.bootstudy.model.Menu;
import com.ksj.bootstudy.service.menu.MenuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
public class MenuRestController {

    @Autowired
    MenuService menuService;

    @RequestMapping(value = "/menu/findById", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> findById(@RequestBody Menu menu) {
        Map<String, Object> result = new HashMap<>();
        try {
            Menu data = menuService.findById(menu.getId()).orElse(new Menu());
            result.put("data", data);
            result.put("code", "Y");
        } catch (Exception e) {
            result.put("code", "N");
            e.printStackTrace();
        }

        return ResponseEntity.ok(result);
    }

    @RequestMapping(value = "/menu/save", method = RequestMethod.PUT)
    public ResponseEntity<Map<String, Object>> save(@RequestBody Menu menu) {
        Map<String, Object> result = new HashMap<>();
        try {
            Menu data = menuService.save(menu);
            result.put("data", data);
            result.put("code", "Y");
        } catch (Exception e) {
            result.put("code", "N");
            e.printStackTrace();
        }

        return ResponseEntity.ok(result);
    }

    @RequestMapping(value = "/menu/delete_menu/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Map<String, Object>> deleteById(@PathVariable("id") Long id) {
        Map<String, Object> result = new HashMap<>();
        try {
            menuService.deleteById(id);
            result.put("code", "Y");
        } catch (Exception e) {
            e.printStackTrace();
            result.put("code", "N");
        }

        return ResponseEntity.ok(result);
    }

//    @RequestMapping(value = "/menu/menu_list.page", method = RequestMethod.GET)
//    public ResponseEntity<Map<String, Object>> findAll() {
//        log.info("findAll() 실행");
//        Map<String, Object> result = new HashMap<>();
//        try {
//            List<Menu> data = menuService.findAll();
//            log.info("??? : " + data.toString());
//            result.put("menuList", data);
//            result.put("code", "Y");
//        } catch (Exception e) {
//            result.put("code", "N");
//            e.printStackTrace();
//        }
//        return ResponseEntity.ok(result);
//    }


}
