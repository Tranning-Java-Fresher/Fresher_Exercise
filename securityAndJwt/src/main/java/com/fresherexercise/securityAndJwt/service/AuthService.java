package com.fresherexercise.securityAndJwt.service;

import org.springframework.security.core.userdetails.UserDetails;

/**
 *
 * @author Admin
 */
public interface AuthService {

    UserDetails loadUserById(Long id);
}