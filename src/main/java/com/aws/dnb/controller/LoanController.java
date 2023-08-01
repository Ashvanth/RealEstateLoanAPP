package com.aws.dnb.controller;

import com.aws.dnb.model.ApplicantInformationDTO;
import com.aws.dnb.model.Arithmetic;
import com.aws.dnb.service.ApplicantService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/loan/v1")
public class LoanController {

    private static ApplicantService applicantService;

    @Autowired
    public LoanController(ApplicantService applicantService) {
        this.applicantService = applicantService;
    }

    @GetMapping("/check")
    public String healthCheck(){
        return "Check Okay";
    }

    @PostMapping
    public ResponseEntity storeApplicationInformation(@RequestBody ApplicantInformationDTO applicantInformation) {
        return ResponseEntity.status(200).body("Application Sent to Advisor"+ applicantService.saveApplication(applicantInformation));
    }

    public String arthimaticOperation(@RequestBody Arithmetic arithmetic){
        return "OK";
    }
}
