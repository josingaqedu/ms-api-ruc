package com.codigo.ms_api_ruc.controller;

import com.codigo.ms_api_ruc.enity.CompanyEntity;
import com.codigo.ms_api_ruc.service.CompanyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/personaJuridica")
public class CompanyController {
    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @PostMapping
    public ResponseEntity<CompanyEntity> getCompany(@RequestParam(name = "ruc") String ruc) {
        CompanyEntity companyEntity = companyService.getCompany(ruc);
        return new ResponseEntity<>(companyEntity, HttpStatus.OK);
    }
}
