package com.back.khotwa.security.authenticationProvider;

import com.back.khotwa.security.detailsService.CustomUserDetailsService;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


public class UserAuthenticationProvider implements AuthenticationProvider {

    private CustomUserDetailsService customUserDetailsService;
    private BCryptPasswordEncoder bCryptPasswordEncoder;



    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
            UsernamePasswordAuthenticationToken auth = (UsernamePasswordAuthenticationToken) authentication;
            String username = String.valueOf(auth.getPrincipal());
            String password = String.valueOf(auth.getCredentials());
            UserDetails userDetails= customUserDetailsService.loadUserByUsername(username);
            if (bCryptPasswordEncoder.matches(password,userDetails.getPassword())) {
                return new UsernamePasswordAuthenticationToken(username,password,userDetails.getAuthorities());
            } else throw new BadCredentialsException("bad.credentials");
    }

    public void setCustomUserDetailsService(CustomUserDetailsService customUserDetailsService) {
        this.customUserDetailsService = customUserDetailsService;
    }

    public void setbCryptPasswordEncoder(BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return false;
    }




}
