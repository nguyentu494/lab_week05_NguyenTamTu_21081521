/*
 * @ (#) SecurityConfig.java   1.0     01/12/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved
 */

package vn.edu.iuh.fit.backend.configs;

/*
 * @description:
 * @author: Tuss Nguyen
 * @date: 01/12/2024
 * @version: 1.0
 */

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests( auth-> auth
                                .requestMatchers("/").permitAll()

                                .requestMatchers("/login").permitAll()
                                .requestMatchers("/companies/**").hasRole("COMPANY")
                                .requestMatchers("/candidate/**").hasRole("CANDIDATE")
                                .requestMatchers("/register").permitAll()
                                .anyRequest().authenticated()
//                                .anyRequest().permitAll()
                )
                .formLogin(form -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/",true)
                )
                .logout(config -> config.logoutSuccessUrl("/")
                )
                .build();
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
