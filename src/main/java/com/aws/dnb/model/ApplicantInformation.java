package com.aws.dnb.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table
public class ApplicantInformation {
    @Id
    private Long customerSSN ;
    private String fullName;
    private Long loanAmount;
    private Long salaryAmount;
    private Long equityAmount;
}
