package com.aws.dnb.service;

import com.aws.dnb.dao.ApplicantionRepository;
import com.aws.dnb.model.ApplicantInformation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApplicantionService {


    private static ApplicantionRepository applicantionRepository;

    @Autowired
    public ApplicantionService(ApplicantionRepository applicantionRepository) {
        this.applicantionRepository = applicantionRepository;
    }

    public String submitApplication(ApplicantInformation applicantInformation)  throws DataAccessException {
        if (validateEquityAmount(applicantInformation)) {
            applicantInformation.setAdvisorAssigned(false);
            ApplicantInformation saveApplicant = applicantionRepository.save(applicantInformation);
            return saveApplicant.getApplicationID().toString();
        } else {
            return "INVALID";
        }
    }

    public Boolean validateEquityAmount(ApplicantInformation applicantInformation) {
        double percentage = applicantInformation.getLoanAmount() * 0.15;
        Double roundedPercentage = Double.valueOf(String.format("%.2f", percentage));
        if (applicantInformation.getEquityAmount() < roundedPercentage) {
            return false;
        } else {
            return true;
        }

    }

    public List<ApplicantInformation> fetchApplications(){
        return applicantionRepository.findAll();
    }
}
