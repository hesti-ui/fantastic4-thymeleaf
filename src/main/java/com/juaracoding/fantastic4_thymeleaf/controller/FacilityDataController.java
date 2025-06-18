package com.juaracoding.fantastic4_thymeleaf.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.juaracoding.fantastic4_thymeleaf.dto.response.ResFasilitasDTO;
import com.juaracoding.fantastic4_thymeleaf.dto.validation.ValFasilitasDTO;
import com.juaracoding.fantastic4_thymeleaf.httpclient.FacilityDataService;
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
@RequestMapping("facility")
public class FacilityDataController {

    @Autowired
    private FacilityDataService facilityDataService;
    private Map<String,Object> filterColumn = new HashMap<String,Object>();

    public FacilityDataController() {
        filterColumn.put("namaFasilitas","Nama Fasilitas");
        filterColumn.put("jumlah","Jumlah");
    }

    private List<Map<String, Object>> getAll(String jwt) {
        try {
            ResponseEntity<Object> response = facilityDataService.findAll(jwt);
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
        try {
            ResponseEntity<Object> response = facilityDataService.findAll(jwt);
            Map<String,Object> map = (Map<String, Object>) response.getBody();
            List<Map<String, Object>> listData = (List<Map<String, Object>>) map.get("data");
            model.addAttribute("listFacility", listData);
            GlobalFunction.setGlobalAttribute(model,request,"FACILITY");
        }catch (Exception e){
            e.printStackTrace();
            return "redirect:/3314&5";
        }
        model.addAttribute("menuActive", "userdata");
        return "/admin/facility-data";
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
            ResponseEntity<Object> response = facilityDataService.findAll(jwt);
            Map<String,Object> map = (Map<String, Object>) response.getBody();
            List<Map<String, Object>> listData = (List<Map<String, Object>>) map.get("data");
            model.addAttribute("listFacility", listData);
            GlobalFunction.setGlobalAttribute(model,request,"FACILITY");
        }catch (Exception e){
            e.printStackTrace();
            return "redirect:/3314&5";
        }
        if(err.equals("500")){
            err = "Internal Server Error (500)";
        }
        model.addAttribute("errorInternalServer", err);
        return "admin/facility-data";
    }

    @PostMapping
    public String save(
            @ModelAttribute("data") @Valid ValFasilitasDTO valFasilitasDTO,
            BindingResult bindingResult,
            Model model,
            WebRequest request) {

        String jwt = GlobalFunction.tokenCheck(model, request);
        if (jwt.equals("redirect:/774$_3")) {
            return jwt;
        }

        if (bindingResult.hasErrors()) {
            model.addAttribute("listFacility", getAll(jwt));
            return "admin/facility-data";
        }

        try {
            ResponseEntity<Object> response = facilityDataService.save(jwt, valFasilitasDTO);
            if (response.getStatusCode().is2xxSuccessful()) {
                return "redirect:/facility";
            } else {
                model.addAttribute("errorMessage", "Gagal menyimpan data");
            }
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("errorMessage", "Terjadi kesalahan saat menyimpan data");
        }

        model.addAttribute("data", valFasilitasDTO);
        model.addAttribute("listFacility", getAll(jwt));
        return "admin/facility-data";
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
            ResponseEntity<Object> response = facilityDataService.findById(jwt, String.valueOf(id));
            Map<String, Object> map = (Map<String, Object>) response.getBody();
            Map<String, Object> mapData = (Map<String, Object>) map.get("data");
            model.addAttribute("data", new ObjectMapper().convertValue(mapData, ResFasilitasDTO.class));
            model.addAttribute("id", id);
        }catch (Exception e){
            e.printStackTrace();
            return "/admin/facility-data";
        }
        return "/admin/facility-data";
    }

    @PostMapping("/{id}")
    public String update(@ModelAttribute("data") @Valid ValFasilitasDTO valFasilitasDTO,
                         BindingResult bindingResult,
                         Model model,
                         @PathVariable Long id,
                         WebRequest request){

        String jwt = GlobalFunction.tokenCheck(model, request);
        if(jwt.equals("redirect:/774$_3")){
            return jwt;
        }

        if(bindingResult.hasErrors()){
            model.addAttribute("data", valFasilitasDTO);
            model.addAttribute("id", id);
            return "/admin/facility-data";
        }

        try{
            ResponseEntity<Object> response = facilityDataService.update(jwt, valFasilitasDTO, String.valueOf(id));
            if(response.getStatusCode().is2xxSuccessful()){
                return "redirect:/facility";
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        model.addAttribute("data", valFasilitasDTO);
        model.addAttribute("id", id);
        return "/admin/facility-data";
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
            ResponseEntity<Object> response = facilityDataService.delete(jwt, String.valueOf(id));
            if(response.getStatusCode().is2xxSuccessful()){
                return "redirect:/facility";
            }
        }catch (Exception e){
            e.printStackTrace();
            return "/form-error";
        }
        return "/form-success";
    }
}


