package com.juaracoding.fantastic4_thymeleaf.httpclient;


import com.juaracoding.fantastic4_thymeleaf.dto.validation.ValFasilitasDTO;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "facilitydata-service", url = "http://localhost:8080/facility")
public interface FacilityDataService {

    @GetMapping
    public ResponseEntity<Object> findAll(@RequestHeader("Authorization") String token);

    @GetMapping("/{sort}/{sort-by}/{page}")
    public ResponseEntity<Object> findByParam(
            @RequestHeader("Authorization") String token,
            @PathVariable String sort,
            @PathVariable(value = "sort-by") String sortBy,
            @PathVariable Integer page,
            @RequestParam Integer size,
            @RequestParam String column,
            @RequestParam String value);

    @PostMapping
    public ResponseEntity<Object> save(@RequestHeader("Authorization") String token,
                                       @RequestBody @Valid ValFasilitasDTO valUserDTO);

    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(
            @RequestHeader("Authorization") String token,
            @PathVariable String id);

    @PutMapping("/{id}")
    public ResponseEntity<Object> update( @RequestHeader("Authorization") String token,
                                          @RequestBody @Valid ValFasilitasDTO valUserDTO,
                                          @PathVariable String id);

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(
            @RequestHeader("Authorization") String token,
            @PathVariable String id);

}
