/*
 * @ (#) AccountCandidateServiceImpl.java   1.0     10/11/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved
 */

package vn.edu.iuh.fit.backend.services.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.iuh.fit.backend.models.Account;
import vn.edu.iuh.fit.backend.models.Candidate;
import vn.edu.iuh.fit.backend.repositories.AccountRepository;
import vn.edu.iuh.fit.backend.services.AccountService;

/*
 * @description:
 * @author: Tuss Nguyen
 * @date: 10/11/2024
 * @version: 1.0
 */

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountRepository accountRepository;

    @Override
    public Account checkLogin(String username, String password) {
        try{
            return accountRepository.findByUsernameAndPassword(username, password);
        }catch (Exception e){
            return null;
        }
    }
}
