package com.juaracoding.fantastic4_thymeleaf.controller;


import com.juaracoding.fantastic4_thymeleaf.dto.validation.LoginDTO;
import com.juaracoding.fantastic4_thymeleaf.dto.validation.RegisDTO;
import com.juaracoding.fantastic4_thymeleaf.utils.GlobalFunction;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.context.request.WebRequest;

import java.util.Random;

@Controller
public class DefaultController {

    @GetMapping("/{flag}")
    public String index(Model model, @PathVariable String flag) {

        LoginDTO loginDTO = new LoginDTO();
        GlobalFunction.getCaptchaLogin(loginDTO);
        model.addAttribute("usr", loginDTO);
        if(flag.equals("xyz$413")) {

        }else if(flag.equals("774$_3")) {

        }
        switch (flag){
            case "xyz$413":model.addAttribute("regisSuccess", "REGISTRASI BERHASIL, SILAHKAN LOGIN !!");break;
            case "774$_3":model.addAttribute("authProblem","Silahkan Login Terlebih dahulu !!");break;
            case "3314&5":model.addAttribute("internalError","Server sedang mengalami gangguan !!");break;
            default:model.addAttribute("none","");break;
        }

        return "/auth/login";
    }


    @GetMapping("/regis")
    public String regis(Model model) {

        model.addAttribute("usr", new RegisDTO());
        return "/auth/regis";
    }


    @GetMapping("/logout")
    public String destroySession(HttpServletRequest request){
        request.getSession().invalidate();
        return "redirect:/"+new Random().nextInt(100);
    }

}
