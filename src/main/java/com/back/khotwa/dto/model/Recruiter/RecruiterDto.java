package com.back.khotwa.dto.model.Recruiter;


import com.back.khotwa.util.validation.annotation.EmailConstraint;
import com.back.khotwa.util.validation.annotation.PasswordConstraint;
import com.back.khotwa.util.validation.annotation.PhoneNumberConstraint;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
@ToString
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class RecruiterDto {
    @EmailConstraint
    private String email;
    @PasswordConstraint
    private String password;
    private String firstName;
    private String lastName;
    @PhoneNumberConstraint
    private String phoneNumber;
    private String companyName;
    private Boolean isProcessing;
    private Boolean emailChecked;
    private Boolean requestApproved;

}
