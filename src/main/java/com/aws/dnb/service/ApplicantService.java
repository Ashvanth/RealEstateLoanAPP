package com.aws.dnb.service;

import com.aws.dnb.dao.ApplicantStoreRepository;
import com.aws.dnb.model.ApplicantInformationDTO;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ApplicantService {


    private static ApplicantStoreRepository applicantStoreRepository ;

    @Autowired
   public ApplicantService(ApplicantStoreRepository applicantStoreRepository){
       this.applicantStoreRepository = applicantStoreRepository;
   }

    public String saveApplication(ApplicantInformationDTO applicantInformation){
        ApplicantInformationDTO saveApplicant = applicantStoreRepository.save(applicantInformation);
        return saveApplicant.getApplicationID().toString();
    }
}
