package com.travelfactory.partners.services;

import com.travelfactory.partners.dto.CompanyDto;

import java.util.List;

public interface CompanyService {

    CompanyDto addCompany(CompanyDto company);

    Iterable<CompanyDto> getAllCompanies();

    CompanyDto getCompanyById(Integer id);

    List<CompanyDto> getCompanyByName(String name);

    CompanyDto updateCompany(CompanyDto company);

    CompanyDto removeCompany(Integer id);

}
