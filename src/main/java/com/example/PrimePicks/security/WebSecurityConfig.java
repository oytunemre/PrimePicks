package com.example.PrimePicks.security;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Component;

import static org.springframework.security.config.Customizer.withDefaults;

@Component
public class WebSecurityConfig {

    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .formLogin(withDefaults());
        return http.build();
    }

//    @Override
//    protected void configure(HttpSecurity security) throws Exception
//    {
//        security.httpBasic().disable();
//    }

}