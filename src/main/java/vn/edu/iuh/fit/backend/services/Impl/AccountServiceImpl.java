/*
 * @ (#) AccountCandidateServiceImpl.java   1.0     10/11/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved
 */

package vn.edu.iuh.fit.backend.services.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import vn.edu.iuh.fit.backend.dto.RegisterAccountDTO;
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
public class AccountServiceImpl implements AccountService, UserDetailsService {
    @Autowired
    private AccountRepository accountRepository;

    @Override
    public RegisterAccountDTO checkLogin(String username, String password) {
        Account account = accountRepository.findByUsernameAndPassword(username, password);
        if(account != null){
            RegisterAccountDTO registerAccountDTO = new RegisterAccountDTO();
            registerAccountDTO.setUsername(account.getUsername());
            registerAccountDTO.setPassword(account.getPassword());
            registerAccountDTO.setRole(account.getRole());
            return registerAccountDTO;
        }
        return null;
    }

    @Override
    public Account register(Account account) {
        return accountRepository.save(account);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Account account = accountRepository.findByUsername(username);

        if(account != null){
            UserDetails springUser = User.withUsername(account.getUsername())
                    .password(account.getPassword())
                    .roles(account.getRole().toString())
                    .build();
            return springUser;
        }

        return null;
    }

    @Override
    public Account findByUsername(String username) {
        return null;
    }
}
