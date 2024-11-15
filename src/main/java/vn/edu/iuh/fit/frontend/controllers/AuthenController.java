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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import vn.edu.iuh.fit.backend.models.Account;
import vn.edu.iuh.fit.backend.services.AccountService;

@Controller
@RequestMapping("auth")
public class AuthenController {

    @Autowired
    private AccountService accountService;

    @PostMapping("login")
    public ModelAndView login(@RequestParam("inpUsername") String username, @RequestParam("inpPassword") String password, RedirectAttributes attributes){
        ModelAndView mav = new ModelAndView();
        Account account = accountService.checkLogin(username, password);
        if(account!= null) {
            if(account.getRole().toString().equals("COMPANY")){
                mav.setViewName("redirect:/companies?a="+account.getId());
            }
            else if(account.getRole().toString().equals("CANDIDATE")){
                mav.addObject("account", account);
                mav.setViewName("candidate/candidates");

            }
        }else {
            mav.addObject("message", "Login failed!");
            mav.setViewName("index");
        }
        return mav;
    }
}
