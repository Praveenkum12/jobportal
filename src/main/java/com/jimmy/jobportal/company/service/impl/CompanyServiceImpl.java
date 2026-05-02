package com.jimmy.jobportal.company.service.impl;

import com.jimmy.jobportal.company.repository.CompanyRepository;
import com.jimmy.jobportal.dto.CompanyDto;
//import com.jimmy.jobportal.repository.CompanyRepository;
import com.jimmy.jobportal.company.service.ICompanyService;
import com.jimmy.jobportal.entity.Company;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements ICompanyService {

    private final CompanyRepository companyRepository;

    @Override
    public List<CompanyDto> getAllCompanies() {
        List<Company> companies = companyRepository.findAll();
        return companies.stream().map(this::transformToDto).collect(Collectors.toList());
    }

    private CompanyDto transformToDto(Company company) {
        return new CompanyDto(company.getId(), company.getName(), company.getLogo(),
                company.getIndustry(), company.getSize(), company.getRating(),
                company.getLocations(), company.getFounded(), company.getDescription(),
                company.getEmployees(), company.getWebsite(), company.getCreatedAt());
    }

}
