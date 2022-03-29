package com.example.testrestapi.config;

import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;



/**
 *
 * @author anhdv
 */
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/auth/login", "/downloadFile/**", "/rabbitmq/*"
                            , "/swagger-resources/**", "/swagger-ui.html", "/v2/api-docs", "/webjars/**").permitAll() // Không authen những url này
                .antMatchers("/users").hasAnyRole("DIRECTOR") //chỉ role "DIRECTOR" mới truy cập được "/users"
                .antMatchers("/redis-test").hasAnyRole("SALE") //chỉ role "SALE" mới truy cập được "/redis-test"
                .antMatchers("/users/**").hasAnyRole("DEFAULT") //chỉ role "DEFAULT" mới truy cập được "/users/**"
                .anyRequest().authenticated();
        http.cors(); // Mở CORS
        http.csrf().disable(); // Disable CSRF

        // Filter kiểm tra jwt token
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