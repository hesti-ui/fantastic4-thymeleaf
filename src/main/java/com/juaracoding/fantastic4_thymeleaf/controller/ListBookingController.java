package com.juaracoding.fantastic4_thymeleaf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class ListBookingController {

    @GetMapping("/listbooking")
    public String listbooking(Model model) {
        model.addAttribute("menuActive", "listbooking");
        return "admin/list-booking";
    }

}
