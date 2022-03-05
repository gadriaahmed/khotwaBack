package com.back.khotwa.service.recruiter;

import com.back.khotwa.dto.mapper.RecruiterMapper;
import com.back.khotwa.dto.model.Recruiter.RecruiterDto;
import com.back.khotwa.exception.EntityType;
import com.back.khotwa.exception.ExceptionType;
import com.back.khotwa.exception.TJException;
import com.back.khotwa.model.recruiter.Recruiter;
import com.back.khotwa.repository.recruiter.RecruiterRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RecruiterServiceImpl implements RecruiterService{

    @Autowired
    private RecruiterRepository recruiterRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private ModelMapper modelMapper;

    public void RecruiterServiceImpl(RecruiterRepository recruiterRepository){
        this.recruiterRepository=recruiterRepository;
    }


    @Override
    public RecruiterDto signup(RecruiterDto recruiterDto) {

        Recruiter recruiter = recruiterRepository.findByEmail(recruiterDto.getEmail());
        if(recruiter==null){
            recruiter = new Recruiter()
                    .setEmail(recruiterDto.getEmail())
                    .setPassword(bCryptPasswordEncoder.encode(recruiterDto.getPassword()))
                    .setCompanyName(recruiterDto.getCompanyName())
                    .setFirstName(recruiterDto.getFirstName())
                    .setLastName(recruiterDto.getLastName())
                    .setPhoneNumber(recruiterDto.getPhoneNumber())
                    .setCompanyName(recruiterDto.getCompanyName())
                    .setEmailChecked(false)
                    .setIsProcessing(false)
                    .setRequestApproved(false);
            return RecruiterMapper.toRecruiterDto(recruiterRepository.save(recruiter));
        }
        else throw exception(EntityType.RECRUITER,ExceptionType.DUPLICATE_ENTITY,recruiterDto.getEmail());
    }

    @Override
    public RecruiterDto findRecruiterByEmail(String email) {
        Optional<Recruiter> recruiter = Optional.ofNullable(recruiterRepository.findByEmail(email));
        if(recruiter.isPresent()){
            return modelMapper.map(recruiter.get(),RecruiterDto.class);
        }
        else throw exception(EntityType.RECRUITER,ExceptionType.ENTITY_NOT_FOUND,email);

    }

    private RuntimeException exception(EntityType entityType, ExceptionType exceptionType,String... args){
        return TJException.throwException(entityType,exceptionType,args);
    }
}
