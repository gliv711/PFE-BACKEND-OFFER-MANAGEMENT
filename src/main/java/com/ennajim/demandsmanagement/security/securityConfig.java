package com.ennajim.demandsmanagement.security;

import com.ennajim.demandsmanagement.filter.CustomAuthorizationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.http.HttpMethod.*;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity

public class securityConfig  extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        super.configure(auth);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {


        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.authorizeRequests().antMatchers("/api/login/**","/api/user/refreshtoken","/api/user","/api/user/email/{email}","/api/company","/api/admin","/swagger-ui/").permitAll();


        http.authorizeRequests().antMatchers(GET,"/api/user/all/**","/api/user/**","/api/user/count","/api/demands/all","/api/demands/{Id}","/api/demands/count").hasAnyAuthority("admin", "adminsuper","company");

        http.authorizeRequests().antMatchers(DELETE,"/api/user/{Id}","/api/demands/{Id}").hasAnyAuthority("admin", "adminsuper");
        http.authorizeRequests().antMatchers(POST,"/api/demands").hasAnyAuthority("admin", "adminsuper","company");




        http.authorizeRequests().anyRequest().authenticated();

        http.addFilterBefore(new CustomAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);

    }
    }

