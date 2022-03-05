package com.back.khotwa.security.authenticationProvider;

import com.back.khotwa.security.detailsService.CustomRecruiterDetailsService;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class RecruiterAuthenticationProvider implements AuthenticationProvider {


    private CustomRecruiterDetailsService customRecruiterDetailsService;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

            UsernamePasswordAuthenticationToken auth = (UsernamePasswordAuthenticationToken) authentication;
            String username = String.valueOf(auth.getPrincipal());
            String password = String.valueOf(auth.getCredentials());
            UserDetails userDetails = customRecruiterDetailsService.loadUserByUsername(username);
        if (bCryptPasswordEncoder.matches(password,userDetails.getPassword())) {
            return authentication;
        } else throw new BadCredentialsException("bad.credentials");
    }

    public void setCustomRecruiterDetailsService(CustomRecruiterDetailsService customRecruiterDetailsService) {
        this.customRecruiterDetailsService = customRecruiterDetailsService;
    }

    public void setbCryptPasswordEncoder(BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return false;
    }
}