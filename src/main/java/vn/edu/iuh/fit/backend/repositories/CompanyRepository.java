package vn.edu.iuh.fit.backend.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import vn.edu.iuh.fit.backend.models.Company;

public interface CompanyRepository extends JpaRepository<Company, Long> {
    Company findCompanyById(long id);
    Page<Company> findAll(Pageable pageable);
    Company findCompanyByAccount_Id(long id);
    Company findCompanyByAccount_Username(String username);
}