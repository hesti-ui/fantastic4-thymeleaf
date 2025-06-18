package com.juaracoding.fantastic4_thymeleaf.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.juaracoding.fantastic4_thymeleaf.dto.response.ResRuanganDTO;
import com.juaracoding.fantastic4_thymeleaf.dto.validation.ValRuanganDTO;
import com.juaracoding.fantastic4_thymeleaf.httpclient.ManageRoomService;
import com.juaracoding.fantastic4_thymeleaf.utils.GlobalFunction;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("manage-room")
public class ManageRoomController {

    @Autowired
    private ManageRoomService manageRoomService;
    private Map<String,Object> filterColumn = new HashMap<String,Object>();

    public ManageRoomController() {
        filterColumn.put("namaRuangan","Nama Ruangan");
        filterColumn.put("minKapasitas","Min Kapasitas");
        filterColumn.put("maxKapasitas","Max Kapasitas");
        filterColumn.put("lokasi","Lokasi");
    }

    private List<Map<String, Object>> getAll(String jwt) {
        try {
            ResponseEntity<Object> response = manageRoomService.findAll(jwt);
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
            ResponseEntity<Object> response = manageRoomService.findAll(jwt);
            Map<String,Object> map = (Map<String, Object>) response.getBody();
            ObjectMapper objectMapper = new ObjectMapper();
            List<ResRuanganDTO> listData = objectMapper.convertValue(
                    map.get("data"),
                    objectMapper.getTypeFactory().constructCollectionType(List.class, ResRuanganDTO.class)
            );

            model.addAttribute("listRoom", listData);

            GlobalFunction.setGlobalAttribute(model,request,"ROOM");
        }catch (Exception e){
            e.printStackTrace();
            return "redirect:/3314&5";
        }
        model.addAttribute("menuActive", "manageroom");
        return "admin/manage-room";
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
            ResponseEntity<Object> response = manageRoomService.findAll(jwt);
            Map<String,Object> map = (Map<String, Object>) response.getBody();
            ObjectMapper objectMapper = new ObjectMapper();
            List<ResRuanganDTO> listData = objectMapper.convertValue(
                    map.get("data"),
                    objectMapper.getTypeFactory().constructCollectionType(List.class, ResRuanganDTO.class)
            );
            model.addAttribute("listRoom", listData);

            GlobalFunction.setGlobalAttribute(model,request,"ROOM");
        }catch (Exception e){
            e.printStackTrace();
            return "redirect:/3314&5";
        }
        if(err.equals("500")){
            err = "Internal Server Error (500)";
        }
        model.addAttribute("errorInternalServer", err);
        return "admin/manage-room";
    }

    @PostMapping
    public String save(
            @ModelAttribute("data") @Valid ValRuanganDTO valRuanganDTO,
            BindingResult bindingResult,
            Model model,
            WebRequest request) {

        String jwt = GlobalFunction.tokenCheck(model, request);
        if (jwt.equals("redirect:/774$_3")) {
            return jwt;
        }

        if (bindingResult.hasErrors()) {
            model.addAttribute("listRoom", getAll(jwt));
            return "admin/manage-room";
        }

        try {
            ResponseEntity<Object> response = manageRoomService.save(jwt, valRuanganDTO);
            if (response.getStatusCode().is2xxSuccessful()) {
                return "redirect:/manage-room";
            } else {
                model.addAttribute("errorMessage", "Gagal menyimpan data");
            }
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("errorMessage", "Terjadi kesalahan saat menyimpan data");
        }

        model.addAttribute("data", valRuanganDTO);
        model.addAttribute("listRoom", getAll(jwt));
        return "admin/manage-room";
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
            ResponseEntity<Object> response = manageRoomService.findById(jwt, String.valueOf(id));
            Map<String, Object> map = (Map<String, Object>) response.getBody();
            Map<String, Object> mapData = (Map<String, Object>) map.get("data");
            model.addAttribute("data", new ObjectMapper().convertValue(mapData, ResRuanganDTO.class));
            model.addAttribute("id", id);
        }catch (Exception e){
            e.printStackTrace();
            return "/admin/manage-room";
        }
        return "/admin/manage-room";
    }

    @PostMapping("/{id}")
    public String update(@ModelAttribute("data") @Valid ValRuanganDTO valRuanganDTO,
                         BindingResult bindingResult,
                         Model model,
                         @PathVariable Long id,
                         WebRequest request){

        String jwt = GlobalFunction.tokenCheck(model, request);
        if(jwt.equals("redirect:/774$_3")){
            return jwt;
        }

        if(bindingResult.hasErrors()){
            model.addAttribute("data", valRuanganDTO);
            model.addAttribute("id", id);
            return "/admin/manage-room";
        }

        try{
            ResponseEntity<Object> response = manageRoomService.update(jwt, valRuanganDTO, String.valueOf(id));
            if(response.getStatusCode().is2xxSuccessful()){
                return "redirect:/manage-room";
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        model.addAttribute("data", valRuanganDTO);
        model.addAttribute("id", id);
        return "/admin/manage-room";
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
            ResponseEntity<Object> response = manageRoomService.delete(jwt, String.valueOf(id));
            if(response.getStatusCode().is2xxSuccessful()){
                return "redirect:/manage-room";
            }
        }catch (Exception e){
            e.printStackTrace();
            return "/form-error";
        }
        return "/form-success";
    }
}


