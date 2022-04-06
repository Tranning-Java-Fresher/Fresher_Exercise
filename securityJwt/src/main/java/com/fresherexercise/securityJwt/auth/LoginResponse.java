/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fresherexercise.securityJwt.auth;

/**
 *
 * @author Admin
 */
public class LoginResponse {
    private final String jwt;

    public LoginResponse() {
        this.jwt = null;
    }

    public LoginResponse(String jwt) {
        this.jwt = jwt;
    }

    public String getJwt() {
        return jwt;
    }
    
    
}
