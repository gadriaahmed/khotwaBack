package com.back.khotwa.dto.mapper;

import com.back.khotwa.dto.model.Recruiter.RecruiterDto;
import com.back.khotwa.model.recruiter.Recruiter;
import org.springframework.stereotype.Component;

@Component
public class RecruiterMapper {

    public static RecruiterDto toRecruiterDto(Recruiter recruiter){
        return new RecruiterDto()
                    .setEmail(recruiter.getEmail())
                    .setFirstName(recruiter.getFirstName())
                    .setLastName(recruiter.getLastName())

                    .setPhoneNumber(recruiter.getPhoneNumber())
                    .setCompanyName(recruiter.getCompanyName())
                    .setEmailChecked(recruiter.getEmailChecked())
                    .setIsProcessing(recruiter.getIsProcessing())
                    .setRequestApproved(recruiter.getRequestApproved());

    }
}
