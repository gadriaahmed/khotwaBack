package com.back.khotwa.controller.api;

import com.back.khotwa.controller.request.RecruiterSignupRequest;
import com.back.khotwa.dto.model.Recruiter.RecruiterDto;
import com.back.khotwa.dto.response.Response;
import com.back.khotwa.service.recruiter.RecruiterService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/recruiter")
@CrossOrigin
@Api(value = "RecruiterRestController",description = "Rest api related to recruiter entity")
public class RecruiterController {

    @Autowired
    private RecruiterService recruiterService;

    @ApiOperation(value = "signup")
    @ApiResponses( value = {
            @ApiResponse(code = 200, message = "Success|OK")
    })
    @PostMapping("/signup")
    public Response signup(@RequestBody @Valid RecruiterSignupRequest recruiterSignupRequest){
        return Response.ok().setPayload(registerRecruiter(recruiterSignupRequest));
    }

    private RecruiterDto registerRecruiter(RecruiterSignupRequest recruiterSignupRequest){
        RecruiterDto recruiterDto = new RecruiterDto()
                .setEmail(recruiterSignupRequest.getEmail())
                .setFirstName(recruiterSignupRequest.getFirstName())
                .setLastName(recruiterSignupRequest.getLastName())
                .setPassword(recruiterSignupRequest.getPassword())
                .setCompanyName(recruiterSignupRequest.getCompanyName())
                .setPhoneNumber(recruiterSignupRequest.getPhoneNumber());

        return recruiterService.signup(recruiterDto);
    }
}
