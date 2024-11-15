/*
 * @ (#) CompanyResource.java   1.0     10/11/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved
 */

package vn.edu.iuh.fit.backend.resources;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import vn.edu.iuh.fit.backend.models.Company;
import vn.edu.iuh.fit.backend.models.Job;

import java.util.List;

/*
 * @description:
 * @author: Tuss Nguyen
 * @date: 10/11/2024
 * @version: 1.0
 */
public interface CompanyResource {
    ResponseEntity<Company> insert(Company company);
    ResponseEntity<Company> update(long id, Company company);
    ResponseEntity<Company> delete(long id);
    ResponseEntity<Company> getById(long id);
    ResponseEntity<Page<Company>> findAll(int page);
}
