package com.aws.dnb.service;

import com.aws.dnb.dao.ApplicantStoreRepository;
import com.aws.dnb.dao.MatchRepositoryImpl;
import com.aws.dnb.model.ApplicantInformation;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Service
@AllArgsConstructor
public class ApplicantService {


    private static ApplicantStoreRepository applicantStoreRepository ;

    @Autowired
   public ApplicantService(ApplicantStoreRepository applicantStoreRepository){
       this.applicantStoreRepository = applicantStoreRepository;
   }

    public String saveApplication(ApplicantInformation applicantInformation){
        ApplicantInformation saveApplicant = applicantStoreRepository.save(applicantInformation);
        return saveApplicant.getEquityAmount().toString();
    }
}
