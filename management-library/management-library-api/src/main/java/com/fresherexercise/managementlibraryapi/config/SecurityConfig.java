/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fresherexercise.managementlibraryapi.config;

import java.util.Arrays;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;


/**
 *
 * @author Admin
 */
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/auth/login", "/downloadFile/**", "/rabbitmq/*"
                            , "/swagger-resources/**", "/swagger-ui.html", "/v2/api-docs", "/webjars/**").permitAll() // Kh�ng authen nh?ng url n�y
                .antMatchers("/users").hasAnyRole("DIRECTOR") //ch? role "DIRECTOR" m?i truy c?p ???c "/users"
                .antMatchers("/redis-test").hasAnyRole("SALE") //ch? role "SALE" m?i truy c?p ???c "/redis-test"
                .antMatchers("/users/**").hasAnyRole("DEFAULT") //ch? role "DEFAULT" m?i truy c?p ???c "/users/**"
                .antMatchers("/**").permitAll()
                .anyRequest().authenticated();
        http.cors(); // M? CORS
        http.csrf().disable(); // Disable CSRF

        // Filter kiem tra jwt token
        //http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
    }
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("*"));
        configuration.setAllowedHeaders(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
