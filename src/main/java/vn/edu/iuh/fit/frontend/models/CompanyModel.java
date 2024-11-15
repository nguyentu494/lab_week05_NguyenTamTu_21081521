/*
 * @ (#) CompanyModel.java   1.0     10/11/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved
 */

package vn.edu.iuh.fit.frontend.models;

/*
 * @description:
 * @author: Tuss Nguyen
 * @date: 10/11/2024
 * @version: 1.0
 */


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import vn.edu.iuh.fit.backend.models.Company;

@Component
public class CompanyModel {

    private RestTemplate restTemplate = new RestTemplate();
    private final String uri = "http://localhost:8080/api/companies";
    private ObjectMapper mapper = new ObjectMapper();

    public Page<Company> getAllCompanies(){
        Page<Company> companies = restTemplate.getForObject(uri+"/1", Page.class);
        return mapper.convertValue(companies, new TypeReference<Page<Company>>() {});
    }

}
