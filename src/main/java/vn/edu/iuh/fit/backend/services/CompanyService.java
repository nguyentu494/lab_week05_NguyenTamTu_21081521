package vn.edu.iuh.fit.backend.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import vn.edu.iuh.fit.backend.models.Company;
import vn.edu.iuh.fit.backend.repositories.CompanyRepository;

import java.util.List;

public interface CompanyService  {

    List<Company> getAllCompanies();
    Company findCompanyById(long id);
    Page<Company> findAll(Pageable pageable);
    Company findCompanyByAccount_Id(long id);
}