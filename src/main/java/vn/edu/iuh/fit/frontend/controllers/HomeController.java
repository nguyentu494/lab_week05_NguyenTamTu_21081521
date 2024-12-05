/*
 * @ (#) HomeController.java   1.0     01/12/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved
 */

package vn.edu.iuh.fit.frontend.controllers;

/*
 * @description:
 * @author: Tuss Nguyen
 * @date: 01/12/2024
 * @version: 1.0
 */

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import vn.edu.iuh.fit.backend.models.Company;

import java.util.Collection;

@Controller
public class HomeController {

    @GetMapping({"/", ""})
    public ModelAndView home(Authentication authentication) {
        ModelAndView mav = new ModelAndView();

        if (authentication != null && authentication.isAuthenticated()) {
            Object principal = authentication.getPrincipal();

            if (principal instanceof UserDetails) {
                UserDetails userDetails = (UserDetails) principal;
                System.out.println(principal);

                // Lấy các thông tin cơ bản
                String username = userDetails.getUsername();
                Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();

                // Log thông tin user
                System.out.println("Username: " + username);
                System.out.println("Authorities: " + authorities);

                // Nếu bạn có thêm thông tin trong userDetails, hãy cast về lớp custom của bạn
                if (userDetails instanceof Company) {
                    Company customUserDetails = (Company) userDetails;
                    System.out.println("Full Name: " + customUserDetails.getCompName());
                    System.out.println("Email: " + customUserDetails.getEmail());
                }

                // Chuyển hướng dựa trên role
                if (authorities.toString().equalsIgnoreCase("[ROLE_COMPANY]")) {
                    mav.setViewName("redirect:/companies");
                    return mav;
                }
            }
        }

        mav.setViewName("dashboard");
        return mav;
    }

}
