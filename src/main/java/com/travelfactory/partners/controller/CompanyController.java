package com.travelfactory.partners.controller;

import com.travelfactory.partners.dto.CompanyDto;
import com.travelfactory.partners.services.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/marketing/ws/partner/campaign")
public class CompanyController {

    private final CompanyService service;

    @Autowired
    public CompanyController(CompanyService service) {
        this.service = service;
    }

    @PostMapping("/")
    public CompanyDto addCompany(@RequestBody CompanyDto company) {
        return service.addCompany(company);
    }

    @GetMapping("/all")
    public Iterable<CompanyDto> getAllCompany() {
        return service.getAllCompanies();
    }

    @GetMapping("/id/{id}")
    public CompanyDto getCompanyById(@PathVariable Integer id) {
        return service.getCompanyById(id);
    }

    @GetMapping("/name/{name}")
    public List<CompanyDto> getCompanyByName(@PathVariable String name) {
        return service.getCompanyByName(name);
    }

    @PutMapping("/{id}")
    public CompanyDto updateCompany(@PathVariable Integer id, @RequestBody CompanyDto company) {
        return service.updateCompany(company);
    }

    @DeleteMapping("/{id}")
    public CompanyDto removeCompany(@PathVariable Integer id) {
        return service.removeCompany(id);
    }
}
