package com.ksj.bootstudy.controller;

import com.ksj.bootstudy.service.email.EmailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class EmailController {

    @Autowired
    EmailService emailService;

    @RequestMapping(value = "/verify-email.do", method = RequestMethod.GET)
    public String verifyEmail(@RequestParam("token") String token) {
        return emailService.verifyEmail(token);
    }
}
