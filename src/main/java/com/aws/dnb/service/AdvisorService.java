package com.aws.dnb.service;

import com.aws.dnb.dao.AdvisorRepository;
import com.aws.dnb.dao.ApplicantionRepository;
import com.aws.dnb.model.AdvisorData;
import com.aws.dnb.model.ApplicantInformation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class AdvisorService {

    private static AdvisorRepository advisorRepository;
    private static ApplicantionRepository applicantionRepository;

    private AdvisorData advisorData;

    @Autowired
    public AdvisorService(AdvisorRepository advisorRepository,
                          ApplicantionRepository applicantionRepository) {
        this.advisorRepository = advisorRepository;
        this.applicantionRepository = applicantionRepository;
    }

    public List<ApplicantInformation> fetchCases() {
        return applicantionRepository.findAll();
    }

    public List<AdvisorData> assignCases() {

        List<ApplicantInformation> ApplicantInformationList = applicantionRepository.findAll();
        List<AdvisorData> advisorDataList = new ArrayList<AdvisorData>();

        for (ApplicantInformation applicantInformation : ApplicantInformationList) {
            if (applicantInformation.isAdvisorAssigned() == false) {
                advisorData = new AdvisorData();
                advisorData.setApplicationStatus(true);
                advisorData.setApplicationid(applicantInformation.getApplicationID());
                advisorDataList.add(advisorData);
            }
        }
        List<AdvisorData> advisorDataList1 = advisorRepository.saveAll(advisorDataList);
        return advisorDataList1;
    }
}
