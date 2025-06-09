package com.juaracoding.fantastic4_thymeleaf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class ActivityController {

    @GetMapping("/activity")
    public String activity(Model model) {
        model.addAttribute("menuActive", "activity");
        return "admin/activity";
    }

}
