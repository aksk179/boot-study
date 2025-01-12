package com.ksj.bootstudy.controller.menu;

import com.ksj.bootstudy.model.MenuDetail;
import com.ksj.bootstudy.service.menu.MenuDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class MenuDetailRestController {

    @Autowired
    MenuDetailService menuDetailService;

    @RequestMapping(value = "/menuDetail/findById", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> findById(@RequestBody MenuDetail menuDetail) {
        Map<String, Object> result = new HashMap<>();
        try {
            MenuDetail data = menuDetailService.findById(menuDetail.getId()).orElse(new MenuDetail());
            result.put("data", data);
            result.put("code", "Y");
        } catch (Exception e) {
            result.put("code", "N");
            e.printStackTrace();
        }

        return ResponseEntity.ok(result);
    }

    @RequestMapping(value = "/menuDetail/save", method = RequestMethod.PUT)
    public ResponseEntity<Map<String, Object>> save(@RequestBody MenuDetail menuDetail) {
        Map<String, Object> result = new HashMap<>();
        try {
            MenuDetail data = menuDetailService.save(menuDetail);
            result.put("data", data);
            result.put("code", "Y");
        } catch (Exception e) {
            result.put("code", "N");
            e.printStackTrace();
        }

        return ResponseEntity.ok(result);
    }
}
