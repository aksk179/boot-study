package com.ksj.bootstudy.controller.role;

import com.ksj.bootstudy.model.Role;
import com.ksj.bootstudy.service.role.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class RoleRestController {

    @Autowired
    RoleService roleService;

    @RequestMapping(value = "/role/findById", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> findById(@RequestBody Role role) {
        Map<String, Object> result = new HashMap<>();
        try {
            Role data = roleService.findById(role.getId()).orElse(new Role());
            result.put("data", data);
            result.put("code", "Y");
        } catch (Exception e) {
            result.put("code", "N");
            e.printStackTrace();
        }

        return ResponseEntity.ok(result);
    }

    @RequestMapping(value = "/role/save", method = RequestMethod.PUT)
    public ResponseEntity<Map<String, Object>> save(@RequestBody Role role) {
        Map<String, Object> result = new HashMap<>();
        try {
            Role data = roleService.save(role);
            result.put("data", data);
            result.put("code", "Y");
        } catch (Exception e) {
            result.put("code", "N");
            e.printStackTrace();
        }

        return ResponseEntity.ok(result);
    }
}
