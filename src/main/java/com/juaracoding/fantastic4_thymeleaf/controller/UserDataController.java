package com.juaracoding.fantastic4_thymeleaf.controller;

import com.juaracoding.fantastic4_thymeleaf.dto.validation.ValUserDTO;
import com.juaracoding.fantastic4_thymeleaf.httpclient.UserDataService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class UserDataController {

    @Autowired
    private UserDataService userDataService;

    @GetMapping("/userdata")
    public String userdata(Model model) {
        model.addAttribute("menuActive", "userdata");
        return "admin/user-data";
    }
}
