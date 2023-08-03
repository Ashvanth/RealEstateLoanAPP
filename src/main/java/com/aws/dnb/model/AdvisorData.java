package com.aws.dnb.model;


import lombok.*;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table
public class AdvisorData {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long advisorid;

    private Long applicationid;

    private Boolean applicationStatus;
}
