/*
 * @ (#) CompanyResourceImpl.java   1.0     10/11/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved
 */

package vn.edu.iuh.fit.backend.resources.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.edu.iuh.fit.backend.models.Company;
import vn.edu.iuh.fit.backend.resources.CompanyResource;
import vn.edu.iuh.fit.backend.services.CompanyService;

import java.util.List;

/*
 * @description:
 * @author: Tuss Nguyen
 * @date: 10/11/2024
 * @version: 1.0
 */
@RestController
@RequestMapping("/api/companies")
public class CompanyResourceImpl implements CompanyResource {

    @Autowired
    private CompanyService companyService;

    @Override
    public ResponseEntity<Company> insert(Company company) {
        return null;
    }

    @Override
    public ResponseEntity<Company> update(long id, Company company) {
        return null;
    }

    @Override
    public ResponseEntity<Company> delete(long id) {
        return null;
    }

    @Override
    public ResponseEntity<Company> getById(long id) {
        return null;
    }

    @GetMapping("/{pageable}")
    @Override
    public ResponseEntity<Page<Company>> findAll(@PathVariable("pageable") int page) {
        return ResponseEntity.ok(companyService.findAll(PageRequest.of(page, 5)));
    }
}
