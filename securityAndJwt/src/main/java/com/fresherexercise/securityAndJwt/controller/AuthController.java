package com.fresherexercise.securityAndJwt.controller;

import com.fresherexercise.securityAndJwt.auth.CustomUserDetails;
import com.fresherexercise.securityAndJwt.auth.LoginRequest;
import com.fresherexercise.securityAndJwt.auth.LoginResponse;
import com.fresherexercise.securityAndJwt.auth.jwt.JwtTokenProvider;
import com.fresherexercise.securityAndJwt.model.User;
import com.fresherexercise.securityAndJwt.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import javax.validation.ValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 *
 * @author anhdv
 */
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider tokenProvider;

    @PostMapping("/login")
    public LoginResponse authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        // Xác thực thông tin người dùng Request lên
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );

        // Nếu không xảy ra exception tức là thông tin hợp lệ
        // Set thông tin authentication vào Security Context
        SecurityContextHolder.getContext().setAuthentication(authentication);
        // Trả về jwt cho người dùng.
        String jwt = tokenProvider.generateToken((CustomUserDetails) authentication.getPrincipal());
        return new LoginResponse(jwt);
    }

    @PostMapping("/register")
    public ResponseEntity<User> addBook(@RequestBody User user) {
        try {
            User _book = userRepository
                    .save(new User(user.getUserName(), passwordEncoder.encode(user.getPassword()), user.getRoleName()));
            return new ResponseEntity<>(_book, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Api /api/random yêu cầu phải xác thực mới có thể request
//    @GetMapping("/random")
//    public String randomStuff() {
//        return ("JWT Hợp lệ mới có thể thấy được message này");
//    }
}
