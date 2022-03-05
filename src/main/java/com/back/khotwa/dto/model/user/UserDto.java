package com.back.khotwa.dto.model.user;

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
public class UserDto {
    @EmailConstraint
    private String email;
    @PasswordConstraint
    private String password;
    private String firstName;
    private String lastName;
    @PhoneNumberConstraint
    private String phoneNumber;
    private Boolean emailChecked;



}
