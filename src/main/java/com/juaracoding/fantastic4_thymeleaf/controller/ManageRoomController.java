package com.juaracoding.fantastic4_thymeleaf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class ManageRoomController {

    @GetMapping("/manageroom")
    public String manageroom(Model model) {
        model.addAttribute("menuActive", "manageroom");
        return "admin/manage-room";
    }

}
