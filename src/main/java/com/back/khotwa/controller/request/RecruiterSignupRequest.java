package com.back.khotwa.controller.request;


import com.back.khotwa.util.validation.annotation.EmailConstraint;
import com.back.khotwa.util.validation.annotation.PasswordConstraint;
import com.back.khotwa.util.validation.annotation.PhoneNumberConstraint;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class RecruiterSignupRequest {

    @NotEmpty(message = "{constraints.NotEmpty.message}")
    @EmailConstraint
    private String email;

    @NotEmpty(message = "{constraints.NotEmpty.message}")
    @PasswordConstraint
    private String password;
    @NotEmpty(message = "{constraints.NotEmpty.message}")
    private String firstName;
    private String lastName;
    @NotEmpty(message = "{constraints.NotEmpty.message}")
    @PhoneNumberConstraint
    private String phoneNumber;
    @NotEmpty(message = "{constraints.NotEmpty.message}")
    private String companyName;
    @NotEmpty(message = "{constraints.NotEmpty.message}")
    private String companySize;
}
