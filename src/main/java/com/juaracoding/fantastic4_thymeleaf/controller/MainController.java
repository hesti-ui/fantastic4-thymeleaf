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
    public String index(Model model) {
        model.addAttribute("menuActive", "home-admin");
        return "admin/home-admin";
        }

//        @GetMapping("/admin")
//        public String adminPage(HttpSession session, Model model) {
//            String username = (String) session.getAttribute("USR_NAME");
//            if (username == null) {
//                return "redirect:/login";
//            }
//            model.addAttribute("username", username);
//            return "admin/main";
//        }
//    }
}
