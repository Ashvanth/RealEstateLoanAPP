package com.aws.dnb.model;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Arithmetic {

    private int first_number;

    private int second_number;

   private String operation;
}
