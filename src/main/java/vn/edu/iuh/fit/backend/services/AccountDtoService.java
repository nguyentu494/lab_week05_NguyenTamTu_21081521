/*
 * @ (#) AccountDtoService.java   1.0     01/12/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved
 */

package vn.edu.iuh.fit.backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import vn.edu.iuh.fit.backend.dto.RegisterAccountDTO;
import vn.edu.iuh.fit.backend.models.Account;
import vn.edu.iuh.fit.backend.repositories.AccountRepository;

/*
 * @description:
 * @author: Tuss Nguyen
 * @date: 01/12/2024
 * @version: 1.0
 */

@Service
public class AccountDtoService {

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private AccountService accountService;

    public boolean findByUsername(String username){
        Account account = accountRepository.findByUsername(username);
        if(account == null){
            return false;
        }
        return true;
    }


}
