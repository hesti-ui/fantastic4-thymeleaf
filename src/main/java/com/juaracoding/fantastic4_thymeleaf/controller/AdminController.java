package com.juaracoding.fantastic4_thymeleaf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AdminController {

    @GetMapping("/admin")
    public String index(Model model) {
        model.addAttribute("menuActive", "main");
        return "admin/main";
    }

    @GetMapping("/user-data")
    public String userdata(Model model) {
        model.addAttribute("menuActive", "user-data");
        return "admin/user-data";
    }

    @GetMapping("/facility-data")
    public String facilitydata(Model model) {
        model.addAttribute("menuActive", "facility-data");
        return "admin/facility-data";
    }

    @GetMapping("/manage-room")
    public String manageroom(Model model) {
        model.addAttribute("menuActive","manage-room");
        return "admin/manage-room";
    }

    @GetMapping("/list-booking")
    public String listbooking(Model model) {
        model.addAttribute("menuActive", "list-booking");
        return "admin/list-booking";
    }

    @GetMapping("/booking-room")
    public String bookingroom(Model model) {
        model.addAttribute("menuActive", "booking-room");
        return "admin/booking-room";
    }

    @GetMapping("/activity")
    public String activity(Model model) {
        model.addAttribute("menuActive", "activity");
        return "admin/activity";
    }
}
