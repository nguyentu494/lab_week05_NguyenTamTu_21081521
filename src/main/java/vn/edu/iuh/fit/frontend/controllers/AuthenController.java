/*
 * @ (#) AuthenController.java   1.0     10/11/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved
 */

package vn.edu.iuh.fit.frontend.controllers;

/*
 * @description:
 * @author: Tuss Nguyen
 * @date: 10/11/2024
 * @version: 1.0
 */

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import vn.edu.iuh.fit.backend.dto.RegisterAccountDTO;
import vn.edu.iuh.fit.backend.dto.RegisterCandidateDTO;
import vn.edu.iuh.fit.backend.dto.RegisterCompanyDTO;
import vn.edu.iuh.fit.backend.repositories.AccountRepository;
import vn.edu.iuh.fit.backend.repositories.CompanyRepository;
import vn.edu.iuh.fit.backend.services.*;

@Controller
public class AuthenController {

    @Autowired
    private AccountService accountService;
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private CandidateService candidateService;
    @Autowired
    private AddressService addressService;
    @Autowired
    private AccountDtoService accountDtoService;
    @Autowired
    private RegistrationService registrationService;

    @GetMapping("/register")
    public ModelAndView register(){
        ModelAndView mav = new ModelAndView();
        RegisterAccountDTO account = new RegisterAccountDTO();
        mav.addObject("account", account);
        mav.setViewName("auth/register");
        return mav;
    }

    @PostMapping("/register")
    public ModelAndView register( @Valid @ModelAttribute RegisterAccountDTO account, BindingResult resultAccount
                                , @Valid @ModelAttribute RegisterCandidateDTO candidateSave, BindingResult resultCandidate
                                , @Valid @ModelAttribute RegisterCompanyDTO companySave, BindingResult resultCompany
                                , @RequestParam(required = false) String status, HttpSession session){


        ModelAndView mav = new ModelAndView();

        System.out.println(candidateSave);

        if(status.equalsIgnoreCase("false")){
            boolean check = accountDtoService.findByUsername(account.getUsername());

            if(check){
                resultAccount.addError(new FieldError("account", "username", "Username is already existed!"));
            }

            if(resultAccount.hasErrors()){
                mav.addObject("account", account);
                mav.addObject("org.springframework.validation.BindingResult.account", resultAccount);
                mav.setViewName("auth/register");
                return mav;
            }

                RegisterAccountDTO accountSave = new RegisterAccountDTO();
                accountSave.setPassword(account.getPassword());
                accountSave.setUsername(account.getUsername());
                accountSave.setRole(account.getRole());
                session.setAttribute("accountSave", accountSave);
                mav.addObject("accountSave", accountSave);

                if(account.getRole().toString().equals("CANDIDATE")){
                    RegisterCandidateDTO candidate = new RegisterCandidateDTO();
                    mav.addObject("candidate", candidate);

                }else if(account.getRole().toString().equals("COMPANY")){
                    RegisterCompanyDTO company = RegisterCompanyDTO.builder().build();
                    mav.addObject("company", company);
                }
        }else{
            try{
                BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
                RegisterAccountDTO accountSave = (RegisterAccountDTO) session.getAttribute("accountSave");
                accountSave.setPassword(encoder.encode(accountSave.getPassword()));




                if(accountSave.getRole().toString().equals("CANDIDATE")) {
                    if(resultCandidate.hasErrors() ){
                        RegisterCandidateDTO candidate = new RegisterCandidateDTO();
                        mav.addObject("accountSave", accountSave);
                        mav.addObject("candidate", candidate);
                        mav.setViewName("auth/register");
                        return mav;
                    }
                    registrationService.register(candidateSave, accountSave, null);

                }else{
                    if(resultCompany.hasErrors()){
                        RegisterCompanyDTO company = RegisterCompanyDTO.builder().build();
                        mav.addObject("company", company);
                        mav.addObject("accountSave", accountSave);
                        mav.setViewName("auth/register");
                        return mav;
                    }
                    registrationService.register(null, accountSave, companySave);
                }
                mav.addObject("success", true);
            }catch (Exception ex){
                resultAccount.addError(new FieldError("account", "username", ex.getMessage()));
                mav.setViewName("auth/register");
                return mav;
            }

        }

        mav.setViewName("auth/register");
        return mav;
    }

    @GetMapping("/login")
    public ModelAndView login(){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("auth/login-user");
        return mav;
    }


}
