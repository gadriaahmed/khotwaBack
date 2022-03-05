package com.back.khotwa.security.detailsService;

import com.back.khotwa.dto.model.Recruiter.RecruiterDto;
import com.back.khotwa.exception.EntityType;
import com.back.khotwa.exception.ExceptionType;
import com.back.khotwa.exception.TJException;
import com.back.khotwa.service.recruiter.RecruiterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CustomRecruiterDetailsService implements UserDetailsService {

    @Autowired
    private RecruiterService recruiterService;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        try {
            RecruiterDto recruiterDto = recruiterService.findRecruiterByEmail(email);

                if (!recruiterDto.getEmailChecked()) {
                    throw TJException.throwException(EntityType.RECRUITER, ExceptionType.USER_NOT_ACTIVATED, recruiterDto.getEmail());
                }
                if (recruiterDto.getIsProcessing()) {
                    throw TJException.throwException(EntityType.RECRUITER, ExceptionType.REQUEST_BEING_PROCESSING, recruiterDto.getEmail());
                }
                if (!recruiterDto.getRequestApproved()) {
                    throw TJException.throwException(EntityType.RECRUITER, ExceptionType.COMPANY_NOT_APPROVED, recruiterDto.getEmail());
                }
                List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
                return buildUserForAuthentication(recruiterDto, authorities);
        }catch (Exception e ){

            throw e;
        }
    }
    private UserDetails buildUserForAuthentication(RecruiterDto recruiterDto, List<GrantedAuthority> authorities) {
        return new org.springframework.security.core.userdetails.User(recruiterDto.getEmail(), recruiterDto.getPassword(), authorities);
    }
}
