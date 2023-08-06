package com.aws.dnb.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table
@Builder
public class ApplicantInformation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty
    private Long applicationID;
    @JsonProperty
    @Column(unique = true)
    private Long customerSSN ;
    @JsonProperty
    private String fullName;
    @JsonProperty
    private Long loanAmount;
    @JsonProperty
    private Long salaryAmount;
    @JsonProperty
    private Long equityAmount;
    @JsonProperty
    private boolean advisorAssigned;
}
