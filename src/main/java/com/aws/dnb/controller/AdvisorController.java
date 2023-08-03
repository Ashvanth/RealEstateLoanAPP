package com.aws.dnb.controller;

import com.aws.dnb.model.AdvisorData;
import com.aws.dnb.model.ApplicantInformation;
import com.aws.dnb.service.AdvisorService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/advisor/v1")
public class AdvisorController {

    @Autowired
   private static AdvisorService advisorService;

    public AdvisorController(AdvisorService advisorService) {
        this.advisorService = advisorService;
    }

    public String healthCheck(){
        return "Advisor check okay";
    }

    @GetMapping("/assigncases")
    public List<AdvisorData> assignCases()
    {
       return advisorService.assignCases();
    }

    @GetMapping("/getCases")
    public List<ApplicantInformation> casesAssigned()
    {
        return advisorService.fetchCases();
    }
}
