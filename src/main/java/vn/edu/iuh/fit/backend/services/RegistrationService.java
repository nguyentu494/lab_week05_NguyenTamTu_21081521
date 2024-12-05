/*
 * @ (#) RegistrationService.java   1.0     01/12/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved
 */

package vn.edu.iuh.fit.backend.services;

import com.neovisionaries.i18n.CountryCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.iuh.fit.backend.dto.RegisterAccountDTO;
import vn.edu.iuh.fit.backend.dto.RegisterCandidateDTO;
import vn.edu.iuh.fit.backend.dto.RegisterCompanyDTO;
import vn.edu.iuh.fit.backend.models.Account;
import vn.edu.iuh.fit.backend.models.Address;
import vn.edu.iuh.fit.backend.models.Candidate;
import vn.edu.iuh.fit.backend.models.Company;

/*
 * @description:
 * @author: Tuss Nguyen
 * @date: 01/12/2024
 * @version: 1.0
 */
@Service
public class RegistrationService {
    @Autowired
    private AddressService addressService;
    @Autowired
    private AccountService accountService;
    @Autowired
    private CandidateService candidateService;
    @Autowired
    private CompanyService companyService;

    public boolean register(RegisterCandidateDTO candidateDTO, RegisterAccountDTO accountDTO, RegisterCompanyDTO companyDTO){
        Account account = new Account();
        account.setUsername(accountDTO.getUsername());
        account.setPassword(accountDTO.getPassword());
        account.setRole(accountDTO.getRole());

        if(candidateDTO==null){
            Address address = Address.builder()
                    .city(companyDTO.getCity())
                    .country(CountryCode.VN)
                    .zipcode(companyDTO.getZipcode())
                    .number(companyDTO.getNumber())
                    .build();
            Company company = Company.builder()
                    .about(companyDTO.getAbout())
                    .account(account)
                    .address(address)
                    .phone(companyDTO.getPhone())
                    .compName(companyDTO.getCompName())
                    .webUrl(companyDTO.getWebUrl())
                    .build();
            try{
                accountService.register(account);
                addressService.save(address);
                companyService.save(company);

                return true;
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }else if(companyDTO==null){
            Address address = Address.builder()
                    .city(candidateDTO.getCity())
                    .country(CountryCode.VN)
                    .zipcode(candidateDTO.getZipcode())
                    .number(candidateDTO.getNumber())
                    .build();
            Candidate candidate = Candidate.builder()
                    .dob(candidateDTO.getDob())
                    .phone(candidateDTO.getPhone())
                    .email(candidateDTO.getEmail())
                    .fullName(candidateDTO.getFullName())
                    .account(account)
                    .address(address)
                    .build();
            try{
                accountService.register(account);
                addressService.save(address);
                candidateService.save(candidate);
                return true;

            }catch (Exception ex){
                ex.printStackTrace();
            }
        }
        return false;
    }
}
