package com.juaracoding.fantastic4_thymeleaf.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.juaracoding.fantastic4_thymeleaf.dto.response.ResUserDTO;
import com.juaracoding.fantastic4_thymeleaf.dto.validation.ValUserDTO;
import com.juaracoding.fantastic4_thymeleaf.httpclient.UserDataService;
import com.juaracoding.fantastic4_thymeleaf.utils.GlobalFunction;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.util.*;

@Controller
@RequestMapping("user")
public class UserDataController {

    @Autowired
    private UserDataService userDataService;
    private Map<String,Object> filterColumn = new HashMap<String,Object>();

    public UserDataController() {
        filterColumn.put("nama","Nama");
        filterColumn.put("email","Email");
        filterColumn.put("noTelp","No Telepon");
        filterColumn.put("departemen","Departemen");
        filterColumn.put("jabatan","Jabatan");
    }

    private List<Map<String, Object>> getAll(String jwt) {
        try {
            ResponseEntity<Object> response = userDataService.findAll(jwt);
            if (response.getBody() instanceof Map) {
                Map<String, Object> responseBody = (Map<String, Object>) response.getBody();
                Object data = responseBody.get("data");
                if (data instanceof List) {
                    return (List<Map<String, Object>>) data;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    @GetMapping
    public String defaultPage(Model model, WebRequest request){
        String jwt = GlobalFunction.tokenCheck(model, request);
        if(jwt.equals("redirect:/774$_3")){
            return jwt;
        }
        try{
            ResponseEntity<Object> response = userDataService.findAll(jwt);
            Map<String,Object> map = (Map<String, Object>) response.getBody();
            List<Map<String, Object>> listData = (List<Map<String, Object>>) map.get("data");
            model.addAttribute("listUser", listData);
            model.addAttribute("data", new ValUserDTO());
            GlobalFunction.setGlobalAttribute(model,request,"USER");
        }catch (Exception e){
            e.printStackTrace();
            return "redirect:/3314&5";
        }
        model.addAttribute("menuActive", "userdata");
        return "admin/user-data";
    }

    @GetMapping("/err/{err}")
    public String defaultPageError(Model model,
                                   @PathVariable String err,
                                   WebRequest request){
        String jwt = GlobalFunction.tokenCheck(model, request);
        if(jwt.equals("redirect:/774$_3")){
            return jwt;
        }
        try{
            ResponseEntity<Object> response = userDataService.findAll(jwt);
            Map<String,Object> map = (Map<String, Object>) response.getBody();
            List<Map<String, Object>> listData = (List<Map<String, Object>>) map.get("data");
            model.addAttribute("listUser", listData);
            GlobalFunction.setGlobalAttribute(model,request,"USER");
        }catch (Exception e){
            e.printStackTrace();
            return "redirect:/3314&5";
        }
        if(err.equals("500")){
            err = "Internal Server Error (500)";
        }
        model.addAttribute("errorInternalServer", err);
        return "admin/user-data";
    }

    @PostMapping("")
    public String save(
            @ModelAttribute("data") @Valid ValUserDTO valUserDTO,
            BindingResult bindingResult,
            Model model,
            WebRequest request) {

        if(bindingResult.hasErrors()){
            model.addAttribute("data",valUserDTO);
            return "/admin/user-data";
        }

        ResponseEntity<Object> response = null;
        String jwt = GlobalFunction.tokenCheck(model, request);
        if(jwt.equals("redirect:/774$_3")){
            return jwt;
        }

        try {
            response = userDataService.save(jwt,valUserDTO);
            if (response.getStatusCode().is2xxSuccessful()) {
                return "redirect:/user";
            } else {
                model.addAttribute("errorMessage", "Gagal menyimpan data");
            }
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("errorMessage", "Terjadi kesalahan saat menyimpan data");
        }

        model.addAttribute("data", valUserDTO);
        model.addAttribute("listUser", getAll(jwt));
        return "admin/user-data";
    }

    @GetMapping("/e/{id}")
    public String openModalsEdit(Model model,
                                 @PathVariable Long id,
                                 WebRequest request){
        String jwt = GlobalFunction.tokenCheck(model, request);
        if(jwt.equals("redirect:/774$_3")){
            return jwt;
        }
        try{
            ResponseEntity<Object> response = userDataService.findById(jwt, String.valueOf(id));
            Map<String, Object> map = (Map<String, Object>) response.getBody();
            Map<String, Object> mapData = (Map<String, Object>) map.get("data");
            model.addAttribute("data", new ObjectMapper().convertValue(mapData, ResUserDTO.class));
            model.addAttribute("id", id);
        }catch (Exception e){
            e.printStackTrace();
            return "/admin/user-data";
        }
        return "/admin/user-data";
    }

    @PostMapping("/{id}")
    public String update(@ModelAttribute("data") @Valid ValUserDTO valUserDTO,
                         BindingResult bindingResult,
                         Model model,
                         @PathVariable Long id,
                         WebRequest request){

        String jwt = GlobalFunction.tokenCheck(model, request);
        if(jwt.equals("redirect:/774$_3")){
            return jwt;
        }

        if(bindingResult.hasErrors()){
            model.addAttribute("data", valUserDTO);
            model.addAttribute("id", id);
            return "/admin/user-data";
        }

        try{
            ResponseEntity<Object> response = userDataService.update(jwt, valUserDTO, String.valueOf(id));
            if(response.getStatusCode().is2xxSuccessful()){
                return "redirect:/user";
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        model.addAttribute("data", valUserDTO);
        model.addAttribute("id", id);
        return "/admin/user-data";
    }

    @DeleteMapping("/{id}")
    public String delete(Model model,
                         @PathVariable Long id,
                         WebRequest request){
        String jwt = GlobalFunction.tokenCheck(model, request);
        if(jwt.equals("redirect:/774$_3")){
            return jwt;
        }
        try{
            ResponseEntity<Object> response = userDataService.delete(jwt, String.valueOf(id));
            if(response.getStatusCode().is2xxSuccessful()){
                return "redirect:/user";
            }
        }catch (Exception e){
            e.printStackTrace();
            return "/form-error";
        }
        return "/form-success";
    }
}


