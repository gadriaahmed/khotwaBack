package com.back.khotwa.model.recruiter;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@Document(collection = "recruiter")
public class Recruiter {

    @Id
    private String id;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String companyName;
    private Boolean isProcessing;
    private Boolean emailChecked;
    private Boolean requestApproved;
}
