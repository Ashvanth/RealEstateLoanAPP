package com.aws.dnb.dao;

import com.aws.dnb.model.ApplicantInformation;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;

@Configuration
public interface ApplicantionRepository extends JpaRepository<ApplicantInformation, Long> {


}
