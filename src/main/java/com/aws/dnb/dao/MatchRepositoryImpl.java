package com.aws.dnb.dao;

import com.aws.dnb.model.ApplicantInformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MatchRepositoryImpl extends JpaRepository<ApplicantInformation,Integer> {


}
