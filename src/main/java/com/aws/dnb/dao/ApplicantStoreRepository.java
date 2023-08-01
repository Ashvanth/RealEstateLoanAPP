package com.aws.dnb.dao;

import com.aws.dnb.model.ApplicantInformationDTO;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Configuration
public interface ApplicantStoreRepository extends JpaRepository<ApplicantInformationDTO,String> {


}
