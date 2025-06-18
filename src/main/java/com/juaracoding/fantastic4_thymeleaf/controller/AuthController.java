package com.juaracoding.fantastic4_thymeleaf.controller;

import com.juaracoding.fantastic4_thymeleaf.config.OtherConfig;
import com.juaracoding.fantastic4_thymeleaf.dto.validation.LoginDTO;
import com.juaracoding.fantastic4_thymeleaf.httpclient.AuthService;
import com.juaracoding.fantastic4_thymeleaf.httpclient.RefreshTokenService;
import com.juaracoding.fantastic4_thymeleaf.security.BcryptImpl;
import com.juaracoding.fantastic4_thymeleaf.utils.GenerateStringMenu;
import com.juaracoding.fantastic4_thymeleaf.utils.GlobalFunction;
import jakarta.validation.Valid;
import org.bouncycastle.util.encoders.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("auth")
public class AuthController {

    private static final Logger log = LoggerFactory.getLogger(AuthController.class);
    @Autowired
    private AuthService authService;

    @Autowired
    private RefreshTokenService refreshTokenService;

    @PostMapping("/login")
    public String login(Model model,
                        @Valid @ModelAttribute("usr") LoginDTO loginDTO,
                        BindingResult result, WebRequest webRequest) {

        String strAnswer = loginDTO.getHiddenCaptcha();
        String decodePassword = new String(Base64.decode(loginDTO.getPassword()));
        GlobalFunction.matchingPattern(decodePassword,"^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[@_#\\-$])[\\w].{8,15}$",
                "password","Format Password Tidak Valid","usr",result);

//        Boolean isValid = false;
//        if(OtherConfig.getEnableAutomationTesting().equals("y")){
//            isValid = loginDTO.getCaptcha().equals(strAnswer);
//        }else{
//            isValid = BcryptImpl.verifyHash(loginDTO.getCaptcha(),strAnswer);
//        }
//
//        if(result.hasErrors() || !isValid){
//            if(!isValid){
//                model.addAttribute("captchaMessage", "Invalid Captcha");
//            }
//            GlobalFunction.getCaptchaLogin(loginDTO);
//            return "/auth/login";
//        }

        loginDTO.setPassword(decodePassword);
        loginDTO.setCaptcha("");
        loginDTO.setHiddenCaptcha("");
        loginDTO.setRealCaptcha("");

        ResponseEntity<Object> response = null;
        String tokenJwt = "";
        String menuNavBar = "";
        String urlImg ="";

        try {
            response = authService.login(loginDTO);
            Map<String,Object> map = (Map<String, Object>) response.getBody();
            Map<String,Object> mapData = (Map<String, Object>) map.get("data");
            tokenJwt = (String) mapData.get("token");
            List<Map<String,Object>> listMenu = (List<Map<String, Object>>) mapData.get("menu");
            menuNavBar = new GenerateStringMenu().stringMenu(listMenu);
            urlImg = map.get("urlImage")==null?null:map.get("urlImage").toString();
//            System.out.println("Menu Nav Bar  : " + menuNavBar);
        }catch (Exception e){
            System.out.println("Error : "+e.getMessage());
            GlobalFunction.getCaptchaLogin(loginDTO);
            return "/auth/login";
        }

        /** input ke table session */
        webRequest.setAttribute("JWT",tokenJwt,1);
        webRequest.setAttribute("USR_NAME",loginDTO.getUsername(),1);
        webRequest.setAttribute("PASSWORD",loginDTO.getPassword(),1);
        webRequest.setAttribute("MENU_NAVBAR",menuNavBar,1);

        model.addAttribute("USR_NAME",loginDTO.getUsername());
        model.addAttribute("MENU_NAVBAR",menuNavBar);
        model.addAttribute("URL_IMG",urlImg);
        return "/admin/home-admin";
    }
}
