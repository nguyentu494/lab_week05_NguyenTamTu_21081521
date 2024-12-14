/*
 * @ (#) CompanyServiceImpl.java   1.0     03/11/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved
 */

package vn.edu.iuh.fit.backend.services.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import vn.edu.iuh.fit.backend.dto.RegisterCompanyDTO;
import vn.edu.iuh.fit.backend.models.Company;
import vn.edu.iuh.fit.backend.repositories.CompanyRepository;
import vn.edu.iuh.fit.backend.services.CompanyService;

import java.util.List;

/*
 * @description:
 * @author: Tuss Nguyen
 * @date: 03/11/2024
 * @version: 1.0
 */
@Service
public class CompanyServiceImpl implements CompanyService {
    @Autowired
    CompanyRepository companyRepository;

    @Override
    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    @Override
    public Company findCompanyById(long id) {
        return companyRepository.findCompanyById(id);
    }

    @Override
    public Page<Company> findAll(Pageable pageable) {
        return companyRepository.findAll(pageable);
    }

    @Override
    public Company findCompanyByAccount_Id(long id) {
        return companyRepository.findCompanyByAccount_Id(id);
    }

    @Override
    public Company save(Company company) {
        return companyRepository.save(company);
    }

    @Override
    public RegisterCompanyDTO findCompanyDTOByUsername(String username) {
        Company company = companyRepository.findCompanyByAccount_Username(username);
        return RegisterCompanyDTO.builder()
                .number(company.getAddress().getNumber())
                .street(company.getAddress().getStreet())
                .phone(company.getPhone())
                .email(company.getEmail())
                .city(company.getAddress().getCity())
                .country(company.getAddress().getCountry())
                .compName(company.getCompName())
                .about(company.getAbout())
                .webUrl(company.getWebUrl())
                .zipcode(company.getAddress().getZipcode())
                .build();
    }

}
