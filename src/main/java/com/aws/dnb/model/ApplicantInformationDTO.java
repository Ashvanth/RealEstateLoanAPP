package com.aws.dnb.model;


import lombok.*;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table
public class ApplicantInformationDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long applicationID;
   @Column(unique = true)
    private Long customerSSN ;
    private String fullName;
    private Long loanAmount;
    private Long salaryAmount;
    private Long equityAmount;
}
