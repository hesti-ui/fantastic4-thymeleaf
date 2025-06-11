package com.juaracoding.fantastic4_thymeleaf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.juaracoding.fantastic4_thymeleaf.httpclient.AuthService;
import com.juaracoding.fantastic4_thymeleaf.httpclient.BookingRoomService;
import com.juaracoding.fantastic4_thymeleaf.dto.validation.BookingRoomDTO;
import org.springframework.beans.factory.annotation.Autowired;

@Controller
@RequestMapping("/admin")
public class BookingRoomController {

    @Autowired
    private BookingRoomService bookingRoomService;

    @GetMapping("/booking")
    public String bookingRoom(Model model) {
        model.addAttribute("menuActive", "booking");
        return "admin/booking";
    }

    @PostMapping("/booking")
    public String bookRoom(@RequestParam("roomId") Long roomId, Model model) {
        // Proses pemesanan ruangan
        boolean success = bookingRoomService.bookRoom(roomId);
        model.addAttribute("menuActive", "booking");
        model.addAttribute("success", success);
        return "admin/booking";
    }



}
