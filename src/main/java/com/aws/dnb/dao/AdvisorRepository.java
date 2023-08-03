package com.aws.dnb.dao;

import com.aws.dnb.model.AdvisorData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdvisorRepository extends JpaRepository<AdvisorData,Long> {
}
