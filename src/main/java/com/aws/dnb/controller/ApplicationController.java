package com.aws.dnb.controller;

import com.aws.dnb.model.ApplicantInformation;
import com.aws.dnb.service.ApplicantionService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/application/v1")
public class ApplicationController {

    private static ApplicantionService applicantionService;

    @Autowired
    public ApplicationController(ApplicantionService applicantionService) {
        this.applicantionService = applicantionService;
    }

    @GetMapping("/check")
    public String healthCheck() {
        return "Check Okay";
    }

    @PostMapping
    public ResponseEntity<?> submitApplicationInformation(@RequestBody ApplicantInformation applicantInformation) {
        try {
            if (applicantInformation.getCustomerSSN() == null || applicantInformation.getFullName() == null ||
                    applicantInformation.getLoanAmount() == null || applicantInformation.getSalaryAmount() == null ||
                    applicantInformation.getEquityAmount() == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("One or more required properties are missing");
            }
            String result = applicantionService.submitApplication(applicantInformation);
            if (result != null && !result.contains("INVALID")) {
                return ResponseEntity.status(HttpStatus.OK).body("Application Sent to Advisor , " +
                        "your application ID is -> " + result);
            } else if (result != null && result.contains("INVALID")) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("EQUITY AMOUNT IS NOT 15 % of LOAN Amount");
            }
        } catch (DataAccessException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Oops!!! Something went wrong");

    }

    @GetMapping("/allApplications")
    public ResponseEntity<List<?>> fetchApplications() {
        return ResponseEntity.status(200).body(applicantionService.fetchApplications());
    }

}
