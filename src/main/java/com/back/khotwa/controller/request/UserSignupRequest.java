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
public class UserSignupRequest {
    @NotEmpty(message = "{constraints.NotEmpty.message}")
    @EmailConstraint
    private String email;

    @NotEmpty(message = "{constraints.NotEmpty.message}")
    @PasswordConstraint
    private String password;

    @NotEmpty(message = "{constraints.NotEmpty.message}")
    private String firstName;

    @NotEmpty(message = "{constraints.NotEmpty.message}")
    private String lastName;

    @NotEmpty(message = "{constraints.NotEmpty.message}")
    @PhoneNumberConstraint
    private String phoneNumber;
}
