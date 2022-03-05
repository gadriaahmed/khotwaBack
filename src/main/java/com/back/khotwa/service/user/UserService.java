package com.back.khotwa.service.user;

import com.back.khotwa.dto.model.user.UserDto;

public interface UserService {

    UserDto signup(UserDto userDto);
    UserDto findUserByEmail(String email);
}
