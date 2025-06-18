package com.juaracoding.fantastic4_thymeleaf.controller;

import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class ApproveBooking {

    @GetMapping("/approvebooking")
    public String approvebooking(Model model) {
        model.addAttribute("menuActive", "approvebooking");
        return "admin/approve-booking";
    }

}
