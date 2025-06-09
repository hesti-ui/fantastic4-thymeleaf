package com.juaracoding.fantastic4_thymeleaf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class UserDataController {

    @GetMapping("/userdata")
    public String userdata(Model model) {
        model.addAttribute("menuActive", "userdata");
        return "admin/user-data";
    }

}
