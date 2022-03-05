package com.back.khotwa.repository.recruiter;

import com.back.khotwa.model.recruiter.Recruiter;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RecruiterRepository extends MongoRepository<Recruiter,String> {

    Recruiter findByEmail(String email);
}
