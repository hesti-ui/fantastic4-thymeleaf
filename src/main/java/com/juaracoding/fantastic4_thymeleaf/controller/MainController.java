package com.juaracoding.fantastic4_thymeleaf.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class MainController {

    @GetMapping("")
    public String main(Model model) {
        model.addAttribute("menuActive", "home-admin");
        return "admin/home-admin";
    }
}