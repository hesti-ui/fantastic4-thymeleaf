package com.juaracoding.fantastic4_thymeleaf.httpclient;

import com.juaracoding.fantastic4_thymeleaf.dto.validation.LoginDTO;
import com.juaracoding.fantastic4_thymeleaf.dto.validation.RegisDTO;
import com.juaracoding.fantastic4_thymeleaf.dto.validation.VerifyRegisDTO;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "auth-services",url = "http://localhost:8080/auth")
public interface AuthService {

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody LoginDTO loginDTO);

    @PostMapping("/regis")
    public ResponseEntity<Object> registration(@RequestBody @Valid RegisDTO regisDTO);

    @PostMapping("/verify-regis")
    public ResponseEntity<Object> verifyRegis(@RequestBody VerifyRegisDTO verifyRegisDTO);
}