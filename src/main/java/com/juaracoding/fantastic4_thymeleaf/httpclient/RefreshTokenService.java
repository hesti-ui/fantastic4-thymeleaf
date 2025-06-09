package com.juaracoding.fantastic4_thymeleaf.httpclient;


import com.juaracoding.fantastic4_thymeleaf.dto.validation.LoginDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "refresh-auth-services",url = "http://localhost:8080/aut")
public interface RefreshTokenService {

    @PostMapping("/refresh-token")
    public ResponseEntity<Object> tokenExpired(@RequestBody LoginDTO loginDTO);
}
