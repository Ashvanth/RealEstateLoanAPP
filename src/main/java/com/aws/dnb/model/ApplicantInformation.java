package com.aws.dnb.model;


import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public record ApplicantInformation(@Id long customerSSN ,
                                   String fullName,
                                   String loanAmount,
                                   String EquityAmount,
                                   String salaryAmount) {


}
