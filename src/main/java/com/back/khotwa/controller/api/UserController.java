package com.back.khotwa.controller.api;


import com.back.khotwa.controller.request.UserSignupRequest;
import com.back.khotwa.dto.model.user.UserDto;
import com.back.khotwa.dto.response.Response;
import com.back.khotwa.service.user.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/api/user")
@CrossOrigin
@Api(value = "UserRestController",description = "Rest api related to condidate entity")
public class UserController {

    @Autowired
    private UserService userService;

    @ApiOperation(value = "Signup")
    @ApiResponses( value = {
            @ApiResponse(code = 200, message = "Success|OK")
    })
    @PostMapping("/signup")
    public Response signup(@RequestBody @Valid UserSignupRequest userSignupRequest){
        return Response.ok().setPayload(registerUser(userSignupRequest));
    }
    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }

    private UserDto registerUser(UserSignupRequest userSignupRequest){
        UserDto userDto = new UserDto()
                    .setEmail(userSignupRequest.getEmail())
                    .setFirstName(userSignupRequest.getFirstName())
                    .setLastName(userSignupRequest.getLastName())
                    .setPassword(userSignupRequest.getPassword())
                    .setPhoneNumber(userSignupRequest.getPhoneNumber());


            return userService.signup(userDto);
    }
}
