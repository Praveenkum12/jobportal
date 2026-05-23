package com.jimmy.jobportal.company.controller;

import com.jimmy.jobportal.company.service.ICompanyService;
import com.jimmy.jobportal.dto.CompanyDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/companies")
@Tag(name = "Company API", description = "Operations related to company")
public class CompanyController {

    private final ICompanyService companyService;

    @GetMapping(path = "/public", version = "1.0")
    public ResponseEntity<List<CompanyDto>> getAllCompanies() {
        List<CompanyDto> companyDtos = companyService.getAllCompanies();
        return ResponseEntity.ok().body(companyDtos);
    }

}
