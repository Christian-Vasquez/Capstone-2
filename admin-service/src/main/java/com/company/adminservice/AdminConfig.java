package com.company.adminservice;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class AdminConfig {

    @Autowired
    DataSource dataSource;

    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder authBuilder) throws Exception {

        PasswordEncoder encoder = new BCryptPasswordEncoder();

        authBuilder.jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery(
                        "select username, password, enabled from users where username = ?")
                .authoritiesByUsernameQuery(
                        "select username, authority from authorities where username = ?")
                .passwordEncoder(encoder);
    }

    public void configure(HttpSecurity httpSecurity) throws Exception {

        httpSecurity.httpBasic();

        httpSecurity.authorizeRequests()
                .mvcMatchers(HttpMethod.DELETE, "/product/{id}").hasAuthority("ROLE_ADMIN")
                .mvcMatchers(HttpMethod.PUT, "/product").hasAuthority("ROLE_LEAD")
                .mvcMatchers(HttpMethod.POST, "/product").hasAuthority("ROLE_MANAGER")
                .mvcMatchers(HttpMethod.DELETE, "/customer/{id}").hasAuthority("ROLE_ADMIN")
                .mvcMatchers(HttpMethod.PUT, "/customer").hasAuthority("ROLE_LEAD")
                .mvcMatchers(HttpMethod.POST, "/customer").hasAuthority("ROLE_LEAD")
                .mvcMatchers(HttpMethod.DELETE, "/level-up/{id}").hasAuthority("ROLE_ADMIN")
                .mvcMatchers(HttpMethod.PUT, "/level-up").hasAuthority("ROLE_LEAD")
                .mvcMatchers(HttpMethod.POST, "/level-up").hasAuthority("ROLE_MANAGER")
                .mvcMatchers(HttpMethod.DELETE, "/inventory/{id}").hasAuthority("ROLE_ADMIN")
                .mvcMatchers(HttpMethod.PUT, "/inventory").hasAuthority("ROLE_EMPLOYEE")
                .mvcMatchers(HttpMethod.POST, "/inventory").hasAuthority("ROLE_MANAGER")
                .mvcMatchers(HttpMethod.DELETE, "/invoices/{id}").hasAuthority("ROLE_ADMIN")
                .mvcMatchers(HttpMethod.PUT, "/invoices").hasAuthority("ROLE_EMPLOYEE")
                .mvcMatchers(HttpMethod.POST, "/invoices").hasAuthority("ROLE_MANAGER")
                .anyRequest().permitAll();

        httpSecurity
                .logout()
                .clearAuthentication(true)
                .logoutSuccessUrl("/allDone")
                .deleteCookies("JSESSIONID")
                .deleteCookies("XSRF-TOKEN")
                .invalidateHttpSession(true);

        httpSecurity
                .csrf()
                .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());

    }
}
