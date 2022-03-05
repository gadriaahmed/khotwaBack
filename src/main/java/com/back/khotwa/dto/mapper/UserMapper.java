package com.back.khotwa.dto.mapper;

import com.back.khotwa.dto.model.user.UserDto;
import com.back.khotwa.model.user.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public static UserDto toUserDto(User user){
        return new UserDto()
                .setEmail(user.getEmail())
                .setFirstName(user.getFirstName())
                .setLastName(user.getLastName())
                .setEmailChecked(user.getEmailChecked())
                .setPhoneNumber(user.getPhoneNumber());
    }
}
