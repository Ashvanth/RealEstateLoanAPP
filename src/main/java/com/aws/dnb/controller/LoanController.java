package com.aws.dnb.controller;

import com.aws.dnb.model.ApplicantInformation;
import com.aws.dnb.model.Arithmetic;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoanController {

    @PostMapping
    public String storeApplicationInformation(@RequestBody ApplicantInformation applicantInformation)
    {
        return "application infromation stored";
    }
}
