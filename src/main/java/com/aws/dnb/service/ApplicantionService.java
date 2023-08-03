package com.aws.dnb.service;

import com.aws.dnb.dao.ApplicantionRepository;
import com.aws.dnb.model.ApplicantInformation;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ApplicantionService {


    private static ApplicantionRepository applicantionRepository;

    @Autowired
    public ApplicantionService(ApplicantionRepository applicantionRepository) {
        this.applicantionRepository = applicantionRepository;
    }

    public String submitApplication(ApplicantInformation applicantInformation) {
        applicantInformation.setAdvisorAssigned(false);
        ApplicantInformation saveApplicant = applicantionRepository.save(applicantInformation);
        return saveApplicant.getApplicationID().toString();
    }

    public List<ApplicantInformation> fetchApplications(){
        return applicantionRepository.findAll();
    }
}
