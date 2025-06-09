package com.juaracoding.fantastic4_thymeleaf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class FacilityDataController {

    @GetMapping("/facilitydata")
    public String facilitydata(Model model) {
        model.addAttribute("menuActive", "facilitydata");
        return "admin/facility-data";
    }

}
