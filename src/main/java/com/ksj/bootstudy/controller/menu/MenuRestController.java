package com.ksj.bootstudy.controller.menu;

import com.ksj.bootstudy.model.Menu;
import com.ksj.bootstudy.service.menu.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
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
}
