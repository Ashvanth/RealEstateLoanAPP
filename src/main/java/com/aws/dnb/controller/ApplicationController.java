package com.aws.dnb.controller;

import com.aws.dnb.model.ApplicantInformation;
import com.aws.dnb.service.ApplicantionService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/application/v1")
public class ApplicationController {

    private static ApplicantionService applicantionService;

    @Autowired
    public ApplicationController(ApplicantionService applicantService) {
        this.applicantionService = applicantionService;
    }

    @GetMapping("/check")
    public String healthCheck() {
        return "Check Okay";
    }

    @PostMapping
    public ResponseEntity submitApplicationInformation(@RequestBody ApplicantInformation applicantInformation) {
        return ResponseEntity.status(200).body("Application Sent to Advisor" + applicantionService.submitApplication(applicantInformation));
    }

    @GetMapping("/allApplications")
    public ResponseEntity<List<ApplicantInformation>> fetchApplications(){
        return ResponseEntity.status(200).body(applicantionService.fetchApplications());
    }

}
