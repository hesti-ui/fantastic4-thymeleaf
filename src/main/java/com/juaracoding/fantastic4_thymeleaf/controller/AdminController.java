package com.juaracoding.fantastic4_thymeleaf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @GetMapping("")
    public String index(Model model) {
        model.addAttribute("menuActive", "main");
        return "admin/main";
    }

    @GetMapping("/userdata")
    public String userdata(Model model) {
        model.addAttribute("menuActive", "userdata");
        return "admin/user-data";
    }

    @GetMapping("/facilitydata")
    public String facilitydata(Model model) {
        model.addAttribute("menuActive", "facilitydata");
        return "admin/facility-data";
    }

    @GetMapping("/manageroom")
    public String manageroom(Model model) {
        model.addAttribute("menuActive","manageroom");
        return "admin/manage-room";
    }

    @GetMapping("/listbooking")
    public String listbooking(Model model) {
        model.addAttribute("menuActive", "listbooking");
        return "admin/list-booking";
    }

    @GetMapping("/booking")
    public String bookingRoom(Model model) {
        model.addAttribute("menuActive", "booking");
        return "admin/booking";
    }

    @GetMapping("/activity")
    public String activity(Model model) {
        model.addAttribute("menuActive", "activity");
        return "admin/activity";
    }

}
