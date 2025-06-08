package com.juaracoding.fantastic4_thymeleaf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

    @GetMapping("")
    public String index(Model model) {
        model.addAttribute("menuActive", "main");
        return "user/main";
    }

    @GetMapping("/booking")
    public String booking(Model model) {
        model.addAttribute("menuActive", "userbooking");
        return "user/booking";
    }

    @GetMapping("/activity")
    public String activity(Model model) {
        model.addAttribute("menuActive","useractivity");
        return "user/activity";
    }

    @GetMapping("/changepassword")
    public String changePassword(Model model) {
        model.addAttribute("menuActive", "changepassword");
        return "user/change-password";
    }
}
