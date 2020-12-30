package com.travelfactory.partners.services;

import com.travelfactory.partners.dao.CompanyRepository;
import com.travelfactory.partners.dto.CompanyDto;
import com.travelfactory.partners.exceptions.WrongCompanyIdException;
import com.travelfactory.partners.exceptions.WrongFormatException;
import com.travelfactory.partners.model.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository repository;

    @Autowired
    public CompanyServiceImpl(CompanyRepository repository) {
        this.repository = repository;
    }

    @Override
    public CompanyDto addCompany(CompanyDto companyDto) {
        if (companyDto.getId() != null &&
                repository.findById(companyDto.getId()).orElse(null) != null) {
            throw new WrongCompanyIdException(companyDto.getId(), "already exists");
        }
        if (!checkPhoneNumber(companyDto.getPhone())) {
            throw new WrongFormatException("phone");
        }
        if (!checkEmail(companyDto.getEmail())) {
            throw new WrongFormatException("email");
        }
        Company company = new Company(
                companyDto.getId(),
                companyDto.getName(),
                companyDto.getFirstName(),
                companyDto.getEmail(),
                companyDto.getPhone());
        Company result = repository.save(company);

        return companyToCompanyDto(result);
    }

    @Override
    public Iterable<CompanyDto> getAllCompanies() {
        List<Company> response = repository.findAll();
        return response.stream()
                .map(this::companyToCompanyDto)
                .collect(Collectors.toList());
    }

    @Override
    public CompanyDto getCompanyById(Integer id) {
        Company company = repository.findById(id).orElseThrow(() ->
                new WrongCompanyIdException(id, "not found"));
        return companyToCompanyDto(company);
    }

    @Override
    public List<CompanyDto> getCompanyByName(String name) {
        Company companyMatcher = new Company();
        companyMatcher.setName(name);
        Example<Company> companyExample = Example.of(companyMatcher);
        List<Company> companies = repository.findAll(companyExample);

        return companies.stream()
                .map(this::companyToCompanyDto)
                .collect(Collectors.toList());
    }

    @Override
    public CompanyDto updateCompany(CompanyDto companyDto) {
        if (companyDto.getId() == null) {
            throw new WrongCompanyIdException(null, "cannot be updated");
        }
        repository.findById(companyDto.getId()).orElseThrow(() ->
                new WrongCompanyIdException(companyDto.getId(), "not found"));

        if (!checkPhoneNumber(companyDto.getPhone())) {
            throw new WrongFormatException("phone");
        }
        if (!checkEmail(companyDto.getEmail())) {
            throw new WrongFormatException("email");
        }
        Company company = new Company(
                companyDto.getId(),
                companyDto.getName(),
                companyDto.getFirstName(),
                companyDto.getEmail(),
                companyDto.getPhone());
        Company result = repository.save(company);

        return companyToCompanyDto(result);
    }

    @Override
    public CompanyDto removeCompany(Integer id) {
        Company candidate = repository.findById(id).orElse(null);
        if (candidate != null) {
            repository.deleteById(id);
            return companyToCompanyDto(candidate);
        }
        return null;
    }

    private CompanyDto companyToCompanyDto(Company company) {

        return new CompanyDto(
                company.getId(),
                company.getName(),
                company.getEmail(),
                company.getFirstname(),
                company.getPhone());
    }

    private boolean checkPhoneNumber(String phone) {
        String pattern = "^[+]*[(]?[0-9]{1,4}[)]?[-\\s.0-9]*$";
        if (phone != null && !phone.matches(pattern)) {
            return false;
        }
        return true;
    }

    private boolean checkEmail(String email) {
        String pattern = "^[\\w-.]+@([\\w-]+.)+[\\w-]{2,4}$";
        if (email != null && !email.matches(pattern)) {
            return false;
        }
        return true;
    }
}
