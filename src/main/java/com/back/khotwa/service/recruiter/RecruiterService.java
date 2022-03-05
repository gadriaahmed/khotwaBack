package com.back.khotwa.service.recruiter;

import com.back.khotwa.dto.model.Recruiter.RecruiterDto;

public interface RecruiterService {

    RecruiterDto signup(RecruiterDto recruiterDto);
    RecruiterDto findRecruiterByEmail(String email);
}
