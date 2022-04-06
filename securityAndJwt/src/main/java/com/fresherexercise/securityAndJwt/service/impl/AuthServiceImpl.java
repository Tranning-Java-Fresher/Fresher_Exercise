package com.fresherexercise.securityAndJwt.service.impl;

import com.fresherexercise.securityAndJwt.auth.CustomUserDetails;
import com.fresherexercise.securityAndJwt.model.User;
import com.fresherexercise.securityAndJwt.repository.UserCustomizeRepository;
import com.fresherexercise.securityAndJwt.service.AuthService;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 * @author Admin
 */
@Service
public class AuthServiceImpl implements UserDetailsService, AuthService {

    @Autowired
    private UserCustomizeRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        // Kiểm tra xem user có tồn tại trong database không?
        User user = userRepository.findByUserName(username);
        if (user == null)
            throw new UsernameNotFoundException("User not found with username : " + username);
        return new CustomUserDetails(user);
    }

    // JWTAuthenticationFilter sẽ sử dụng hàm này
    @Transactional
    @Override
    public UserDetails loadUserById(Long id) {
        User user = userRepository.findById(id);
        if (user == null)
            throw new UsernameNotFoundException("User not found with id : " + id);
        return new CustomUserDetails(user);
    }
}