package com.mvc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http

                .csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/","/product").permitAll()
                //neu khong chi dinh method thi se la tat ca phuong thuc deu dc
                .antMatchers(HttpMethod.POST, "/user**").hasAnyRole("ADMIN")
                .antMatchers(HttpMethod.GET,"/user**").hasAnyRole("USER", "ADMIN")
                .anyRequest().authenticated()
                //cái nào khác permit all thì đều phải authenticate httpbasic
                .and()
                .httpBasic();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


}
