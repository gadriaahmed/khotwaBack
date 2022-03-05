package com.back.khotwa.service.user;

import com.back.khotwa.dto.mapper.UserMapper;
import com.back.khotwa.dto.model.user.UserDto;
import com.back.khotwa.exception.EntityType;
import com.back.khotwa.exception.ExceptionType;
import com.back.khotwa.exception.TJException;
import com.back.khotwa.model.user.User;
import com.back.khotwa.repository.user.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import static com.back.khotwa.exception.EntityType.USER;
import static com.back.khotwa.exception.ExceptionType.*;


import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDto signup(UserDto userDto) {
        User user = userRepository.findByEmail(userDto.getEmail());
        if(user==null){
            user = new User()
                        .setEmail(userDto.getEmail())
                        .setFirstName(userDto.getFirstName())
                        .setLastName(userDto.getLastName())
                        .setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()))
                        .setPhoneNumber(userDto.getPhoneNumber())
                        .setEmailChecked(false);
            return UserMapper.toUserDto(userRepository.save(user));
        }
        System.out.println("failed");
        throw exception(USER,DUPLICATE_ENTITY,userDto.getEmail());
    }

    @Override
    public UserDto findUserByEmail(String email) {
        Optional<User> user = Optional.ofNullable(userRepository.findByEmail(email));
        if(user.isPresent()){
            return modelMapper.map(user.get(),UserDto.class);
        }
        throw exception(USER,ENTITY_NOT_FOUND,email);
    }

    private RuntimeException exception(EntityType entityType, ExceptionType exceptionType,String... args){
        return TJException.throwException(entityType,exceptionType,args);
    }
}
